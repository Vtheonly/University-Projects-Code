import java.sql.*;
import java.util.HashMap;

public class JDBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/IHMDB";
    private static final String USER = "root";
    private static final String PASSWORD = "74532180";
    private static Connection connection;


    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL JDBC Driver not found.", e);
            } catch (SQLException e) {
                throw new SQLException("Error connecting to the database.", e);
            }
        }
        return connection;
    }


    void deleteUser(HashMap<String, String> Data) {
    }

    void deleteVehicle(HashMap<String, String> Data) {
    }

    void deleteReservation(HashMap<String, String> Data) {
    }

    void deleteClient(HashMap<String, String> Data) {
    }

    void deletePayment(HashMap<String, String> Data) {
    }

    void deleteReturn(HashMap<String, String> Data) {
    }


    void updateUser(HashMap<String, String> Data) {
    }

    void updateVehicle(HashMap<String, String> Data) {
    }

    void updateReservation(HashMap<String, String> Data) {
    }

    void updateClient(HashMap<String, String> Data) {
    }

    void updatePayment(HashMap<String, String> Data) {
    }

    void updateReturn(HashMap<String, String> Data) {
    }


    public static void insertUser(HashMap<String, String> data) {
        String sql = "INSERT INTO Utilisateur (nom, prenom, email, mot_de_passe, role) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, data.get("nom"));
            stmt.setString(2, data.get("prenom"));
            stmt.setString(3, data.get("email"));
            stmt.setString(4, data.get("mot_de_passe"));
            stmt.setString(5, data.get("role"));

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error while inserting user: " + e.getMessage());
        }
    }




    static String isLoginValid(String user, String password) {
        String sql = "SELECT role FROM Utilisateur WHERE email = ? AND mot_de_passe = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // If valid login, return the role
                    return rs.getString("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return null if login is invalid
        return null;
    }



    // Insert Vehicle
    public static void insertVehicle(HashMap<String, String> data) {
        String sql = "INSERT INTO Vehicule (marque, modele, annee, type, carburant, prix_location_jour, etat) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, data.get("marque"));
            statement.setString(2, data.get("modele"));
            statement.setInt(3, Integer.parseInt(data.get("annee")));
            statement.setString(4, data.get("type"));
            statement.setString(5, data.get("carburant"));
            statement.setDouble(6, Double.parseDouble(data.get("prix_location_jour")));
            statement.setString(7, data.get("etat"));
            statement.executeUpdate();
            System.out.println("Vehicle inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert Reservation
    public static void insertReservation(HashMap<String, String> data) {
        String sql = "INSERT INTO Reservation (id_vehicule, id_client, date_debut, date_fin, montant_total, statut) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, Integer.parseInt(data.get("id_vehicule")));
            statement.setInt(2, Integer.parseInt(data.get("id_client")));
            statement.setString(3, data.get("date_debut"));
            statement.setString(4, data.get("date_fin"));
            statement.setDouble(5, Double.parseDouble(data.get("montant_total")));
            statement.setString(6, data.get("statut"));
            statement.executeUpdate();
            System.out.println("Reservation inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert Client
    public static void insertClient(HashMap<String, String> data) {
        String sql = "INSERT INTO Client (nom, prenom, telephone, email, numero_permis) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, data.get("nom"));
            statement.setString(2, data.get("prenom"));
            statement.setString(3, data.get("telephone"));
            statement.setString(4, data.get("email"));
            statement.setString(5, data.get("numero_permis"));
            statement.executeUpdate();
            System.out.println("Client inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert Payment
    public static void insertPayment(HashMap<String, String> data) {
        String sql = "INSERT INTO Paiement (id_reservation, montant, date_paiement, mode_paiement) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, Integer.parseInt(data.get("id_reservation")));
            statement.setDouble(2, Double.parseDouble(data.get("montant")));
            statement.setString(3, data.get("date_paiement"));
            statement.setString(4, data.get("mode_paiement"));
            statement.executeUpdate();
            System.out.println("Payment inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert Return
    static void insertReturn(HashMap<String, String> data) {
        String sql = "INSERT INTO Retour (id_reservation, date_retour, etat_retour, frais_supplementaires) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, Integer.parseInt(data.get("id_reservation")));
            statement.setString(2, data.get("date_retour"));
            statement.setString(3, data.get("etat_retour"));
            String fraisSupplementaires = data.get("frais_supplementaires");
            if (fraisSupplementaires != null && !fraisSupplementaires.isEmpty()) {
                statement.setDouble(4, Double.parseDouble(fraisSupplementaires));
            } else {
                statement.setNull(4, java.sql.Types.DOUBLE);
            }
            statement.executeUpdate();
            System.out.println("Return inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }








    HashMap getUsers(HashMap<String, String> Constraints) {
        String sql = "SELECT * FROM INTO Client (nom, prenom, telephone, email, numero_permis) VALUES (?, ?, ?, ?, ?)";

        return null;
    }

    HashMap getVehicles(HashMap<String, String> Constraints) {
        return null;
    }

    HashMap getReservations(HashMap<String, String> Constraints) {
        return null;
    }

    HashMap getClients(HashMap<String, String> Constraints) {
        return null;
    }

    HashMap getPayments(HashMap<String, String> Constraints) {
        return null;
    }

    HashMap getReturns(HashMap<String, String> Constraints) {
        return null;
    }











    public static void main(String[] args) throws SQLException {

        getConnection();


        HashMap<String, String> user = Toolkit.parseUser("dddDoe", "John", "john.doe@example.com", "password123", "admin");

        HashMap<String, String> vehicle = Toolkit.parseVehicle("Toyota", "Corolla", 2022, "Sedan", "Petrol", 50.75, "Available");

        HashMap<String, String> client = Toolkit.parseClient("Smith", "Jane", "+123456789", "janet.smith@example.com", "B1234567");

        HashMap<String, String> reservation = Toolkit.parseReservation(5, 3, "2024-12-01", "2024-12-05", 203.00, "Confirmed");

        HashMap<String, String> retour = Toolkit.parseReturn(5, "2024-12-06", "Good condition", 10.50);

        HashMap<String, String> payment = Toolkit.parsePayment(1, 203.00, "2024-12-01", "Credit Card");

//insertVehicle(vehicle);
        insertReturn(retour);
//insertUser(user);
//insertReservation(reservation);
//        insertClient(client);
    }
}
