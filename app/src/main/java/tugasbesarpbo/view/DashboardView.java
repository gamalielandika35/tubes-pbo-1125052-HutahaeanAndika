
package tugasbesarpbo.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import tugasbesarpbo.util.UIStyle;

public class DashboardView extends JFrame {

    private JLabel welcomeLabel;
    private JPanel menuPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private HashMap<String, JPanel> pages;
    private HashMap<String, JButton> menuButtons;
    private String currentRole;
    private String activeMenu;
    private JButton logoutButton;
    private JLabel totalServiceOrdersLabel;
    private JLabel completedOrdersLabel;
    private JLabel inProgressLabel;
    private JLabel totalCustomersLabel;

    public DashboardView(String fullName, String role) {

        setTitle("ElectroCare - Dashboard");
        currentRole = role;

        setSize(1100, 700);
        setMinimumSize(new Dimension(900, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        getContentPane().setBackground(UIStyle.BACKGROUND);

        pages = new HashMap<String, JPanel>();
        menuButtons = new HashMap<String, JButton>();

        welcomeLabel = new JLabel(
                "Welcome, " + fullName + "  |  " + role);
        welcomeLabel.setFont(UIStyle.getHeadingFont());
        welcomeLabel.setForeground(Color.WHITE);

        logoutButton = new JButton("Logout");
        UIStyle.styleDangerButton(logoutButton);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(UIStyle.NAVY);
        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 20, 15, 20));

        headerPanel.add(welcomeLabel, BorderLayout.CENTER);
        headerPanel.add(logoutButton, BorderLayout.EAST);

        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0, 1, 5, 8));
        menuPanel.setBackground(UIStyle.NAVY);
        menuPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 10, 15, 10));
        menuPanel.setPreferredSize(new Dimension(210, 0));

        cardLayout = new CardLayout();

        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(UIStyle.BACKGROUND);
        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Membuat halaman Dashboard
        addPage("Dashboard", createDashboardPanel());

        add(headerPanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createDashboardPanel() {

        JPanel mainPanel = new JPanel(new BorderLayout(15, 20));
        mainPanel.setBackground(UIStyle.BACKGROUND);
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Dashboard Overview");
        titleLabel.setFont(UIStyle.getTitleFont());
        titleLabel.setForeground(UIStyle.NAVY);

        JLabel subtitleLabel = new JLabel(
                "Welcome to ElectroCare Service Management System");
        subtitleLabel.setFont(UIStyle.getNormalFont());
        subtitleLabel.setForeground(UIStyle.GRAY);

        JPanel headingPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        headingPanel.setOpaque(false);
        headingPanel.add(titleLabel);
        headingPanel.add(subtitleLabel);

        topPanel.add(headingPanel, BorderLayout.CENTER);

        JPanel statisticsPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        statisticsPanel.setOpaque(false);

        totalServiceOrdersLabel = createStatCard(
                "Total Service Orders", UIStyle.BLUE);

        completedOrdersLabel = createStatCard(
                "Completed Orders", UIStyle.GREEN);

        inProgressLabel = createStatCard(
                "In Progress", UIStyle.ORANGE);

        totalCustomersLabel = createStatCard(
                "Total Customers", new Color(124, 58, 237));

        statisticsPanel.add(totalServiceOrdersLabel.getParent());
        statisticsPanel.add(completedOrdersLabel.getParent());
        statisticsPanel.add(inProgressLabel.getParent());
        statisticsPanel.add(totalCustomersLabel.getParent());

        JPanel informationPanel = new JPanel(new BorderLayout(10, 10));
        informationPanel.setBackground(UIStyle.WHITE);
        informationPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(UIStyle.BORDER),
                        BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        JLabel informationTitle = new JLabel("Service Management");
        informationTitle.setFont(UIStyle.getHeadingFont());
        informationTitle.setForeground(UIStyle.NAVY);

        JPanel informationList = new JPanel(
                new GridLayout(3, 1, 5, 5));
        informationList.setBackground(UIStyle.WHITE);

        informationList.add(new JLabel("Service Order Management"));
        informationList.add(new JLabel("Diagnosis and Repair Management"));
        informationList.add(new JLabel("Payment and Invoice Management"));

        informationPanel.add(
                informationTitle, BorderLayout.NORTH);
        informationPanel.add(
                informationList, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(statisticsPanel, BorderLayout.CENTER);
        mainPanel.add(informationPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JLabel createStatCard(String title, Color color) {

        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(UIStyle.WHITE);
        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(UIStyle.BORDER),
                        BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(UIStyle.getNormalFont());
        titleLabel.setForeground(UIStyle.GRAY);

        JLabel valueLabel = new JLabel("--");
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        valueLabel.setForeground(color);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return valueLabel;
    }

    public void addMenuButton(String menuName) {

        JButton button = new JButton(menuName);

        button.setFont(UIStyle.getNormalFont());
        button.setForeground(Color.WHITE);
        button.setBackground(UIStyle.NAVY);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(
                BorderFactory.createEmptyBorder(12, 15, 12, 10));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        menuButtons.put(menuName, button);
        menuPanel.add(button);
    }

    public void setActiveMenu(String menuName) {

        activeMenu = menuName;

        for (String name : menuButtons.keySet()) {

            JButton button = menuButtons.get(name);

            if (name.equals(menuName)) {
                button.setBackground(UIStyle.BLUE);
                button.setForeground(Color.WHITE);
            } else {
                button.setBackground(UIStyle.NAVY);
                button.setForeground(Color.WHITE);
            }
        }
    }

    public void addPage(String pageName, JComponent component) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(UIStyle.BACKGROUND);
        panel.add(component, BorderLayout.CENTER);

        pages.put(pageName, panel);
        contentPanel.add(panel, pageName);
    }

    public void showPage(String pageName) {

        if (pages.containsKey(pageName) && canAccessPage(pageName)) {

            cardLayout.show(contentPanel, pageName);
            setActiveMenu(pageName);

        } else {

            cardLayout.show(contentPanel, "Dashboard");
            setActiveMenu("Dashboard");
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
                    || pageName.equals("Service Task");
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

    public void setDashboardStatistics(
            int totalServiceOrders,
            int completedOrders,
            int inProgress,
            int totalCustomers) {

        totalServiceOrdersLabel.setText(
                String.valueOf(totalServiceOrders));

        completedOrdersLabel.setText(
                String.valueOf(completedOrders));

        inProgressLabel.setText(
                String.valueOf(inProgress));

        totalCustomersLabel.setText(
                String.valueOf(totalCustomers));
    }
}
