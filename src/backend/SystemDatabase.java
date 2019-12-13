package backend;

import java.util.ArrayList;
import java.util.List;

public class SystemDatabase {
    private List<Client> clients;

    public SystemDatabase() {
        clients = new ArrayList<>();
    }

    public SystemDatabase(String testStr) {
        clients = new ArrayList<>();
        clients.add(new Client("123", "123"));
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

    public boolean courseNumExist(String username, String courseNum) {
        for (Course course : getClient(username).getCourses()) {
            if (course.getCourseNum().equals(courseNum)) {
                return true;
            }
        }
        return false;
    }

    // functions below write data
    public void register(String username, String password) {
        clients.add(new Client(username, password));
    }

    public void addCourse(String username, String courseNum, String courseName, Semester semester) {
        getClient(username).addCourse(courseNum, courseName, semester);
    }
}
