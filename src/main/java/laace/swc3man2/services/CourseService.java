package laace.swc3man2.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import laace.swc3man2.models.CourseModel;
import laace.swc3man2.models.CourseModelAPI;
import laace.swc3man2.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    String legacyURL = "http://18.185.40.91/course";
    RestTemplate restTemplate = new RestTemplate();

    public void fetchFromAPI() {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference typeReference = new TypeReference<List<CourseModel>>(){};
        InputStream inputStream;

        try {
            inputStream = new URL(legacyURL).openStream();
            List<CourseModel> courseModels = objectMapper.readValue(inputStream, typeReference);

            for (CourseModel course:courseModels) {
                if (!courseRepository.existsById(course.getId())){
                    courseRepository.save(course);
                }
            }
        } catch (IOException iOE) {
            iOE.printStackTrace();
        }
    }

    /*
    new CourseModelAPI(courseModel.getId(),
                courseModel.getSemester(), courseModel.getEcts(), courseModel.getNumberOfTeachers(),
                courseModel.getName(), courseModel.getStudyprogramme(), courseModel.getNamedanish(),
                courseModel.getDescription(), courseModel.getLanguange(), courseModel.isMandatory())
     */

    public void postCourseToAPI(CourseModel courseModel) {
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request;
        ResponseEntity<String> response;

        headers.setContentType(MediaType.APPLICATION_JSON);
        map.add("id", "100");
        request = new HttpEntity<>(map, headers);

        try {
            response = restTemplate.postForEntity(legacyURL, request, String.class);
            System.out.println(response.getStatusCode());
        } catch (HttpClientErrorException hCEE) {
            hCEE.printStackTrace();
        }
    }

    /*
Next, let’s look at how to submit a form using the POST method.

First, we need to set the “Content-Type” header to application/x-www-form-urlencoded.

This makes sure that a large query string can be sent to the server, containing name/value pairs separated by ‘&‘:

HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

We can wrap the form variables into a LinkedMultiValueMap:

MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
map.add("id", "1");

Next, we build the Request using an HttpEntity instance:

HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

Finally, we can connect to the REST service by calling restTemplate.postForEntity() on the Endpoint: /foos/form

ResponseEntity<String> response = restTemplate.postForEntity(
  fooResourceUrl+"/form", request , String.class);

assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
     */

    public Page<CourseModel> listAll(int page) {
        return courseRepository.findAll(PageRequest.of(page,10));
    }

    public CourseModel findCourseById(int id) {
        //https://www.baeldung.com/java-optional
        //if it can't find the id it just returns null
        //given that we have proper null handling (but we don't)
        return courseRepository.findById(id).orElse(null);
    }

    public void addCourse(CourseModel courseModel) {
        courseRepository.save(courseModel);
        postCourseToAPI(courseModel);
    }

    public void editCourse(CourseModel courseModel, int id){
        courseModel.setId(id);
        courseRepository.save(courseModel);
    }

}
