package com.fadhlapp;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    private DataStore store = new DataStore();
    private boolean dark = false;

    public DashboardFrame() {
        super("Fadhl Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setupUI();
    }

    private void setupUI() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("YouTube Videos", new MediaPanel(store.getYoutubeVideos()));
        tabs.addTab("Instagram Videos", new MediaPanel(store.getInstagramVideos()));
        tabs.addTab("Instagram Photos", new MediaPanel(store.getInstagramPhotos()));
        tabs.addTab("Personal Photos", new MediaPanel(store.getPersonalPhotos()));
        tabs.addTab("Websites", new WebsitesPanel(store.getWebsites()));
        tabs.addTab("Accounts", new AccountsPanel(store.getAccounts()));
        add(tabs, BorderLayout.CENTER);

        JButton themeBtn = new JButton("Toggle Theme");
        add(themeBtn, BorderLayout.SOUTH);
        themeBtn.addActionListener(e -> toggleTheme());
    }

    private void toggleTheme() {
        dark = !dark;
        Color bg = dark ? Color.DARK_GRAY : Color.LIGHT_GRAY;
        Color fg = dark ? Color.WHITE : Color.BLACK;
        SwingUtilities.invokeLater(() -> applyTheme(this.getContentPane(), bg, fg));
    }

    private void applyTheme(Container c, Color bg, Color fg) {
        for(Component comp : c.getComponents()) {
            comp.setBackground(bg);
            comp.setForeground(fg);
            if(comp instanceof Container) {
                applyTheme((Container) comp, bg, fg);
            }
        }
    }
}
