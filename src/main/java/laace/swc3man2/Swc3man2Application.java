package laace.swc3man2;

import laace.swc3man2.repositories.TeacherRepository;
import laace.swc3man2.models.TeacherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Swc3man2Application implements CommandLineRunner {

     @Autowired
     private TeacherRepository teacherRepository;

    public static void main(String[] args) {
        SpringApplication.run(Swc3man2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Cleanup the tables
        teacherRepository.deleteAllInBatch();

        // ========================================

        // Create a Teacher
        TeacherModel faisal = new TeacherModel("Faisal", "fasj@kea.dk");
        TeacherModel troels = new TeacherModel("Troels", "troe@kea.dk");
        TeacherModel lasse = new TeacherModel("Lasse", "lass5643@stud.kea.dk");
        // TeacherModel emil = new TeacherModel("Emil", "emil.dk");

        teacherRepository.save(faisal);
        teacherRepository.save(troels);
        teacherRepository.save(lasse);
        // teacherRepository.save(emil);

        // System.out.println(teacherRepository.getOne(1));
    }
}
