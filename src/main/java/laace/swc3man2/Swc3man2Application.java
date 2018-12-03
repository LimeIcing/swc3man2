package laace.swc3man2;

import laace.swc3man2.services.CourseService;
import laace.swc3man2.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Swc3man2Application implements CommandLineRunner {

    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;

    public static void main(String[] args) {
        SpringApplication.run(Swc3man2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        courseService.fetchFromAPI();
        teacherService.fetchFromAPI();

    }
}
