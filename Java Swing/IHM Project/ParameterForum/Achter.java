package ParameterForum;

import Connector.JDBConnector;
import Tabs.AchterTabPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;

public class Achter extends JFrame {

    private JTextField idReservationField;
    private JTextField montantField;
    private JTextField datePaiementField;
    private JTextField modePaiementField;
    private JButton executeButton;

    public Achter(String Mode) {
        setTitle("Gestion des Paiements");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 5, 5));

        
        add(new JLabel("ID Réservation:"));
        idReservationField = new JTextField();
        add(idReservationField);

        
        add(new JLabel("Montant:"));
        montantField = new JTextField();
        add(montantField);

        
        add(new JLabel("Date Paiement:"));
        datePaiementField = new JTextField();
        add(datePaiementField);

        
        add(new JLabel("Mode de Paiement:"));
        modePaiementField = new JTextField();
        add(modePaiementField);

        
        executeButton = new JButton("Execute");
        add(executeButton);
        add(new JLabel("")); 

        
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<String, String> paymentData = getPaymentData();

                if (Mode.equals("S")) {
                    try {
                        AchterTabPanel.populateTable(JDBConnector.getPayments(paymentData));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (Mode.equals("C")) {
                    JDBConnector.insertPayment(paymentData);
                    AchterTabPanel.refreshTable();
                    dispose();
                } else if (Mode.equals("U")) {
                    JDBConnector.updatePayment(AchterTabPanel.updatePlaceholder, paymentData);
                    AchterTabPanel.refreshTable();
                    dispose();
                }
            }
        });

        setVisible(true);
    }

    private HashMap<String, String> getPaymentData() {
        HashMap<String, String> data = new HashMap<>();

        
        if (!idReservationField.getText().trim().isEmpty()) {
            data.put("id_reservation", idReservationField.getText().trim());
        }
        if (!montantField.getText().trim().isEmpty()) {
            data.put("montant", montantField.getText().trim());
        }
        if (!datePaiementField.getText().trim().isEmpty()) {
            data.put("date_paiement", datePaiementField.getText().trim());
        }
        if (!modePaiementField.getText().trim().isEmpty()) {
            data.put("mode_paiement", modePaiementField.getText().trim());
        }

        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Achter("S"); 
            }
        });
    }
}