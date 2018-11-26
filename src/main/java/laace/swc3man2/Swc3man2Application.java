package laace.swc3man2;

<<<<<<< HEAD
import laace.swc3man2.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
=======
>>>>>>> 5df3010ca756767ef08a3d21925e01e230c6584c
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Swc3man2Application implements CommandLineRunner {

<<<<<<< HEAD
    @Autowired
    private CourseService courseService;

=======
>>>>>>> 5df3010ca756767ef08a3d21925e01e230c6584c
    public static void main(String[] args) {
        SpringApplication.run(Swc3man2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
<<<<<<< HEAD
        courseService.fetchFromAPI();
=======
>>>>>>> 5df3010ca756767ef08a3d21925e01e230c6584c
    }
}
