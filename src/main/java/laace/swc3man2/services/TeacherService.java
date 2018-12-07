package laace.swc3man2.services;

import laace.swc3man2.models.RoleModel;
import laace.swc3man2.models.TeacherModel;
import laace.swc3man2.repositories.RoleRepository;
import laace.swc3man2.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("teacherService")
public class TeacherService {

    private TeacherRepository teacherRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

}
