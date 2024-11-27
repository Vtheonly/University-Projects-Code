import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class ProSwingApp extends JFrame {
    private Color primaryColor = new Color(45, 45, 48);
    private Color accentColor = new Color(66, 133, 244);
    private Color warningColor = new Color(255, 69, 58);

    public ProSwingApp() {
        setTitle("Pro Swing Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        
        add(createSidebarPanel(), BorderLayout.WEST);
        add(createContentPanel(), BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);

        
        getContentPane().setBackground(primaryColor);
        pack();
        setLocationRelativeTo(null);
    }

    
    private JPanel createSidebarPanel() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(primaryColor.darker());

        JButton openFileButton = new JButton("Open File");
        openFileButton.setBackground(accentColor);
        openFileButton.setForeground(Color.WHITE);
        openFileButton.addActionListener(e -> openFileChooser());

        JButton showWarningButton = new JButton("Show Warning");
        showWarningButton.setBackground(warningColor);
        showWarningButton.setForeground(Color.WHITE);
        showWarningButton.addActionListener(e -> showWarningDialog());

        sidebar.add(openFileButton);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(showWarningButton);
        return sidebar;
    }

    
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 2, 10, 10));
        contentPanel.setBackground(primaryColor);

        
        for (int i = 0; i < 4; i++) {
            JPanel panel = new JPanel();
            panel.setBackground(new Color(80, 80, 85).darker());
            panel.setBorder(BorderFactory.createLineBorder(accentColor, 2));

            JLabel label = new JLabel("Panel " + (i + 1));
            label.setForeground(Color.WHITE);
            panel.add(label);

            contentPanel.add(panel);
        }
        return contentPanel;
    }

    
    private JPanel createFooterPanel() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footer.setBackground(primaryColor.darker().darker());

        JLabel footerLabel = new JLabel("Footer Section - All Rights Reserved");
        footerLabel.setForeground(Color.LIGHT_GRAY);
        footer.add(footerLabel);

        return footer;
    }

    
    private void openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Selected file: " + file.getName(), "File Selected", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    
    private void showWarningDialog() {
        JOptionPane.showMessageDialog(this, "This is a warning!", "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProSwingApp app = new ProSwingApp();
            app.setVisible(true);
        });
    }
}
