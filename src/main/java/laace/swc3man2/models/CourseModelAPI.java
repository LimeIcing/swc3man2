package laace.swc3man2.models;

public class CourseModelAPI {
    int id, semester, ects, numberOfTeachers;
    String name, studyprogramme, namedanish, description, languange;
    boolean mandatory;

    public CourseModelAPI(int id, int semester, int ects, int numberOfTeachers, String name, String studyprogramme,
                          String namedanish, String description, String languange, boolean mandatory) {
        this.id = id;
        this.semester = semester;
        this.ects = ects;
        this.numberOfTeachers = numberOfTeachers;
        this.name = name;
        this.studyprogramme = studyprogramme;
        this.namedanish = namedanish;
        this.description = description;
        this.languange = languange;
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

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }
    // endregion
}
