import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class ColorfulComplexApp extends JFrame {
    private JTable table;
    private JTree tree;
    private JTextArea logArea;
    private JTabbedPane tabbedPane;
    private Timer colorTimer;
    private Random random = new Random();

    
    private final Color DARK_THEME = new Color(45, 45, 45);
    private final Color ACCENT_COLOR = new Color(255, 140, 0);
    private final Color SECONDARY_COLOR = new Color(70, 130, 180);
    private final Color HIGHLIGHT_COLOR = new Color(50, 205, 50);

    public ColorfulComplexApp() {
        setTitle("Colorful Modern Application");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        JPanel headerPanel = createGradientHeader();

        
        JPanel mainContent = new JPanel(new BorderLayout(10, 10));
        mainContent.setBackground(DARK_THEME);
        mainContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        tabbedPane = createTabbedPane();

        
        add(headerPanel, BorderLayout.NORTH);
        add(mainContent, BorderLayout.CENTER);
        mainContent.add(tabbedPane, BorderLayout.CENTER);

        
        startColorAnimation();
    }

    private JPanel createGradientHeader() {
        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gradient = new GradientPaint(0, 0, ACCENT_COLOR, getWidth(), 0, SECONDARY_COLOR);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        header.setPreferredSize(new Dimension(0, 60));

        
        JLabel titleLabel = new JLabel("Dynamic Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        header.add(titleLabel);

        return header;
    }

    private JTabbedPane createTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(DARK_THEME);
        tabbedPane.setForeground(Color.WHITE);

        
        tabbedPane.addTab("Dashboard", createDashboardPanel());

        
        tabbedPane.addTab("Data Visualization", createDataVisPanel());

        
        tabbedPane.addTab("Settings", createSettingsPanel());

        return tabbedPane;
    }

    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(DARK_THEME);
        GridBagConstraints gbc = new GridBagConstraints();

        
        for (int i = 0; i < 4; i++) {
            JPanel card = createAnimatedCard("Metric " + (i + 1),
                    String.valueOf(random.nextInt(1000)),
                    new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            gbc.weightx = 0.5;
            gbc.weighty = 0.5;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(10, 10, 10, 10);
            panel.add(card, gbc);
        }

        return panel;
    }

    private JPanel createAnimatedCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradient = new GradientPaint(0, 0, color, getWidth(), getHeight(),
                        color.darker());
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };

        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        card.setPreferredSize(new Dimension(200, 150));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 32));

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    private JPanel createDataVisPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(DARK_THEME);

        
        String[] columns = {"Category", "Value", "Trend", "Status"};
        Object[][] data = new Object[20][4];
        for (int i = 0; i < 20; i++) {
            data[i] = new Object[]{
                    "Item " + (i + 1),
                    random.nextInt(1000),
                    random.nextBoolean() ? "↑" : "↓",
                    random.nextBoolean() ? "Active" : "Inactive"
            };
        }

        table = new JTable(data, columns) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component comp = super.prepareRenderer(renderer, row, col);
                comp.setBackground(row % 2 == 0 ? DARK_THEME : DARK_THEME.brighter());
                comp.setForeground(Color.WHITE);
                return comp;
            }
        };

        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(DARK_THEME);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createSettingsPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 20, 20));
        panel.setBackground(DARK_THEME);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        
        for (int i = 0; i < 6; i++) {
            JToggleButton toggle = new JToggleButton("Setting " + (i + 1)) {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    if (isSelected()) {
                        g2d.setColor(HIGHLIGHT_COLOR);
                    } else {
                        g2d.setColor(SECONDARY_COLOR);
                    }
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                    super.paintComponent(g);
                }
            };
            toggle.setForeground(Color.WHITE);
            toggle.setFont(new Font("Arial", Font.BOLD, 14));
            toggle.setBorderPainted(false);
            toggle.setFocusPainted(false);
            panel.add(toggle);
        }

        return panel;
    }

    private void startColorAnimation() {
        colorTimer = new Timer(3000, e -> {
            
            Component[] components = ((JPanel)tabbedPane.getComponentAt(0)).getComponents();
            for (Component comp : components) {
                if (comp instanceof JPanel) {
                    comp.setBackground(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                    comp.repaint();
                }
            }
        });
        colorTimer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ColorfulComplexApp app = new ColorfulComplexApp();
            app.setLocationRelativeTo(null);
            app.setVisible(true);
        });
    }
}