package Tabs;

import Connector.JDBConnector;
import ParameterForum.Users;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminTabPanel extends JPanel {

    private JTable adminTable;
    private static DefaultTableModel tableModel;
    public static HashMap<String, String> updatePlaceholder = new HashMap<>();


    public AdminTabPanel() {

        updatePlaceholder.put("nom", "");
        updatePlaceholder.put("prenom", "");
        updatePlaceholder.put("email", "");
        updatePlaceholder.put("mot_de_passe", "");
        updatePlaceholder.put("role", "");


        setLayout(new BorderLayout());

        
        JLabel titleLabel = new JLabel("Admin Panel", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        
        String[] columns = {"Nom", "Prenom", "Email", "Password", "Role"};
        tableModel = new DefaultTableModel(columns, 0); 
        adminTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(adminTable);
        add(tableScrollPane, BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add users");
        JButton editButton = new JButton("Edit users");
        JButton deleteButton = new JButton("Delete users");
        JButton searchButton = new JButton("Search users");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);

        add(buttonPanel, BorderLayout.SOUTH);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Users("C"); 
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             new Users("U");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDBConnector.deleteUser(updatePlaceholder);
                refreshTable();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Users("S");
            }
        });





        adminTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    getFocusedRowData();

                    System.out.println(updatePlaceholder);

                }
            }
        });

    }


    public static void populateTable(List<HashMap<String, String>> userData) {
        tableModel.setRowCount(0);

        
        for (Map<String, String> user : userData) {
            String nom = user.getOrDefault("nom", "");
            String prenom = user.getOrDefault("prenom", "");
            String email = user.getOrDefault("email", "");
            String password = user.getOrDefault("mot_de_passe", "");
            String role = user.getOrDefault("role", "");
            tableModel.addRow(new Object[]{nom, prenom, email, password, role});
        }
    }
    public static void refreshTable() {
        try {
            populateTable(JDBConnector.getUsers(new HashMap<>()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void getFocusedRowData() {
        int selectedRow = adminTable.getSelectedRow();
        if (selectedRow != -1) {
            updatePlaceholder.put("nom", tableModel.getValueAt(selectedRow, 0).toString());
            updatePlaceholder.put("prenom", tableModel.getValueAt(selectedRow, 1).toString());
            updatePlaceholder.put("email", tableModel.getValueAt(selectedRow, 2).toString());
            updatePlaceholder.put("mot_de_passe", tableModel.getValueAt(selectedRow, 3).toString());
            updatePlaceholder.put("role", tableModel.getValueAt(selectedRow, 4).toString());
        } else {
            
            updatePlaceholder.clear();
        }
    }






}
