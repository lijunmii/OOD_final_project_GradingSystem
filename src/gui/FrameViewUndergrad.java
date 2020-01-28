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

public class FrameViewUndergrad extends JFrame {
    private JPanel panel = new JPanel();

    private JTable tableGrades;
    private JScrollPane scrollPaneGrades;

    private JTextArea textAreaInfo = new JTextArea(10, 10);
    private JTextArea textAreaComment = new JTextArea(10, 10);

    FrameViewUndergrad() {}
    FrameViewUndergrad(Course course) {
        panel.setLayout(new BorderLayout());

        JPanel panel_1 = new JPanel(); {
            panel_1.setBorder(new EtchedBorder());
            JLabel title = new JLabel(course.toString(), JLabel.CENTER);
            panel_1.add(title);
        }
        panel.add(panel_1, BorderLayout.NORTH);

        JPanel panel_2 = new JPanel(); {
            panel_2.setLayout(new GridLayout(1, 1));
            panel_2.setBorder(BorderFactory.createEtchedBorder());

            // set data
            java.util.List<String> assignmentNames = new ArrayList<>();
            assignmentNames.add("Student id");
            for (Assignment assignment : course.getAssignments()) {
                assignmentNames.add(assignment.toString());
            }
            String[] columnNames = assignmentNames.toArray(new String[assignmentNames.size()]);

            List<Student> students = course.getStudents();
            int graduateCount = 0;
            for (Student student : students) {
                if (student.getType().equals("U")) {
                    graduateCount += 1;
                }
            }
            Object[][] gradeData = new Object[graduateCount][columnNames.length];
            for (int i = 0, x = 0; i < gradeData.length && x < students.size(); x++) {
                Student student = students.get(x);
                if (!student.getType().equals("U")) {
                    continue;
                }
                gradeData[i][0] = student.getStudentId();
                for (int j = 1; j < gradeData[i].length; j++) {
                    gradeData[i][j] = student.getGrades().get(j - 1);
                }
                i++;
            }

            tableGrades = new JTable(gradeData, columnNames);

            // set model
            TableModel model = new DefaultTableModel(gradeData, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            RowSorter<TableModel> sorter = new TableRowSorter<>(model);

            tableGrades.setModel(model);
            tableGrades.setRowSorter(sorter);
            tableGrades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            tableGrades.getSelectionModel().addListSelectionListener(e -> { // print info & comment when a new cell is selected
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

            tableGrades.getColumnModel().getSelectionModel().addListSelectionListener(e -> { // print info & comment when a new cell is selected
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

            if (tableGrades.getRowCount() > 0) {
                tableGrades.setRowSelectionInterval(0, 0);
            }

            // set look
            tableGrades.setBorder(new EtchedBorder());
            tableGrades.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tableGrades.getTableHeader().setReorderingAllowed(false);
            for (int i = 0; i < tableGrades.getColumnCount(); i++) {
                tableGrades.getColumnModel().getColumn(i).setPreferredWidth(125);
            }
            tableGrades.setRowHeight(25);

            tableGrades.setGridColor(Color.BLACK);
            tableGrades.setShowGrid(true);
            tableGrades.setShowHorizontalLines(true);
            tableGrades.setShowVerticalLines(true);

            scrollPaneGrades = new JScrollPane(tableGrades);
            scrollPaneGrades.setBorder(BorderFactory.createTitledBorder("Grades"));
            panel_2.add(scrollPaneGrades);
        }
        panel.add(panel_2, BorderLayout.CENTER);

        JPanel panel_3 = new JPanel(); {
            panel_3.setLayout(new GridLayout(2, 1));
            panel_3.setPreferredSize(new Dimension(300, 0));

            textAreaInfo.setBorder(new EtchedBorder());
            textAreaInfo.setText("You will see student or assignment info here.");
            textAreaInfo.setEditable(false);
            textAreaInfo.setLineWrap(true);
            textAreaInfo.setWrapStyleWord(true);
            JScrollPane jScrollPaneInfo = new JScrollPane(textAreaInfo);
            jScrollPaneInfo.setBorder(BorderFactory.createTitledBorder("Info"));
            panel_3.add(jScrollPaneInfo);

            textAreaComment.setBorder(new EtchedBorder());
            textAreaComment.setText("You will see comment here.");
            textAreaComment.setEditable(false);
            textAreaComment.setLineWrap(true);
            textAreaComment.setWrapStyleWord(true);
            JScrollPane jScrollPaneComment = new JScrollPane(textAreaComment);
            jScrollPaneComment.setBorder(BorderFactory.createTitledBorder("Comment"));
            panel_3.add(jScrollPaneComment, BorderLayout.CENTER);
        }
        panel.add(panel_3, BorderLayout.EAST);

        add(panel);
        setTitle("View Undergraduate Students");
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
