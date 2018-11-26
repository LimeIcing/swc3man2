package laace.swc3man2;

import laace.swc3man2.interfaces.CourseRepositoryInterface;
import laace.swc3man2.interfaces.TeacherRepositoryInterface;
import laace.swc3man2.models.CourseModel;
import laace.swc3man2.models.TeacherModel;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class Swc3man2Application implements CommandLineRunner {

     @Autowired
     private TeacherRepositoryInterface teacherRepository;
     @Autowired
     private CourseRepositoryInterface CRI;

    public static void main(String[] args) {
        SpringApplication.run(Swc3man2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Cleanup the tables
        teacherRepository.deleteAllInBatch();
        CRI.deleteAllInBatch();

        // ========================================

        // Create a Teacher
        TeacherModel faisal = new TeacherModel("Faisal", "fasj@kea.dk");
        TeacherModel troels = new TeacherModel("Troels", "troe@kea.dk");
        TeacherModel lasse = new TeacherModel("Lasse", "lass5643@stud.kea.dk");
        // TeacherModel emil = new TeacherModel("Emil", "emil.dk");
        CourseModel swc = new CourseModel(1,2,2,1,1,5,"software test",
                "What","Dette er dansk","Dette er et godt fag","Dansk","ABE-21","Du skal kunne noget specielt",
                "Du har lært noget specielt","Super godt læring med en super god lære","Code og Diverse","Mundligt",true);

        teacherRepository.save(faisal);
        teacherRepository.save(troels);
        teacherRepository.save(lasse);
        CRI.save(swc);

        // teacherRepository.save(emil);

        // System.out.println(teacherRepository.getOne(1));
    }
}
