import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;

public class Update {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 920);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(255, 255, 0));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(30, 20, 20, 20));
        headerPanel.setBackground(new Color(255, 255, 0));

        JLabel titleLabel = new JLabel("Update Student Record", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        titleLabel.setForeground(Color.RED);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(titleLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(35, 70, 35, 70));
        mainPanel.setBackground(new Color(255, 255, 0));

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setBackground(new Color(255, 255, 0));

        JLabel usnLabel = new JLabel("Enter USN:");
        usnLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        usnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JComboBox<String> usnCombo = new JComboBox<>();
        usnCombo.setEditable(true);
        usnCombo.setMaximumSize(new Dimension(400, 40));
        usnCombo.setAlignmentX(Component.LEFT_ALIGNMENT);
        usnCombo.setBorder(new RoundedBorder(10, Color.BLUE));
        usnCombo.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton();
                button.setVisible(false);
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }
        });
        refreshUsnDropdown(usnCombo);

        JLabel statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusLabel.setForeground(new Color(170, 0, 0));

        JButton searchButton = new JButton("Search");
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(searchButton);

        searchPanel.add(usnLabel);
        searchPanel.add(Box.createVerticalStrut(10));
        searchPanel.add(usnCombo);
        searchPanel.add(Box.createVerticalStrut(10));
        searchPanel.add(statusLabel);
        searchPanel.add(Box.createVerticalStrut(12));
        searchPanel.add(searchButton);

        // Update Fields Panel (initially hidden)
        JPanel updatePanel = new JPanel();
        updatePanel.setLayout(new BorderLayout());
        updatePanel.setBorder(new EmptyBorder(20, 70, 20, 70));
        updatePanel.setBackground(new Color(255, 255, 0));
        updatePanel.setVisible(false);

        JPanel formPanel = new JPanel(new GridLayout(1, 2, 28, 0));
        formPanel.setBackground(new Color(255, 255, 0));

        JPanel leftForm = new JPanel();
        leftForm.setLayout(new BoxLayout(leftForm, BoxLayout.Y_AXIS));
        leftForm.setBackground(new Color(255, 255, 0));

        JPanel rightForm = new JPanel();
        rightForm.setLayout(new BoxLayout(rightForm, BoxLayout.Y_AXIS));
        rightForm.setBackground(new Color(255, 255, 0));

        JTextField usnDisplayField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField semField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField cityField = new JTextField();
        JTextField pinCodeField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField mobileField = new JTextField();
        JTextField branchField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField degreeProgramField = new JTextField();
        JTextField departmentIdField = new JTextField();
        JTextField attendanceField = new JTextField();

        JTextArea subjectsArea = new JTextArea(3, 20);
        subjectsArea.setLineWrap(true);
        subjectsArea.setWrapStyleWord(true);
        subjectsArea.setMaximumSize(new Dimension(560, 90));
        subjectsArea.setBorder(new RoundedBorder(10, Color.BLUE));

        setupUpdateField(new JLabel("USN:"), usnDisplayField, leftForm, false);
        setupUpdateField(new JLabel("Name:"), nameField, leftForm, false);
        setupUpdateField(new JLabel("SEM:"), semField, leftForm, true);
        setupUpdateField(new JLabel("Email:"), emailField, leftForm, true);
        setupUpdateField(new JLabel("City:"), cityField, leftForm, false);
        setupUpdateField(new JLabel("Pin Code:"), pinCodeField, leftForm, false);
        setupUpdateField(new JLabel("DOB:"), dobField, leftForm, false);
        setupUpdateField(new JLabel("Gender:"), genderField, leftForm, false);

        setupUpdateField(new JLabel("Mobile Number:"), mobileField, rightForm, true);
        setupUpdateField(new JLabel("Branch:"), branchField, rightForm, false);
        setupUpdateField(new JLabel("Year:"), yearField, rightForm, true);
        setupUpdateField(new JLabel("Degree Program:"), degreeProgramField, rightForm, false);
        setupUpdateField(new JLabel("Department ID:"), departmentIdField, rightForm, false);
        setupUpdateField(new JLabel("Current Semester Subjects:"), subjectsArea, rightForm);
        setupUpdateField(new JLabel("Attendance Percent:"), attendanceField, rightForm, true);

        subjectsArea.setEditable(true);

        formPanel.add(leftForm);
        formPanel.add(rightForm);

        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));
        actionsPanel.setBackground(new Color(255, 255, 0));

        JLabel editableHint = new JLabel(
                "Editable: SEM, Email, Mobile Number, Year, Current Semester Subjects, Attendance Percent");
        editableHint.setFont(new Font("SansSerif", Font.BOLD, 13));
        editableHint.setForeground(new Color(120, 0, 0));
        editableHint.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionsPanel.add(Box.createVerticalStrut(8));
        actionsPanel.add(editableHint);

        JButton updateButton = new JButton("Update");
        updateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(updateButton);
        JButton rulesButton = new JButton("Validation Rules");
        rulesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rulesButton.setPreferredSize(new Dimension(220, 50));
        rulesButton.setMaximumSize(new Dimension(220, 50));
        styleButton(rulesButton);
        rulesButton.addActionListener(ev -> {
            String rules = Validation.getAllConstraints();
            JTextArea ta = new JTextArea(rules);
            ta.setEditable(false);
            ta.setLineWrap(true);
            ta.setWrapStyleWord(true);
            ta.setBorder(new EmptyBorder(10, 10, 10, 10));
            JScrollPane sp = new JScrollPane(ta);
            sp.setPreferredSize(new Dimension(620, 320));
            JOptionPane.showMessageDialog(frame, sp, "Validation Rules", JOptionPane.INFORMATION_MESSAGE);
        });
        JButton backButton = new JButton("Back to Dashboard");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setPreferredSize(new Dimension(220, 50));
        backButton.setMaximumSize(new Dimension(220, 50));
        styleButton(backButton);

        actionsPanel.add(Box.createVerticalStrut(20));
        actionsPanel.add(rulesButton);
        actionsPanel.add(Box.createVerticalStrut(12));
        actionsPanel.add(updateButton);
        actionsPanel.add(Box.createVerticalStrut(12));
        actionsPanel.add(backButton);

        updatePanel.add(formPanel, BorderLayout.CENTER);
        updatePanel.add(actionsPanel, BorderLayout.SOUTH);

        mainPanel.add(searchPanel);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(updatePanel);

        searchButton.addActionListener(e -> {
            String usn = getTypedUsn(usnCombo);
            if (usn.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter USN.");
                return;
            }
            UsnHistory.add(usn);
            refreshUsnDropdown(usnCombo);

            String sql = "SELECT `USN`, `Name`, `SEM`, `Email`, `City`, `Pin_Code`, `DOB`, `Gender`, "
                    + "`Mobile_Number`, `Branch`, `Year`, `Degree_Program`, `Department_ID`, "
                    + "`Current_Semester_Subjects`, `Attendance_Percent` FROM sdata WHERE `USN` = ?";

            try (Connection conn = new dbConnect().getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, usn);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        usnDisplayField.setText(rs.getString("USN"));
                        nameField.setText(rs.getString("Name"));
                        semField.setText(rs.getString("SEM"));
                        emailField.setText(rs.getString("Email"));
                        cityField.setText(rs.getString("City"));
                        pinCodeField.setText(rs.getString("Pin_Code"));
                        dobField.setText(rs.getString("DOB"));
                        genderField.setText(rs.getString("Gender"));
                        mobileField.setText(rs.getString("Mobile_Number"));
                        branchField.setText(rs.getString("Branch"));
                        yearField.setText(rs.getString("Year"));
                        degreeProgramField.setText(rs.getString("Degree_Program"));
                        departmentIdField.setText(rs.getString("Department_ID"));
                        subjectsArea.setText(rs.getString("Current_Semester_Subjects"));
                        attendanceField.setText(rs.getString("Attendance_Percent"));

                        statusLabel.setText("Student found.");
                        statusLabel.setForeground(new Color(0, 120, 0));
                        updatePanel.setVisible(true);
                    } else {
                        statusLabel.setText("Student not present.");
                        statusLabel.setForeground(new Color(170, 0, 0));
                        updatePanel.setVisible(false);
                    }
                }
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage());
            }
        });

        updateButton.addActionListener(e -> {
            String usn = usnDisplayField.getText().trim();
            if (usn.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Search a student first.");
                return;
            }

            // Validate editable fields
            String sem = semField.getText().trim();
            String email = emailField.getText().trim();
            String mobile = mobileField.getText().trim();
            String year = yearField.getText().trim();
            String subjects = subjectsArea.getText().trim();
            String attendance = attendanceField.getText().trim();
            StringBuilder errors = new StringBuilder();
            String m;
            m = (!sem.isEmpty()) ? Validation.getFieldErrorMessage("sem", sem) : "";
            if (!m.isEmpty())
                errors.append(m).append("\n");
            m = (!email.isEmpty()) ? Validation.getFieldErrorMessage("email", email) : "";
            if (!m.isEmpty())
                errors.append(m).append("\n");
            m = (!mobile.isEmpty()) ? Validation.getFieldErrorMessage("mobile", mobile) : "";
            if (!m.isEmpty())
                errors.append(m).append("\n");
            m = (!year.isEmpty()) ? Validation.getFieldErrorMessage("year", year) : "";
            if (!m.isEmpty())
                errors.append(m).append("\n");
            m = (!subjects.isEmpty()) ? Validation.getFieldErrorMessage("subjects", subjects) : "";
            if (!m.isEmpty())
                errors.append(m).append("\n");
            m = (!attendance.isEmpty()) ? Validation.getFieldErrorMessage("attendance", attendance) : "";
            if (!m.isEmpty())
                errors.append(m).append("\n");

            if (errors.length() > 0) {
                JTextArea ta = new JTextArea(errors.toString());
                ta.setEditable(false);
                ta.setLineWrap(true);
                ta.setWrapStyleWord(true);
                ta.setBorder(new EmptyBorder(10, 10, 10, 10));
                JScrollPane sp = new JScrollPane(ta);
                sp.setPreferredSize(new Dimension(520, 220));
                JOptionPane.showMessageDialog(frame, sp, "Validation Errors", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String updateSql = "UPDATE sdata SET `SEM` = ?, `Email` = ?, `Mobile_Number` = ?, `Year` = ?, "
                    + "`Current_Semester_Subjects` = ?, `Attendance_Percent` = ? WHERE `USN` = ?";

            try (Connection conn = new dbConnect().getConnection();
                    PreparedStatement ps = conn.prepareStatement(updateSql)) {
                ps.setString(1, semField.getText().trim());
                ps.setString(2, emailField.getText().trim());
                ps.setString(3, mobileField.getText().trim());
                ps.setString(4, yearField.getText().trim());
                ps.setString(5, subjectsArea.getText().trim());
                ps.setString(6, attendanceField.getText().trim());
                ps.setString(7, usn);

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(frame, "Successfully data updated.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Student not present.");
                }
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage());
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            Dashboard.main(new String[0]);
        });

        JScrollPane mainScrollPane = new JScrollPane(mainPanel);
        mainScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        mainScrollPane.getViewport().setBackground(new Color(255, 255, 0));
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(14);
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(mainScrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static String getTypedUsn(JComboBox<String> usnCombo) {
        Object editorValue = usnCombo.getEditor().getItem();
        return editorValue == null ? "" : editorValue.toString().trim();
    }

    private static void refreshUsnDropdown(JComboBox<String> usnCombo) {
        String current = getTypedUsn(usnCombo);
        usnCombo.removeAllItems();
        for (String usn : UsnHistory.getRecent()) {
            usnCombo.addItem(usn);
        }
        usnCombo.getEditor().setItem(current);
    }

    private static void setupUpdateField(JLabel label, Component field, JPanel panel) {
        label.setFont(new Font("SansSerif", Font.BOLD, 15));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        if (field instanceof JTextField) {
            field.setMaximumSize(new Dimension(560, 40));
            ((JTextField) field).setBorder(new RoundedBorder(10, Color.BLUE));
        } else if (field instanceof JTextArea) {
            field.setMaximumSize(new Dimension(560, 90));
            ((JTextArea) field).setBorder(new RoundedBorder(10, Color.BLUE));
        }
        if (field instanceof JComponent) {
            ((JComponent) field).setAlignmentX(Component.LEFT_ALIGNMENT);
        }
        panel.add(label);
        panel.add(Box.createVerticalStrut(5));
        panel.add(field);
        panel.add(Box.createVerticalStrut(15));
    }

    private static void setupUpdateField(JLabel label, JTextField field, JPanel panel, boolean editable) {
        field.setEditable(editable);
        if (!editable) {
            field.setBackground(new Color(245, 245, 245));
        }
        setupUpdateField(label, (Component) field, panel);
    }

    private static void styleButton(JButton button) {
        Dimension buttonSize = new Dimension(150, 50);
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);
        button.setFocusPainted(false);
        Color normalButtonColor = new Color(255, 204, 204);
        Color hoverButtonColor = new Color(170, 0, 0);
        button.setBorder(new LineBorder(new Color(180, 80, 80), 2, false));
        button.setBorderPainted(true);
        button.setBackground(normalButtonColor);
        button.setForeground(Color.BLACK);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverButtonColor);
                button.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(normalButtonColor);
                button.setForeground(Color.BLACK);
            }
        });
    }
}
