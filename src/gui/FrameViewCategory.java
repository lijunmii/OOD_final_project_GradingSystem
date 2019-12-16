package gui;

import backend.*;

import javax.swing.*;

public class FrameViewCategory extends JFrame {
    private JPanel panel = new JPanel();

    FrameViewCategory() {}
    FrameViewCategory(Course course, String category) {
        // layout here
        ;

        add(panel);
        setTitle("View '" + category + "' Category");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
