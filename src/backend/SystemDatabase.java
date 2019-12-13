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

        Client client = new Client("123", "123");
        client.addCourse("CS542", "Machine Learning", new Semester(2018, Season.Spring));
        client.addCourse("CS504", "Data Tools", new Semester(2019, Season.Spring));
        client.addCourse("CS651", "Distributed System", new Semester(2019, Season.Summer));
        client.addCourse("CS591P1", "OOD in Java", new Semester(2018, Season.Fall));
        client.addCourse("CS640", "Artificial Intelligence", new Semester(2019, Season.Fall));
        clients.add(client);

        //todo: read clients from db here
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

    public boolean courseNumExist(String username, String courseNum) {
        for (Course course : getClient(username).getCourses()) {
            if (course.getCourseNum().equals(courseNum)) {
                return true;
            }
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

        //todo: write clients into db here
        save2Database();
    }

    public void addCourse(String username, String courseNum, String courseName, Semester semester) {
        getClient(username).addCourse(courseNum, courseName, semester);

        //todo: write clients into db here
        save2Database();
    }

    public void delCourse(String username, List<String> courseNums) {
        getClient(username).delCourse(courseNums);

        //todo: write clients into db here
        save2Database();
    }
}
