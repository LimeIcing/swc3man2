package laace.swc3man2.repositorys;

import laace.swc3man2.models.TeacherModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<TeacherModel, Integer>
{

}
