package gui;

import backend.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

        ;
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
