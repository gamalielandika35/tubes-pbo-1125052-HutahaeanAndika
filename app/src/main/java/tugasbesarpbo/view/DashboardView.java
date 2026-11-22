
package tugasbesarpbo.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class DashboardView extends JFrame {

    private JLabel welcomeLabel;
    private JPanel menuPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private HashMap<String, JPanel> pages;
    private String currentRole;
    private JButton logoutButton;

    public DashboardView(String fullName, String role) {

        setTitle("ElectroCare - Dashboard");
        this.currentRole = role;
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        welcomeLabel = new JLabel(
                "Welcome, " + fullName + " (" + role + ")"
        );

        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        logoutButton = new JButton("Logout");

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(welcomeLabel, BorderLayout.CENTER);
        headerPanel.add(logoutButton, BorderLayout.EAST);

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0, 1, 5, 5));
        menuPanel.setPreferredSize(new Dimension(200, 0));

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        pages = new HashMap<String, JPanel>();

        addPage("Dashboard",
                new JLabel("Welcome to ElectroCare Dashboard",
                        SwingConstants.CENTER));

        add(headerPanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    public void addMenuButton(String menuName) {
        JButton button = new JButton(menuName);
        menuPanel.add(button);
    }

    public void addPage(String pageName, JComponent component) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(component, BorderLayout.CENTER);

        pages.put(pageName, panel);
        contentPanel.add(panel, pageName);
    }

    public void showPage(String pageName) {
        if (pages.containsKey(pageName) && canAccessPage(pageName)) {
            cardLayout.show(contentPanel, pageName);
        } else {
            cardLayout.show(contentPanel, "Dashboard");
        }
    }

    private boolean canAccessPage(String pageName) {

        if (pageName.equals("Dashboard")) {
            return true;
        }

        if (currentRole.equals("ADMIN")) {
            return true;
        }

        if (currentRole.equals("CASHIER")) {
            return pageName.equals("Customer")
                    || pageName.equals("Device")
                    || pageName.equals("Service Order")
                    || pageName.equals("Payment")
                    || pageName.equals("Invoice");
        }

        if (currentRole.equals("TECHNICIAN")) {
            return pageName.equals("Service Order")
                    || pageName.equals("Diagnosis")
                    || pageName.equals("Repair");
        }

        return false;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }
}
