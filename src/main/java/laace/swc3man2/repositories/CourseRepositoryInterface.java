package laace.swc3man2.interfaces;

import laace.swc3man2.models.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepositoryInterface extends JpaRepository<CourseModel, Integer> {

    @Override
    List<CourseModel> findAll();
}
