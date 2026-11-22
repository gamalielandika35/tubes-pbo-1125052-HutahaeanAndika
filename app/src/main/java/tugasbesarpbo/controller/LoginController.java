
package tugasbesarpbo.controller;

import tugasbesarpbo.exception.InvalidLoginException;
import tugasbesarpbo.model.User;
import tugasbesarpbo.service.AuthenticationService;
import tugasbesarpbo.util.SessionManager;
import tugasbesarpbo.view.DashboardView;
import tugasbesarpbo.view.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginController {

    private LoginView view;
    private AuthenticationService authenticationService;

    public LoginController(LoginView view) {
        this.view = view;
        authenticationService = new AuthenticationService();

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

        try {
            User user = authenticationService.login(username, password);

            SessionManager.getInstance().setCurrentUser(user);

            DashboardView dashboardView = new DashboardView(
                    user.getFullName(),
                    user.getRole().toString());

            DashboardController dashboardController = new DashboardController(dashboardView);

            dashboardController.loadDashboard();

            dashboardView.setVisible(true);
            view.dispose();

        } catch (InvalidLoginException e) {
            view.getStatusLabel().setText(e.getMessage());

        } catch (SQLException e) {
            view.getStatusLabel().setText(
                    "Terjadi kesalahan saat mengakses database.");
            e.printStackTrace();
        }
    }
}
