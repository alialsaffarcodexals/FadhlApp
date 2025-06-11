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
        list.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            String text = value.getName() + " (" + value.getDateAdded().format(DateTimeFormatter.ISO_DATE) + ")";
            return new JLabel(text);
        });
        add(new JScrollPane(list), BorderLayout.CENTER);

        JPanel input = new JPanel();
        input.add(new JLabel("Name:"));
        input.add(nameField);
        input.add(new JLabel("Path:"));
        input.add(pathField);
        JButton addBtn = new JButton("Add");
        input.add(addBtn);
        add(input, BorderLayout.NORTH);

        JPanel bottom = new JPanel();
        bottom.add(new JLabel("Search:"));
        bottom.add(searchField);
        JButton delBtn = new JButton("Delete");
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
