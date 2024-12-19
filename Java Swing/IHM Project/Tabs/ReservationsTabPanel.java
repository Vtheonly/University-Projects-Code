package Tabs;

import javax.swing.*;
import java.awt.*;

public class ReservationsTabPanel extends JPanel {

    public ReservationsTabPanel() {
        setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Reservations Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Table
        String[] columns = {"ID", "Vehicle ID", "Client ID", "Start Date", "End Date", "Status"};
        Object[][] data = {}; // Empty data for now
        JTable reservationsTable = new JTable(data, columns);
        JScrollPane tableScrollPane = new JScrollPane(reservationsTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add Reservation");
        JButton editButton = new JButton("Edit Reservation");
        JButton deleteButton = new JButton("Delete Reservation");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
