package com.fadhlapp;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class MediaPanel extends JPanel {
    private DefaultListModel<MediaItem> model = new DefaultListModel<>();
    private JList<MediaItem> list = new JList<>(model);
    private JTextField nameField = new JTextField(10);
    private JTextField pathField = new JTextField(10);
    private JTextField searchField = new JTextField(10);

    public MediaPanel(List<MediaItem> items) {
        setLayout(new BorderLayout());
        list.setFont(new Font("SansSerif", Font.PLAIN, 16));
        list.setCellRenderer((lst, value, index, isSelected, cellHasFocus) -> {
            String text = value.getName() + " (" + value.getDateAdded().format(DateTimeFormatter.ISO_DATE) + ")";
            JLabel lab = new JLabel(text);
            lab.setFont(new Font("SansSerif", Font.PLAIN, 16));
            if(isSelected) lab.setBackground(new Color(200,200,255));
            lab.setOpaque(true);
            return lab;
        });
        add(new JScrollPane(list), BorderLayout.CENTER);

        JPanel input = new JPanel();
        JLabel nLab = new JLabel("Name:");
        nLab.setFont(new Font("SansSerif", Font.PLAIN, 16));
        input.add(nLab);
        nameField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        input.add(nameField);
        JLabel pLab = new JLabel("Path:");
        pLab.setFont(new Font("SansSerif", Font.PLAIN, 16));
        input.add(pLab);
        pathField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        input.add(pathField);
        JButton addBtn = new JButton("Add");
        addBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        addBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseEntered(java.awt.event.MouseEvent e) {
                addBtn.setFont(addBtn.getFont().deriveFont(18f));
            }
            @Override public void mouseExited(java.awt.event.MouseEvent e) {
                addBtn.setFont(addBtn.getFont().deriveFont(16f));
            }
        });
        input.add(addBtn);
        add(input, BorderLayout.NORTH);

        JPanel bottom = new JPanel();
        JLabel sLab = new JLabel("Search:");
        sLab.setFont(new Font("SansSerif", Font.PLAIN, 16));
        bottom.add(sLab);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        bottom.add(searchField);
        JButton delBtn = new JButton("Delete");
        delBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        delBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseEntered(java.awt.event.MouseEvent e) {
                delBtn.setFont(delBtn.getFont().deriveFont(18f));
            }
            @Override public void mouseExited(java.awt.event.MouseEvent e) {
                delBtn.setFont(delBtn.getFont().deriveFont(16f));
            }
        });
        bottom.add(delBtn);
        add(bottom, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            String name = nameField.getText();
            String path = pathField.getText();
            if(!name.isEmpty() && !path.isEmpty()) {
                MediaItem item = new MediaItem(name, path);
                items.add(item);
                model.addElement(item);
                nameField.setText("");
                pathField.setText("");
            }
        });

        delBtn.addActionListener(e -> {
            MediaItem selected = list.getSelectedValue();
            if(selected != null) {
                items.remove(selected);
                model.removeElement(selected);
            }
        });

        searchField.getDocument().addDocumentListener(new SimpleDocumentListener(() -> {
            String term = searchField.getText().toLowerCase();
            model.clear();
            List<MediaItem> filtered = items.stream()
                .filter(i -> i.getName().toLowerCase().contains(term))
                .collect(Collectors.toList());
            for(MediaItem m : filtered) {
                model.addElement(m);
            }
        }));
    }
}
