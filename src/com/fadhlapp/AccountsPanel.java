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
        table.setFont(new Font("SansSerif", Font.PLAIN, 16));
        table.setRowHeight(22);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel input = new JPanel();
        JLabel nLab = new JLabel("Name:");
        nLab.setFont(new Font("SansSerif", Font.PLAIN, 16));
        input.add(nLab);
        nameField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        input.add(nameField);
        JLabel eLab = new JLabel("Email:");
        eLab.setFont(new Font("SansSerif", Font.PLAIN, 16));
        input.add(eLab);
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        input.add(emailField);
        JLabel pLab = new JLabel("Password:");
        pLab.setFont(new Font("SansSerif", Font.PLAIN, 16));
        input.add(pLab);
        passField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        input.add(passField);
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
