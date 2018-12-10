package laace.swc3man2.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import laace.swc3man2.models.StudentModel;
import laace.swc3man2.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    String legacyURL = "http://18.185.40.91/student";

    public void fetchFromAPI() {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference typeReference = new TypeReference<List<StudentModel>>(){};
        InputStream inputStream;

        try {
            inputStream = new URL(legacyURL).openStream();
            List<StudentModel> studentModels = objectMapper.readValue(inputStream, typeReference);
            for (StudentModel student:studentModels) {
                if (!studentRepository.existsById(student.getId())){
                    studentRepository.save(student);
                }
            }
        } catch (IOException iOE) {
            iOE.printStackTrace();
        }
    }

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
