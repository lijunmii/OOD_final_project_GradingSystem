package gui;

import backend.*;

import javax.swing.*;

public class FrameViewGraduate extends JFrame {
    private JPanel panel = new JPanel();

    FrameViewGraduate() {}
    FrameViewGraduate(Course course) {
        // layout here
        ;

        add(panel);
        setTitle("View Graduate Students");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
