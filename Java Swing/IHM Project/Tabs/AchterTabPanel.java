package Tabs;

import Connector.JDBConnector;
import ParameterForum.Achter;

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

public class AchterTabPanel extends JPanel {

    private JTable paymentsTable;
    private static DefaultTableModel tableModel;
    public static HashMap<String, String> updatePlaceholder = new HashMap<>();

    public AchterTabPanel() {
        
        updatePlaceholder.put("id_paiement", "");
        updatePlaceholder.put("id_reservation", "");
        updatePlaceholder.put("montant", "");
        updatePlaceholder.put("date_paiement", "");
        updatePlaceholder.put("mode_paiement", "");

        setLayout(new BorderLayout());

        
        JLabel titleLabel = new JLabel("Payments Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        
        String[] columns = {"ID", "Reservation ID", "Amount", "Payment Date", "Payment Method"};
        tableModel = new DefaultTableModel(columns, 0);
        paymentsTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(paymentsTable);
        add(tableScrollPane, BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add Payment");
        JButton editButton = new JButton("Edit Payment");
        JButton deleteButton = new JButton("Delete Payment");
        JButton searchButton = new JButton("Search Payment");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        add(buttonPanel, BorderLayout.SOUTH);

        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Achter("C"); 
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (paymentsTable.getSelectedRow() != -1) {
                    new Achter("U"); 
                } else {
                    JOptionPane.showMessageDialog(AchterTabPanel.this, "Please select a payment to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (paymentsTable.getSelectedRow() != -1) {
                    int response = JOptionPane.showConfirmDialog(AchterTabPanel.this,
                            "Are you sure you want to delete the selected payment?",
                            "Confirm Delete", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        JDBConnector.deletePayment(updatePlaceholder);
                        refreshTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(AchterTabPanel.this, "Please select a payment to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Achter("S"); 
            }
        });

        
        paymentsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    getFocusedRowData();
                    System.out.println(updatePlaceholder);
                }
            }
        });
    }

    
    public static void populateTable(List<HashMap<String, String>> paymentData) {
        tableModel.setRowCount(0);
        for (HashMap<String, String> payment : paymentData) {
            tableModel.addRow(new Object[]{
                    payment.get("id_paiement"),
                    payment.get("id_reservation"),
                    payment.get("montant"),
                    payment.get("date_paiement"),
                    payment.get("mode_paiement")
            });
        }
    }

    
    public static void refreshTable() {
        try {
            populateTable(JDBConnector.getPayments(new HashMap<>()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
    public void getFocusedRowData() {
        int selectedRow = paymentsTable.getSelectedRow();
        if (selectedRow != -1) {
            updatePlaceholder.put("id_paiement", tableModel.getValueAt(selectedRow, 0).toString());
            updatePlaceholder.put("id_reservation", tableModel.getValueAt(selectedRow, 1).toString());
            updatePlaceholder.put("montant", tableModel.getValueAt(selectedRow, 2).toString());
            updatePlaceholder.put("date_paiement", tableModel.getValueAt(selectedRow, 3).toString());
            updatePlaceholder.put("mode_paiement", tableModel.getValueAt(selectedRow, 4).toString());
        } else {
            updatePlaceholder.clear();
        }
    }
}