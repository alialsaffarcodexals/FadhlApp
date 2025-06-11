package com.fadhlapp;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import com.fadhlapp.Theme;

public class DashboardFrame extends JFrame {
    private DataStore store = new DataStore();

    public DashboardFrame() {
        super("Fadhl Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setupUI();
    }

    private void setupUI() {
        JPanel grid = new JPanel(new GridLayout(2, 3, 20, 20));
        grid.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        grid.setBackground(Theme.BG);

        JButton yt = createButton("YouTube Videos");
        yt.addActionListener(e -> openMedia("YouTube Videos", store.getYoutubeVideos()));
        grid.add(yt);

        JButton igv = createButton("Instagram Videos");
        igv.addActionListener(e -> openMedia("Instagram Videos", store.getInstagramVideos()));
        grid.add(igv);

        JButton igp = createButton("Instagram Photos");
        igp.addActionListener(e -> openMedia("Instagram Photos", store.getInstagramPhotos()));
        grid.add(igp);

        JButton personal = createButton("Personal Photos");
        personal.addActionListener(e -> openMedia("Personal Photos", store.getPersonalPhotos()));
        grid.add(personal);

        JButton web = createButton("Websites");
        web.addActionListener(e -> openWebsites());
        grid.add(web);

        JButton acc = createButton("Accounts");
        acc.addActionListener(e -> openAccounts());
        grid.add(acc);

        add(grid, BorderLayout.CENTER);
        Theme.apply(this.getContentPane());
    }

    private JButton createButton(String text) {
        JButton b = new JButton(text);
        b.setFont(new Font("SansSerif", Font.BOLD, 22));
        b.setBackground(Theme.BLUE);
        b.setForeground(Theme.FG);
        return b;
    }

    private void openMedia(String title, List<MediaItem> items) {
        MediaFrame f = new MediaFrame(title, items, this);
        f.setVisible(true);
        this.setVisible(false);
    }

    private void openWebsites() {
        WebsitesFrame f = new WebsitesFrame(store.getWebsites(), this);
        f.setVisible(true);
        this.setVisible(false);
    }

    private void openAccounts() {
        AccountsFrame f = new AccountsFrame(store.getAccounts(), this);
        f.setVisible(true);
        this.setVisible(false);
    }
}
