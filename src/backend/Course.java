package backend;

import java.util.List;

public class Course {
    private String courseNum;
    private String courseName;
    private Semester semester;
    private List<Assignment> assignments;
    private List<Student> students;

    public Course(String courseNum, String courseName, Semester semester) {
        this.courseNum = courseNum;
        this.courseName = courseName;
        this.semester = semester;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public void updateInfo(String courseNum, String courseName) {
        setCourseNum(courseNum);
        setCourseName(courseName);
    }

    @Override
    public String toString() {
        return courseNum + ", " + courseName + ", " + semester.toString();
    }
}
