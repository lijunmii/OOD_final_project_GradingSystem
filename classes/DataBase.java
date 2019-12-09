import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author Group 21 CS 591 Java OOD
 * @date Dec. 08 2019
 *
 */
public class DataBase {

    /** database credentials including host, username, password */
    private static final String url = "jdbc:mysql://localhost:3306/gradingSystem?useSSL=false";
    private static final String user = "root";
    private static final String password = "pass";

    private static Connection conn;
    private static Statement stmt;
    private ResultSet res;

    /**
     * Connection to the local Database
     */
    public void connectDatabase() {
        try {
            // opening db connection to MySQL server
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Disconnect to the local Database
     */
    public void disconnectDatabase() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void writeToDatabase();

    private void readFromDatabase();
}
