import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.regex.*;
import java.util.stream.Collectors;

public class App extends JFrame {
    // Constants for styling and theming
    private static final Color PRIMARY_COLOR = new Color(33, 150, 243);
    private static final Color SECONDARY_COLOR = new Color(63, 81, 181);
    private static final Color BACKGROUND_COLOR = new Color(240, 242, 245);
    private static final Color TEXT_COLOR = new Color(33, 33, 33);
    private static final Font MAIN_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);

    // Main components
    private JTabbedPane tabbedPane;
    private JLabel statusLabel;
    private Clipboard systemClipboard;

    public App() {
        // Professional frame setup
        setTitle("Advanced Text & Utility Workbench");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // System clipboard
        systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // Modern look and feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize components
        initComponents();
        setupLayout();
        
        // Add a professional touch
        setIconImage(createAppIcon());
    }

    private Image createAppIcon() {
        try {
            java.net.URL iconUrl = getClass().getResource("/app_icon.png");
            return iconUrl != null ? new ImageIcon(iconUrl).getImage() : null;
        } catch (Exception e) {
            return null;
        }
    }

    private void initComponents() {
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(MAIN_FONT);

        // Add tabs
        tabbedPane.addTab("Text Transformer", createTextTransformerPanel());
        tabbedPane.addTab("Regex Toolkit", createRegexToolkitPanel());
        tabbedPane.addTab("Hash Generator", createHashGeneratorPanel());
        tabbedPane.addTab("URL Encoder", createURLEncoderPanel());
        tabbedPane.addTab("File Utilities", createFileUtilitiesPanel());

        // Status label
        statusLabel = new JLabel("Ready | Advanced Utility Workbench Initialized");
        statusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        statusLabel.setForeground(Color.GRAY);
    }

    private JPanel createTextTransformerPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Input area
        JTextArea inputArea = new JTextArea();
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        JScrollPane inputScrollPane = new JScrollPane(inputArea);

        // Output area
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);

        // Transformation options
        String[] transformOptions = {
            "Uppercase", "Lowercase", "Capitalize", "Reverse", 
            "Remove Whitespace", "Word Count", "Camel Case"
        };
        JComboBox<String> transformSelector = new JComboBox<>(transformOptions);

        JButton transformButton = new JButton("Transform");
        transformButton.addActionListener(e -> {
            String input = inputArea.getText();
            String selectedTransform = (String) transformSelector.getSelectedItem();
            String result = performTextTransformation(input, selectedTransform);
            outputArea.setText(result);
        });

        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(transformSelector);
        controlPanel.add(transformButton);

        panel.add(new JLabel("Input Text:"), BorderLayout.NORTH);
        panel.add(inputScrollPane, BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.EAST);
        panel.add(new JLabel("Transformed Text:"), BorderLayout.SOUTH);

        return panel;
    }

    private String performTextTransformation(String input, String transformType) {
        if (input.isEmpty()) return "";

        switch (transformType) {
            case "Uppercase": return input.toUpperCase();
            case "Lowercase": return input.toLowerCase();
            case "Capitalize": 
                return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
            case "Reverse": 
                return new StringBuilder(input).reverse().toString();
            case "Remove Whitespace": 
                return input.replaceAll("\\s+", "");
            case "Word Count": 
                return "Word Count: " + input.trim().split("\\s+").length;
            case "Camel Case":
                return convertToCamelCase(input);
            default: return input;
        }
    }

    private String convertToCamelCase(String input) {
        StringBuilder camelCase = new StringBuilder();
        boolean capitalizeNext = false;
        for (char c : input.trim().toCharArray()) {
            if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                camelCase.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                camelCase.append(Character.toLowerCase(c));
            }
        }
        return camelCase.toString();
    }

    private JPanel createRegexToolkitPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField patternField = new JTextField();
        JTextArea inputArea = new JTextArea();
        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);

        JButton matchButton = new JButton("Find Matches");
        matchButton.addActionListener(e -> {
            try {
                Pattern pattern = Pattern.compile(patternField.getText());
                Matcher matcher = pattern.matcher(inputArea.getText());
                
                List<String> matches = new ArrayList<>();
                while (matcher.find()) {
                    matches.add(matcher.group());
                }
                
                outputArea.setText(String.join("\n", matches));
            } catch (Exception ex) {
                outputArea.setText("Invalid Regex Pattern: " + ex.getMessage());
            }
        });

        JPanel controlPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        controlPanel.add(new JLabel("Regex Pattern:"));
        controlPanel.add(patternField);
        controlPanel.add(new JLabel("Input Text:"));
        controlPanel.add(new JScrollPane(inputArea));
        controlPanel.add(new JLabel("Matches:"));
        controlPanel.add(new JScrollPane(outputArea));

        panel.add(controlPanel, BorderLayout.CENTER);
        panel.add(matchButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createHashGeneratorPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea inputArea = new JTextArea();
        JTable hashTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(
            new String[]{"Algorithm", "Hash Value"}, 0
        );
        hashTable.setModel(tableModel);

        JButton generateButton = new JButton("Generate Hashes");
        generateButton.addActionListener(e -> {
            String input = inputArea.getText();
            tableModel.setRowCount(0);  // Clear previous results

            String[] algorithms = {"MD5", "SHA-1", "SHA-256", "SHA-512"};
            for (String algo : algorithms) {
                try {
                    MessageDigest digest = MessageDigest.getInstance(algo);
                    byte[] hash = digest.digest(input.getBytes());
                    String hashString = bytesToHex(hash);
                    tableModel.addRow(new Object[]{algo, hashString});
                } catch (Exception ex) {
                    tableModel.addRow(new Object[]{algo, "Error generating hash"});
                }
            }
        });

        panel.add(new JLabel("Input Text for Hashing:"), BorderLayout.NORTH);
        panel.add(new JScrollPane(inputArea), BorderLayout.CENTER);
        panel.add(generateButton, BorderLayout.WEST);
        panel.add(new JScrollPane(hashTable), BorderLayout.SOUTH);

        return panel;
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private JPanel createURLEncoderPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField urlField = new JTextField();
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        JButton encodeButton = new JButton("Encode");
        JButton decodeButton = new JButton("Decode");

        encodeButton.addActionListener(e -> {
            try {
                String encoded = java.net.URLEncoder.encode(urlField.getText(), "UTF-8");
                resultArea.setText("Encoded: " + encoded);
            } catch (Exception ex) {
                resultArea.setText("Encoding Error: " + ex.getMessage());
            }
        });

        decodeButton.addActionListener(e -> {
            try {
                String decoded = java.net.URLDecoder.decode(urlField.getText(), "UTF-8");
                resultArea.setText("Decoded: " + decoded);
            } catch (Exception ex) {
                resultArea.setText("Decoding Error: " + ex.getMessage());
            }
        });

        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(new JLabel("URL/Text:"));
        controlPanel.add(urlField);
        controlPanel.add(encodeButton);
        controlPanel.add(decodeButton);

        panel.add(controlPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createFileUtilitiesPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField filePathField = new JTextField();
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        JButton selectFileButton = new JButton("Select File");
        JButton analyzeButton = new JButton("Analyze File");

        selectFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                filePathField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        analyzeButton.addActionListener(e -> {
            File file = new File(filePathField.getText());
            if (file.exists()) {
                StringBuilder analysis = new StringBuilder();
                analysis.append("File Name: ").append(file.getName()).append("\n");
                analysis.append("Path: ").append(file.getAbsolutePath()).append("\n");
                analysis.append("Size: ").append(file.length()).append(" bytes\n");
                analysis.append("Readable: ").append(file.canRead()).append("\n");
                analysis.append("Writable: ").append(file.canWrite()).append("\n");
                analysis.append("Executable: ").append(file.canExecute()).append("\n");
                
                resultArea.setText(analysis.toString());
            } else {
                resultArea.setText("File not found!");
            }
        });

        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(new JLabel("File Path:"));
        controlPanel.add(filePathField);
        controlPanel.add(selectFileButton);
        controlPanel.add(analyzeButton);

        panel.add(controlPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        return panel;
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.put("control", BACKGROUND_COLOR);
                UIManager.put("text", TEXT_COLOR);
                UIManager.put("nimbusBase", PRIMARY_COLOR);
                
                App app = new App();
                app.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}