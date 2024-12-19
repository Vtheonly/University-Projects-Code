import Tabs.*;

import javax.swing.*;
import java.awt.*;

public class MainPageController extends JFrame {

    public MainPageController(String role) {

        setTitle("Main Page - Role: " + role);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);



        JTabbedPane tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.addTab("Vehicles", new VehiclesTabPanel());
        tabbedPane.addTab("Clients", new ClientsTabPanel());
        tabbedPane.addTab("Reservations", new ReservationsTabPanel());
        tabbedPane.addTab("Admin Panel", new AdminTabPanel());
        tabbedPane.addTab("Returns", new RetrunsTabPanel());


        setVisible(true);


        Toolkit.setTabsDisabled(
                Toolkit.getPrivilege(null)
                ,tabbedPane);


    }




    // Main method for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainPageController("Admin"));
    }
}
