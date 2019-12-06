public class Course {

    private String subject;
    private String courseNum;
    private String semester;

    public Course(String subject, String courseNum, String semester) {
        this.subject = subject;
        this.courseNum = courseNum;
        this.semester = semester;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
