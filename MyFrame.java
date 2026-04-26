import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

class MyFrame extends JFrame {

    // frame
    static JFrame f;

    // label to display text
    static JLabel l;

    // default constructor
    void text() {
    }

    public static void main(String[] args) {
        f = new JFrame("Student Mangement System");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(700, 520);
        f.setLocationRelativeTo(null);
        f.setLayout(new BorderLayout());
        f.getContentPane().setBackground(new Color(255, 255, 0));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(35, 20, 20, 20));
        headerPanel.setBackground(new Color(255, 255, 0));

        l = new JLabel("SMS-LOGIN-PORTAL", SwingConstants.CENTER);
        l.setFont(new Font("SansSerif", Font.BOLD, 28));
        l.setForeground(Color.RED);
        l.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel heading = new JLabel("Login Portal", SwingConstants.CENTER);
        heading.setFont(new Font("SansSerif", Font.BOLD, 18));
        heading.setForeground(new Color(120, 0, 0));
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(l);
        headerPanel.add(Box.createVerticalStrut(8));
        headerPanel.add(heading);

        JPanel centerpanel = new JPanel();
        centerpanel.setLayout(new BoxLayout(centerpanel, BoxLayout.Y_AXIS));
        centerpanel.setBorder(new EmptyBorder(40, 130, 60, 130));
        centerpanel.setBackground(new Color(255, 255, 0));

        JLabel userlabel = new JLabel("UserName:");
        userlabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        userlabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField username = new JTextField();
        username.setMaximumSize(new Dimension(360, 42));
        username.setBorder(new RoundedBorder(10, Color.BLUE));
        username.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel passworJLabel = new JLabel("Password:");
        passworJLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        passworJLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(360, 42));
        passwordField.setBorder(new RoundedBorder(10, Color.BLUE));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton loginButton = new JButton("Login");
        Dimension buttonSize = new Dimension(170, 52);
        loginButton.setPreferredSize(buttonSize);
        loginButton.setMaximumSize(buttonSize);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setFocusPainted(false);

        Color normalButtonColor = new Color(255, 204, 204);
        Color hoverButtonColor = new Color(170, 0, 0);
        loginButton.setBorder(new LineBorder(new Color(180, 80, 80), 2, false));
        loginButton.setBorderPainted(true);
        loginButton.setBackground(normalButtonColor);
        loginButton.setForeground(Color.BLACK);
        loginButton.setOpaque(true);
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(hoverButtonColor);
                loginButton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(normalButtonColor);
                loginButton.setForeground(Color.BLACK);
            }
        });

        centerpanel.add(userlabel);
        centerpanel.add(Box.createVerticalStrut(8));
        centerpanel.add(username);
        centerpanel.add(Box.createVerticalStrut(18));
        centerpanel.add(passworJLabel);
        centerpanel.add(Box.createVerticalStrut(8));
        centerpanel.add(passwordField);
        centerpanel.add(Box.createVerticalStrut(30));
        centerpanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = username.getText().trim();
                String pass = new String(passwordField.getPassword()).trim();
                if (user.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "Please enter username and password.");
                    return;
                }
                dbConnect.checkLogin(user, pass, f);
            }
        });

        f.add(headerPanel, BorderLayout.NORTH);
        f.add(centerpanel, BorderLayout.CENTER);
        f.setVisible(true);
    }

}