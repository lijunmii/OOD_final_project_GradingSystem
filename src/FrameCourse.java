import javax.swing.*;

public class FrameCourse extends JFrame {
    private JPanel panel = new JPanel();

    FrameCourse() {}
    FrameCourse(Client client, SystemDatabase systemDatabase) {
        ;

        add(panel);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(false);

        ;
    }
}
