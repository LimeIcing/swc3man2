package laace.swc3man2.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import laace.swc3man2.models.CourseModel;
import laace.swc3man2.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    String baseURL = "http://18.185.40.91/";

    public void fetchFromAPI() {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference typeReference = new TypeReference<List<CourseModel>>(){};
        InputStream inputStream = null;

        try {
            inputStream = new URL(baseURL + "course").openStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            List<CourseModel> courseModels = objectMapper.readValue(inputStream, typeReference);
            courseRepository.saveAll(courseModels);
        } catch (IOException iOE) {
            iOE.printStackTrace();
        }
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
