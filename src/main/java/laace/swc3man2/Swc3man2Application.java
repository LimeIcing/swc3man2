package laace.swc3man2;

import laace.swc3man2.models.CourseModel;
import laace.swc3man2.models.StudentModel;
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
        //courseService.fetchFromAPI();
        //teacherService.fetchFromAPI();
        //studentService.fetchFromAPI();

        testmethod();
    }

    public void testmethod()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "objectdb://localhost/myDbFile2.odb;user=admin;password=admin");

        EntityManager em = emf.createEntityManager();

        Session session = em
                .unwrap( Session.class );

        StudentModel misc = session
                .bySimpleNaturalId(StudentModel.class)
                .load( "Misc" );

        StudentModel jdbc = session
                .bySimpleNaturalId(StudentModel.class)
                .load( "JDBC" );

        StudentModel hibernate = session
                .bySimpleNaturalId(StudentModel.class)
                .load( "Hibernate" );

        StudentModel jooq = session
                .bySimpleNaturalId(StudentModel.class)
                .load( "jOOQ" );

        CourseModel testcourse1 = new CourseModel(1,60,3,40,45,50,"Software construction",
                "idk", "programmering","kod!","java haha jk dansk",
                "idk", "1","2","3","4",
                "5",new Date("11-02-2018"),true);
        testcourse1.setId(1);

        testcourse1.addCourse(jdbc);
        testcourse1.addCourse(hibernate);
        testcourse1.addCourse(jooq);
        testcourse1.addCourse(misc);

        em.persist(testcourse1);

        CourseModel testcourse2 = new CourseModel(1,60,3,40,45,50,"Software construction",
                "idk", "programmering","kod!","java haha jk dansk",
                "idk", "1","2","3","4",
                "5",new Date("11-02-2018"),true);

        testcourse2.setId(2);

        testcourse2.addCourse(jdbc);
        testcourse2.addCourse(hibernate);
        testcourse2.addCourse(jooq);

        em.persist(testcourse2);
    }
}
