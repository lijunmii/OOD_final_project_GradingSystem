package gui;

import backend.*;

import javax.swing.*;

public class FrameViewUndergrad extends JFrame {
    private JPanel panel = new JPanel();

    FrameViewUndergrad() {}
    FrameViewUndergrad(Course course) {
        // layout here
        ;

        add(panel);
        setTitle("View Undergraduate Students");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
