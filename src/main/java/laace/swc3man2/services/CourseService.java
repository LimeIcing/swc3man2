package laace.swc3man2.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import laace.swc3man2.models.CourseModel;
import laace.swc3man2.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    // region new code; WIP; doesn't work yet; throws NullPointerException; repo is null from constructor
    /*
    private String urlSuffix = "course";
    private TypeReference typeReference = new TypeReference<List<CourseModel>>(){};
    public Thread fetcher = new Thread(new ServiceThread(courseRepository, typeReference, urlSuffix));
    */
    // endregion

    // region old, working code for fetching from API

    String baseURL = "http://18.185.40.91/";

    public void fetchFromAPI() {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference typeReference = new TypeReference<List<CourseModel>>(){};
        InputStream inputStream;

        try {
            inputStream = new URL(baseURL + "course").openStream();
            List<CourseModel> courseModels = objectMapper.readValue(inputStream, typeReference);
            courseRepository.saveAll(courseModels);
        } catch (IOException iOE) {
            iOE.printStackTrace();
        }
    }

    // endregion

    public List<CourseModel> listAll() {
        return courseRepository.findAll();
    }

    public CourseModel findCourseById(int id) {
        //https://www.baeldung.com/java-optional
        //if it can't find the id it just returns null
        //given that we have proper null handling (but we don't)
        return courseRepository.findById(id).orElse(null);
    }

    public void addCourse(CourseModel courseModel) {
        courseRepository.save(courseModel);
    }


}
