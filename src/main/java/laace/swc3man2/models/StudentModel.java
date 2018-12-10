package laace.swc3man2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

//#Have to use this to load data into cool box. dunno what do.
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "Students")
@Table(name = "student")
public class StudentModel {
    // region fields
    @Id
    @GeneratedValue
    private Integer id;

    @Size(max = 64)
    private String name;

    @Size(max = 64)
    private String email;

    @Size(max =64)
    private String username;

    @Size(max = 64)
    private String password;

    private int enabled;

    @ManyToMany(
            mappedBy = "students"
    )
    private Set<CourseModel> courses = new HashSet<>();

    // endregion

    public StudentModel() {
    }

    public StudentModel(@Size(max = 64) String name, @Size(max = 64) String email, @Size(max = 64) String username, @Size(max = 64) String password, int enabled) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    // region getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Set<CourseModel> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseModel> courses) {
        this.courses = courses;
    }

    // endregion

    @Override
    public String toString() {
        return "StudentModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentModel studentModel = (StudentModel) o;
        return Objects.equals(name, studentModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
