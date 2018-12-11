package laace.swc3man2.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import laace.swc3man2.models.CourseModel;
import laace.swc3man2.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
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

    public void postToAPI(CourseModel courseModel) {
        // region code for filling out the model until we flesh out the creation form
        String blep = "blep is not mlem";
        courseModel.setId(100);
        courseModel.setStudyprogramme(blep);
        courseModel.setPrerequisites(blep);
        courseModel.setLearningOutcome(blep);
        courseModel.setContent(blep);
        courseModel.setLearningActivities(blep);
        courseModel.setExamForm(blep);
        // endregion

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<CourseModel> request = new HttpEntity<>(courseModel, headers);
        System.out.println(courseModel);

        ResponseEntity<String> response = restTemplate.
                exchange(legacyURL, HttpMethod.POST, request, String.class);
    }

    public Page<CourseModel> listAll(int page) {
        return courseRepository.findAll(PageRequest.of(page,10));
    }

    public CourseModel findCourseById(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    /*public List<CourseModel> findCoursesByName(String searchQuery) {
        return courseRepository.findAllByNameContainsOrNamedanishContains(searchQuery);
    }*/

    public void addCourse(CourseModel courseModel) {
        courseRepository.save(courseModel);
        postToAPI(courseModel);
    }

    public void editCourse(CourseModel courseModel, int id){
        courseModel.setId(id);
        courseRepository.save(courseModel);
    }
}