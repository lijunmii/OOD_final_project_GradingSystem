package backend;

import java.util.ArrayList;
import java.util.Iterator;
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

    public void addCourse(String courseNum, String courseName, Semester semester) {
        courses.add(new Course(courseNum, courseName, semester));
    }

    public void delCourse(List<String> courseNums) {
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            for (String courseNum : courseNums) {
                if (course.getCourseNum().equals(courseNum)) {
                    iterator.remove();
                }
            }
        }
    }
}
