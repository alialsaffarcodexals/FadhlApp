package com.fadhlapp;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField userField = new JTextField(15);
    private JPasswordField passField = new JPasswordField(15);
    private JButton loginBtn = new JButton("Login");
    private JLabel message = new JLabel(" ");

    public LoginFrame() {
        super("FadhlApp Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 180);
        setLocationRelativeTo(null);
        setupUI();
    }

    private void setupUI() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1; panel.add(userField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1; panel.add(passField, gbc);
        gbc.gridwidth = 2; gbc.gridx = 0; gbc.gridy = 2; panel.add(loginBtn, gbc);
        gbc.gridy = 3; panel.add(message, gbc);
        add(panel);

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
