package Tabs;

import javax.swing.*;
import java.awt.*;

public class AdminTabPanel extends JPanel {

    public AdminTabPanel() {
        setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Admin Panel", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Table
        String[] columns = {"ID", "Name", "Role", "Email"};
        Object[][] data = {}; // Empty data for now
        JTable adminTable = new JTable(data, columns);
        JScrollPane tableScrollPane = new JScrollPane(adminTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add Admin");
        JButton editButton = new JButton("Edit Admin");
        JButton deleteButton = new JButton("Delete Admin");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
