import Connector.JDBConnector;
import Tabs.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.HashMap;

public class MainPageController extends JFrame {

    public MainPageController(String role) {

        setTitle("Main Page - Role: " + role);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);



        JTabbedPane tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);



        tabbedPane.addTab("Achter",new AchterTabPanel());
        tabbedPane.addTab("Clients", new ClientsTabPanel());
        tabbedPane.addTab("Reservations", new ReservationsTabPanel());
        tabbedPane.addTab("Vehicles", new VehiclesTabPanel());
        tabbedPane.addTab("Admin Panel", new AdminTabPanel());
        try {
            AchterTabPanel.populateTable(JDBConnector.getPayments(new HashMap<>()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            ClientsTabPanel.populateTable(JDBConnector.getClients(new HashMap<>()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ReservationsTabPanel.populateTable(JDBConnector.getReservations(new HashMap<>()));



        try {
            VehiclesTabPanel.populateTable(JDBConnector.getVehicles(new HashMap<>()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            AdminTabPanel.populateTable(JDBConnector.getUsers(new HashMap<>()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        setVisible(true);

        if(role.equals("admin")) {
            Toolkit.setTabsDisabled(
                    new int[] {,}
                    ,tabbedPane);
        }else if(role.equals("manaager")) {
            Toolkit.setTabsDisabled(
                    new int[] {4}
                    ,tabbedPane);
        }
        else if(role.equals("client")) {
            Toolkit.setTabsDisabled(
                    new int[] {1,2,3,4}
                    ,tabbedPane);
        }


    }




    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainPageController("Admin"));
    }
}
