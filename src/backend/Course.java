package backend;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Course implements Serializable {
    private String courseNum;
    private String courseName;
    private Semester semester;
    private List<Assignment> assignments = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private Double curveNum = 0.0;

    public Course(String courseNum, String courseName, Semester semester) {
        this.courseNum = courseNum;
        this.courseName = courseName;
        this.semester = semester;
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

    public void updateGrade(int studentIndex, int assignmentIndex, Double newScore) {
        students.get(studentIndex).getGrades().get(assignmentIndex).setScore(newScore);
    }

    public void updateGrade(int studentIndex, int assignmentIndex, String newScoreStr) {
        students.get(studentIndex).getGrades().get(assignmentIndex).setScore(newScoreStr);
    }

    public void importExcel(File f) {
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(f);
        } catch (Exception e) {
            System.out.println("File doesn't meet the requirements!");
        }
        Sheet sheet = workbook.getSheet(0);

        assignments = new ArrayList<>();
        for (int i = 3; i < sheet.getColumns(); i++) {
            Cell cell = sheet.getCell(i, 0);

            String[] array = cell.getContents().split(" ");
            Assignment a = new Assignment(array[0], array[1],
                    Double.parseDouble(array[2]), Double.parseDouble(array[3]));
            assignments.add(a);
        }

        students = new ArrayList<>();
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
                Double score;
                if (Tools.isNumeric(ls.get(x))) {
                    score = Double.parseDouble(ls.get(x));
                } else {
                    score = 0.0;
                }
                Double fullScore = getAssignments().get(x - 3).getFullScore();
                Grade g = new Grade(score, fullScore);
                lg.add(g);
            }
            s.setGrades(lg);
            students.add(s);
        }
        Collections.sort(students);
    }

    public Student getStudent(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public boolean studentExist(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return true;
            }
        }
        return false;
    }

    public void addStudent(String studentId, String studentName, int studentType) {
        Student student = new Student(studentId, studentName);
        student.setType(studentType);

        List<Grade> grades = new ArrayList<>();
        for (Assignment assignment : assignments) {
            Double fullScore = assignment.getFullScore();
            grades.add(new Grade(fullScore));
        }
        student.setGrades(grades);

        students.add(student);
        Collections.sort(students);
    }

    public void delStudent(String studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(studentId)) {
                students.remove(i);
                break;
            }
        }
    }

    public boolean assignmentExist(String category, String name) {
        for (Assignment assignment : assignments) {
            if (assignment.getCategory().equals(category) && assignment.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void addAssignment(String category, String name, Double fullScore, Double weight, String startDate, String dueDate, String note) {
        Assignment assignment = new Assignment(category, name, fullScore, weight);
        assignment.setStartDate(startDate);
        assignment.setDueDate(dueDate);
        assignment.setNote(note);

        assignments.add(assignment);

        for (Student student : students) {
            student.getGrades().add(new Grade(fullScore));
        }
    }

    public void delAssignment(int assignmentIndex) {
        assignments.remove(assignmentIndex);

        for (Student student : students) {
            student.getGrades().remove(assignmentIndex);
        }
    }

    public boolean weightAddUp2One() {
        Double allWeight = 0.0;
        for (Assignment assignment : assignments) {
            allWeight += assignment.getWeight();
        }
        return Math.abs(allWeight - 1.0) < 1e-4; // because double add will result in a tiny error. 0.5 + 0.5 = 0.999999...
    }

    public void calculateFinalGrade() {
        for (Student student : students) {
            Double finalScoreRaw = 0.0;
            for (int i = 0; i < assignments.size(); i++) {
                Double weight = assignments.get(i).getWeight();
                if (student.getGrades().get(i).getScore() == null) {
                    continue;
                }
                Double score = student.getGrades().get(i).getScore();
                Double fullScore = student.getGrades().get(i).getFullScore();
                finalScoreRaw += score / fullScore * weight;
            }
            if (finalScoreRaw > 1) {
                finalScoreRaw = 1.0;
            }
            finalScoreRaw *= 100;
            student.setFinalGradeRaw(finalScoreRaw);
        }
    }

    public Double getCurveNum() {
        return curveNum;
    }

    public void setCurveNum(Double curveNum) {
        this.curveNum = curveNum;
    }

    public void curveFinalGrade() {
        for (Student student : students) {
            Double finalGradeCurved = student.getFinalGradeRaw() + curveNum;
            if (finalGradeCurved > 100) {
                finalGradeCurved = 100.0;
            }
            student.setFinalGradeCurved(finalGradeCurved);
        }
    }
}
