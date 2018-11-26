package laace.swc3man2.services;

import laace.swc3man2.models.CourseModel;
import laace.swc3man2.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    RestTemplate restTemplate = new RestTemplate();
    String baseURL = "http://18.185.40.91/";

    public void fetchFromAPI() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(baseURL + "course", String.class);
        System.out.println(responseEntity.getBody());
    }

    public List<CourseModel> listAll() {
        return courseRepository.findAll();
    }

    public CourseModel findCourseById(int id) {
        //https://www.baeldung.com/java-optional
        //if it can't find the id it just returns null
        //given that we have proper null handling (but we don't)
        return courseRepository.findById(id).orElse(null);
    }

    public CourseModel addCourse(CourseModel courseModel) {
        return courseRepository.save(courseModel);
    }
}
