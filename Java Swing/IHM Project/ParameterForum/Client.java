package ParameterForum;

import Connector.JDBConnector;
import Tabs.ClientsTabPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;

public class Client extends JFrame {

    private JTextField nomField;
    private JTextField prenomField;
    private JTextField telephoneField;
    private JTextField emailField;
    private JTextField numeroPermisField;
    private JButton executeButton;

    public Client(String Mode) {
        setTitle("Gestion des Clients");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        
        add(new JLabel("Nom:"));
        nomField = new JTextField();
        add(nomField);

        
        add(new JLabel("Prénom:"));
        prenomField = new JTextField();
        add(prenomField);

        
        add(new JLabel("Téléphone:"));
        telephoneField = new JTextField();
        add(telephoneField);

        
        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        
        add(new JLabel("Numéro de Permis:"));
        numeroPermisField = new JTextField();
        add(numeroPermisField);

        
        executeButton = new JButton("Execute");
        add(executeButton);
        add(new JLabel("")); 

        
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<String, String> clientData = getClientData();

                if (Mode.equals("S")) {
                    try {
                        ClientsTabPanel.populateTable(JDBConnector.getClients(clientData));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (Mode.equals("C")) {
                    JDBConnector.insertClient(clientData);
                    ClientsTabPanel.refreshTable();
                    dispose();
                } else if (Mode.equals("U")) {
                    JDBConnector.updateClient(ClientsTabPanel.updatePlaceholder, clientData);
                    ClientsTabPanel.refreshTable();
                    dispose();
                }
            }
        });

        setVisible(true);
    }

    private HashMap<String, String> getClientData() {
        HashMap<String, String> data = new HashMap<>();

        
        if (!nomField.getText().trim().isEmpty()) {
            data.put("nom", nomField.getText().trim());
        }
        if (!prenomField.getText().trim().isEmpty()) {
            data.put("prenom", prenomField.getText().trim());
        }
        if (!telephoneField.getText().trim().isEmpty()) {
            data.put("telephone", telephoneField.getText().trim());
        }
        if (!emailField.getText().trim().isEmpty()) {
            data.put("email", emailField.getText().trim());
        }
        if (!numeroPermisField.getText().trim().isEmpty()) {
            data.put("numero_permis", numeroPermisField.getText().trim());
        }

        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client("S"); 
            }
        });
    }
}