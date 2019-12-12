import backend.*;
import gui.*;
import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        SystemDatabase systemDatabase = new SystemDatabase("");
        FrameMainMenu frameMainMenu = new FrameMainMenu(systemDatabase);
        frameMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMainMenu.setVisible(true);
    }
}
