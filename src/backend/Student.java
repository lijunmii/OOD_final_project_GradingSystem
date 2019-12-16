package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Comparable<Student>, Serializable {
    protected static final String[] types = {"U", "G"};

    protected String studentId;
    protected String studentName;
    protected String type;
    protected List<Grade> grades = new ArrayList<>();
    protected String comment = "Leave your comment for this student here.";
    protected Double finalGradeRaw;
    protected Double finalGradeCurved;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public void setType(String s) {
        this.type = s;
    }

    public void setType(int index) {
        type = types[index];
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

    public void updateInfo(String studentId, String studentName, int studentType) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.type = types[studentType];
    }

    public Double getFinalGradeRaw() {
        return finalGradeRaw;
    }

    public Double getFinalGradeCurved() {
        return finalGradeCurved;
    }

    public void setFinalGradeRaw(Double finalGradeRaw) {
        this.finalGradeRaw = finalGradeRaw;
    }

    public void setFinalGradeCurved(Double finalGradeCurved) {
        this.finalGradeCurved = finalGradeCurved;
    }
}