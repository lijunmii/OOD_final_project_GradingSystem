package gui;

import backend.*;
import javax.swing.*;
import java.awt.*;

public class FrameAddCourse extends JFrame {
    private JPanel panel = new JPanel();
    private JTextField textFieldCourseNum = new JTextField(8);
    private JTextField textFieldCourseName = new JTextField(20);
    private JComboBox comboBoxYear = new JComboBox();
    private JComboBox comboBoxSeason = new JComboBox();
    private JButton buttonAddCourse = new JButton("Add Course");

    FrameAddCourse() {}
    FrameAddCourse(Client client, SystemDatabase systemDatabase) {

        textFieldCourseNum.setBorder(BorderFactory.createTitledBorder("Course Number"));
        panel.add(textFieldCourseNum);

        textFieldCourseName.setBorder(BorderFactory.createTitledBorder("Course Name"));
        panel.add(textFieldCourseName);

        comboBoxYear.addItem("2019");
        comboBoxYear.addItem("2020");
        comboBoxYear.addItem("2021");
        panel.add(comboBoxYear);

        comboBoxSeason.addItem("Spring");
        comboBoxSeason.addItem("Summer");
        comboBoxSeason.addItem("Fall");
        panel.add(comboBoxSeason);

        panel.add(buttonAddCourse);

        add(panel);
        setTitle("ADD COURSE");
        setSize(500, 110);
        setLocationRelativeTo(null);
        setResizable(false);

        buttonAddCourse.addActionListener(e -> {
            if (textFieldCourseNum.getText().length() > 0 && textFieldCourseName.getText().length() > 0) {
                String courseNum = textFieldCourseNum.getText();
                String courseName = textFieldCourseName.getText();
                if (!systemDatabase.courseNumExist(client.getUsername(), courseNum)) {
                    Semester semester = new Semester();
                    int year = Integer.parseInt((String) comboBoxYear.getSelectedItem());
                    switch (comboBoxSeason.getSelectedIndex()) {
                        case 0: semester = new Semester(year, Season.Spring); break;
                        case 1: semester = new Semester(year, Season.Summer); break;
                        case 2: semester = new Semester(year, Season.Fall); break;
                    }
                    systemDatabase.addCourse(client.getUsername(), courseNum, courseName, semester);
                } else {
                    JOptionPane.showMessageDialog(this, "Course number already exists!", "EXISTED", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Empty input!", "MISSING CONTENT", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}