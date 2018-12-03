package laace.swc3man2.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import laace.swc3man2.models.CourseModel;
import laace.swc3man2.models.TeacherModel;
import laace.swc3man2.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    String legacyURL = "http://18.185.40.91/teacher";

    // region old, working code for fetching from API
    public void fetchFromAPI() {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference typeReference = new TypeReference<List<TeacherModel>>(){};
        InputStream inputStream;

        try {
            inputStream = new URL(legacyURL).openStream();
            List<TeacherModel> teacherModels = objectMapper.readValue(inputStream, typeReference);
            for (TeacherModel teacher:teacherModels) {
                if (!teacherRepository.existsById(teacher.getId())){
                    teacherRepository.save(teacher);
                }
            }
        } catch (IOException iOE) {
            iOE.printStackTrace();
        }
    }
    // endregion

    public Page<TeacherModel> listAll(int page) {
        return teacherRepository.findAll(PageRequest.of(page,10));
    }

    public TeacherModel findTeacherById(int id) {
        //https://www.baeldung.com/java-optional
        //if it can't find the id it just returns null
        //given that we have proper null handling (but we don't)
        return teacherRepository.findById(id).orElse(null);
    }

}
