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

public class FrameFinalGrade extends JFrame {
    private JPanel panel = new JPanel();

    private JPanel panel_2;
    Object[][] finalGrade;
    private String[] columnNames = {"Student id", "Raw Grade", "Curved Grade"};
    private JTable tableFinalGrade = new JTable();
    private JScrollPane scrollPaneFinalGrade;

    private JTextField textFieldCurve = new JTextField(5);
    private JButton buttonCurve = new JButton("Curve");

    private JButton buttonStatistics = new JButton("Statistics");

    FrameFinalGrade() {}
    FrameFinalGrade(SystemDatabase systemDatabase, Course course) {
        panel.setLayout(new BorderLayout());

        JPanel panel_1 = new JPanel(); {
            panel_1.setBorder(new EtchedBorder());
            JLabel title = new JLabel(course.toString() + " - Final Grade", JLabel.CENTER);
            panel_1.add(title);
        }
        panel.add(panel_1, BorderLayout.NORTH);

        panel_2 = new JPanel(); {
            panel_2.setLayout(new GridLayout(1, 1));
            panel_2.setBorder(new EtchedBorder());

            finalGrade = new Object[course.getStudents().size()][3];
            for (int i = 0; i < finalGrade.length; i++) {
                finalGrade[i][0] = course.getStudents().get(i).getStudentId();
                finalGrade[i][1] = Tools.df_X_1.format(course.getStudents().get(i).getFinalGradeRaw()) + "%";
                finalGrade[i][2] = Tools.df_X_1.format(course.getStudents().get(i).getFinalGradeCurved()) + "%";
                //finalGrade[i][1] = Tools.df_3_1.format(course.getStudents().get(i).getFinalGradeRaw()) + "%";
                //finalGrade[i][2] = Tools.df_3_1.format(course.getStudents().get(i).getFinalGradeCurved()) + "%";
            }

            tableFinalGrade = new JTable(finalGrade, columnNames);

            TableModel model = new DefaultTableModel(finalGrade, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            RowSorter<TableModel> sorter = new TableRowSorter<>(model);

            tableFinalGrade.setModel(model);
            tableFinalGrade.setRowSorter(sorter);
            tableFinalGrade.setRowHeight(25);
            tableFinalGrade.getTableHeader().setReorderingAllowed(false);
            tableFinalGrade.setShowGrid(true);
            tableFinalGrade.setShowHorizontalLines(true);
            tableFinalGrade.setShowVerticalLines(true);

            scrollPaneFinalGrade = new JScrollPane(tableFinalGrade);
            scrollPaneFinalGrade.setBorder(BorderFactory.createTitledBorder("Final grade"));
            panel_2.add(scrollPaneFinalGrade);
        }
        panel.add(panel_2, BorderLayout.CENTER);

        JPanel panel_3 = new JPanel(); {
            panel_3.setBorder(new EtchedBorder());

            textFieldCurve.setText(course.getCurveNum().toString());
            panel_3.add(textFieldCurve);
            panel_3.add(new JLabel("%"));
            panel_3.add(buttonCurve);

            panel_3.add(buttonStatistics);
        }
        panel.add(panel_3, BorderLayout.SOUTH);

        add(panel);
        setTitle("FINAL GRADE");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        buttonCurve.addActionListener(e -> { // apply curve
            String curveNumStr = textFieldCurve.getText();
            if (curveNumStr.length() > 0 && Tools.isNumeric(curveNumStr)) {
                Double curveNum = Double.parseDouble(curveNumStr);
                systemDatabase.curveFinalGrade(course, curveNum);

                // update panel_2
                for (int i = 0; i < finalGrade.length; i++) {
                    finalGrade[i][2] = Tools.df_X_1.format(course.getStudents().get(i).getFinalGradeCurved()) + "%";
                    //finalGrade[i][2] = Tools.df_3_1.format(course.getStudents().get(i).getFinalGradeCurved()) + "%";
                }
                tableFinalGrade = new JTable(finalGrade, columnNames);

                TableModel model = new DefaultTableModel(finalGrade, columnNames) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                RowSorter<TableModel> sorter = new TableRowSorter<>(model);

                tableFinalGrade.setModel(model);
                tableFinalGrade.setRowSorter(sorter);
                tableFinalGrade.setRowHeight(25);
                tableFinalGrade.getTableHeader().setReorderingAllowed(false);
                tableFinalGrade.setShowGrid(true);
                tableFinalGrade.setShowHorizontalLines(true);
                tableFinalGrade.setShowVerticalLines(true);

                scrollPaneFinalGrade = new JScrollPane(tableFinalGrade);
                scrollPaneFinalGrade.setBorder(BorderFactory.createTitledBorder("Final grade"));

                panel_2.removeAll();
                panel_2.setLayout(new GridLayout(1, 1));
                panel_2.setBorder(new EtchedBorder());
                panel_2.add(scrollPaneFinalGrade);
                panel_2.updateUI();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input.", "INVALID INPUT", JOptionPane.WARNING_MESSAGE);
            }
        });

        buttonStatistics.addActionListener(e -> { // show final grade statistics: mean, median, standard deviation
            List<Double> grades = new ArrayList<>();
            for (Student student : course.getStudents()) {
                if (student.getFinalGradeCurved() != null) {
                    grades.add(student.getFinalGradeCurved());
                }
            }
            Double mean = Tools.mean(grades);
            Double median = Tools.median(grades);
            Double standardDeviation = Tools.standardDeviation(grades.toArray(new Double[grades.size()]));
            String statistics = "Mean: " + Tools.df_X_1.format(mean);
            statistics += "\nMedian: " + Tools.df_X_1.format(median);
            statistics += "\nStandard deviation: " + Tools.df_X_1.format(standardDeviation);
            JOptionPane.showMessageDialog(this, statistics, "FINAL STATISTICS", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
