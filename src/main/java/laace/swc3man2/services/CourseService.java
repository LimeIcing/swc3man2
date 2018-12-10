package laace.swc3man2.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import laace.swc3man2.models.CourseModel;
import laace.swc3man2.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
import java.util.Optional;

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
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request;
        ResponseEntity<String> response;

        headers.setContentType(MediaType.APPLICATION_JSON);
        map.add("id", "" + courseModel.getId());
        map.add("semester", "" + courseModel.getSemester());
        map.add("name", courseModel.getName());
        map.add("studyprogramme", courseModel.getStudyprogramme());
        map.add("namedanish", courseModel.getNamedanish());
        map.add("ects", "" + courseModel.getEcts());
        map.add("description", courseModel.getDescription());
        map.add("mandatory", "" + courseModel.isMandatory());
        map.add("numberOfTeachers", "" + courseModel.getNumberOfTeachers());
        map.add("languange", courseModel.getLanguange());
        request = new HttpEntity<>(map, headers);

        try {
            response = restTemplate.postForEntity(legacyURL + "/form", request, String.class);
            System.out.println(response.getStatusCode());
        } catch (HttpClientErrorException hCEE) {
            hCEE.printStackTrace();
        }
    }

    public Page<CourseModel> listAll(int page) {
        return courseRepository.findAll(PageRequest.of(page,10));
    }

    public CourseModel findCourseById(int id) {
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