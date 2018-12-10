package laace.swc3man2.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import laace.swc3man2.models.RoleModel;
import laace.swc3man2.models.StudentModel;
import laace.swc3man2.models.TeacherModel;
import laace.swc3man2.repositories.RoleRepository;
import laace.swc3man2.repositories.StudentRepository;
import laace.swc3man2.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    String legacyURL = "http://18.185.40.91/student";

    @Autowired
    public StudentService(StudentRepository studentRepository,
                          RoleRepository roleRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public StudentModel findStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    // region old, working code for fetching from API
    public void fetchFromAPI() {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference typeReference = new TypeReference<List<StudentModel>>(){};
        InputStream inputStream;

        try {
            inputStream = new URL(legacyURL).openStream();
            List<StudentModel> studentModels = objectMapper.readValue(inputStream, typeReference);
            for (StudentModel student : studentModels) {
                if (!studentRepository.existsById(student.getId())){
                    student.setPassword("student");
                    student.setActive(1);
                    student.setPassword(bCryptPasswordEncoder.encode((student.getPassword())));
                    RoleModel studentRole = roleRepository.findByRole("STUDENT");
                    student.setRoles(new HashSet<RoleModel>(Arrays.asList(studentRole)));
                    studentRepository.save(student);
                }
            }
        } catch (IOException iOE) {
            iOE.printStackTrace();
        }
    }
    // endregion

    public Page<StudentModel> listAll(int page) {
        return studentRepository.findAll(PageRequest.of(page,10));
    }

    public StudentModel findStudentById(int id) {
        //https://www.baeldung.com/java-optional
        //if it can't find the id it just returns null
        //given that we have proper null handling (but we don't)
        return studentRepository.findById(id).orElse(null);
    }

}
