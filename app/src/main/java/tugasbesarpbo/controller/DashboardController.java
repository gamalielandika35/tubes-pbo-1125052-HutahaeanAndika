
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
import tugasbesarpbo.view.ServiceTaskView;
import tugasbesarpbo.model.DashboardStatistics;
import tugasbesarpbo.service.DashboardService;
import java.sql.SQLException;
import tugasbesarpbo.view.CustomerView;
import tugasbesarpbo.view.DeviceView;

public class DashboardController {

    private DashboardView dashboardView;
    private DashboardService dashboardService;

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
        dashboardService = new DashboardService();

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
        dashboardView.showPage("Dashboard");
        loadDashboardStatistics();

        dashboardView.revalidate();
        dashboardView.repaint();
    }

    private void loadDashboardStatistics() {

        try {
            DashboardStatistics statistics = dashboardService.getStatistics();

            dashboardView.setDashboardStatistics(
                    statistics.getTotalServiceOrders(),
                    statistics.getCompletedOrders(),
                    statistics.getInProgress(),
                    statistics.getTotalCustomers());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAdminMenu() {
        addMenu("Dashboard");
        addMenu("User Management");
        addMenu("Customer");
        addMenu("Device");
        addMenu("Service Order");
        addMenu("Service Task");
        addMenu("Payment");
    }

    private void loadCashierMenu() {
        addMenu("Dashboard");
        addMenu("Customer");
        addMenu("Device");
        addMenu("Service Order");
        addMenu("Payment");
    }

    private void loadTechnicianMenu() {
        addMenu("Dashboard");
        addMenu("Service Order");
        addMenu("Service Task");

    }

    private void addMenu(String menuName) {

        dashboardView.addMenuButton(menuName);

        if (menuName.equals("Dashboard")) {

            // bagian ini ada di dashboard view

        } else if (menuName.equals("User Management")) {

            UserManagementView userManagementView = new UserManagementView();

            new UserManagementController(userManagementView);
            dashboardView.addPage(menuName, userManagementView);

        } else if (menuName.equals("Customer")) {

            CustomerView customerView = new CustomerView();
            dashboardView.addPage(menuName, customerView);

        } else if (menuName.equals("Device")) {

            DeviceView deviceView = new DeviceView();
            dashboardView.addPage(menuName, deviceView);
        } else if (menuName.equals("Service Order")) {

            ServiceOrderView serviceOrderView = new ServiceOrderView();

            new ServiceOrderController(serviceOrderView);
            dashboardView.addPage(menuName, serviceOrderView);

        } else if (menuName.equals("Payment")) {

            PaymentView paymentView = new PaymentView();

            new PaymentController(paymentView);
            dashboardView.addPage(menuName, paymentView);

        } else if (menuName.equals("Service Task")) {

            ServiceTaskView serviceTaskView = new ServiceTaskView();

            new ServiceTaskController(serviceTaskView);
            dashboardView.addPage(menuName, serviceTaskView);

        } else {

            JPanel page = new JPanel();
            page.add(new JLabel(menuName + " Page"));

            dashboardView.addPage(menuName, page);
        }

        JButton button = (JButton) dashboardView.getMenuPanel()
                .getComponent(
                        dashboardView.getMenuPanel().getComponentCount() - 1);

        button.addActionListener(new ActionListener() {
            @Override
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
