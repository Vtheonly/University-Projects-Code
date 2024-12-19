package Tabs;

import Connector.JDBConnector;
import ParameterForum.Client;


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

public class ClientsTabPanel extends JPanel {

    private JTable clientsTable;
    private static DefaultTableModel tableModel;
    public static HashMap<String, String> updatePlaceholder = new HashMap<>();

    public ClientsTabPanel() {
        
        updatePlaceholder.put("id_client", "");
        updatePlaceholder.put("nom", "");
        updatePlaceholder.put("prenom", "");
        updatePlaceholder.put("telephone", "");
        updatePlaceholder.put("email", "");
        updatePlaceholder.put("numero_permis", "");

        setLayout(new BorderLayout());

        
        JLabel titleLabel = new JLabel("Clients Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        
        String[] columns = {"ID", "Nom", "Prénom", "Téléphone", "Email", "Numéro Permis"};
        tableModel = new DefaultTableModel(columns, 0);
        clientsTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(clientsTable);
        add(tableScrollPane, BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add Client");
        JButton editButton = new JButton("Edit Client");
        JButton deleteButton = new JButton("Delete Client");
        JButton searchButton = new JButton("Search Client"); 
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        add(buttonPanel, BorderLayout.SOUTH);

        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
new Client("C"); 
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clientsTable.getSelectedRow() != -1) {
                    new Client("U"); 
                } else {
                    JOptionPane.showMessageDialog(ClientsTabPanel.this, "Please select a client to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clientsTable.getSelectedRow() != -1) {
                    int response = JOptionPane.showConfirmDialog(ClientsTabPanel.this,
                            "Are you sure you want to delete the selected client?",
                            "Confirm Delete", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        JDBConnector.deleteClient(updatePlaceholder);
                        refreshTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(ClientsTabPanel.this, "Please select a client to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Client("S"); 
            }
        });

        
        clientsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    getFocusedRowData();
                    System.out.println(updatePlaceholder);
                }
            }
        });
    }

    
    public static void populateTable(List<HashMap<String, String>> clientData) {
        tableModel.setRowCount(0);
        for (HashMap<String, String> client : clientData) {
            tableModel.addRow(new Object[]{
                    client.get("id_client"),
                    client.get("nom"),
                    client.get("prenom"),
                    client.get("telephone"),
                    client.get("email"),
                    client.get("numero_permis")
            });
        }
    }

    
    public static void refreshTable() {
        try {
            populateTable(JDBConnector.getClients(new HashMap<>()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
    public void getFocusedRowData() {
        int selectedRow = clientsTable.getSelectedRow();
        if (selectedRow != -1) {
            updatePlaceholder.put("id_client", tableModel.getValueAt(selectedRow, 0).toString());
            updatePlaceholder.put("nom", tableModel.getValueAt(selectedRow, 1).toString());
            updatePlaceholder.put("prenom", tableModel.getValueAt(selectedRow, 2).toString());
            updatePlaceholder.put("telephone", tableModel.getValueAt(selectedRow, 3).toString());
            updatePlaceholder.put("email", tableModel.getValueAt(selectedRow, 4).toString());
            updatePlaceholder.put("numero_permis", tableModel.getValueAt(selectedRow, 5).toString());
        } else {
            updatePlaceholder.clear();
        }
    }
}