package laace.swc3man2.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import laace.swc3man2.models.RoleModel;
import laace.swc3man2.models.TeacherModel;
import laace.swc3man2.repositories.RoleRepository;
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

@Service("teacherService")
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    String legacyURL = "http://18.185.40.91/teacher";

    @Autowired
    public TeacherService(TeacherRepository teacherRepository,
                          RoleRepository roleRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.teacherRepository = teacherRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public TeacherModel findTeacherByEmail(String email) {
        return teacherRepository.findByEmail(email);
    }

    public void saveTeacher(TeacherModel teacherModel) {
        teacherModel.setPassword(bCryptPasswordEncoder.encode(teacherModel.getPassword()));
        teacherModel.setActive(1);
        RoleModel teacherRole = roleRepository.findByRole("ADMIN");
        teacherModel.setRoles(new HashSet<RoleModel>(Arrays.asList(teacherRole)));
        teacherRepository.save(teacherModel);
    }

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
