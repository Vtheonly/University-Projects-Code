package ParameterForum;

import Connector.JDBConnector;
import Tabs.ReservationsTabPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Reservation extends JFrame {

    private JTextField idVehiculeField;
    private JTextField idClientField;
    private JTextField dateDebutField;
    private JTextField dateFinField;
    private JTextField montantTotalField;
    private JTextField statutField;
    private JButton executeButton;

    public Reservation(String Mode) {
        setTitle("Gestion des Réservations");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 5, 5));

        
        add(new JLabel("ID Véhicule:"));
        idVehiculeField = new JTextField();
        add(idVehiculeField);

        
        add(new JLabel("ID Client:"));
        idClientField = new JTextField();
        add(idClientField);

        
        add(new JLabel("Date Début:"));
        dateDebutField = new JTextField();
        add(dateDebutField);

        
        add(new JLabel("Date Fin:"));
        dateFinField = new JTextField();
        add(dateFinField);

        
        add(new JLabel("Montant Total:"));
        montantTotalField = new JTextField();
        add(montantTotalField);

        
        add(new JLabel("Statut:"));
        statutField = new JTextField();
        add(statutField);

        
        executeButton = new JButton("Execute");
        add(executeButton);
        add(new JLabel("")); 

        
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<String, String> reservationData = getReservationData();

                if (Mode.equals("S")) {
                    ReservationsTabPanel.populateTable(JDBConnector.getReservations(reservationData));
                } else if (Mode.equals("C")) {
                    JDBConnector.insertReservation(reservationData);
                    ReservationsTabPanel.refreshTable();
                    dispose();
                } else if (Mode.equals("U")) {
                    JDBConnector.updateReservation(ReservationsTabPanel.updatePlaceholder, reservationData);
                    ReservationsTabPanel.refreshTable();
                    dispose();
                }
            }
        });

        setVisible(true);
    }

    private HashMap<String, String> getReservationData() {
        HashMap<String, String> data = new HashMap<>();

        
        if (!idVehiculeField.getText().trim().isEmpty()) {
            data.put("id_vehicule", idVehiculeField.getText().trim());
        }
        if (!idClientField.getText().trim().isEmpty()) {
            data.put("id_client", idClientField.getText().trim());
        }
        if (!dateDebutField.getText().trim().isEmpty()) {
            data.put("date_debut", dateDebutField.getText().trim());
        }
        if (!dateFinField.getText().trim().isEmpty()) {
            data.put("date_fin", dateFinField.getText().trim());
        }
        if (!montantTotalField.getText().trim().isEmpty()) {
            data.put("montant_total", montantTotalField.getText().trim());
        }
        if (!statutField.getText().trim().isEmpty()) {
            data.put("statut", statutField.getText().trim());
        }

        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Reservation("S"); 
            }
        });
    }
}