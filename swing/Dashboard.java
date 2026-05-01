import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;

public class Dashboard {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(255, 255, 0));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(50, 20, 10, 20));
        headerPanel.setBackground(new Color(255, 255, 0));

        JLabel imageLabel = new JLabel(new ImageIcon("image.png"));
        ImageIcon originalIcon = new ImageIcon("image.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(132, 170, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        imageLabel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("Welcome to Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.RED);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(imageLabel);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(titleLabel);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(255, 255, 0));

        Dimension buttonSize = new Dimension(240, 70);

        JButton btn1 = new JButton("Add student");
        JButton btn2 = new JButton("delete student");
        JButton btn3 = new JButton("update student");
        JButton btn4 = new JButton("Search student");

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Add.main(new String[0]);
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Delete.main(new String[0]);
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Update.main(new String[0]);
            }
        });

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Search.main(new String[0]);
            }
        });

        Color normalButtonColor = new Color(255, 204, 204);
        Color hoverButtonColor = new Color(170, 0, 0);
        JButton[] buttons = { btn1, btn2, btn3, btn4 };
        for (JButton button : buttons) {
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setPreferredSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.setFocusPainted(false);
            button.setBorder(new LineBorder(new Color(180, 80, 80), 2, false));
            button.setBorderPainted(true);
            button.setBackground(normalButtonColor);
            button.setForeground(Color.BLACK);
            button.setContentAreaFilled(true);
            button.setRolloverEnabled(true);
            button.setOpaque(true);
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

        panel.add(Box.createVerticalGlue());
        panel.add(btn1);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btn2);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btn3);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btn4);
        panel.add(Box.createVerticalGlue());

        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}