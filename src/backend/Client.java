package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Client implements Serializable {
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

    public void delCourse(List<String> courseNums, List<String> semesters) {
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            for (int i = 0; i < courseNums.size(); i++) {
                String courseNum = courseNums.get(i);
                String semester = semesters.get(i);
                if (course.getCourseNum().equals(courseNum) && course.getSemester().toString().equals(semester)) {
                    iterator.remove();
                }
            }
        }
    }

    public Course getCourse(String courseNum, String semester) {
        for (Course course : courses) {
            if (course.getCourseNum().equals(courseNum) && course.getSemester().toString().equals(semester)) {
                return course;
            }
        }
        return null;
    }

    public void updateCourseInfo(int courseIndex, String courseNum, String courseName) {
        courses.get(courseIndex).updateInfo(courseNum, courseName);
    }
}
