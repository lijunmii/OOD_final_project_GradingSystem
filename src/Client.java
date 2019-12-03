import java.util.ArrayList;
import java.util.List;

public class Client {
    private String username;
    private String password;
    private List<Course> courses;

    Client(String username, String password) {
        this.username = username;
        this.password = password;
        courses = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
