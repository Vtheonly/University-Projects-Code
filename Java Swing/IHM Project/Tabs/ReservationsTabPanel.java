package Tabs;

import Connector.JDBConnector;
import ParameterForum.Reservation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class ReservationsTabPanel extends JPanel {

    private JTable reservationsTable;
    private static DefaultTableModel tableModel;
    public static HashMap<String, String> updatePlaceholder = new HashMap<>();

    public ReservationsTabPanel() {
        
        updatePlaceholder.put("id_reservation", "");
        updatePlaceholder.put("id_vehicule", "");
        updatePlaceholder.put("id_client", "");
        updatePlaceholder.put("date_debut", "");
        updatePlaceholder.put("date_fin", "");
        updatePlaceholder.put("montant_total", "");
        updatePlaceholder.put("statut", "");

        setLayout(new BorderLayout());

        
        JLabel titleLabel = new JLabel("Reservations Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        
        String[] columns = {"ID", "Vehicle ID", "Client ID", "Start Date", "End Date", "Total Amount", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        reservationsTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(reservationsTable);
        add(tableScrollPane, BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add Reservation");
        JButton editButton = new JButton("Edit Reservation");
        JButton deleteButton = new JButton("Delete Reservation");
        JButton searchButton = new JButton("Search Reservation");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        add(buttonPanel, BorderLayout.SOUTH);

        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Reservation("C"); 
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (reservationsTable.getSelectedRow() != -1) {
                    new Reservation("U"); 
                } else {
                    JOptionPane.showMessageDialog(ReservationsTabPanel.this, "Please select a reservation to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (reservationsTable.getSelectedRow() != -1) {
                    int response = JOptionPane.showConfirmDialog(ReservationsTabPanel.this,
                            "Are you sure you want to delete the selected reservation?",
                            "Confirm Delete", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        JDBConnector.deleteReservation(updatePlaceholder);
                        refreshTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(ReservationsTabPanel.this, "Please select a reservation to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Reservation("S"); 
            }
        });

        
        reservationsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    getFocusedRowData();
                    System.out.println(updatePlaceholder);
                }
            }
        });
    }

    
    public static void populateTable(List<HashMap<String, String>> reservationData) {
        tableModel.setRowCount(0);
        for (HashMap<String, String> reservation : reservationData) {
            tableModel.addRow(new Object[]{
                    reservation.get("id_reservation"),
                    reservation.get("id_vehicule"),
                    reservation.get("id_client"),
                    reservation.get("date_debut"),
                    reservation.get("date_fin"),
                    reservation.get("montant_total"),
                    reservation.get("statut")
            });
        }
    }

    
    public static void refreshTable() {
        populateTable(JDBConnector.getReservations(new HashMap<>()));
    }

    
    public void getFocusedRowData() {
        int selectedRow = reservationsTable.getSelectedRow();
        if (selectedRow != -1) {
            updatePlaceholder.put("id_reservation", tableModel.getValueAt(selectedRow, 0).toString());
            updatePlaceholder.put("id_vehicule", tableModel.getValueAt(selectedRow, 1).toString());
            updatePlaceholder.put("id_client", tableModel.getValueAt(selectedRow, 2).toString());
            updatePlaceholder.put("date_debut", tableModel.getValueAt(selectedRow, 3).toString());
            updatePlaceholder.put("date_fin", tableModel.getValueAt(selectedRow, 4).toString());
            updatePlaceholder.put("montant_total", tableModel.getValueAt(selectedRow, 5).toString());
            updatePlaceholder.put("statut", tableModel.getValueAt(selectedRow, 6).toString());
        } else {
            updatePlaceholder.clear();
        }
    }
}