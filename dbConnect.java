import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class dbConnect {
    private static Connection mycon = null;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String db = "adv_java", user = "root", pass = "Ss@15022005";
        String url = "jdbc:mysql://localhost:3306/" + db;
        Class.forName("com.mysql.cj.jdbc.Driver");
        mycon = DriverManager.getConnection(url, user, pass);
        return mycon;
    }

    public static void checkLogin(String user, String pass, JFrame frame) {
        String sql = "SELECT * FROM students WHERE username = ? AND pass = ?";

        try (Connection conn = new dbConnect().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user);
            pstmt.setString(2, pass);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    frame.dispose();
                    Dashboard.main(new String[0]);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password.");
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage());
        }
    }
}
