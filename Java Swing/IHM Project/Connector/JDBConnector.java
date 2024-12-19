package Connector;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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


    public static void deleteUser(HashMap<String, String> data) {
        try {
            getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "DELETE FROM Utilisateur WHERE nom = ? AND prenom = ? AND email = ? AND mot_de_passe = ? AND role = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set values for the WHERE clause from the data HashMap
            stmt.setString(1, data.get("nom"));
            stmt.setString(2, data.get("prenom"));
            stmt.setString(3, data.get("email"));
            stmt.setString(4, data.get("mot_de_passe"));
            stmt.setString(5, data.get("role"));

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("No user found matching the provided data. Deletion failed.");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void deleteVehicle(HashMap<String, String> data) {
        try {
            getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "DELETE FROM Vehicule WHERE marque = ? AND modele = ? AND annee = ? AND type = ? AND carburant = ? AND prix_location_jour = ? AND etat = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set values for the WHERE clause from the data HashMap
            stmt.setString(1, data.get("marque"));
            stmt.setString(2, data.get("modele"));
            stmt.setInt(3, Integer.parseInt(data.get("annee")));
            stmt.setString(4, data.get("type"));
            stmt.setString(5, data.get("carburant"));
            stmt.setDouble(6, Double.parseDouble(data.get("prix_location_jour")));
            stmt.setString(7, data.get("etat"));

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Vehicle deleted successfully!");
            } else {
                System.out.println("No vehicle found matching the provided data. Deletion failed.");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting vehicle: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid number format for 'annee' or 'prix_location_jour'.");
            e.printStackTrace();
        }
    }


    public static void deleteReservation(HashMap<String, String> data) {
        try {
            getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "DELETE FROM Reservation WHERE id_vehicule = ? AND id_client = ? AND date_debut = ? AND date_fin = ? AND montant_total = ? AND statut = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set values for the WHERE clause from the data HashMap
            stmt.setInt(1, Integer.parseInt(data.get("id_vehicule")));
            stmt.setInt(2, Integer.parseInt(data.get("id_client")));
            stmt.setDate(3, java.sql.Date.valueOf(data.get("date_debut")));
            stmt.setDate(4, java.sql.Date.valueOf(data.get("date_fin")));
            stmt.setDouble(5, Double.parseDouble(data.get("montant_total")));
            stmt.setString(6, data.get("statut"));

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Reservation deleted successfully!");
            } else {
                System.out.println("No reservation found matching the provided data. Deletion failed.");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting reservation: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid number format for 'id_vehicule', 'id_client', or 'montant_total'.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: Invalid date format. Use yyyy-MM-dd.");
            e.printStackTrace();
        }
    }

    public static void deleteClient(HashMap<String, String> data) {
        try {
            getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "DELETE FROM Client WHERE nom = ? AND prenom = ? AND telephone = ? AND email = ? AND numero_permis = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set values for the WHERE clause from the data HashMap
            stmt.setString(1, data.get("nom"));
            stmt.setString(2, data.get("prenom"));
            stmt.setString(3, data.get("telephone"));
            stmt.setString(4, data.get("email"));
            stmt.setString(5, data.get("numero_permis"));

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Client deleted successfully!");
            } else {
                System.out.println("No client found matching the provided data. Deletion failed.");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting client: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void deletePayment(HashMap<String, String> Data) {
    }

    public static void updateUser(HashMap<String, String> oldData, HashMap<String, String> newData) {
        try {
            getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "UPDATE Utilisateur SET nom = ?, prenom = ?, email = ?, mot_de_passe = ?, role = ? " +
                "WHERE nom = ? AND prenom = ? AND email = ? AND mot_de_passe = ? AND role = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set values for the new data (the fields to be updated)
            stmt.setString(1, newData.get("nom"));
            stmt.setString(2, newData.get("prenom"));
            stmt.setString(3, newData.get("email"));
            stmt.setString(4, newData.get("mot_de_passe"));
            stmt.setString(5, newData.get("role"));

            // Set values for the old data (used in the WHERE clause to find the user)
            stmt.setString(6, oldData.get("nom"));
            stmt.setString(7, oldData.get("prenom"));
            stmt.setString(8, oldData.get("email"));
            stmt.setString(9, oldData.get("mot_de_passe"));
            stmt.setString(10, oldData.get("role"));

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User updated successfully!");
            } else {
                System.out.println("No user found matching the old data. Update failed.");
            }

        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updateVehicle(HashMap<String, String> oldData, HashMap<String, String> newData) {
        try {
            getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "UPDATE Vehicule SET marque = ?, modele = ?, annee = ?, type = ?, carburant = ?, prix_location_jour = ?, etat = ? " +
                "WHERE id_vehicule = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set values for the new data (the fields to be updated)
            stmt.setString(1, newData.get("marque"));
            stmt.setString(2, newData.get("modele"));
            stmt.setInt(3, Integer.parseInt(newData.get("annee")));
            stmt.setString(4, newData.get("type"));
            stmt.setString(5, newData.get("carburant"));
            stmt.setDouble(6, Double.parseDouble(newData.get("prix_location_jour")));
            stmt.setString(7, newData.get("etat"));

            // Set value for the WHERE clause using the id_vehicule from oldData
            stmt.setInt(8, Integer.parseInt(oldData.get("id_vehicule")));

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Vehicle updated successfully!");
            } else {
                System.out.println("No vehicle found with the provided ID. Update failed.");
            }

        } catch (SQLException e) {
            System.err.println("Error updating vehicle: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid number format for 'annee', 'prix_location_jour', or 'id_vehicule'.");
            e.printStackTrace();
        }
    }

    public static void updateClient(HashMap<String, String> oldData, HashMap<String, String> newData) {
        try {
            getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "UPDATE Client SET nom = ?, prenom = ?, telephone = ?, email = ?, numero_permis = ? " +
                "WHERE nom = ? AND prenom = ? AND telephone = ? AND email = ? AND numero_permis = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set values for the new data (the fields to be updated)
            stmt.setString(1, newData.get("nom"));
            stmt.setString(2, newData.get("prenom"));
            stmt.setString(3, newData.get("telephone"));
            stmt.setString(4, newData.get("email"));
            stmt.setString(5, newData.get("numero_permis"));

            // Set values for the WHERE clause using the old data
            stmt.setString(6, oldData.get("nom"));
            stmt.setString(7, oldData.get("prenom"));
            stmt.setString(8, oldData.get("telephone"));
            stmt.setString(9, oldData.get("email"));
            stmt.setString(10, oldData.get("numero_permis"));

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Client updated successfully!");
            } else {
                System.out.println("No client found matching the old data. Update failed.");
            }

        } catch (SQLException e) {
            System.err.println("Error updating client: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updateReservation(HashMap<String, String> oldData, HashMap<String, String> newData) {
        try {
            getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "UPDATE Reservation SET id_vehicule = ?, id_client = ?, date_debut = ?, date_fin = ?, montant_total = ?, statut = ? " +
                "WHERE id_vehicule = ? AND id_client = ? AND date_debut = ? AND date_fin = ? AND montant_total = ? AND statut = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set values for the new data (the fields to be updated)
            stmt.setInt(1, Integer.parseInt(newData.get("id_vehicule")));
            stmt.setInt(2, Integer.parseInt(newData.get("id_client")));
            stmt.setDate(3, java.sql.Date.valueOf(newData.get("date_debut")));
            stmt.setDate(4, java.sql.Date.valueOf(newData.get("date_fin")));
            stmt.setDouble(5, Double.parseDouble(newData.get("montant_total")));
            stmt.setString(6, newData.get("statut"));

            // Set values for the WHERE clause using the old data
            stmt.setInt(7, Integer.parseInt(oldData.get("id_vehicule")));
            stmt.setInt(8, Integer.parseInt(oldData.get("id_client")));
            stmt.setDate(9, java.sql.Date.valueOf(oldData.get("date_debut")));
            stmt.setDate(10, java.sql.Date.valueOf(oldData.get("date_fin")));
            stmt.setDouble(11, Double.parseDouble(oldData.get("montant_total")));
            stmt.setString(12, oldData.get("statut"));

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Reservation updated successfully!");
            } else {
                System.out.println("No reservation found matching the old data. Update failed.");
            }

        } catch (SQLException e) {
            System.err.println("Error updating reservation: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid number format for 'id_vehicule', 'id_client', or 'montant_total'.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: Invalid date format. Use yyyy-MM-dd.");
            e.printStackTrace();
        }
    }

    public static void updatePayment(HashMap<String, String> oldData, HashMap<String, String> newData) {
        try {
            getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sql = "UPDATE Paiement SET id_reservation = ?, montant = ?, date_paiement = ?, mode_paiement = ? " +
                "WHERE id_reservation = ? AND montant = ? AND date_paiement = ? AND mode_paiement = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set values for the new data (the fields to be updated)
            stmt.setInt(1, Integer.parseInt(newData.get("id_reservation")));
            stmt.setDouble(2, Double.parseDouble(newData.get("montant")));
            stmt.setDate(3, java.sql.Date.valueOf(newData.get("date_paiement")));
            stmt.setString(4, newData.get("mode_paiement"));

            // Set values for the WHERE clause using the old data
            stmt.setInt(5, Integer.parseInt(oldData.get("id_reservation")));
            stmt.setDouble(6, Double.parseDouble(oldData.get("montant")));
            stmt.setDate(7, java.sql.Date.valueOf(oldData.get("date_paiement")));
            stmt.setString(8, oldData.get("mode_paiement"));

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Payment updated successfully!");
            } else {
                System.out.println("No payment found matching the old data. Update failed.");
            }

        } catch (SQLException e) {
            System.err.println("Error updating payment: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid number format for 'id_reservation' or 'montant'.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: Invalid date format. Use yyyy-MM-dd.");
            e.printStackTrace();
        }
    }

    public static void insertUser(HashMap<String, String> data) {
        String sql = "INSERT INTO Utilisateur (nom, prenom, email, mot_de_passe, role) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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

    public static String isLoginValid(String user, String password) {
        String sql = "SELECT role FROM Utilisateur WHERE email = ? AND mot_de_passe = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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

    public static List<HashMap<String, String>> getUsers(HashMap<String, String> Constraints) throws SQLException {

        getConnection();

        List<HashMap<String, String>> usersResult = new ArrayList<>();

        List<String> parameters = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT * FROM Utilisateur");
        if (!Constraints.isEmpty()) {
            query.append(" WHERE ");
            Constraints.forEach((key, value) -> {
                query.append(key).append(" = ? AND ");
                parameters.add(value);
            });
            query.setLength(query.length() - 5); // Remove trailing " AND "
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;
            for (String param : parameters) {
                preparedStatement.setString(index++, param);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    HashMap<String, String> row = new HashMap<>();
                    row.put("nom", resultSet.getString("nom"));
                    row.put("prenom", resultSet.getString("prenom"));
                    row.put("email", resultSet.getString("email"));
                    row.put("mot_de_passe", resultSet.getString("mot_de_passe"));
                    row.put("role", resultSet.getString("role"));
                    usersResult.add(row);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usersResult;
    }

    public static List<HashMap<String, String>> getVehicles(HashMap<String, String> constraints) throws SQLException {

        try {
            getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<HashMap<String, String>> vehiclesResult = new ArrayList<>();
        List<String> parameters = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT * FROM Vehicule");
        if (!constraints.isEmpty()) {
            query.append(" WHERE ");
            constraints.forEach((key, value) -> {
                if (key.equals("id_vehicule") || key.equals("annee")) {
                    query.append(key).append(" = ? AND ");
                    try {
                        parameters.add(String.valueOf(Integer.parseInt(value)));
                    } catch (NumberFormatException e) {
                        System.err.println("Error: Invalid number format for '" + key + "'.");
                        e.printStackTrace();
                        throw new IllegalArgumentException("Invalid number format for '" + key + "'.");
                    }
                } else if (key.equals("prix_location_jour")) {
                    query.append(key).append(" = ? AND ");
                    try {
                        parameters.add(String.valueOf(Double.parseDouble(value)));
                    } catch (NumberFormatException e) {
                        System.err.println("Error: Invalid number format for 'prix_location_jour'.");
                        e.printStackTrace();
                        throw new IllegalArgumentException("Invalid number format for 'prix_location_jour'.");
                    }
                } else {
                    query.append(key).append(" = ? AND ");
                    parameters.add(value);
                }
            });
            query.setLength(query.length() - 5); // Remove trailing " AND "
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;
            for (String param : parameters) {
                preparedStatement.setString(index++, param);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    HashMap<String, String> row = new HashMap<>();
                    row.put("id_vehicule", String.valueOf(resultSet.getInt("id_vehicule")));
                    row.put("marque", resultSet.getString("marque"));
                    row.put("modele", resultSet.getString("modele"));
                    row.put("annee", String.valueOf(resultSet.getInt("annee")));
                    row.put("type", resultSet.getString("type"));
                    row.put("carburant", resultSet.getString("carburant"));
                    row.put("prix_location_jour", String.valueOf(resultSet.getBigDecimal("prix_location_jour")));
                    row.put("etat", resultSet.getString("etat"));
                    vehiclesResult.add(row);
                }
            }

            // Debugging: Print query and parameters
            System.out.println("Query: " + query);
            System.out.println("Parameters: " + parameters);

        } catch (SQLException e) {
            System.err.println("Error getting vehicles: " + e.getMessage());
            e.printStackTrace();
            throw e; // Re-throw the exception after logging
        }

        return vehiclesResult;
    }

    public static List<HashMap<String, String>> getReservations(HashMap<String, String> Constraints) {

        List<HashMap<String, String>> reservationsResult = new ArrayList<>();
        List<String> parameters = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT * FROM Reservation");
        if (!Constraints.isEmpty()) {
            query.append(" WHERE ");
            Constraints.forEach((key, value) -> {
                query.append(key).append(" = ? AND ");
                parameters.add(value);
            });
            query.setLength(query.length() - 5); // Remove trailing " AND "
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;
            for (String param : parameters) {
                preparedStatement.setString(index++, param);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    HashMap<String, String> row = new HashMap<>();
                    row.put("id_reservation", String.valueOf(resultSet.getInt("id_reservation")));
                    row.put("id_vehicule", String.valueOf(resultSet.getInt("id_vehicule")));
                    row.put("id_client", String.valueOf(resultSet.getInt("id_client")));
                    row.put("date_debut", resultSet.getString("date_debut"));
                    row.put("date_fin", resultSet.getString("date_fin"));
                    row.put("montant_total", String.valueOf(resultSet.getBigDecimal("montant_total")));
                    row.put("statut", resultSet.getString("statut"));
                    reservationsResult.add(row);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Debugging: Print query and parameters
        System.out.println("Query: " + query);
        System.out.println("Parameters: " + parameters);

        return reservationsResult;
    }

    public static List<HashMap<String, String>> getClients(HashMap<String, String> constraints) throws SQLException {

        try {
            getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<HashMap<String, String>> clientsResult = new ArrayList<>();
        List<String> parameters = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT * FROM Client");
        if (!constraints.isEmpty()) {
            query.append(" WHERE ");
            constraints.forEach((key, value) -> {
                if (key.equals("id_client")) {
                    query.append(key).append(" = ? AND ");
                    try {
                        parameters.add(String.valueOf(Integer.parseInt(value)));
                    } catch (NumberFormatException e) {
                        System.err.println("Error: Invalid number format for '" + key + "'.");
                        e.printStackTrace();
                        throw new IllegalArgumentException("Invalid number format for '" + key + "'.");
                    }
                } else {
                    query.append(key).append(" = ? AND ");
                    parameters.add(value);
                }
            });
            query.setLength(query.length() - 5); // Remove trailing " AND "
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;
            for (String param : parameters) {
                preparedStatement.setString(index++, param);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    HashMap<String, String> row = new HashMap<>();
                    row.put("id_client", String.valueOf(resultSet.getInt("id_client")));
                    row.put("nom", resultSet.getString("nom"));
                    row.put("prenom", resultSet.getString("prenom"));
                    row.put("telephone", resultSet.getString("telephone"));
                    row.put("email", resultSet.getString("email"));
                    row.put("numero_permis", resultSet.getString("numero_permis"));
                    clientsResult.add(row);
                }
            }

            // Debugging: Print query and parameters
            System.out.println("Query: " + query);
            System.out.println("Parameters: " + parameters);

        } catch (SQLException e) {
            System.err.println("Error getting clients: " + e.getMessage());
            e.printStackTrace();
            throw e; // Re-throw the exception after logging
        }

        return clientsResult;
    }

    public static List<HashMap<String, String>> getPayments(HashMap<String, String> constraints) throws SQLException {

        try {
            getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<HashMap<String, String>> paymentsResult = new ArrayList<>();
        List<String> parameters = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT * FROM Paiement");
        if (!constraints.isEmpty()) {
            query.append(" WHERE ");
            constraints.forEach((key, value) -> {
                if (key.equals("id_paiement") || key.equals("id_reservation")) {
                    query.append(key).append(" = ? AND ");
                    try {
                        parameters.add(String.valueOf(Integer.parseInt(value)));
                    } catch (NumberFormatException e) {
                        System.err.println("Error: Invalid number format for '" + key + "'.");
                        e.printStackTrace();
                        throw new IllegalArgumentException("Invalid number format for '" + key + "'.");
                    }
                } else if (key.equals("montant")) {
                    query.append(key).append(" = ? AND ");
                    try {
                        parameters.add(String.valueOf(Double.parseDouble(value)));
                    } catch (NumberFormatException e) {
                        System.err.println("Error: Invalid number format for '" + key + "'.");
                        e.printStackTrace();
                        throw new IllegalArgumentException("Invalid number format for '" + key + "'.");
                    }
                } else if (key.equals("date_paiement")) {
                    query.append(key).append(" = ? AND ");
                    try {
                        parameters.add(String.valueOf(java.sql.Date.valueOf(value)));
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error: Invalid date format for '" + key + "'. Use yyyy-MM-dd.");
                        e.printStackTrace();
                        throw new IllegalArgumentException("Invalid date format for '" + key + "'. Use yyyy-MM-dd.");
                    }
                }
                else {
                    query.append(key).append(" = ? AND ");
                    parameters.add(value);
                }
            });
            query.setLength(query.length() - 5); // Remove trailing " AND "
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;
            for (String param : parameters) {
                preparedStatement.setString(index++, param);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    HashMap<String, String> row = new HashMap<>();
                    row.put("id_paiement", String.valueOf(resultSet.getInt("id_paiement")));
                    row.put("id_reservation", String.valueOf(resultSet.getInt("id_reservation")));
                    row.put("montant", String.valueOf(resultSet.getBigDecimal("montant")));
                    row.put("date_paiement", resultSet.getString("date_paiement"));
                    row.put("mode_paiement", resultSet.getString("mode_paiement"));
                    paymentsResult.add(row);
                }
            }

            // Debugging: Print query and parameters
            System.out.println("Query: " + query);
            System.out.println("Parameters: " + parameters);

        } catch (SQLException e) {
            System.err.println("Error getting payments: " + e.getMessage());
            e.printStackTrace();
            throw e; // Re-throw the exception after logging
        }

        return paymentsResult;
    }


    public static void main(String[] args) throws SQLException {
        getConnection();
    }
}
