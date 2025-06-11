package com.fadhlapp;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import com.fadhlapp.Theme;

public class WebsitesFrame extends JFrame {
    public WebsitesFrame(List<Website> sites, DashboardFrame menu) {
        super("Websites");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        WebsitesPanel panel = new WebsitesPanel(sites);
        JButton back = new JButton("Return to Menu");
        back.setBackground(Theme.BLUE);
        back.setForeground(Theme.FG);
        back.addActionListener(e -> { menu.setVisible(true); dispose(); });
        add(new JScrollPane(panel), BorderLayout.CENTER);
        add(back, BorderLayout.SOUTH);
        Theme.apply(getContentPane());
    }
}
