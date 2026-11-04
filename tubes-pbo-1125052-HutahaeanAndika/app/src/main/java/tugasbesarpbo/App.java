package tugasbesarpbo;

import tugasbesarpbo.Database.databaseConnection;
import tugasbesarpbo.view.LoginView;
import tugasbesarpbo.controller.LoginController;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.SwingUtilities;

public class App {

    public static void main(String[] args) {

        try (Connection connection = databaseConnection.getConnection()) {

            System.out.println("Koneksi PostgreSQL berhasil!");
            System.out.println("Database: " + connection.getCatalog());

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    LoginView view = new LoginView();
                    new LoginController(view);
                }
            });

        } catch (SQLException e) {

            System.out.println("Koneksi database gagal!");
            System.out.println(e.getMessage());

        }
    }
}