package Tabs;

import javax.swing.*;
import java.awt.*;

public class ClientsTabPanel extends JPanel {

    public ClientsTabPanel() {
        setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Clients Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Table
        String[] columns = {"ID", "Name", "Email", "Phone", "License Number"};
        Object[][] data = {}; // Empty data for now
        JTable clientsTable = new JTable(data, columns);
        JScrollPane tableScrollPane = new JScrollPane(clientsTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add Client");
        JButton editButton = new JButton("Edit Client");
        JButton deleteButton = new JButton("Delete Client");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}