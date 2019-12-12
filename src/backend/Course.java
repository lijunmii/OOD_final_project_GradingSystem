package backend;

import java.util.List;

public class Course {
    private String courseNum;
    private String courseName;
    private String semester;
    private List<Student> students;

    public String getCourseNum() {
        return courseNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getSemester() {
        return semester;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
