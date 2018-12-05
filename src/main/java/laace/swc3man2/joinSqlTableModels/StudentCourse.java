/*package laace.swc3man2.joinSqlTableModels;

import laace.swc3man2.models.CourseModel;
import laace.swc3man2.models.StudentModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(name = "StudentCourse")
@Table(name = "student_course")
public class StudentCourse {

    @EmbeddedId
    private StudentCourseId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("Id")
    private StudentModel student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("Id")
    private CourseModel course;

    @Column(name = "created_on")
    private Date createdOn = new Date();


    public StudentCourse(){}

    //--junction table
    //constructor for composite primary key
    //gets object from each table
    //makes composite key from each table
    public StudentCourse( CourseModel course, StudentModel student) {

        this.course = course;
        this.student = student;

        this.id = new StudentCourseId(course.getId(), student.getId());
    }

    public StudentCourseId getId() {
        return id;
    }

    public void setId(StudentCourseId id) {
        this.id = id;
    }

    public StudentModel getStudent() {
        return student;
    }

    public void setStudent(StudentModel student) {
        this.student = student;
    }

    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCourse that = (StudentCourse) o;
        return Objects.equals(student, that.student) &&
                Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, student);
    }
}*/
