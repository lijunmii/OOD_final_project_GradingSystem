package gui;

import backend.*;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FrameCourse extends JFrame {
    private SystemDatabase systemDatabase;
    private Course course;

    private JPanel panel = new JPanel();

    private JPanel panel_2;
    private String[] columnNames;
    private Object[][] gradeData;
    private JTable tableGrades;
    private JScrollPane scrollPaneGrades;

    private JTextArea textAreaComment = new JTextArea(10, 15);
    private JButton buttonSaveComment = new JButton("Save");
    private JButton buttonImportExcel = new JButton("Import data");
    private JButton buttonAddStudent = new JButton("Add student");
    private JButton buttonDelStudent = new JButton("Delete student");
    private JButton buttonAddAssignment = new JButton("Add Assignment");
    private JButton buttonDelAssignment = new JButton("Delete Assignment");
    private JButton buttonCalculateGrades = new JButton("CALCULATE FINAL GRADES");

    private JButton buttonViewAll = new JButton("All students");
    private JButton buttonViewUnderGrad = new JButton("Undergrad");
    private JButton buttonViewGrad = new JButton("Graduate");
    private JButton buttonViewCategory = new JButton("View a specific category");
    private JButton buttonShowPercentage = new JButton("Percentage");
    private JButton buttonShowRawScore = new JButton("Raw score");

    FrameCourse() {}
    FrameCourse(FrameMainMenu frameMainMenu, SystemDatabase systemDatabase, Course course) {
        this.systemDatabase = systemDatabase;
        this.course = course;
        panel.setLayout(new BorderLayout());

        JPanel panel_1 = new JPanel(); {
            panel_1.setBorder(new EtchedBorder());
            JLabel title = new JLabel(course.toString(), JLabel.CENTER);
            panel_1.add(title);
        }
        panel.add(panel_1, BorderLayout.NORTH);

        panel_2 = new JPanel(); {
            panel_2.setLayout(new GridLayout(1, 1));
            panel_2.setBorder(BorderFactory.createEtchedBorder());

            //todo: table read student and assignment list from course
            updateGradeTable();
        }
        panel.add(panel_2, BorderLayout.CENTER);

        JPanel panel_3 = new JPanel(); {
            panel_3.setLayout(new GridLayout(3, 1));

            JPanel panel_3_1 = new JPanel(); {
                panel_3_1.add(textAreaComment);
                panel_3_1.add(buttonSaveComment);
            }
            panel_3.add(panel_3_1);

            JPanel panel_3_2 = new JPanel(); {
                panel_3_2.add(buttonImportExcel);
                panel_3_2.add(buttonAddStudent);
                panel_3_2.add(buttonDelStudent);
            }
            panel_3.add(panel_3_2);

            JPanel panel_3_3 = new JPanel(); {
                panel_3_3.add(buttonCalculateGrades);
            }
            panel_3.add(panel_3_3);
        }
        panel.add(panel_3, BorderLayout.EAST);

        JPanel panel_4 = new JPanel(); {
            panel_4.add(buttonViewAll);
            panel_4.add(buttonViewUnderGrad);
            panel_4.add(buttonViewGrad);

            panel_4.add(buttonViewCategory);

            panel_4.add(buttonShowPercentage);
            panel_4.add(buttonShowRawScore);
        }
        panel.add(panel_4, BorderLayout.SOUTH);

        add(panel);
        setTitle(course.toString());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        //import files
        buttonImportExcel.addActionListener(e -> {
            JFileChooser j = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("Excel file", "xls", "xlsx");
            int option = j.showOpenDialog(this);
            int result= j.showOpenDialog(this);
            if(result==JFileChooser.APPROVE_OPTION){
                File file=j.getSelectedFile();
                try {
                    extract(file,course);
                } catch (IOException i){
                    System.out.println("IOEXCEPTION");

                } catch (BiffException b) {
                    System.out.println("BiffException");
                }
            }

        });
    }

    public void extract(File f, Course course) throws BiffException, IOException {
        Workbook workbook = Workbook.getWorkbook(f);
        Sheet sheet = workbook.getSheet(0);
        System.out.println("Rows: "+ sheet.getRows());
        System.out.println("Cols: "+ sheet.getColumns());
        List<Assignment> l = new ArrayList<Assignment>();
        for (int i = 3; i<sheet.getColumns(); i++) {
            Cell cell = sheet.getCell(i,0);

            String[] array = cell.getContents().split(" ");
            System.out.println(array[0]);
            Assignment a = new Assignment(array[0],array[1],
                    Double.parseDouble(array[2]),Double.parseDouble(array[3]));
            l.add(a);
        }
        course.setAssignments(l);
        List<Student> students = new ArrayList<>();
        for(int i = 1;i<sheet.getRows();i++) {
            List<String> ls = new ArrayList<>();
            List<Grade> lg = new ArrayList<>();
            for ( int j = 0; j<sheet.getColumns();j++) {
                Cell cell = sheet.getCell(j,i);
                ls.add(cell.getContents());
            }
            String studentID = ls.get(0);
            String studentName = ls.get(1);
            Student s = new Student(studentID,studentName);
            s.setType(ls.get(2));
            for(int x = 3; x <ls.size(); x++) {
                Double score = Double.parseDouble(ls.get(x));
                Double fullScore = course.getAssignments().get(x-3).getFullScore();
                Grade g = new Grade(score, fullScore);
                lg.add(g);
            }
            s.setGrades(lg);
            students.add(s);
//            System.out.println(s.getStudentId());
//            System.out.println(s.getStudentName());
        }
        course.setStudents(students);
    }

    public void updateGradeTable() {
//        List<String> assignmentNames = new ArrayList<>();
//        assignmentNames.add("Student id");
//        for (Assignment assignment : course.getAssignments()) {
//            assignmentNames.add(assignment.toString());
//        }
//        columnNames = assignmentNames.toArray(new String[assignmentNames.size()]);
//
//        List<Student> students = course.getStudents();
//        gradeData = new Object[students.size()][columnNames.length];
//        for (int i = 0; i < gradeData.length; i++) {
//            Student student = students.get(i);
//            gradeData[i][0] = student.getStudentId();
//            for (int j = 1; j < gradeData[i].length; j++) {
//                gradeData[i][j] = student.getGrades().get(j - 1);
//            }
//        }

        // for test
        columnNames = new String[] {"Student id", "hw1", "hw2", "exam1"};
        gradeData = new Object[4][4];
        gradeData[0][0] = "U1250";
        gradeData[1][0] = "U1045";
        gradeData[2][0] = "U1134";
        gradeData[3][0] = "U1080";

        tableGrades = new JTable(gradeData, columnNames);

        TableModel model = new DefaultTableModel(gradeData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0 ? false : true;
            }
        };
        model.addTableModelListener(e -> {
            int row = e.getFirstRow();
            String newScore = (String) tableGrades.getValueAt(tableGrades.getEditingRow(), tableGrades.getEditingColumn());
            //todo: update grade after editing
            //systemDatabase.updateGradeInfo();
        });
        RowSorter<TableModel> sorter = new TableRowSorter<>(model);

        tableGrades.setModel(model);
        tableGrades.setRowSorter(sorter);
        if (tableGrades.getRowCount() > 0) {
            tableGrades.setRowSelectionInterval(0, 0);
        }

        panel_2.removeAll();
        panel_2.setLayout(new GridLayout(1, 1));
        panel_2.setBorder(BorderFactory.createEtchedBorder());
        scrollPaneGrades = new JScrollPane(tableGrades);
        scrollPaneGrades.setBorder(BorderFactory.createTitledBorder("Courses"));
        panel_2.add(scrollPaneGrades);
        panel_2.updateUI();
    }
}
