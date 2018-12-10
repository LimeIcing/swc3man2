package laace.swc3man2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import laace.swc3man2.joinSqlTableModels.StudentCourse;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;
import javax.persistence.*;
import java.util.*;

//#Have to use this to load data into cool box. dunno what do.
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "Courses")
@Table(name = "course")
@NaturalIdCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CourseModel {
    // region fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int semester;
    private int ects;
    private int numberOfTeachers;
    private int minNumberOfStudents;
    private int expectedNumberOfStudents;
    private int maxNumberOfStudents;

    private String name;
    private String studyprogramme;
    private String namedanish;
    private String description;
    private String languange;
    private String classCode;

    // TEXT
    private String prerequisites;
    private String learningOutcome;
    private String content;
    private String learningActivities;
    private String examForm;

    @Transient
    private List<TeacherModel> teachers;

    @ManyToMany(cascade = { CascadeType.PERSIST,
    CascadeType.MERGE})
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "course_id"), //parent
    inverseJoinColumns = @JoinColumn(name ="student_id")) //child
    private Set<StudentModel> students = new HashSet<>();

    @Temporal(TemporalType.DATE)
    private Date lastUpdated = Calendar.getInstance().getTime();
    // endregion

    private boolean mandatory;

    public CourseModel() {
    }

    public CourseModel(int semester, int ects, int numberOfTeachers, int minNumberOfStudents,
                       int expectedNumberOfStudents, int maxNumberOfStudents, String name, String studyprogramme,
                       String namedanish, String description, String languange, String classCode, String prerequisites,
                       String learningOutcome, String content, String learningActivities, String examForm,
                       List<TeacherModel> teachers, Set<StudentModel> students, Date lastUpdated, boolean mandatory) {
        this.semester = semester;
        this.ects = ects;
        this.numberOfTeachers = numberOfTeachers;
        this.minNumberOfStudents = minNumberOfStudents;
        this.expectedNumberOfStudents = expectedNumberOfStudents;
        this.maxNumberOfStudents = maxNumberOfStudents;
        this.name = name;
        this.studyprogramme = studyprogramme;
        this.namedanish = namedanish;
        this.description = description;
        this.languange = languange;
        this.classCode = classCode;
        this.prerequisites = prerequisites;
        this.learningOutcome = learningOutcome;
        this.content = content;
        this.learningActivities = learningActivities;
        this.examForm = examForm;
        this.teachers = teachers;
        this.students = students;
        this.lastUpdated = lastUpdated;
        this.mandatory = mandatory;
    }

    public CourseModel(int semester, int ects, int numberOfTeachers, int minNumberOfStudents,
                       int expectedNumberOfStudents, int maxNumberOfStudents, String name,
                       String studyprogramme, String namedanish, String description,
                       String languange, String classCode, String prerequisites,
                       String learningOutcome, String content, String learningActivities,
                       String examForm, Date lastUpdated, boolean mandatory) {
        this.semester = semester;
        this.ects = ects;
        this.numberOfTeachers = numberOfTeachers;
        this.minNumberOfStudents = minNumberOfStudents;
        this.expectedNumberOfStudents = expectedNumberOfStudents;
        this.maxNumberOfStudents = maxNumberOfStudents;
        this.name = name;
        this.studyprogramme = studyprogramme;
        this.namedanish = namedanish;
        this.description = description;
        this.languange = languange;
        this.classCode = classCode;
        this.prerequisites = prerequisites;
        this.learningOutcome = learningOutcome;
        this.content = content;
        this.learningActivities = learningActivities;
        this.examForm = examForm;
        //this.teachers = teachers;
        this.lastUpdated = lastUpdated;
        this.mandatory = mandatory;
    }

    public CourseModel(int id, int semester, int ects, int numberOfTeachers, int minNumberOfStudents,
                       int expectedNumberOfStudents, int maxNumberOfStudents, String name,
                       String studyprogramme, String namedanish, String description,
                       String languange, String classCode, String prerequisites,
                       String learningOutcome, String content, String learningActivities,
                       String examForm, List<TeacherModel> teachers, Date lastUpdated, boolean mandatory) {
        this.id = id;
        this.semester = semester;
        this.ects = ects;
        this.numberOfTeachers = numberOfTeachers;
        this.minNumberOfStudents = minNumberOfStudents;
        this.expectedNumberOfStudents = expectedNumberOfStudents;
        this.maxNumberOfStudents = maxNumberOfStudents;
        this.name = name;
        this.studyprogramme = studyprogramme;
        this.namedanish = namedanish;
        this.description = description;
        this.languange = languange;
        this.classCode = classCode;
        this.prerequisites = prerequisites;
        this.learningOutcome = learningOutcome;
        this.content = content;
        this.learningActivities = learningActivities;
        this.examForm = examForm;
        //this.teachers = teachers;
        this.lastUpdated = lastUpdated;
        this.mandatory = mandatory;
    }

    // region getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public int getNumberOfTeachers() {
        return numberOfTeachers;
    }

    public void setNumberOfTeachers(int numberOfTeachers) {
        this.numberOfTeachers = numberOfTeachers;
    }

    public int getMinNumberOfStudents() {
        return minNumberOfStudents;
    }

    public void setMinNumberOfStudents(int minNumberOfStudents) {
        this.minNumberOfStudents = minNumberOfStudents;
    }

    public int getExpectedNumberOfStudents() {
        return expectedNumberOfStudents;
    }

    public void setExpectedNumberOfStudents(int expectedNumberOfStudents) {
        this.expectedNumberOfStudents = expectedNumberOfStudents;
    }

    public int getMaxNumberOfStudents() {
        return maxNumberOfStudents;
    }

    public void setMaxNumberOfStudents(int maxNumberOfStudents) {
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudyprogramme() {
        return studyprogramme;
    }

    public void setStudyprogramme(String studyprogramme) {
        this.studyprogramme = studyprogramme;
    }

    public String getNamedanish() {
        return namedanish;
    }

    public void setNamedanish(String namedanish) {
        this.namedanish = namedanish;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguange() {
        return languange;
    }

    public void setLanguange(String languange) {
        this.languange = languange;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getLearningOutcome() {
        return learningOutcome;
    }

    public void setLearningOutcome(String learningOutcome) {
        this.learningOutcome = learningOutcome;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLearningActivities() {
        return learningActivities;
    }

    public void setLearningActivities(String learningActivities) {
        this.learningActivities = learningActivities;
    }

    public String getExamForm() {
        return examForm;
    }

    public void setExamForm(String examForm) {
        this.examForm = examForm;
    }
/*
    public List<TeacherModel> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherModel> teachers) {
        this.teachers = teachers;
    }
*/
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }
    // endregion

    public void addStudent(StudentModel studentModel)
    {
        students.add(studentModel);
        studentModel.getCourses().add(this);
    }

    public void removeStudent(StudentModel studentModel)
    {
        students.remove(studentModel);
        studentModel.getCourses().remove(this);
    }

    @Override
    public String toString() {
        return "CourseModel{" +
                "id=" + id +
                ", semester=" + semester +
                ", ects=" + ects +
                ", numberOfTeachers=" + numberOfTeachers +
                ", minNumberOfStudents=" + minNumberOfStudents +
                ", expectedNumberOfStudents=" + expectedNumberOfStudents +
                ", maxNumberOfStudents=" + maxNumberOfStudents +
                ", name='" + name + '\'' +
                ", studyprogramme='" + studyprogramme + '\'' +
                ", namedanish='" + namedanish + '\'' +
                ", description='" + description + '\'' +
                ", languange='" + languange + '\'' +
                ", classCode='" + classCode + '\'' +
                ", prerequisites='" + prerequisites + '\'' +
                ", learningOutcome='" + learningOutcome + '\'' +
                ", content='" + content + '\'' +
                ", learningActivities='" + learningActivities + '\'' +
                ", examForm='" + examForm + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", mandatory=" + mandatory +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseModel)) return false;
        return id != null && id.equals(((CourseModel) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

}