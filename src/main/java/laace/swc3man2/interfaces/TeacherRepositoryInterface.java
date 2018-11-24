package laace.swc3man2.interfaces;

import laace.swc3man2.models.TeacherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepositoryInterface extends JpaRepository<TeacherModel, Integer> {
    @Override
    List<TeacherModel> findAll();

    @Override
    TeacherModel getOne(Integer integer);
}
