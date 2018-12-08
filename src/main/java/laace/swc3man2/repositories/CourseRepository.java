package laace.swc3man2.repositories;

import laace.swc3man2.models.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseModel, Integer> {

    @Query("select c.name, s.name from student_course sc inner join course c on c.id = sc.course_id\n" +
            "inner join student s on s.id = sc.student_id where s.id = id")
    public List<CourseModel> findCourseByStudentId(@Param("id") int id);
}
