package ParameterForum;

import Connector.JDBConnector;
import Tabs.VehiclesTabPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;

public class Vehicule extends JFrame {

    private JTextField marqueField;
    private JTextField modeleField;
    private JTextField anneeField;
    private JTextField typeField;
    private JTextField carburantField;
    private JTextField prixField;
    private JTextField etatField;
    private JButton executeButton;

    public Vehicule(String Mode) {
        setTitle("Gestion des Véhicules");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 5, 5));

        
        add(new JLabel("Marque:"));
        marqueField = new JTextField();
        add(marqueField);

        
        add(new JLabel("Modèle:"));
        modeleField = new JTextField();
        add(modeleField);

        
        add(new JLabel("Année:"));
        anneeField = new JTextField();
        add(anneeField);

        
        add(new JLabel("Type:"));
        typeField = new JTextField();
        add(typeField);

        
        add(new JLabel("Carburant:"));
        carburantField = new JTextField();
        add(carburantField);

        
        add(new JLabel("Prix par jour:"));
        prixField = new JTextField();
        add(prixField);

        
        add(new JLabel("État:"));
        etatField = new JTextField();
        add(etatField);

        
        executeButton = new JButton("Execute");
        add(executeButton);
        add(new JLabel("")); 

        
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<String, String> vehiculeData = getVehiculeData();

                if (Mode.equals("S")) {
                    try {
                        VehiclesTabPanel.populateTable(JDBConnector.getVehicles(vehiculeData));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (Mode.equals("C")) {
                    JDBConnector.insertVehicle(vehiculeData);
                    VehiclesTabPanel.refreshTable();
                    dispose();
                } else if (Mode.equals("U")) {
                    JDBConnector.updateVehicle(VehiclesTabPanel.updatePlaceholder, vehiculeData);
                    VehiclesTabPanel.refreshTable();
                    dispose();
                }
            }
        });

        setVisible(true);
    }

    private HashMap<String, String> getVehiculeData() {
        HashMap<String, String> data = new HashMap<>();

        if (!marqueField.getText().trim().isEmpty()) {
            data.put("marque", marqueField.getText().trim());
        }
        if (!modeleField.getText().trim().isEmpty()) {
            data.put("modele", modeleField.getText().trim());
        }
        if (!anneeField.getText().trim().isEmpty()) {
            data.put("annee", anneeField.getText().trim());
        }
        if (!typeField.getText().trim().isEmpty()) {
            data.put("type", typeField.getText().trim());
        }
        if (!carburantField.getText().trim().isEmpty()) {
            data.put("carburant", carburantField.getText().trim());
        }
        if (!prixField.getText().trim().isEmpty()) {
            data.put("prix_location_jour", prixField.getText().trim());
        }
        if (!etatField.getText().trim().isEmpty()) {
            data.put("etat", etatField.getText().trim());
        }

        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Vehicule("S"); 
            }
        });
    }
}