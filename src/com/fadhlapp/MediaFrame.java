package com.fadhlapp;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import com.fadhlapp.Theme;

public class MediaFrame extends JFrame {
    public MediaFrame(String title, List<MediaItem> items, DashboardFrame menu) {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        MediaPanel panel = new MediaPanel(items);
        JButton back = new JButton("Return to Menu");
        back.setBackground(Theme.BLUE);
        back.setForeground(Theme.FG);
        back.addActionListener(e -> { menu.setVisible(true); dispose(); });
        add(new JScrollPane(panel), BorderLayout.CENTER);
        add(back, BorderLayout.SOUTH);
        Theme.apply(getContentPane());
    }
}
