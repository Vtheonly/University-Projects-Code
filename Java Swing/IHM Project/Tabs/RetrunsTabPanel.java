package Tabs;

import javax.swing.*;
import java.awt.*;

public class RetrunsTabPanel extends JPanel {

    public RetrunsTabPanel() {
        setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Returns Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Table
        String[] columns = {"ID", "Reservation ID", "Return Date", "Condition", "Fees"};
        Object[][] data = {}; // Empty data for now
        JTable returnsTable = new JTable(data, columns);
        JScrollPane tableScrollPane = new JScrollPane(returnsTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add Return");
        JButton editButton = new JButton("Edit Return");
        JButton deleteButton = new JButton("Delete Return");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
