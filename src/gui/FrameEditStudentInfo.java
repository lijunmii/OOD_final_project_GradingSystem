package gui;

import backend.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class FrameEditStudentInfo extends JFrame {
    private JPanel panel = new JPanel();
    private JTextField inputId = new JTextField(18);
    private JTextField inputName = new JTextField(18);
    private JComboBox comboBoxType = new JComboBox();
    private JButton buttonAdd = new JButton("Update Info");

    FrameEditStudentInfo() {}
    FrameEditStudentInfo(FrameCourse frameCourse, SystemDatabase systemDatabase, Course course, Student student) {
        panel.setLayout(new GridLayout(3, 1));
        panel.setBorder(BorderFactory.createEtchedBorder());

        JPanel panel_1 = new JPanel(); {
            panel_1.setBorder(new TitledBorder("Student id"));
            inputId.setText(student.getStudentId());
            panel_1.add(inputId);
        }
        panel.add(panel_1);

        JPanel panel_2 = new JPanel(); {
            panel_2.setBorder(new TitledBorder("Student name"));
            inputName.setText(student.getStudentName());
            panel_2.add(inputName);
        }
        panel.add(panel_2);

        JPanel panel_3 = new JPanel(); {
            panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

            comboBoxType.addItem("Undergrad");
            comboBoxType.addItem("Graduate");
            if (student.getType().equals("U")) {
                comboBoxType.setSelectedIndex(0);
            } else if (student.getType().equals("G")) {
                comboBoxType.setSelectedIndex(1);
            }
            panel_3.add(comboBoxType);

            panel_3.add(buttonAdd);
        }
        panel.add(panel_3);

        add(panel);
        setTitle("UPDATE INFO");
        setSize(250, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        buttonAdd.addActionListener(e -> {
            String studentId = inputId.getText();
            String studentName = inputName.getText();
            if (studentId.length() > 0 && studentName.length() > 0) {
                if (!course.studentExist(studentId) || studentId.equals(student.getStudentId())) {
                    int studentTypeIndex = comboBoxType.getSelectedIndex();
                    systemDatabase.updateStudentInfo(student, studentId, studentName, studentTypeIndex);
                    JOptionPane.showMessageDialog(this, "Student info updated.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    frameCourse.updateGradeTable();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Student already exists!", "EXISTED", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Empty info!", "MISSING CONTENT", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
