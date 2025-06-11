package com.fadhlapp;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    private DataStore store = new DataStore();
    private boolean dark = false;

    public DashboardFrame() {
        super("Fadhl Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setupUI();
    }

    private void setupUI() {
        JPanel grid = new JPanel(new GridLayout(2, 3, 10, 10));
        grid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        grid.add(wrapPanel("YouTube Videos", new MediaPanel(store.getYoutubeVideos())));
        grid.add(wrapPanel("Instagram Videos", new MediaPanel(store.getInstagramVideos())));
        grid.add(wrapPanel("Instagram Photos", new MediaPanel(store.getInstagramPhotos())));
        grid.add(wrapPanel("Personal Photos", new MediaPanel(store.getPersonalPhotos())));
        grid.add(wrapPanel("Websites", new WebsitesPanel(store.getWebsites())));
        grid.add(wrapPanel("Accounts", new AccountsPanel(store.getAccounts())));

        add(new JScrollPane(grid), BorderLayout.CENTER);

        JButton themeBtn = new JButton("Toggle Theme");
        add(themeBtn, BorderLayout.SOUTH);
        themeBtn.addActionListener(e -> toggleTheme());
    }

    private JPanel wrapPanel(String title, JComponent component) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createTitledBorder(title));
        p.add(component, BorderLayout.CENTER);
        return p;
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
