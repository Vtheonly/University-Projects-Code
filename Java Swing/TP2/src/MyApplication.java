import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MyApplication extends JFrame {
    public MyApplication() {
        
        setTitle("My Application");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setBackground(Color.LIGHT_GRAY);

        
        JButton openButton = new JButton("Open File");
        openButton.setBackground(Color.WHITE);
        openButton.setForeground(Color.BLACK);

        
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    
                }
            }
        });

        
        topPanel.add(openButton);

        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 2));
        centerPanel.setBackground(Color.WHITE);

        
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");

        
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You clicked Button 1!");
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You clicked Button 2!");
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You clicked Button 3!");
            }
        });

        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to click Button 4?", "Warning", JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "You clicked Button 4!");
                }
            }
        });

        
        centerPanel.add(button1);
        centerPanel.add(button2);
        centerPanel.add(button3);
        centerPanel.add(button4);

        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(Color.LIGHT_GRAY);

        
        JLabel label = new JLabel("This is the bottom panel");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        
        bottomPanel.add(label, BorderLayout.CENTER);

        
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        
        MyApplication app = new MyApplication();
        app.setVisible(true);
    }
}
