import javax.swing.*;
import java.awt.*;

public class ComplexDashboard extends JFrame {

    public ComplexDashboard() {
        setTitle("Complex Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        
        JPanel welcomePanel = createPanel(new Color(50, 50, 50));
        JLabel welcomeLabel = new JLabel("Welcome to the Dashboard");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(255, 255, 255));
        welcomePanel.add(welcomeLabel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(welcomePanel, gbc);

        
        JPanel userInfoPanel = createPanel(new Color(70, 70, 70));
        userInfoPanel.setLayout(new GridLayout(3, 2, 10, 10));
        userInfoPanel.add(new JLabel("Username:"));
        userInfoPanel.add(new JTextField(10));
        userInfoPanel.add(new JLabel("Email:"));
        userInfoPanel.add(new JTextField(10));
        userInfoPanel.add(new JLabel("Role:"));
        userInfoPanel.add(new JComboBox<>(new String[]{"Admin", "User", "Guest"}));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(userInfoPanel, gbc);

        
        JPanel actionPanel = createPanel(new Color(90, 90, 90));
        actionPanel.setLayout(new GridLayout(1, 3, 10, 10));
        actionPanel.add(createButton("Save", new Color(0, 150, 0)));
        actionPanel.add(createButton("Edit", new Color(255, 165, 0)));
        actionPanel.add(createButton("Delete", new Color(150, 0, 0)));

        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(actionPanel, gbc);

        
        JPanel statusPanel = createPanel(new Color(110, 110, 110));
        statusPanel.setLayout(new GridLayout(1, 2, 10, 10));
        statusPanel.add(new JLabel("Status:"));
        statusPanel.add(new JTextField("Active", 10));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(statusPanel, gbc);

        
        JPanel logPanel = createPanel(new Color(130, 130, 130));
        logPanel.setLayout(new BorderLayout());
        JTextArea logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setBackground(new Color(130, 130, 130));
        logArea.setForeground(new Color(255, 255, 255));
        logArea.setText("Log Messages:\n- User logged in\n- Data saved\n- System updated");
        logPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 2;
        mainPanel.add(logPanel, gbc);

        
        JPanel notificationPanel = createPanel(new Color(150, 150, 150));
        notificationPanel.setLayout(new GridLayout(2, 1, 10, 10));
        notificationPanel.add(new JLabel("Notifications:"));
        notificationPanel.add(new JTextArea("New updates available!\nSecurity patch applied.", 2, 20));

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        mainPanel.add(notificationPanel, gbc);

        
        JPanel settingsPanel = createPanel(new Color(170, 170, 170));
        settingsPanel.setLayout(new GridLayout(2, 2, 10, 10));
        settingsPanel.add(new JLabel("Theme:"));
        settingsPanel.add(new JComboBox<>(new String[]{"Dark", "Light"}));
        settingsPanel.add(new JLabel("Language:"));
        settingsPanel.add(new JComboBox<>(new String[]{"English", "Spanish", "French"}));

        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(settingsPanel, gbc);

        add(mainPanel);
    }

    private JPanel createPanel(Color color) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return panel;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ComplexDashboard dashboard = new ComplexDashboard();
            dashboard.setVisible(true);
        });
    }
}