package laace.swc3man2.repositories;

import laace.swc3man2.models.TeacherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherModel, Integer> {
    @Override
    List<TeacherModel> findAll();

    @Override
    TeacherModel getOne(Integer integer);
}
