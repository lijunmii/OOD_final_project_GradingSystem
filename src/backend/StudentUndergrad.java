package backend;

public class StudentUndergrad extends Student {
    StudentUndergrad(String studentId, String studentName) {
        super(studentId, studentName);
        type = types[0];
    }
}
