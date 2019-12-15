package backend;

import java.util.ArrayList;
import java.util.List;

public class Student implements Comparable<Student> {
    protected static final String[] types = {"undergraduate", "graduate"};

    protected String studentId;
    protected String studentName;
    protected String type;
    protected List<Grade> grades = new ArrayList<>();
    protected String comment = "Leave your comment for this student here.";

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public void setType(String s) {
        this.type = s;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getType() {
        return type;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getInfo() {
        String info = "Student id: " + studentId;
        info += "\nStudent name: " + studentName;
        info += "\nStudent type: " + type;
        return info;
    }

    @Override
    public int compareTo(Student o) {
        return studentId.compareTo(o.getStudentId());
    }
}