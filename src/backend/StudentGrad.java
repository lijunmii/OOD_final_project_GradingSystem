package backend;

public class StudentGrad extends Student {
    StudentGrad(String studentId, String studentName) {
        super(studentId, studentName);
        type = types[1];
    }
}
