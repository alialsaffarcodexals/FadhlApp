package com.fadhlapp;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import com.fadhlapp.Theme;

public class LoginFrame extends JFrame {
    private JTextField userField = new JTextField(15);
    private JPasswordField passField = new JPasswordField(15);
    private JButton loginBtn = new JButton("Login");
    private JLabel message = new JLabel(" ");

    public LoginFrame() {
        super("FadhlApp Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setupUI();
    }

    private void setupUI() {
        ImageIcon img = null;
        try {
            img = new ImageIcon(new URL("https://i.imgur.com/8JkN5WQ.jpg"));
        } catch (Exception ex) {
            img = new ImageIcon();
        }
        JLabel bg = new JLabel(img);
        setContentPane(bg);
        bg.setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,15,15,15);
        gbc.gridwidth = 2;

        JLabel welcome = new JLabel("\u0645\u0631\u062D\u0628\u0627 \u0641\u0627\u0636\u0644");
        welcome.setFont(new Font("SansSerif", Font.BOLD, 40));
        welcome.setForeground(Theme.YELLOW);
        gbc.gridx = 0; gbc.gridy = 0; panel.add(welcome, gbc);

        JLabel uLabel = new JLabel("Username:");
        uLabel.setForeground(Theme.FG);
        uLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1; panel.add(uLabel, gbc);
        userField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        gbc.gridx = 1; panel.add(userField, gbc);

        JLabel pLabel = new JLabel("Password:");
        pLabel.setForeground(Theme.FG);
        pLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        gbc.gridx = 0; gbc.gridy = 2; panel.add(pLabel, gbc);
        passField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        gbc.gridx = 1; panel.add(passField, gbc);

        loginBtn.setFont(new Font("SansSerif", Font.BOLD, 22));
        loginBtn.setBackground(Theme.RED);
        loginBtn.setForeground(Theme.FG);
        gbc.gridwidth = 2; gbc.gridx = 0; gbc.gridy = 3; panel.add(loginBtn, gbc);

        loginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                loginBtn.setFont(loginBtn.getFont().deriveFont(26f));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                loginBtn.setFont(loginBtn.getFont().deriveFont(22f));
            }
        });

        message.setForeground(Theme.RED);
        message.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gbc.gridy = 4; panel.add(message, gbc);

        GridBagConstraints center = new GridBagConstraints();
        center.gridx = 0;
        center.gridy = 0;
        bg.add(panel, center);

        loginBtn.addActionListener(e -> authenticate());
    }

    private void authenticate() {
        String user = userField.getText();
        String pass = new String(passField.getPassword());
        if("Fadhl".equals(user) && "1234".equals(pass)) {
            DashboardFrame dash = new DashboardFrame();
            dash.setVisible(true);
            dispose();
        } else {
            message.setText("Invalid credentials");
        }
    }
}
