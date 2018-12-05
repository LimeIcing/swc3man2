package laace.swc3man2.joinSqlTableModels;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * this class is for the composite primary key mapping
 * which belongs to intermediary join table
 */
@Embeddable
public class StudentCourseId implements Serializable {

    @Column(name = "studentId")
    private int studentId;

    @Column(name = "courseId")
    private int courseId;

    private StudentCourseId(){}

    public StudentCourseId(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        StudentCourseId that = (StudentCourseId) o;
        return Objects.equals(courseId, that.courseId) &&
        Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }

}
