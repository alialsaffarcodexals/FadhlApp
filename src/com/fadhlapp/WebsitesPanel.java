package com.fadhlapp;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class WebsitesPanel extends JPanel {
    private DefaultListModel<Website> model = new DefaultListModel<>();
    private JList<Website> list = new JList<>(model);
    private JTextField nameField = new JTextField(10);
    private JTextField urlField = new JTextField(10);
    private JTextField searchField = new JTextField(10);

    public WebsitesPanel(List<Website> websites) {
        setLayout(new BorderLayout());
        list.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> new JLabel(value.getName()+" - "+value.getUrl()));
        add(new JScrollPane(list), BorderLayout.CENTER);

        JPanel input = new JPanel();
        input.add(new JLabel("Name:"));
        input.add(nameField);
        input.add(new JLabel("URL:"));
        input.add(urlField);
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
    }
}
