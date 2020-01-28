package backend;

import java.io.Serializable;

public class StudentGrad extends Student implements Serializable {
    StudentGrad(String studentId, String studentName) {
        super(studentId, studentName);
        type = types[1];
    }
}
