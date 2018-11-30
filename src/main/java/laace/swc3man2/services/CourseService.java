package laace.swc3man2.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import laace.swc3man2.models.CourseModel;
import laace.swc3man2.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    String legacyURL = "http://18.185.40.91/course";

    // region new code; WIP; doesn't work yet; throws NullPointerException; repo is null from constructor
    /*
    private TypeReference typeReference = new TypeReference<List<CourseModel>>(){};
    public Thread fetcher = new Thread(new ServiceThread(courseRepository, typeReference, legacyURL));
    */
    // endregion

    // region old, working code for fetching from API

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

    // endregion

    public Page<CourseModel> listAll(int page) {
        return courseRepository.findAll(new PageRequest(page,10));
    }

    public CourseModel findCourseById(int id) {
        //https://www.baeldung.com/java-optional
        //if it can't find the id it just returns null
        //given that we have proper null handling (but we don't)
        return courseRepository.findById(id).orElse(null);
    }

    public void addCourse(CourseModel courseModel) {
        System.out.println(courseModel);
        courseRepository.save(courseModel);
    }

    public void pushCourseToAPI() {

    }

}
