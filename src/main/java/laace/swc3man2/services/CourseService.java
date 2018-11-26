package laace.swc3man2.services;

import laace.swc3man2.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseService {
    @Autowired
    CourseRepository courseRepository;
}
