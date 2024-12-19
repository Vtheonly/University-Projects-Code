import Tabs.*;

import javax.swing.*;
import java.util.HashMap;


class Toolkit {

    static int[] getPrivilege(HashMap<String, String> User) {

        return new int[] { 0, 1 ,2,3};
    }


    static void setTabsDisabled(int[] Privileges, JTabbedPane Tabs) {
        for(int Tab:Privileges){
            Tabs.setEnabledAt(Tab,false);
        }
    }












    // Parse user data into a HashMap
    public static HashMap<String, String> parseUser(String nom, String prenom, String email, String motDePasse, String role) {
        HashMap<String, String> userData = new HashMap<>();
        userData.put("nom", nom);
        userData.put("prenom", prenom);
        userData.put("email", email);
        userData.put("mot_de_passe", motDePasse);
        userData.put("role", role);
        return userData;
    }

    // Parse vehicle data into a HashMap
    public static HashMap<String, String> parseVehicle(String marque, String modele, int annee, String type, String carburant, double prixLocationJour, String etat) {
        HashMap<String, String> vehicleData = new HashMap<>();
        vehicleData.put("marque", marque);
        vehicleData.put("modele", modele);
        vehicleData.put("annee", String.valueOf(annee));
        vehicleData.put("type", type);
        vehicleData.put("carburant", carburant);
        vehicleData.put("prix_location_jour", String.valueOf(prixLocationJour));
        vehicleData.put("etat", etat);
        return vehicleData;
    }

    // Parse client data into a HashMap
    public static HashMap<String, String> parseClient(String nom, String prenom, String telephone, String email, String numeroPermis) {
        HashMap<String, String> clientData = new HashMap<>();
        clientData.put("nom", nom);
        clientData.put("prenom", prenom);
        clientData.put("telephone", telephone);
        clientData.put("email", email);
        clientData.put("numero_permis", numeroPermis);
        return clientData;
    }

    // Parse reservation data into a HashMap
    public static HashMap<String, String> parseReservation(int idVehicule, int idClient, String dateDebut, String dateFin, double montantTotal, String statut) {
        HashMap<String, String> reservationData = new HashMap<>();
        reservationData.put("id_vehicule", String.valueOf(idVehicule));
        reservationData.put("id_client", String.valueOf(idClient));
        reservationData.put("date_debut", dateDebut);
        reservationData.put("date_fin", dateFin);
        reservationData.put("montant_total", String.valueOf(montantTotal));
        reservationData.put("statut", statut);
        return reservationData;
    }

    // Parse return (Retour) data into a HashMap
    public static HashMap<String, String> parseReturn(int idReservation, String dateRetour, String etatRetour, Double fraisSupplementaires) {
        HashMap<String, String> returnData = new HashMap<>();
        returnData.put("id_reservation", String.valueOf(idReservation));
        returnData.put("date_retour", dateRetour);
        returnData.put("etat_retour", etatRetour);
        returnData.put("frais_supplementaires", fraisSupplementaires != null ? String.valueOf(fraisSupplementaires) : "0.0");
        return returnData;
    }

    // Parse payment data into a HashMap
    public static HashMap<String, String> parsePayment(int idReservation, double montant, String datePaiement, String modePaiement) {
        HashMap<String, String> paymentData = new HashMap<>();
        paymentData.put("id_reservation", String.valueOf(idReservation));
        paymentData.put("montant", String.valueOf(montant));
        paymentData.put("date_paiement", datePaiement);
        paymentData.put("mode_paiement", modePaiement);
        return paymentData;
    }

    


}

