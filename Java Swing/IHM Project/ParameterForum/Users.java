package ParameterForum;

import Connector.JDBConnector;
import Tabs.AdminTabPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
public class Users extends JFrame {



    private JTextField nomField;
    private JTextField prenomField;
    private JTextField emailField;
    private JPasswordField motDePasseField; 
    private JTextField roleField;
    private JButton saveButton;

    public Users(String Mode) {
        setTitle("Gestion des Utilisateurs"); 
        setSize(400, 300); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        
        add(new JLabel("Nom:"));
        nomField = new JTextField();
        add(nomField);

        
        add(new JLabel("Prénom:"));
        prenomField = new JTextField();
        add(prenomField);

        
        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        
        add(new JLabel("Mot de passe:"));
        motDePasseField = new JPasswordField();
        add(motDePasseField);

        
        add(new JLabel("Rôle:"));
        roleField = new JTextField();
        add(roleField);

        
        saveButton = new JButton("Execute");
        add(saveButton);
        add(new JLabel("")); 

        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<String, String> userData = getUserData();

                System.out.println(userData);

                
                if(Mode.equals("S")){
                    try {
                        AdminTabPanel.populateTable( JDBConnector.getUsers(userData));

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }else if(Mode.equals("C")){
                    JDBConnector.insertUser(userData);
                    AdminTabPanel.refreshTable();

                }else if (Mode.equals("U")){
                    JDBConnector.updateUser(AdminTabPanel.updatePlaceholder,userData);
                    AdminTabPanel.refreshTable();
                }

            }
        });

        setVisible(true);
    }

    private HashMap<String, String> getUserData() {
        HashMap<String, String> data = new HashMap<>();

        if (!nomField.getText().trim().isEmpty()) {
            data.put("nom", nomField.getText().trim());
        }
        if (!prenomField.getText().trim().isEmpty()) {
            data.put("prenom", prenomField.getText().trim());
        }
        if (!emailField.getText().trim().isEmpty()) {
            data.put("email", emailField.getText().trim());
        }
        
        char[] passwordChars = motDePasseField.getPassword();
        if (passwordChars.length > 0) {
            data.put("mot_de_passe", new String(passwordChars));
        }
        if (!roleField.getText().trim().isEmpty()) {
            data.put("role", roleField.getText().trim());
        }

        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Users("S");
            }
        });
    }
}