import javax.swing.*;
import java.awt.*;

public class JButtonExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JButton Example");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("OK");
        button.setPreferredSize(new Dimension(100, 50));   // Resize the button
        button.setToolTipText("Press OK to proceed");      // Tooltip text
        button.setMnemonic('o');                          // Shortcut key: Alt + 'o'
        button.setForeground(Color.RED);
        button.setBackground(Color.GREEN);




        frame.add(button);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
    }
}