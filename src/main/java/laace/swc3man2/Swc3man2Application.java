package laace.swc3man2;

import laace.swc3man2.models.CourseModel;
import laace.swc3man2.models.StudentModel;
import laace.swc3man2.repositories.CourseRepository;
import laace.swc3man2.services.CourseService;
import laace.swc3man2.services.StudentService;
import laace.swc3man2.services.TeacherService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Swc3man2Application implements CommandLineRunner {

    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(Swc3man2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

            courseService.fetchFromAPI();
            teacherService.fetchFromAPI();
            studentService.fetchFromAPI();
        }
    }