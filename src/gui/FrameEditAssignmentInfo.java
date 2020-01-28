package gui;

import backend.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class FrameEditAssignmentInfo extends JFrame {
    private JPanel panel = new JPanel();
    private JTextField inputCategory = new JTextField(18);
    private JTextField inputName = new JTextField(18);
    private JTextField inputFullScore = new JTextField(18);
    private JTextField inputWeight = new JTextField(18);
    private JTextField inputStartDate = new JTextField(18);
    private JTextField inputDueDate = new JTextField(18);
    private JTextField inputNote = new JTextField(18);
    private JButton buttonAdd = new JButton("Update Info");

    FrameEditAssignmentInfo() {}
    FrameEditAssignmentInfo(FrameCourse frameCourse, SystemDatabase systemDatabase, Course course, Assignment assignment) {
        panel.setLayout(new GridLayout(8, 1));
        panel.setBorder(BorderFactory.createEtchedBorder());

        JPanel panel_1 = new JPanel(); {
            panel_1.setBorder(new TitledBorder("Category (*)"));
            inputCategory.setText(assignment.getCategory());
            panel_1.add(inputCategory);
        }
        panel.add(panel_1);

        JPanel panel_2 = new JPanel(); {
            panel_2.setBorder(new TitledBorder("Assignment name (*)"));
            inputName.setText(assignment.getName());
            panel_2.add(inputName);
        }
        panel.add(panel_2);

        JPanel panel_3 = new JPanel(); {
            panel_3.setBorder(new TitledBorder("Full score (*)"));
            inputFullScore.setText(assignment.getFullScore().toString());
            panel_3.add(inputFullScore);
        }
        panel.add(panel_3);

        JPanel panel_4 = new JPanel(); {
            panel_4.setBorder(new TitledBorder("Weight (*)"));
            inputWeight.setText(assignment.getWeight().toString());
            panel_4.add(inputWeight);
        }
        panel.add(panel_4);

        JPanel panel_5 = new JPanel(); {
            panel_5.setBorder(new TitledBorder("Start date"));
            inputStartDate.setText(assignment.getStartDate());
            panel_5.add(inputStartDate);
        }
        panel.add(panel_5);

        JPanel panel_6 = new JPanel(); {
            panel_6.setBorder(new TitledBorder("Due date"));
            inputDueDate.setText(assignment.getDueDate());
            panel_6.add(inputDueDate);
        }
        panel.add(panel_6);

        JPanel panel_7 = new JPanel(); {
            panel_7.setBorder(new TitledBorder("Note"));
            inputNote.setText(assignment.getNote());
            panel_7.add(inputNote);
        }
        panel.add(panel_7);

        JPanel panel_8 = new JPanel(); {
            panel_8.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 16));
            panel_8.add(buttonAdd);
        }
        panel.add(panel_8);

        add(panel);
        setTitle("UPDATE INFO");
        setSize(250, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        buttonAdd.addActionListener(e -> {
            String category = inputCategory.getText();
            String name = inputName.getText();
            String fullScoreStr = inputFullScore.getText();
            String weightStr = inputWeight.getText();

            String startDate = inputStartDate.getText();
            String dueDate = inputDueDate.getText();
            String note = inputNote.getText();

            if (category.length() > 0 && name.length() > 0 && fullScoreStr.length() > 0 && weightStr.length() > 0) {
                if (!course.assignmentExist(category, name) || (category.equals(assignment.getCategory()) && name.equals(assignment.getName()))) {
                    if (Tools.isNumeric(fullScoreStr) && Tools.isNumeric(weightStr)) {
                        Double fullScore = Double.parseDouble(fullScoreStr);
                        Double weight = Double.parseDouble(weightStr);
                        systemDatabase.updateAssignmentInfo(assignment, category, name, fullScore, weight, startDate, dueDate, note);
                        JOptionPane.showMessageDialog(this, "Assignment info updated.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                        frameCourse.updateGradeTable();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Wrong score or weight (number only).", "WRONG FORM", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Assignment already exists!", "EXISTED", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Empty info!", "MISSING CONTENT", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
