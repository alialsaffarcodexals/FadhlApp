package com.fadhlapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.fadhlapp.Theme;

public class WebsitesPanel extends JPanel {
    private DefaultListModel<Website> model = new DefaultListModel<>();
    private JList<Website> list = new JList<>(model);
    private JTextField nameField = new JTextField(10);
    private JTextField urlField = new JTextField(10);
    private JTextField searchField = new JTextField(10);

    public WebsitesPanel(List<Website> websites) {
        setLayout(new BorderLayout());
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        list.setFixedCellWidth(200);
        list.setFixedCellHeight(80);
        list.setCellRenderer((jlist, value, index, isSelected, focus) -> {
            JLabel name = new JLabel(value.getName(), SwingConstants.CENTER);
            name.setFont(new Font("SansSerif", Font.BOLD, 20));
            name.setBorder(BorderFactory.createLineBorder(Theme.YELLOW));
            name.setOpaque(true);
            name.setBackground(isSelected ? Theme.RED : Theme.BG);
            name.setForeground(Theme.FG);
            return name;
        });
        add(new JScrollPane(list), BorderLayout.CENTER);

        JPanel input = new JPanel();
        input.setBackground(Theme.BG);
        JLabel nLab = new JLabel("Name:");
        nLab.setFont(new Font("SansSerif", Font.PLAIN, 16));
        input.add(nLab);
        nameField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        input.add(nameField);
        JLabel uLab = new JLabel("URL:");
        uLab.setFont(new Font("SansSerif", Font.PLAIN, 16));
        input.add(uLab);
        urlField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        input.add(urlField);
        JButton addBtn = new JButton("Add");
        addBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        addBtn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                addBtn.setFont(addBtn.getFont().deriveFont(18f));
            }
            @Override public void mouseExited(MouseEvent e) {
                addBtn.setFont(addBtn.getFont().deriveFont(16f));
            }
        });
        input.add(addBtn);
        add(input, BorderLayout.NORTH);

        JPanel bottom = new JPanel();
        bottom.setBackground(Theme.BG);
        JLabel sLab = new JLabel("Search:");
        sLab.setFont(new Font("SansSerif", Font.PLAIN, 16));
        bottom.add(sLab);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        bottom.add(searchField);
        JButton delBtn = new JButton("Delete");
        delBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        delBtn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                delBtn.setFont(delBtn.getFont().deriveFont(18f));
            }
            @Override public void mouseExited(MouseEvent e) {
                delBtn.setFont(delBtn.getFont().deriveFont(16f));
            }
        });
        bottom.add(delBtn);
        add(bottom, BorderLayout.SOUTH);

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    Website w = list.getSelectedValue();
                    if(w != null) {
                        try {
                            Desktop.getDesktop().browse(new URI(w.getUrl()));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });

        addBtn.addActionListener(e -> {
            String n = nameField.getText();
            String u = urlField.getText();
            if(!n.isEmpty() && !u.isEmpty()) {
                Website w = new Website(n, u);
                websites.add(w);
                model.addElement(w);
                nameField.setText("");
                urlField.setText("");
            }
        });

        delBtn.addActionListener(e -> {
            Website w = list.getSelectedValue();
            if(w != null) {
                websites.remove(w);
                model.removeElement(w);
            }
        });

        searchField.getDocument().addDocumentListener(new SimpleDocumentListener(() -> {
            String term = searchField.getText().toLowerCase();
            model.clear();
            List<Website> filtered = websites.stream()
                .filter(w -> w.getName().toLowerCase().contains(term))
                .collect(Collectors.toList());
            for(Website w : filtered) {
                model.addElement(w);
            }
        }));

        Theme.apply(this);
    }
}
