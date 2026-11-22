
package tugasbesarpbo.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UserManagementView extends JPanel {

    private JTable userTable;
    private DefaultTableModel tableModel;

    private JTextField fullNameField;
    private JTextField usernameField;
    private JTextField phoneField;
    private JPasswordField passwordField;

    private JComboBox<String> roleComboBox;
    private JCheckBox activeCheckBox;

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;

    private JLabel statusLabel;

    public UserManagementView() {

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("User Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        String[] columns = {
            "ID", "Full Name", "Username", "Role", "Phone", "Active"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        userTable = new JTable(tableModel);
        userTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane = new JScrollPane(userTable);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 8));

        fullNameField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        phoneField = new JTextField();

        roleComboBox = new JComboBox<String>(
                new String[]{"ADMIN", "CASHIER", "TECHNICIAN"}
        );

        activeCheckBox = new JCheckBox("Active", true);

        formPanel.add(new JLabel("Full Name"));
        formPanel.add(fullNameField);

        formPanel.add(new JLabel("Username"));
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password"));
        formPanel.add(passwordField);

        formPanel.add(new JLabel("Phone"));
        formPanel.add(phoneField);

        formPanel.add(new JLabel("Role"));
        formPanel.add(roleComboBox);

        formPanel.add(new JLabel("Status"));
        formPanel.add(activeCheckBox);

        addButton = new JButton("Add User");
        updateButton = new JButton("Update User");
        deleteButton = new JButton("Delete User");
        clearButton = new JButton("Clear");

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT)
        );

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        statusLabel = new JLabel(" ");

        JPanel bottomPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel southPanel = new JPanel(
                new BorderLayout(5, 5)
        );

        southPanel.add(bottomPanel, BorderLayout.CENTER);
        southPanel.add(statusLabel, BorderLayout.SOUTH);

        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    public JTable getUserTable() {
        return userTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTextField getFullNameField() {
        return fullNameField;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }

    public JComboBox<String> getRoleComboBox() {
        return roleComboBox;
    }

    public JCheckBox getActiveCheckBox() {
        return activeCheckBox;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }
}
