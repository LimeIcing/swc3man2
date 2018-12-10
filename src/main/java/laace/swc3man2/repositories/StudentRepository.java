package laace.swc3man2.repositories;

import laace.swc3man2.models.StudentModel;
import laace.swc3man2.models.TeacherModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentModel,Integer> {

    StudentModel findByEmail(String email);

    @Override
    List<StudentModel> findAll();
}
