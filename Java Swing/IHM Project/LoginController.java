import Connector.JDBConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController extends JFrame {

    public LoginController() {
        setTitle("Login");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Call the updated isLoginValid function
                String role = JDBConnector.isLoginValid(username, password);
                if (role != null) {
                    JOptionPane.showMessageDialog(null, "Login Successful! Role: " + role, "Success", JOptionPane.INFORMATION_MESSAGE);
                    initMainPageController(role);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initMainPageController(String role) {
        JOptionPane.showMessageDialog(this, "Welcome to the Main Page! Your role is: " + role, "Main Page", JOptionPane.INFORMATION_MESSAGE);
        new MainPageController(role);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginController::new);
    }
}
