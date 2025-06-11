package com.fadhlapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class AccountsPanel extends JPanel {
    private DefaultTableModel model = new DefaultTableModel(new Object[]{"Name","Email","Password"},0);
    private JTable table = new JTable(model);
    private JTextField nameField = new JTextField(8);
    private JTextField emailField = new JTextField(8);
    private JTextField passField = new JTextField(8);
    private JTextField searchField = new JTextField(8);

    public AccountsPanel(List<Account> accounts) {
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel input = new JPanel();
        input.add(new JLabel("Name:"));
        input.add(nameField);
        input.add(new JLabel("Email:"));
        input.add(emailField);
        input.add(new JLabel("Password:"));
        input.add(passField);
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
            String em = emailField.getText();
            String ps = passField.getText();
            if(!n.isEmpty() && !em.isEmpty()) {
                Account a = new Account(n, em, ps);
                accounts.add(a);
                model.addRow(new Object[]{n,em,ps});
                nameField.setText("");
                emailField.setText("");
                passField.setText("");
            }
        });

        delBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row >= 0) {
                accounts.remove(row);
                model.removeRow(row);
            }
        });

        searchField.getDocument().addDocumentListener(new SimpleDocumentListener(() -> {
            String term = searchField.getText().toLowerCase();
            model.setRowCount(0);
            List<Account> filtered = accounts.stream()
                .filter(a -> a.getName().toLowerCase().contains(term))
                .collect(Collectors.toList());
            for(Account a: filtered) {
                model.addRow(new Object[]{a.getName(), a.getEmail(), a.getPassword()});
            }
        }));
    }
}
