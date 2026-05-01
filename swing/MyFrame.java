import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class MyFrame {

    public static void main(String[] args) {
        UIManager.put("Button.focus", new Color(0, 0, 0, 0));

        JFrame frame = new JFrame("Student Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520, 360);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(255, 255, 0));

        JLabel title = new JLabel("Login to Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(new Color(170, 0, 0));
        title.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 12, 12, 12));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 40, 20, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("SansSerif", Font.BOLD, 15));

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(260, 34));

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("SansSerif", Font.BOLD, 15));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(260, 34));

        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginBtn.setBackground(new Color(255, 204, 204));
        loginBtn.setForeground(Color.BLACK);
        loginBtn.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(loginBtn, gbc);

        loginBtn.addActionListener((ActionEvent e) -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            dbConnect.checkLogin(username, password, frame);
        });

        passwordField.addActionListener((ActionEvent e) -> loginBtn.doClick());
        usernameField.addActionListener((ActionEvent e) -> loginBtn.doClick());

        frame.add(title, BorderLayout.NORTH);
        frame.add(formPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
