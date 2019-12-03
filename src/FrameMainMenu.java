import javax.swing.*;
import java.awt.*;

public class FrameMainMenu extends JFrame {
    private Client client = null;
    private boolean login = false;

    private JPanel panel = new JPanel();

    private JLabel labelWelcome = new JLabel("Welcome! Please", JLabel.CENTER);
    private JButton buttonLogin = new JButton("Login");
    private JLabel labelRegister = new JLabel("Not enrolled yet?", JLabel.CENTER);
    private JButton buttonRegister = new JButton("Register");

    private JList listCourses = new JList();
    private JScrollPane scrollPaneCourses = new JScrollPane();

    private JButton buttonOpenCourse = new JButton("Open course page");
    private JButton buttonAddCourse = new JButton("Add new course");
    private JButton buttonDelCourse = new JButton("Delete course");

    private FrameLogin frameLogin = new FrameLogin();
    private FrameRegister frameRegister = new FrameRegister();
    private FrameCourse frameCourse = new FrameCourse();
    private FrameAddCourse frameAddCourse = new FrameAddCourse();

    FrameMainMenu(SystemDatabase systemDatabase) {
        panel.setLayout(new GridLayout(3, 1));

        JPanel panel_1 = new JPanel(); {
            panel_1.setLayout(new GridLayout(3, 1));
            panel_1.setBorder(BorderFactory.createEtchedBorder());
            panel_1.setBackground(Color.WHITE);

            JLabel title = new JLabel("<html><font color='red'>GRADING SYSTEM</font>™</html>", JLabel.CENTER);
            title.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.BLACK));
            panel_1.add(title);

            JPanel panel_1_2 = new JPanel(); {
                panel_1_2.add(labelWelcome);
                panel_1_2.add(buttonLogin);
                panel_1_2.add(new JLabel("here.", JLabel.CENTER));
            }
            panel_1.add(panel_1_2);

            JPanel panel_1_3 = new JPanel(); {
                panel_1_3.add(labelRegister);
                panel_1_3.add(buttonRegister);
            }
            panel_1.add(panel_1_3);
        }
        panel.add(panel_1);

        JPanel panel_2 = new JPanel(); {
            panel_2.setLayout(new GridLayout(1, 1));
            panel_2.setBorder(BorderFactory.createEtchedBorder());

            scrollPaneCourses = new JScrollPane(listCourses);
            scrollPaneCourses.setBorder(BorderFactory.createTitledBorder("Courses"));
            panel_2.add(scrollPaneCourses);
        }
        panel.add(panel_2);

        JPanel panel_3 = new JPanel(); {
            panel_3.setBorder(BorderFactory.createEtchedBorder());

            buttonOpenCourse.setPreferredSize(new Dimension(180, 33));
            panel_3.add(buttonOpenCourse);

            buttonAddCourse.setPreferredSize(new Dimension(180, 33));
            panel_3.add(buttonAddCourse);

            buttonDelCourse.setPreferredSize(new Dimension(180, 33));
            panel_3.add(buttonDelCourse);
        }
        panel.add(panel_3);

        add(panel);
        setSize(250, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        buttonLogin.addActionListener(e -> { // login
            if (!subWindowExist() && !login) {
                frameLogin = new FrameLogin(this, systemDatabase);
                frameLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameLogin.setVisible(true);
            } else if (!subWindowExist() && login) { // logout
                client = null;
                successLogout();
            }
        });

        buttonRegister.addActionListener(e -> { // register
            if (!subWindowExist() && !login) {
                frameRegister = new FrameRegister(systemDatabase);
                frameRegister.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameRegister.setVisible(true);
            }
        });

        buttonOpenCourse.addActionListener(e -> { // open course page
            if (!subWindowExist()) {
                if (login) {
                    frameCourse = new FrameCourse(client, systemDatabase);
                    frameCourse.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frameCourse.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Please login first.", "NO LOGIN", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        buttonAddCourse.addActionListener(e -> { // add new course
            if (!subWindowExist()) {
                if (login) {
                    //todo
                } else {
                    JOptionPane.showMessageDialog(this, "Please login first.", "NO LOGIN", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        buttonDelCourse.addActionListener(e -> { // delete course
            if (!subWindowExist()) {
                if (login) {
                    //todo
                } else {
                    JOptionPane.showMessageDialog(this, "Please login first.", "NO LOGIN", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public void successLogin(Client currentClient) {
        labelWelcome.setText("Welcome! You can");
        buttonLogin.setText("Logout");
        labelRegister.setText("Select your courses below.");
        buttonRegister.setVisible(false);
        client = currentClient;
        //todo: set course list
        login = true;
    }

    public void successLogout() {
        labelWelcome.setText("Welcome! Please");
        buttonLogin.setText("Login");
        labelRegister.setText("Not enrolled yet?");
        buttonRegister.setVisible(true);
        client = null;
        login = false;
    }

    private boolean subWindowExist() { // one sub window at the same time
        return (frameLogin.isVisible() ||
                frameRegister.isVisible() ||
                frameCourse.isVisible() ||
                frameAddCourse.isVisible());
    }
}
