
package tugasbesarpbo.view;

import javax.swing.*;
import java.awt.*;
import tugasbesarpbo.util.UIStyle;

public class LoginView extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;

    public LoginView() {

        setTitle("ElectroCare - Login");
        setSize(450, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(UIStyle.BACKGROUND);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(UIStyle.BACKGROUND);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(0, 1, 8, 8));
        loginPanel.setBackground(UIStyle.WHITE);
        loginPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                UIStyle.BORDER, 1),
                        BorderFactory.createEmptyBorder(
                                30, 35, 30, 35)
                )
        );

        JLabel titleLabel = new JLabel(
                "ELECTROCARE",
                SwingConstants.CENTER
        );
        titleLabel.setFont(new Font(
                "Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(UIStyle.NAVY);

        JLabel subtitleLabel = new JLabel(
                "Service Management System",
                SwingConstants.CENTER
        );
        subtitleLabel.setFont(UIStyle.getNormalFont());
        subtitleLabel.setForeground(UIStyle.GRAY);

        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");

        UIStyle.styleLabel(usernameLabel);
        UIStyle.styleLabel(passwordLabel);

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        UIStyle.styleTextField(usernameField);
        UIStyle.styleTextField(passwordField);

        loginButton = new JButton("Login");
        UIStyle.stylePrimaryButton(loginButton);

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(UIStyle.getNormalFont());
        statusLabel.setForeground(UIStyle.RED);

        loginPanel.add(titleLabel);
        loginPanel.add(subtitleLabel);
        loginPanel.add(new JLabel(""));
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel(""));
        loginPanel.add(loginButton);
        loginPanel.add(statusLabel);

        mainPanel.add(loginPanel);

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }
}
