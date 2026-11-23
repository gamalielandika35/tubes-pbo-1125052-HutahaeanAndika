
package tugasbesarpbo.controller;

import tugasbesarpbo.model.Role;
import tugasbesarpbo.model.User;
import tugasbesarpbo.util.SessionManager;
import tugasbesarpbo.view.DashboardView;
import tugasbesarpbo.view.LoginView;
import tugasbesarpbo.view.UserManagementView;
import tugasbesarpbo.view.ServiceOrderView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tugasbesarpbo.view.PaymentView;
import tugasbesarpbo.view.ServiceDetailView;

public class DashboardController {

    private DashboardView dashboardView;

    public DashboardController(DashboardView dashboardView) {
        this.dashboardView = dashboardView;

        dashboardView.getLogoutButton().addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        logout();
                    }
                });
    }

    public void loadDashboard() {
        User currentUser = SessionManager.getInstance().getCurrentUser();

        if (currentUser == null) {
            return;
        }

        Role role = currentUser.getRole();

        if (role == Role.ADMIN) {
            loadAdminMenu();
        } else if (role == Role.CASHIER) {
            loadCashierMenu();
        } else if (role == Role.TECHNICIAN) {
            loadTechnicianMenu();
        }

        dashboardView.revalidate();
        dashboardView.repaint();
    }

    private void loadAdminMenu() {
        addMenu("Dashboard");
        addMenu("User Management");
        addMenu("Customer");
        addMenu("Device");
        addMenu("Service Order");
        addMenu("Reports");
        addMenu("Service Detail");
    }

    private void loadCashierMenu() {
        addMenu("Dashboard");
        addMenu("Customer");
        addMenu("Device");
        addMenu("Service Order");
        addMenu("Payment");
        addMenu("Invoice");
    }

    private void loadTechnicianMenu() {
        addMenu("Dashboard");
        addMenu("Service Order");
        addMenu("Diagnosis");
        addMenu("Repair");
        addMenu("Service Detail");
    }

    private void addMenu(String menuName) {
        dashboardView.addMenuButton(menuName);

        if (menuName.equals("User Management")) {
            UserManagementView userManagementView = new UserManagementView();

            new UserManagementController(userManagementView);
            dashboardView.addPage(menuName, userManagementView);

        } else if (menuName.equals("Service Order")) {
            ServiceOrderView serviceOrderView = new ServiceOrderView();

            new ServiceOrderController(serviceOrderView);
            dashboardView.addPage(menuName, serviceOrderView);

        } else if (menuName.equals("Payment")) {
            PaymentView paymentView = new PaymentView();

            new PaymentController(paymentView);
            dashboardView.addPage(menuName, paymentView);
        } else if (menuName.equals("Service Detail")) {
            ServiceDetailView serviceDetailView = new ServiceDetailView();
            
            new ServiceDetailController(serviceDetailView);
            dashboardView.addPage(menuName, serviceDetailView);
        }else {
            JPanel page = new JPanel();
            page.add(new JLabel(menuName + " Page"));
            dashboardView.addPage(menuName, page);
        }

        JButton button = (JButton) dashboardView.getMenuPanel()
                .getComponent(
                        dashboardView.getMenuPanel().getComponentCount() - 1);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dashboardView.showPage(menuName);
            }
        });
    }

    private void logout() {
        SessionManager.getInstance().logout();

        LoginView loginView = new LoginView();
        new LoginController(loginView);

        loginView.setVisible(true);
        dashboardView.dispose();
    }
}
