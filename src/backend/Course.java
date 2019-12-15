package backend;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseNum;
    private String courseName;
    private Semester semester;
    private List<Assignment> assignments;
    private List<Student> students;

    public Course(String courseNum, String courseName, Semester semester) {
        this.courseNum = courseNum;
        this.courseName = courseName;
        this.semester = semester;
        assignments = new ArrayList<>();
        students = new ArrayList<>();
    }

    public void setAssignments(List<Assignment> l) {
        this.assignments = l;
    }

    public void setStudents(List<Student> S) {
        this.students = S;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return courseNum + " " + courseName + ", " + semester.toString();
    }

    public void updateInfo(String courseNum, String courseName) {
        setCourseNum(courseNum);
        setCourseName(courseName);
    }

    public void importExcel(File f) {
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(f);
        } catch (Exception e) {
            System.out.println("File doesn't meet the requirements!");
        }
        Sheet sheet = workbook.getSheet(0);
        List<Assignment> l = new ArrayList<Assignment>();
        for (int i = 3; i < sheet.getColumns(); i++) {
            Cell cell = sheet.getCell(i, 0);

            String[] array = cell.getContents().split(" ");
            Assignment a = new Assignment(array[0], array[1],
                    Double.parseDouble(array[2]), Double.parseDouble(array[3]));
            l.add(a);
        }
        setAssignments(l);

        List<Student> students = new ArrayList<>();
        for (int i = 1; i < sheet.getRows(); i++) {
            List<String> ls = new ArrayList<>();
            List<Grade> lg = new ArrayList<>();
            for (int j = 0; j < sheet.getColumns(); j++) {
                Cell cell = sheet.getCell(j, i);
                ls.add(cell.getContents());
            }
            String studentID = ls.get(0);
            String studentName = ls.get(1);
            Student s = new Student(studentID, studentName);
            s.setType(ls.get(2));
            for (int x = 3; x < ls.size(); x++) {
                Double score = Double.parseDouble(ls.get(x));
                Double fullScore = getAssignments().get(x - 3).getFullScore();
                Grade g = new Grade(score, fullScore);
                lg.add(g);
            }
            s.setGrades(lg);
            students.add(s);
        }
        setStudents(students);
    }
}
