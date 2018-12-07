package laace.swc3man2.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import laace.swc3man2.models.CourseModel;
import laace.swc3man2.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

    public void postCourseToAPI(CourseModel courseModel) {
        HttpEntity<CourseModel> request = new HttpEntity<>(courseModel);
        ResponseEntity<CourseModel> response = restTemplate.exchange(
                legacyURL, HttpMethod.POST, request, CourseModel.class);
        CourseModel responseCourse = response.getBody();

        if (responseCourse.getId() == courseModel.getId()){
            System.out.println("Posted successfully to API");
        } else {
            System.out.println("Post to API failed");
        }
    }

    /*
ResponseEntity<Foo> response = restTemplate
  .exchange(fooResourceUrl, HttpMethod.POST, request, Foo.class);

assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

Foo foo = response.getBody();

assertThat(foo, notNullValue());
assertThat(foo.getName(), is("bar"));
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
