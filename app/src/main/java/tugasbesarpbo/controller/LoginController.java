package tugasbesarpbo.controller;

import tugasbesarpbo.view.LoginView;
import tugasbesarpbo.Database.databaseConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tugasbesarpbo.util.PasswordUtil;

public class LoginController {

    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;

        view.getLoginButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    private void login() {
        String username = view.getUsernameField().getText();
        String password = new String(
                view.getPasswordField().getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            view.getStatusLabel().setText(
                    "Username dan password harus diisi!");
            return;
        }

        String sql = "SELECT password_hash, role FROM users "
                + "WHERE username = ? AND is_active = TRUE";

        try (Connection connection = databaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {

                    String passwordHash = result.getString("password_hash");

                    String role = result.getString("role");

                    boolean passwordValid = PasswordUtil.checkPassword(password, passwordHash);

                    if (passwordValid) {
                        view.getStatusLabel().setText(
                                "Login berhasil! Role: " + role);
                    } else {
                        view.getStatusLabel().setText(
                                "Password salah!");
                    }

                } else {
                    view.getStatusLabel().setText(
                            "Username tidak ditemukan atau akun tidak aktif.");
                }
            }

        } catch (SQLException e) {

            view.getStatusLabel().setText(
                    "Terjadi kesalahan saat mengakses database.");

            e.printStackTrace();
        }
    }
}