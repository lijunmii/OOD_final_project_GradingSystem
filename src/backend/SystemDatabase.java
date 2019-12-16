package backend;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.*;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Group 21
 * @date Dec. 12, 2019
 */
public class SystemDatabase {
    private List<Client> clients;

    /** database credentials including host, username, password */
    private static final String url = "jdbc:mysql://localhost:3306/grading?useSSL=false";
    private static final String user = "root";
    private static final String password = "pass";

    private static Connection conn;
    private static Statement stmt;
    private static ResultSet res;

    public SystemDatabase() {
        clients = new ArrayList<>();
        connectDatabase();
        readFromDatabase();

    }

    public SystemDatabase(String testStr) {
        clients = new ArrayList<>();

        // for test
        Client client = new Client("123", "123");
        client.addCourse("CS542", "Machine Learning", new Semester(2018, Season.Spring));
        client.addCourse("CS504", "Data Tools", new Semester(2019, Season.Spring));
        client.addCourse("CS651", "Distributed System", new Semester(2019, Season.Summer));
        client.addCourse("CS591P1", "OOD in Java", new Semester(2018, Season.Fall));
        client.addCourse("CS640", "Artificial Intelligence", new Semester(2019, Season.Fall));
        clients.add(client);

        //readFromDatabase();
    }

    public Client getClient(String username) {
        for (Client client : clients) {
            if (client.getUsername().equals(username)) {
                return client;
            }
        }
        return null;
    }

    public Client getClient(String username, String password) {
        for (Client client : clients) {
            if (client.getUsername().equals(username) && client.getPassword().equals(password)) {
                return client;
            }
        }
        return null;
    }

    public boolean usernameExist(String username) {
        for (Client client : clients) {
            if (client.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean courseExist(String username, String courseNum, String semester) {
        if (getClient(username).getCourse(courseNum, semester) != null) {
            return true;
        }
        return false;
    }

    public void readFromDatabase() {
        String query = "select * from clientByte";
        try {
            // opening db connection to MySQL server
            stmt = conn.createStatement();
            res = stmt.executeQuery(query);
            while (res.next()) {
                Client c = byte2obj(res.getBytes("javaObject"));
                clients.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { stmt.close(); } catch (SQLException e) {e.printStackTrace();}
            try { res.close(); } catch (SQLException e) {e.printStackTrace();}
        }
    }

    public void saveToDatabase() {
        try {
            String clearQuery = "TRUNCATE TABLE clientByte";
            stmt = conn.createStatement();
            stmt.executeUpdate(clearQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try { stmt.close(); } catch (SQLException e) {e.printStackTrace();}
        }
        for(Client c: clients){
            try {
                String userName = c.getUsername();
                String query = "Insert clientByte values(?,?)";
                PreparedStatement preStat = conn.prepareStatement(query);
                byte [] clientBytes = obj2byte(c);
                preStat.setString(1,userName);
                preStat.setBytes(2,clientBytes);
                preStat.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try { stmt.close(); } catch (SQLException e) {e.printStackTrace();}
            }
        }
    }

    // functions below will write data into db
    public void register(String username, String password) {
        clients.add(new Client(username, password));

        saveToDatabase();
    }

    public void addCourse(String username, String courseNum, String courseName, Semester semester) {
        getClient(username).addCourse(courseNum, courseName, semester);

        saveToDatabase();
    }

    public void delCourse(String username, List<String> courseNums, List<String> semesters) {
        getClient(username).delCourse(courseNums, semesters);

        saveToDatabase();
    }

    public void updateCourseInfo(String username, int courseIndex, String courseNum, String courseName) {
        getClient(username).updateCourseInfo(courseIndex, courseNum, courseName);

        saveToDatabase();
    }

    public void importExcel(File f, Course course) {
        course.importExcel(f);

        saveToDatabase();
    }

    public void updateGrade(Course course, int studentIndex, int assignmentIndex, Double newScore) {
        course.updateGrade(studentIndex, assignmentIndex, newScore);

        saveToDatabase();
    }

    public void updateGrade(Course course, int studentIndex, int assignmentIndex, String newScoreStr) {
        course.updateGrade(studentIndex, assignmentIndex, newScoreStr);

        saveToDatabase();
    }

    public void updateComment(Student student, String comment) {
        student.setComment(comment);

        saveToDatabase();
    }

    public void updateComment(Student student, int assignmentIndex, String comment) {
        student.getGrades().get(assignmentIndex).setComment(comment);

        saveToDatabase();
    }

    public void addStudent(Course course, String studentId, String studentName, int studentType) {
        course.addStudent(studentId, studentName, studentType);

        saveToDatabase();
    }

    public void updateStudentInfo(Student student, String studentId, String studentName, int studentType) {
        student.updateInfo(studentId, studentName, studentType);

        saveToDatabase();
    }

    public void delStudent(Course course, String studentId) {
        course.delStudent(studentId);

        saveToDatabase();
    }

    public void addAssignment(Course course, String category, String name, Double fullScore, Double weight, String startDate, String dueDate, String note) {
        course.addAssignment(category, name, fullScore, weight, startDate, dueDate, note);

        saveToDatabase();
    }

    public void updateAssignmentInfo(Assignment assignment, String category, String name, Double fullScore, Double weight, String startDate, String dueDate, String note) {
        assignment.updateInfo(category, name, fullScore, weight, startDate, dueDate, note);

        saveToDatabase();
    }

    public void delAssignment(Course course, int assignmentIndex) {
        course.delAssignment(assignmentIndex);

        saveToDatabase();
    }

    public void calculateFinalGrade(Course course) {
        course.calculateFinalGrade();

        saveToDatabase();
    }

    public void curveFinalGrade(Course course, Double curveNum) {
        course.setCurveNum(curveNum);
        course.curveFinalGrade();

        saveToDatabase();
    }

    /**
     * Convert Java Object to Byte[]
     */
    public static byte[] obj2byte(Object obj) throws Exception {
        byte[] ret = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(obj);
        out.close();
        ret = baos.toByteArray();
        baos.close();
        return ret;
    }

    /**
     * Convert byte[] to Java object
     */
    public static Client byte2obj(byte[] bytes) throws Exception {
        Object ret = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bais);
        ret = in.readObject();
        in.close();
        return (Client)ret;
    }

    /**
     * Connection to the local Database
     */
    public void connectDatabase() {
        try {
            // opening db connection to MySQL server
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnect to the local Database
     */
    public void disconnectDatabase() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
