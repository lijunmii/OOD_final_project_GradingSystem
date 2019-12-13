package backend;

public class Student {
    protected static final String[] types = {"undergraduate", "graduate"};

    protected String studentId;
    protected String studentName;
    protected String type;

    Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
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
}