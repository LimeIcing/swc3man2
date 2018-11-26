package laace.swc3man2.services;

import laace.swc3man2.models.CourseModel;
import laace.swc3man2.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public List<CourseModel> listAll()
    {
        return courseRepository.findAll();
    }

    public CourseModel findCourseById(int id)
    {
        //https://www.baeldung.com/java-optional
        //if it can't find the id it just returns null
        //given that we have proper null handling (but we don't)
        return courseRepository.findById(id).orElse(null);
    }

    public CourseModel addCourse(CourseModel courseModel)
    {
        return courseRepository.save(courseModel);
    }


}
