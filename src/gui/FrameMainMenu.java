package gui;

import backend.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FrameMainMenu extends JFrame {
    private Client client = null;
    private boolean login = false;
    private SystemDatabase systemDatabase;

    private JPanel panel = new JPanel();

    private JLabel labelWelcome = new JLabel("Welcome! Please", JLabel.CENTER);
    private JButton buttonLogin = new JButton("Login");
    private JLabel labelRegister = new JLabel("Not enrolled yet?", JLabel.CENTER);
    private JButton buttonRegister = new JButton("Register");

    private JPanel panel_2;
    private String[] columnNames = {"Course ID", "Course Name", "Semester"};
    private Object[][] courseData = new Object[0][3];
    private JTable listCourses = new JTable(courseData, columnNames);
    private JScrollPane scrollPaneCourses;

    private JButton buttonOpenCourse = new JButton("Open course page");
    private JButton buttonAddCourse = new JButton("Add new course");
    private JButton buttonDelCourse = new JButton("Delete course");

    private FrameLogin frameLogin = new FrameLogin();
    private FrameRegister frameRegister = new FrameRegister();
    private FrameCourse frameCourse = new FrameCourse();
    private FrameAddCourse frameAddCourse = new FrameAddCourse();

    public FrameMainMenu(SystemDatabase systemDatabase) {
        this.systemDatabase = systemDatabase;
        panel.setLayout(new BorderLayout());

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
        panel.add(panel_1, BorderLayout.NORTH);

        panel_2 = new JPanel(); {
            panel_2.setLayout(new GridLayout(1, 1));
            panel_2.setBorder(BorderFactory.createEtchedBorder());

            scrollPaneCourses = new JScrollPane(listCourses);
            scrollPaneCourses.setBorder(BorderFactory.createTitledBorder("Courses"));
            panel_2.add(scrollPaneCourses);
        }
        panel.add(panel_2, BorderLayout.CENTER);

        JPanel panel_3 = new JPanel(); {
            panel_3.setBorder(BorderFactory.createEtchedBorder());
            panel_3.setLayout(new GridLayout(3, 1));

            buttonOpenCourse.setPreferredSize(new Dimension(180, 33));
            panel_3.add(buttonOpenCourse);

            buttonAddCourse.setPreferredSize(new Dimension(180, 33));
            panel_3.add(buttonAddCourse);

            buttonDelCourse.setPreferredSize(new Dimension(180, 33));
            panel_3.add(buttonDelCourse);
        }
        panel.add(panel_3, BorderLayout.SOUTH);

        add(panel);
        setSize(360, 500);
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
                    String courseNum = (String) listCourses.getValueAt(listCourses.getSelectedRow(), 0);
                    String semester = listCourses.getValueAt(listCourses.getSelectedRow(), 2).toString();
                    Course course = client.getCourse(courseNum, semester);
                    frameCourse = new FrameCourse(systemDatabase, course);
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
                    frameAddCourse = new FrameAddCourse(this, systemDatabase);
                    frameAddCourse.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frameAddCourse.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Please login first.", "NO LOGIN", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        buttonDelCourse.addActionListener(e -> { // delete course
            if (!subWindowExist()) {
                if (login) {
                    if (listCourses.getRowCount() > 0) {
                        int[] rows = listCourses.getSelectedRows();
                        List<String> courseNums = new ArrayList<>();
                        List<String> semesters = new ArrayList<>();
                        for (int i = 0; i < rows.length; i++) {
                            courseNums.add((String) listCourses.getValueAt(rows[i], 0));
                            semesters.add(listCourses.getValueAt(rows[i], 2).toString());
                        }
                        int result = JOptionPane.showConfirmDialog(null, "Delete course(s)?", "CONFIRM", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            systemDatabase.delCourse(client.getUsername(), courseNums, semesters);
                            updateData();
                            updateTable();
                            JOptionPane.showMessageDialog(this, "Course(s) deleted.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "No course selected.", "NO COURSE", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please login first.", "NO LOGIN", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    public void setClient(Client currentClient) {
        client = currentClient;
    }

    public Client getClient() {
        return client;
    }

    public void successLogin(Client currentClient) {
        labelWelcome.setText("Welcome! You can");
        buttonLogin.setText("Logout");
        labelRegister.setText("Select your courses below.");
        buttonRegister.setVisible(false);
        client = currentClient;

        updateData();
        updateTable();

        login = true;
    }

    public void successLogout() {
        labelWelcome.setText("Welcome! Please");
        buttonLogin.setText("Login");
        labelRegister.setText("Not enrolled yet?");
        buttonRegister.setVisible(true);
        client = null;

        courseData = new Object[0][3];
        listCourses = new JTable(courseData, columnNames);
        updateTable();

        login = false;
    }

    public void updateData() {
        courseData = new Object[client.getCourses().size()][3];
        for (int i = 0; i < client.getCourses().size(); i++) {
            Course course = client.getCourses().get(i);
            courseData[i][0] = course.getCourseNum();
            courseData[i][1] = course.getCourseName();
            courseData[i][2] = course.getSemester();
        }
        listCourses = new JTable(courseData, columnNames);

        TableModel model = new DefaultTableModel(courseData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 ? false : true;
            }
        };
        model.addTableModelListener(e -> {
            int row = e.getFirstRow();
            String courseNum = (String) listCourses.getValueAt(listCourses.getEditingRow(), 0);
            String courseName = (String) listCourses.getValueAt(listCourses.getEditingRow(), 1);
            String username = client.getUsername();
            systemDatabase.updateCourseInfo(username, row, courseNum, courseName);
            //listCourses.changeSelection(listCourses.getEditingRow(), listCourses.getEditingColumn(), false, false);
        });
        RowSorter<TableModel> sorter = new TableRowSorter<>(model);

        listCourses.setModel(model);
        listCourses.setRowSorter(sorter);

        listCourses.setRowHeight(25);

        listCourses.getTableHeader().setReorderingAllowed(false);

        listCourses.setShowGrid(true);
        listCourses.setShowHorizontalLines(true);
        listCourses.setShowVerticalLines(true);

        if (listCourses.getRowCount() > 0) {
            listCourses.setRowSelectionInterval(0, 0);
        }
    }

    public void updateTable() {
        panel_2.removeAll();
        panel_2.setLayout(new GridLayout(1, 1));
        panel_2.setBorder(BorderFactory.createEtchedBorder());
        scrollPaneCourses = new JScrollPane(listCourses);
        scrollPaneCourses.setBorder(BorderFactory.createTitledBorder("Courses"));
        panel_2.add(scrollPaneCourses);
        panel_2.updateUI();
    }

    private boolean subWindowExist() { // one sub window at the same time
        return (frameLogin.isVisible() ||
            frameRegister.isVisible() ||
            frameCourse.isVisible() ||
            frameAddCourse.isVisible());
    }
}
