package backend;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SystemDatabase {
    private List<Client> clients;

    public SystemDatabase() {
        clients = new ArrayList<>();
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

        readClients();
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

    public void readClients() {
        //todo: read clients from db
    }

    public void save2Database() {
        //todo: save clients into db
    }

    // functions below will write data into db
    public void register(String username, String password) {
        clients.add(new Client(username, password));

        save2Database();
    }

    public void addCourse(String username, String courseNum, String courseName, Semester semester) {
        getClient(username).addCourse(courseNum, courseName, semester);

        save2Database();
    }

    public void delCourse(String username, List<String> courseNums, List<String> semesters) {
        getClient(username).delCourse(courseNums, semesters);

        save2Database();
    }

    public void updateCourseInfo(String username, int courseIndex, String courseNum, String courseName) {
        getClient(username).updateCourseInfo(courseIndex, courseNum, courseName);

        save2Database();
    }

    public void importExcel(File f, Course course) {
        course.importExcel(f);

        save2Database();
    }

    public void updateGrade(Course course, int studentIndex, int assignmentIndex, Double newScore) {
        course.updateGrade(studentIndex, assignmentIndex, newScore);

        save2Database();
    }

    public void updateGrade(Course course, int studentIndex, int assignmentIndex, String newScoreStr) {
        course.updateGrade(studentIndex, assignmentIndex, newScoreStr);

        save2Database();
    }

    public void updateComment(Student student, String comment) {
        student.setComment(comment);

        save2Database();
    }

    public void updateComment(Student student, int assignmentIndex, String comment) {
        student.getGrades().get(assignmentIndex).setComment(comment);

        save2Database();
    }

    public void addStudent(Course course, String studentId, String studentName, int studentType) {
        course.addStudent(studentId, studentName, studentType);

        save2Database();
    }

    public void delStudent() {
        //todo: delete student

        save2Database();
    }

    public void addAssignment(Course course, String category, String name, Double fullScore, Double weight, String startDate, String dueDate, String note) {
        course.addAssignment(category, name, fullScore, weight, startDate, dueDate, note);

        save2Database();
    }

    public void delAssignment(Course course, int assignmentIndex) {
        //todo: delete assignment

        save2Database();
    }
}
