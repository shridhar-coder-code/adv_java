import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.Border;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

public class Add {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 950);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(255, 255, 0));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(30, 20, 20, 20));
        headerPanel.setBackground(new Color(255, 255, 0));

        JLabel titleLabel = new JLabel("Add Student Details", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        titleLabel.setForeground(Color.RED);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(titleLabel);

        JPanel panel = new JPanel(new GridLayout(1, 2, 40, 0));
        panel.setBorder(new EmptyBorder(20, 60, 30, 60));
        panel.setBackground(new Color(255, 255, 0));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(255, 255, 0));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(new Color(255, 255, 0));

        JLabel label = new JLabel("Name:");
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField textField = new JTextField();
        textField.setMaximumSize(new Dimension(400, 40));
        textField.setAlignmentX(Component.LEFT_ALIGNMENT);
        textField.setBorder(new RoundedBorder(10, Color.BLACK));

        JLabel label2 = new JLabel("USN:");
        label2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label2.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField textField1 = new JTextField();
        textField1.setMaximumSize(new Dimension(400, 40));
        textField1.setAlignmentX(Component.LEFT_ALIGNMENT);
        textField1.setBorder(new RoundedBorder(10, Color.BLACK));

        JLabel label3 = new JLabel("SEM:");
        label3.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label3.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField textField2 = new JTextField();
        textField2.setMaximumSize(new Dimension(400, 40));
        textField2.setAlignmentX(Component.LEFT_ALIGNMENT);
        textField2.setBorder(new RoundedBorder(10, Color.BLACK));

        JLabel label4 = new JLabel("Email:");
        label4.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label4.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField textField3 = new JTextField();
        textField3.setMaximumSize(new Dimension(400, 40));
        textField3.setAlignmentX(Component.LEFT_ALIGNMENT);
        textField3.setBorder(new RoundedBorder(10, Color.BLACK));

        JLabel label6 = new JLabel("City:");
        label6.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label6.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField cityField = new JTextField();
        cityField.setMaximumSize(new Dimension(400, 40));
        cityField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label7 = new JLabel("Pin Code:");
        label7.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label7.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField pinCodeField = new JTextField();
        pinCodeField.setMaximumSize(new Dimension(400, 40));
        pinCodeField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label8 = new JLabel("Date of Birth (DD-MM-YYYY):");
        label8.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label8.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField dobField = new JTextField();
        dobField.setMaximumSize(new Dimension(400, 40));
        dobField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label9 = new JLabel("Gender:");
        label9.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label9.setAlignmentX(Component.LEFT_ALIGNMENT);

        JComboBox<String> genderBox = new JComboBox<>(new String[] { "Select", "Male", "Female", "Other" });
        genderBox.setMaximumSize(new Dimension(400, 40));
        genderBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label10 = new JLabel("Mobile Number:");
        label10.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label10.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField mobileField = new JTextField();
        mobileField.setMaximumSize(new Dimension(400, 40));
        mobileField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label13 = new JLabel("Branch:");
        label13.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label13.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField branchField = new JTextField();
        branchField.setMaximumSize(new Dimension(400, 40));
        branchField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label14 = new JLabel("Year:");
        label14.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label14.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField yearField = new JTextField();
        yearField.setMaximumSize(new Dimension(400, 40));
        yearField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label15 = new JLabel("Degree Program:");
        label15.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label15.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField degreeProgramField = new JTextField();
        degreeProgramField.setMaximumSize(new Dimension(400, 40));
        degreeProgramField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label17 = new JLabel("Department ID:");
        label17.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label17.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField departmentIdField = new JTextField();
        departmentIdField.setMaximumSize(new Dimension(400, 40));
        departmentIdField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label18 = new JLabel("Current Semester Subjects:");
        label18.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label18.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea subjectsArea = new JTextArea(3, 20);
        subjectsArea.setLineWrap(true);
        subjectsArea.setWrapStyleWord(true);
        JScrollPane subjectsScroll = new JScrollPane(subjectsArea);
        subjectsScroll.setMaximumSize(new Dimension(400, 80));
        subjectsScroll.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label19 = new JLabel("Attendance Percent:");
        label19.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label19.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField attendanceField = new JTextField();
        attendanceField.setMaximumSize(new Dimension(400, 40));
        attendanceField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField[] allTextFields = {
                textField, textField1, textField2, textField3, cityField, pinCodeField, dobField, mobileField,
                branchField, yearField, degreeProgramField, departmentIdField, attendanceField
        };

        for (JTextField tf : allTextFields) {
            tf.setBorder(new RoundedBorder(10, Color.BLUE));
        }

        JTextArea[] allTextAreas = { subjectsArea };
        for (JTextArea ta : allTextAreas) {
            ta.setBorder(new RoundedBorder(10, Color.GRAY));
        }

        JLabel[] allLabels = {
                label, label2, label3, label4, label6, label7, label8, label9, label10,
                label13, label14, label15, label17, label18, label19
        };
        Font boldLabelFont = new Font("SansSerif", Font.BOLD, 15);
        Color darkLabelColor = new Color(25, 25, 25);
        for (JLabel currentLabel : allLabels) {
            currentLabel.setFont(boldLabelFont);
            currentLabel.setForeground(darkLabelColor);
        }

        JButton btn = new JButton("Add details");
        Dimension buttonSize = new Dimension(200, 60);
        btn.setPreferredSize(buttonSize);
        btn.setMaximumSize(buttonSize);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setFocusPainted(false);

        JButton backBtn = new JButton("Back to Dashboard");
        backBtn.setPreferredSize(buttonSize);
        backBtn.setMaximumSize(buttonSize);
        backBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        backBtn.setFocusPainted(false);

        Color normalButtonColor = new Color(255, 204, 204);
        Color hoverButtonColor = new Color(170, 0, 0);

        btn.setBorder(new LineBorder(new Color(180, 80, 80), 2, false));
        btn.setBorderPainted(true);
        btn.setBackground(normalButtonColor);
        btn.setForeground(Color.BLACK);
        btn.setContentAreaFilled(true);
        btn.setRolloverEnabled(true);
        btn.setOpaque(true);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(hoverButtonColor);
                btn.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(normalButtonColor);
                btn.setForeground(Color.BLACK);
            }
        });

        backBtn.setBorder(new LineBorder(new Color(180, 80, 80), 2, false));
        backBtn.setBorderPainted(true);
        backBtn.setBackground(normalButtonColor);
        backBtn.setForeground(Color.BLACK);
        backBtn.setContentAreaFilled(true);
        backBtn.setRolloverEnabled(true);
        backBtn.setOpaque(true);
        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backBtn.setBackground(hoverButtonColor);
                backBtn.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backBtn.setBackground(normalButtonColor);
                backBtn.setForeground(Color.BLACK);
            }
        });

        btn.addActionListener(e -> {
            String name = textField.getText().trim();
            String usn = textField1.getText().trim();
            String sem = textField2.getText().trim();
            String email = textField3.getText().trim();
            String city = cityField.getText().trim();
            String pinCode = pinCodeField.getText().trim();
            String dob = dobField.getText().trim();
            String gender = String.valueOf(genderBox.getSelectedItem());
            String mobile = mobileField.getText().trim();
            String branch = branchField.getText().trim();
            String year = yearField.getText().trim();
            String degreeProgram = degreeProgramField.getText().trim();
            String departmentId = departmentIdField.getText().trim();
            String subjects = subjectsArea.getText().trim();
            String attendance = attendanceField.getText().trim();

            if (name.isEmpty() || usn.isEmpty() || sem.isEmpty() || email.isEmpty() || city.isEmpty()
                    || pinCode.isEmpty()
                    || dob.isEmpty() || mobile.isEmpty() || branch.isEmpty() || year.isEmpty()
                    || degreeProgram.isEmpty()
                    || departmentId.isEmpty() || subjects.isEmpty() || attendance.isEmpty()
                    || "Select".equals(gender)) {
                javax.swing.JOptionPane.showMessageDialog(frame, "Please fill all fields before adding.");
                return;
            }

            String createTableSql = "CREATE TABLE IF NOT EXISTS sdata ("
                    + "`USN` VARCHAR(20) PRIMARY KEY, "
                    + "`Name` VARCHAR(100) NOT NULL, "
                    + "`SEM` VARCHAR(10) NOT NULL, "
                    + "`Email` VARCHAR(120) NOT NULL, "
                    + "`City` VARCHAR(80) NOT NULL, "
                    + "`Pin_Code` VARCHAR(20) NOT NULL, "
                    + "`DOB` VARCHAR(30) NOT NULL, "
                    + "`Gender` VARCHAR(20) NOT NULL, "
                    + "`Mobile_Number` VARCHAR(20) NOT NULL, "
                    + "`Branch` VARCHAR(80) NOT NULL, "
                    + "`Year` VARCHAR(10) NOT NULL, "
                    + "`Degree_Program` VARCHAR(100) NOT NULL, "
                    + "`Department_ID` VARCHAR(30) NOT NULL, "
                    + "`Current_Semester_Subjects` TEXT NOT NULL, "
                    + "`Attendance_Percent` VARCHAR(10) NOT NULL"
                    + ")";

            String insertSql = "INSERT INTO sdata "
                    + "(`USN`, `Name`, `SEM`, `Email`, `City`, `Pin_Code`, `DOB`, `Gender`, `Mobile_Number`, `Branch`, `Year`, `Degree_Program`, `Department_ID`, `Current_Semester_Subjects`, `Attendance_Percent`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = new dbConnect().getConnection();
                    PreparedStatement createStmt = conn.prepareStatement(createTableSql);
                    PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

                createStmt.execute();

                insertStmt.setString(1, usn);
                insertStmt.setString(2, name);
                insertStmt.setString(3, sem);
                insertStmt.setString(4, email);
                insertStmt.setString(5, city);
                insertStmt.setString(6, pinCode);
                insertStmt.setString(7, dob);
                insertStmt.setString(8, gender);
                insertStmt.setString(9, mobile);
                insertStmt.setString(10, branch);
                insertStmt.setString(11, year);
                insertStmt.setString(12, degreeProgram);
                insertStmt.setString(13, departmentId);
                insertStmt.setString(14, subjects);
                insertStmt.setString(15, attendance);

                insertStmt.executeUpdate();
                javax.swing.JOptionPane.showMessageDialog(frame, "Student added successfully.");
            } catch (SQLException | ClassNotFoundException ex) {
                javax.swing.JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage());
            }
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
            Dashboard.main(new String[0]);
        });

        leftPanel.add(label);
        leftPanel.add(textField);
        leftPanel.add(Box.createVerticalStrut(15));

        leftPanel.add(label2);
        leftPanel.add(textField1);
        leftPanel.add(Box.createVerticalStrut(15));

        leftPanel.add(label3);
        leftPanel.add(textField2);
        leftPanel.add(Box.createVerticalStrut(15));

        leftPanel.add(label4);
        leftPanel.add(textField3);
        leftPanel.add(Box.createVerticalStrut(15));

        leftPanel.add(label6);
        leftPanel.add(cityField);
        leftPanel.add(Box.createVerticalStrut(15));

        leftPanel.add(label7);
        leftPanel.add(pinCodeField);
        leftPanel.add(Box.createVerticalStrut(15));

        leftPanel.add(label8);
        leftPanel.add(dobField);
        leftPanel.add(Box.createVerticalStrut(15));

        leftPanel.add(label9);
        leftPanel.add(genderBox);
        leftPanel.add(Box.createVerticalStrut(15));

        leftPanel.add(label10);
        leftPanel.add(mobileField);
        leftPanel.add(Box.createVerticalStrut(15));

        rightPanel.add(label13);
        rightPanel.add(branchField);
        rightPanel.add(Box.createVerticalStrut(15));

        rightPanel.add(label14);
        rightPanel.add(yearField);
        rightPanel.add(Box.createVerticalStrut(15));

        rightPanel.add(label15);
        rightPanel.add(degreeProgramField);
        rightPanel.add(Box.createVerticalStrut(15));

        rightPanel.add(label17);
        rightPanel.add(departmentIdField);
        rightPanel.add(Box.createVerticalStrut(15));

        rightPanel.add(label18);
        rightPanel.add(subjectsScroll);
        rightPanel.add(Box.createVerticalStrut(15));

        rightPanel.add(label19);
        rightPanel.add(attendanceField);
        rightPanel.add(Box.createVerticalStrut(15));

        rightPanel.add(btn);
        rightPanel.add(Box.createVerticalStrut(15));
        rightPanel.add(backBtn);
        rightPanel.add(Box.createVerticalStrut(15));

        panel.add(leftPanel);
        panel.add(rightPanel);

        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
