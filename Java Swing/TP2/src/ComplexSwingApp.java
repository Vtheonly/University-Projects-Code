import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;

public class ComplexSwingApp extends JFrame {
    private JTable table;
    private JTree tree;
    private JTextArea textArea;

    public ComplexSwingApp() {
        setTitle("Complex Swing Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);

        
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        JPanel leftPanel = createLeftPanel();
        mainPanel.add(leftPanel, BorderLayout.WEST);

        
        JPanel centerPanel = createCenterPanel();
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        
        JPanel bottomPanel = createBottomPanel();
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem("New"));
        fileMenu.add(new JMenuItem("Open"));
        fileMenu.add(new JMenuItem("Save"));
        fileMenu.addSeparator();
        fileMenu.add(new JMenuItem("Exit"));

        
        JMenu editMenu = new JMenu("Edit");
        editMenu.add(new JMenuItem("Cut"));
        editMenu.add(new JMenuItem("Copy"));
        editMenu.add(new JMenuItem("Paste"));

        
        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(new JMenuItem("About"));

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Navigation",
                TitledBorder.LEFT, TitledBorder.TOP));

        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode parent1 = new DefaultMutableTreeNode("Parent 1");
        DefaultMutableTreeNode parent2 = new DefaultMutableTreeNode("Parent 2");

        parent1.add(new DefaultMutableTreeNode("Child 1.1"));
        parent1.add(new DefaultMutableTreeNode("Child 1.2"));
        parent2.add(new DefaultMutableTreeNode("Child 2.1"));
        parent2.add(new DefaultMutableTreeNode("Child 2.2"));

        root.add(parent1);
        root.add(parent2);

        tree = new JTree(root);
        JScrollPane treeScroll = new JScrollPane(tree);
        panel.add(treeScroll, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Data Table"),
                BorderFactory.createLoweredBevelBorder()));

        
        String[] columnNames = {"ID", "Name", "Department", "Status"};
        Object[][] data = {
                {"001", "John Doe", "IT", "Active"},
                {"002", "Jane Smith", "HR", "Active"},
                {"003", "Bob Johnson", "Sales", "Inactive"},
                {"004", "Alice Brown", "Marketing", "Active"}
        };

        table = new JTable(data, columnNames);
        table.setFillsViewportHeight(true);

        
        JToolBar toolbar = new JToolBar();
        toolbar.add(new JButton("Add"));
        toolbar.add(new JButton("Edit"));
        toolbar.add(new JButton("Delete"));

        panel.add(toolbar, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Status"));

        textArea = new JTextArea(4, 50);
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ComplexSwingApp app = new ComplexSwingApp();
            app.setLocationRelativeTo(null);
            app.setVisible(true);
        });
    }
}