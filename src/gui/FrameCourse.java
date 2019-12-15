package gui;

import backend.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.File;
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

    private JTextArea textAreaInfo = new JTextArea();
    private JButton buttonEditInfo = new JButton("Edit info");
    private JTextArea textAreaComment = new JTextArea();
    private JButton buttonSaveComment = new JButton("Save comment");

    private JButton buttonImportExcel = new JButton("Import data");
    private JButton buttonAddStudent = new JButton("Add student");
    private JButton buttonDelStudent = new JButton("Delete student");
    private JButton buttonAddAssignment = new JButton("Add Assignment");
    private JButton buttonDelAssignment = new JButton("Delete Assignment");

    private JButton buttonCalculateGrades = new JButton("CALCULATE FINAL GRADES");

    private JButton buttonViewUnderGrad = new JButton("View Undergrad");
    private JButton buttonViewGrad = new JButton("View Graduate");
    private JButton buttonViewCategory = new JButton("View category");
    private JButton buttonShowPercentage = new JButton("Percentage");
    private JButton buttonShowRawScore = new JButton("Raw score");

    private FrameAddStudent frameAddStudent = new FrameAddStudent();
    private FrameAddAssignment frameAddAssignment = new FrameAddAssignment();

    FrameCourse() {
    }

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
            panel_2.setBorder(new EtchedBorder());

            setModel();
            updateGradeTable();
        }
        panel.add(panel_2, BorderLayout.CENTER);

        JPanel panel_3 = new JPanel(); {
            panel_3.setLayout(new GridLayout(3, 1));

            JPanel panel_3_1 = new JPanel(); {
                panel_3_1.setLayout(new BorderLayout());
                panel_3_1.setBorder(new EtchedBorder());

                textAreaInfo.setBorder(new EtchedBorder());
                textAreaInfo.setText("You will see assignment info here.");
                textAreaInfo.setEditable(false);
                textAreaInfo.setLineWrap(true);
                textAreaInfo.setWrapStyleWord(true);
                JScrollPane jScrollPaneInfo = new JScrollPane(textAreaInfo);
                panel_3_1.add(jScrollPaneInfo, BorderLayout.CENTER);

                JPanel panel_3_1_1 = new JPanel(); {
                    panel_3_1_1.add(buttonEditInfo);
                }

                panel_3_1.add(panel_3_1_1, BorderLayout.SOUTH);
            }
            panel_3.add(panel_3_1);

            JPanel panel_3_2 = new JPanel(); {
                panel_3_2.setLayout(new BorderLayout());
                panel_3_2.setBorder(new EtchedBorder());

                textAreaComment.setBorder(new EtchedBorder());
                textAreaComment.setText("Leave your comment here.");
                textAreaComment.setLineWrap(true);
                textAreaComment.setWrapStyleWord(true);
                JScrollPane jScrollPaneComment = new JScrollPane(textAreaComment);
                panel_3_2.add(jScrollPaneComment, BorderLayout.CENTER);

                JPanel panel_3_2_1 = new JPanel(); {
                    panel_3_2_1.add(buttonSaveComment);
                }
                panel_3_2.add(panel_3_2_1, BorderLayout.SOUTH);
            }
            panel_3.add(panel_3_2);

            JPanel panel_3_3 = new JPanel(); {
                panel_3_3.setPreferredSize(new Dimension(300, 0));
                panel_3_3.setLayout(new GridLayout(1, 2));
                panel_3_3.setBorder(new EtchedBorder());

                JPanel panel_3_3_1 = new JPanel(); {
                    panel_3_3_1.setLayout(new GridLayout(5, 1));
                    panel_3_3_1.add(buttonImportExcel);
                    panel_3_3_1.add(buttonAddStudent);
                    panel_3_3_1.add(buttonDelStudent);
                    panel_3_3_1.add(buttonAddAssignment);
                    panel_3_3_1.add(buttonDelAssignment);
                }
                panel_3_3.add(panel_3_3_1);

                panel_3_3.add(buttonCalculateGrades);
            }
            panel_3.add(panel_3_3);
        }
        panel.add(panel_3, BorderLayout.EAST);

        JPanel panel_4 = new JPanel(); {
            panel_4.setBorder(new EtchedBorder());

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

        buttonImportExcel.addActionListener(e -> { // import data from excel file
            JFileChooser j = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("Excel file", "xls");
            j.setFileFilter(filter);
            int result = j.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = j.getSelectedFile();
                systemDatabase.importExcel(file, course);
                updateGradeTable();
            }
        });

        buttonEditInfo.addActionListener(e -> { // todo:edit assignment or student info
            ;
        });

        buttonSaveComment.addActionListener(e -> { // save comment for a cell after edit
            int row = tableGrades.getSelectedRow();
            int column = tableGrades.getSelectedColumn();

            if (row >= 0 && column >= 0) {
                String studentId = tableGrades.getValueAt(row, 0).toString();
                Student student = course.getStudent(studentId);
                String comment = textAreaComment.getText();
                if (column == 0) { // update student comment
                    systemDatabase.updateComment(student, comment);
                } else { // update grade comment
                    int assignmentIndex = column - 1;
                    systemDatabase.updateComment(student, assignmentIndex, comment);
                }
                JOptionPane.showMessageDialog(this, "Comment Saved.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No cell selected.", "NO SELECTION", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        buttonAddStudent.addActionListener(e -> { // add student
            if (!subWindowExist()) {
                frameAddStudent = new FrameAddStudent(this, systemDatabase, course);
                frameAddStudent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameAddStudent.setVisible(true);
            }
        });

        buttonAddAssignment.addActionListener(e -> { // todo:add assignment
            if (!subWindowExist()) {
                frameAddAssignment = new FrameAddAssignment(this, systemDatabase, course);
                frameAddAssignment.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameAddAssignment.setVisible(true);
            }
        });
    }

    public void updateGradeTable() {
        setData();
        tableGrades = new JTable(gradeData, columnNames);

        TableModel model = setModel();

        RowSorter<TableModel> sorter = new TableRowSorter<>(model);

        tableGrades.setModel(model);
        tableGrades.setRowSorter(sorter);

        tableGrades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tableGrades.getSelectionModel().addListSelectionListener(e -> {
            int row = tableGrades.getSelectedRow();
            int column = tableGrades.getSelectedColumn();

            if (row >= 0 && column >= 0) {
                String studentId = tableGrades.getValueAt(row, 0).toString();
                Student student = course.getStudent(studentId);
                if (column == 0) { // print student info & comment
                    textAreaInfo.setText(student.getInfo());
                    textAreaComment.setText(student.getComment());
                } else { // print assignment info & grade comment
                    int assignmentIndex = column - 1;
                    textAreaInfo.setText(course.getAssignments().get(assignmentIndex).getInfo());
                    textAreaComment.setText(student.getGrades().get(assignmentIndex).getComment());
                }
            }
        });

        tableGrades.getColumnModel().getSelectionModel().addListSelectionListener(e -> {
            int row = tableGrades.getSelectedRow();
            int column = tableGrades.getSelectedColumn();

            if (row >= 0 && column >= 0) {
                String studentId = tableGrades.getValueAt(row, 0).toString();
                Student student = course.getStudent(studentId);
                if (column == 0) { // print student info & comment
                    textAreaInfo.setText(student.getInfo());
                    textAreaComment.setText(student.getComment());
                } else { // print assignment info & grade comment
                    int assignmentIndex = column - 1;
                    textAreaInfo.setText(course.getAssignments().get(assignmentIndex).getInfo());
                    textAreaComment.setText(student.getGrades().get(assignmentIndex).getComment());
                }
            }
        });

        tableGrades.getTableHeader().setReorderingAllowed(false);

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

    public void setData() {
        List<String> assignmentNames = new ArrayList<>();
        assignmentNames.add("Student id");
        for (Assignment assignment : course.getAssignments()) {
            assignmentNames.add(assignment.toString());
        }
        columnNames = assignmentNames.toArray(new String[assignmentNames.size()]);

        List<Student> students = course.getStudents();
        gradeData = new Object[students.size()][columnNames.length];
        for (int i = 0; i < gradeData.length; i++) {
            Student student = students.get(i);
            gradeData[i][0] = student.getStudentId();
            for (int j = 1; j < gradeData[i].length; j++) {
                gradeData[i][j] = student.getGrades().get(j - 1);
            }
        }
    }

    public TableModel setModel() {
        TableModel model = new DefaultTableModel(gradeData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0 ? false : true;
            }

            // todo:sort numerical rather than alphabetical, and keep it editable
//            @Override
//            public Class getColumnClass(int columnIndex) {
//                if (columnIndex == 0) {
//                    return String.class;
//                }
//                return Number.class;
//            }

            // todo: set col width
        };

        model.addTableModelListener(e -> { // update grade after editing
            int row = e.getFirstRow();
            int column = e.getColumn();
            String newScoreStr = tableGrades.getValueAt(tableGrades.getEditingRow(), tableGrades.getEditingColumn()).toString();
            if (Tools.isNumeric(newScoreStr)) { // sec raw score or score lost
                Double newScore = Double.parseDouble(newScoreStr);
                systemDatabase.updateGrade(course, row, column - 1, newScore);
                updateGradeTable();
            } else if (Tools.isNumeric(newScoreStr.substring(0, newScoreStr.length() - 1)) && newScoreStr.substring(newScoreStr.length() - 1).equals("%")) { // set percentage score
                systemDatabase.updateGrade(course, row, column - 1, newScoreStr);
                updateGradeTable();
            } else  {
                JOptionPane.showMessageDialog(this, "Wrong form (number only).", "WRONG FORM", JOptionPane.INFORMATION_MESSAGE);
                updateGradeTable();
            }
        });

        return model;
    }

    private boolean subWindowExist() { // one sub window at the same time
        return (frameAddStudent.isVisible() ||
                frameAddAssignment.isVisible());
    }
}
