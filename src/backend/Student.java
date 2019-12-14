package backend;

import java.util.ArrayList;
import java.util.List;

public class Student {
    protected static final String[] types = {"undergraduate", "graduate"};

    protected String studentId;
    protected String studentName;
    protected String type;
    protected List<Grade> grades;

    Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        grades = new ArrayList<>();
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
}