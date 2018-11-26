package laace.swc3man2.services;

import laace.swc3man2.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}
