package backend;

import java.io.Serializable;

public class StudentUndergrad extends Student implements Serializable {
    StudentUndergrad(String studentId, String studentName) {
        super(studentId, studentName);
        type = types[0];
    }
}
