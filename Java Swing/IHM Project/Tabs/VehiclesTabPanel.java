package Tabs;

import Connector.JDBConnector;
import ParameterForum.Vehicule;

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

public class VehiclesTabPanel extends JPanel {

    private JTable vehiclesTable;
    private static DefaultTableModel tableModel;
    public static HashMap<String, String> updatePlaceholder = new HashMap<>();

    public VehiclesTabPanel() {
        
        updatePlaceholder.put("id_vehicule", "");
        updatePlaceholder.put("marque", "");
        updatePlaceholder.put("modele", "");
        updatePlaceholder.put("annee", "");
        updatePlaceholder.put("type", "");
        updatePlaceholder.put("carburant", "");
        updatePlaceholder.put("prix_location_jour", "");
        updatePlaceholder.put("etat", "");

        setLayout(new BorderLayout());

        
        JLabel titleLabel = new JLabel("Vehicles Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        
        String[] columns = {"ID", "Marque", "Modèle", "Année", "Type", "Carburant", "Prix/Jour", "État"};
        tableModel = new DefaultTableModel(columns, 0);
        vehiclesTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(vehiclesTable);
        add(tableScrollPane, BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add Vehicle");
        JButton editButton = new JButton("Edit Vehicle");
        JButton deleteButton = new JButton("Delete Vehicle");
        JButton searchButton = new JButton("Search Vehicle");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        add(buttonPanel, BorderLayout.SOUTH);

        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Vehicule("C"); 
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vehiclesTable.getSelectedRow() != -1) {
                    new Vehicule("U"); 
                } else {
                    JOptionPane.showMessageDialog(VehiclesTabPanel.this, "Please select a vehicle to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vehiclesTable.getSelectedRow() != -1) {
                    int response = JOptionPane.showConfirmDialog(VehiclesTabPanel.this,
                            "Are you sure you want to delete the selected vehicle?",
                            "Confirm Delete", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        JDBConnector.deleteVehicle(updatePlaceholder);
                        refreshTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(VehiclesTabPanel.this, "Please select a vehicle to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Vehicule("S"); 
            }
        });

        
        vehiclesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    getFocusedRowData();
                    System.out.println(updatePlaceholder);

                }
            }
        });
    }

    
    public static void populateTable(List<HashMap<String, String>> vehicleData) {
        tableModel.setRowCount(0);
        for (HashMap<String, String> vehicle : vehicleData) {
            tableModel.addRow(new Object[]{
                    vehicle.get("id_vehicule"),
                    vehicle.get("marque"),
                    vehicle.get("modele"),
                    vehicle.get("annee"),
                    vehicle.get("type"),
                    vehicle.get("carburant"),
                    vehicle.get("prix_location_jour"),
                    vehicle.get("etat")
            });
        }
    }

    
    public static void refreshTable() {
        try {
            populateTable(JDBConnector.getVehicles(new HashMap<>()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
    public void getFocusedRowData() {
        int selectedRow = vehiclesTable.getSelectedRow();
        if (selectedRow != -1) {
            updatePlaceholder.put("id_vehicule", tableModel.getValueAt(selectedRow, 0).toString());
            updatePlaceholder.put("marque", tableModel.getValueAt(selectedRow, 1).toString());
            updatePlaceholder.put("modele", tableModel.getValueAt(selectedRow, 2).toString());
            updatePlaceholder.put("annee", tableModel.getValueAt(selectedRow, 3).toString());
            updatePlaceholder.put("type", tableModel.getValueAt(selectedRow, 4).toString());
            updatePlaceholder.put("carburant", tableModel.getValueAt(selectedRow, 5).toString());
            updatePlaceholder.put("prix_location_jour", tableModel.getValueAt(selectedRow, 6).toString());
            updatePlaceholder.put("etat", tableModel.getValueAt(selectedRow, 7).toString());
        } else {
            updatePlaceholder.clear();
        }
    }
}