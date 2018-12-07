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
        /*
        courseService.fetchFromAPI();
        teacherService.fetchFromAPI();
        studentService.fetchFromAPI();
        */


        //this is used for testing many to many tables
        //but doesn't work; getting error: no persistence provider for entitymanager
        //TODO: make persistence xml file with settings for entitymanaget to access db
        //testmethod();
    }
/*
    public void testmethod()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "jdbc:mysql://localhost/kea_db;user=groot;password=iamgroot");

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

        CourseModel testcourse2 = new CourseModel(1,60,3,40,45,50,"Software construction",
                "idk", "programmering","kod!","java haha jk dansk",
                "idk", "1","2","3","4",
                "5",new Date("11-02-2018"),true);

        StudentModel student1 = new StudentModel("peter","hi@hi.com","imblack","grape",1);
        StudentModel student2 = new StudentModel("hansi", "hansi@hansi.com", "imwhite","potatoes", 1);

        testcourse1.setId(1);
        testcourse2.setId(2);

        testcourse1.addStudent(student1);
        testcourse2.addStudent(student2);

        em.persist(testcourse1);
        em.persist(testcourse2);

        /*CourseModel testcourse1 = new CourseModel(1,60,3,40,45,50,"Software construction",
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

        em.persist(testcourse2);*/

    }

