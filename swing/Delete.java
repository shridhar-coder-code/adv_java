import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
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
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;

public class Delete {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1650, 820);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(255, 255, 0));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(30, 20, 20, 20));
        headerPanel.setBackground(new Color(255, 255, 0));

        JLabel titleLabel = new JLabel("Delete Student Record", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        titleLabel.setForeground(Color.RED);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(titleLabel);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(35, 80, 35, 80));
        panel.setBackground(new Color(255, 255, 0));

        JLabel label = new JLabel("USN:");
        label.setFont(new Font("SansSerif", Font.BOLD, 15));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JComboBox<String> usnCombo = new JComboBox<>();
        usnCombo.setEditable(true);
        usnCombo.setPreferredSize(new Dimension(400, 40));
        usnCombo.setMaximumSize(new Dimension(400, 40));
        usnCombo.setAlignmentX(Component.CENTER_ALIGNMENT);
        usnCombo.setBorder(new RoundedBorder(10, Color.BLUE));
        usnCombo.setBackground(Color.WHITE);
        usnCombo.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton();
                button.setVisible(false);
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }
        });
        JTextField usnEditor = (JTextField) usnCombo.getEditor().getEditorComponent();
        usnEditor.setBackground(Color.WHITE);
        usnEditor.setForeground(Color.BLACK);
        usnEditor.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        usnCombo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                showRecentUsnPopup(usnCombo);
            }
        });
        usnEditor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                showRecentUsnPopup(usnCombo);
            }
        });
        refreshUsnDropdown(usnCombo);

        JLabel statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        statusLabel.setForeground(new Color(170, 0, 0));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] columns = {
                "USN", "Name", "SEM", "Email", "City", "Pin_Code", "DOB", "Gender", "Mobile_Number",
                "Branch", "Year", "Degree_Program", "Department_ID", "Current_Semester_Subjects", "Attendance_Percent"
        };
        DefaultTableModel detailsModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable detailsTable = new JTable(detailsModel);
        detailsTable.setRowHeight(28);
        detailsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        detailsTable.setBackground(new Color(255, 255, 0));
        detailsTable.setForeground(Color.BLACK);
        detailsTable.setSelectionBackground(new Color(255, 235, 150));
        detailsTable.setSelectionForeground(Color.BLACK);
        detailsTable.setGridColor(Color.BLACK);
        detailsTable.setShowGrid(true);
        detailsTable.setFont(new Font("SansSerif", Font.PLAIN, 13));
        detailsTable.getTableHeader().setBackground(new Color(255, 255, 0));
        detailsTable.getTableHeader().setForeground(Color.BLACK);
        detailsTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        detailsTable.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        JScrollPane detailsScroll = new JScrollPane(detailsTable);
        detailsScroll.setPreferredSize(new Dimension(1200, 240));
        detailsScroll.setMaximumSize(new Dimension(1200, 320));
        detailsScroll.setMinimumSize(new Dimension(900, 200));
        detailsScroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsScroll.getViewport().setBackground(new Color(255, 255, 0));
        detailsScroll.setBorder(BorderFactory.createEmptyBorder());
        detailsScroll.setViewportBorder(BorderFactory.createEmptyBorder());
        detailsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JButton searchButton = new JButton("Search");
        Dimension buttonSize = new Dimension(150, 50);
        searchButton.setPreferredSize(buttonSize);
        searchButton.setMaximumSize(buttonSize);
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.setFocusPainted(false);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(buttonSize);
        deleteButton.setMaximumSize(buttonSize);
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.setFocusPainted(false);

        JButton backBtn = new JButton("Back to Dashboard");
        backBtn.setPreferredSize(new Dimension(220, 50));
        backBtn.setMaximumSize(new Dimension(220, 50));
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        backBtn.setFocusPainted(false);

        Color normalButtonColor = new Color(255, 204, 204);
        Color hoverButtonColor = new Color(170, 0, 0);

        searchButton.setBorder(BorderFactory.createLineBorder(new Color(180, 80, 80), 2, false));
        searchButton.setBorderPainted(true);
        searchButton.setBackground(normalButtonColor);
        searchButton.setForeground(Color.BLACK);
        searchButton.setOpaque(true);
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                searchButton.setBackground(hoverButtonColor);
                searchButton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                searchButton.setBackground(normalButtonColor);
                searchButton.setForeground(Color.BLACK);
            }
        });

        deleteButton.setBorder(BorderFactory.createLineBorder(new Color(180, 80, 80), 2, false));
        deleteButton.setBorderPainted(true);
        deleteButton.setBackground(normalButtonColor);
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setOpaque(true);
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                deleteButton.setBackground(hoverButtonColor);
                deleteButton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                deleteButton.setBackground(normalButtonColor);
                deleteButton.setForeground(Color.BLACK);
            }
        });

        backBtn.setBorder(BorderFactory.createLineBorder(new Color(180, 80, 80), 2, false));
        backBtn.setBorderPainted(true);
        backBtn.setBackground(normalButtonColor);
        backBtn.setForeground(Color.BLACK);
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
                        detailsModel.setRowCount(0);
                        Object[] row = {
                                rs.getString("USN"),
                                rs.getString("Name"),
                                rs.getString("SEM"),
                                rs.getString("Email"),
                                rs.getString("City"),
                                rs.getString("Pin_Code"),
                                rs.getString("DOB"),
                                rs.getString("Gender"),
                                rs.getString("Mobile_Number"),
                                rs.getString("Branch"),
                                rs.getString("Year"),
                                rs.getString("Degree_Program"),
                                rs.getString("Department_ID"),
                                rs.getString("Current_Semester_Subjects"),
                                rs.getString("Attendance_Percent")
                        };
                        detailsModel.addRow(row);
                        statusLabel.setText("Student found.");
                        statusLabel.setForeground(new Color(0, 120, 0));
                    } else {
                        detailsModel.setRowCount(0);
                        statusLabel.setText("Student not present.");
                        statusLabel.setForeground(new Color(170, 0, 0));
                    }
                }
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage());
            }
        });

        deleteButton.addActionListener(e -> {
            if (detailsModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(frame, "Search and load a student record first.");
                return;
            }

            String usn = getTypedUsn(usnCombo);
            if (usn.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter USN.");
                return;
            }

            // Validate USN format before deletion
            if (!Validation.isValidUSN(usn)) {
                JOptionPane.showMessageDialog(frame, "Invalid USN Format");
                return;
            }

            UsnHistory.add(usn);
            refreshUsnDropdown(usnCombo);

            int confirm = JOptionPane.showConfirmDialog(frame,
                    "Delete student with USN " + usn + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            String sql = "DELETE FROM sdata WHERE `USN` = ?";
            try (Connection conn = new dbConnect().getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, usn);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(frame, "Student deleted successfully.");
                    detailsModel.setRowCount(0);
                    statusLabel.setText("Record deleted.");
                    statusLabel.setForeground(new Color(0, 120, 0));
                } else {
                    statusLabel.setText("Student not present.");
                    statusLabel.setForeground(new Color(170, 0, 0));
                }
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage());
            }
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
            Dashboard.main(new String[0]);
        });

        panel.add(label);
        panel.add(Box.createVerticalStrut(10));
        JPanel usnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        usnPanel.setBackground(new Color(255, 255, 0));
        usnPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usnPanel.add(usnCombo);
        panel.add(usnPanel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(statusLabel);
        panel.add(Box.createVerticalStrut(15));
        JPanel actionButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        actionButtonsPanel.setBackground(new Color(255, 255, 0));
        actionButtonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionButtonsPanel.add(searchButton);
        actionButtonsPanel.add(deleteButton);
        panel.add(actionButtonsPanel);
        panel.add(Box.createVerticalStrut(18));
        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        tablePanel.setBackground(new Color(255, 255, 0));
        tablePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tablePanel.add(detailsScroll);
        panel.add(tablePanel);
        panel.add(Box.createVerticalStrut(18));
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        backButtonPanel.setBackground(new Color(255, 255, 0));
        backButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButtonPanel.add(backBtn);
        panel.add(backButtonPanel);
        panel.add(Box.createVerticalStrut(20));

        frame.add(headerPanel, BorderLayout.NORTH);
        JScrollPane mainScrollPane = new JScrollPane(panel);
        mainScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        mainScrollPane.getViewport().setBackground(new Color(255, 255, 0));
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(14);
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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

    private static void showRecentUsnPopup(JComboBox<String> usnCombo) {
        refreshUsnDropdown(usnCombo);
        if (usnCombo.getItemCount() == 0 || !usnCombo.isShowing()) {
            return;
        }
        usnCombo.showPopup();
    }
}
