package Tabs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VehiclesTabPanel extends JPanel {

    private JTable vehiclesTable;
    private DefaultTableModel tableModel;

    public VehiclesTabPanel() {
        setLayout(new BorderLayout()); // Use BorderLayout for structuring the panel

        // Add a title label at the top
        JLabel titleLabel = new JLabel("Vehicles Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Add a table to display vehicle data
        String[] columns = {"ID", "Brand", "Model", "Year", "Status"};
        Object[][] data = {
                {"1", "Toyota", "Corolla", "2022", "Available"},
                {"2", "Honda", "Civic", "2021", "Rented"},
                {"3", "Ford", "Focus", "2020", "Maintenance"}
        };

        // Use DefaultTableModel to allow dynamic updates to the table
        tableModel = new DefaultTableModel(data, columns);
        vehiclesTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(vehiclesTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Add a button panel at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton addButton = new JButton("Add Vehicle");
        JButton editButton = new JButton("Edit Vehicle");
        JButton deleteButton = new JButton("Delete Vehicle");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Add button functionality
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddVehicle();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onEditVehicle();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDeleteVehicle();
            }
        });
    }

    // Method to handle adding a vehicle
    private void onAddVehicle() {
        String id = JOptionPane.showInputDialog(this, "Enter Vehicle ID:");
        String brand = JOptionPane.showInputDialog(this, "Enter Vehicle Brand:");
        String model = JOptionPane.showInputDialog(this, "Enter Vehicle Model:");
        String year = JOptionPane.showInputDialog(this, "Enter Vehicle Year:");
        String status = JOptionPane.showInputDialog(this, "Enter Vehicle Status:");

        if (id != null && brand != null && model != null && year != null && status != null) {
            tableModel.addRow(new Object[]{id, brand, model, year, status});
        }
    }

    // Method to handle editing a vehicle
    private void onEditVehicle() {
        int selectedRow = vehiclesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a vehicle to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = (String) tableModel.getValueAt(selectedRow, 0);
        String brand = JOptionPane.showInputDialog(this, "Edit Vehicle Brand:", tableModel.getValueAt(selectedRow, 1));
        String model = JOptionPane.showInputDialog(this, "Edit Vehicle Model:", tableModel.getValueAt(selectedRow, 2));
        String year = JOptionPane.showInputDialog(this, "Edit Vehicle Year:", tableModel.getValueAt(selectedRow, 3));
        String status = JOptionPane.showInputDialog(this, "Edit Vehicle Status:", tableModel.getValueAt(selectedRow, 4));

        if (brand != null && model != null && year != null && status != null) {
            tableModel.setValueAt(brand, selectedRow, 1);
            tableModel.setValueAt(model, selectedRow, 2);
            tableModel.setValueAt(year, selectedRow, 3);
            tableModel.setValueAt(status, selectedRow, 4);
        }
    }

    // Method to handle deleting a vehicle
    private void onDeleteVehicle() {
        int selectedRow = vehiclesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a vehicle to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this vehicle?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(selectedRow);
        }
    }

    public static void main(String[] args) {
        // Test the Tabs.VehiclesTabPanel as a standalone component
        JFrame frame = new JFrame("Vehicles Tab Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new VehiclesTabPanel());
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
}
