
package tugasbesarpbo.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ServiceOrderView extends JPanel {

    private JTable serviceOrderTable;
    private DefaultTableModel tableModel;

    private JTextField ticketNumberField;
    private JTextField customerIdField;
    private JTextField deviceIdField;
    private JTextField receivedByField;
    private JTextField technicianIdField;
    private JTextField estimatedCompletionField;

    private JTextArea complaintArea;

    private JComboBox<String> statusComboBox;

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;

    private JLabel statusLabel;

    public ServiceOrderView() {

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Service Order Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        String[] columns = {
                "Service ID", "Ticket Number", "Customer ID",
                "Device ID", "Received By", "Technician ID",
                "Complaint", "Status", "Received At",
                "Estimated Completion", "Completed At"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        serviceOrderTable = new JTable(tableModel);
        serviceOrderTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(serviceOrderTable);

        JPanel formPanel = new JPanel(new GridBagLayout());

        ticketNumberField = new JTextField(20);
        customerIdField = new JTextField(20);
        deviceIdField = new JTextField(20);
        receivedByField = new JTextField(20);
        technicianIdField = new JTextField(20);
        estimatedCompletionField = new JTextField(20);

        complaintArea = new JTextArea(3, 20);
        complaintArea.setLineWrap(true);
        complaintArea.setWrapStyleWord(true);

        statusComboBox = new JComboBox<String>(
                new String[] {
                        "RECEIVED",
                        "DIAGNOSING",
                        "WAITING_FOR_PARTS",
                        "IN_REPAIR",
                        "COMPLETED",
                        "CANCELLED"
                });

        addFormRow(formPanel, 0, "Ticket Number", ticketNumberField);
        addFormRow(formPanel, 1, "Customer ID", customerIdField);
        addFormRow(formPanel, 2, "Device ID", deviceIdField);
        addFormRow(formPanel, 3, "Received By", receivedByField);
        addFormRow(formPanel, 4, "Technician ID", technicianIdField);
        addFormRow(formPanel, 5, "Complaint",
                new JScrollPane(complaintArea));
        addFormRow(formPanel, 6, "Status", statusComboBox);
        addFormRow(formPanel, 7, "Estimated Completion (yyyy-MM-dd)",
                estimatedCompletionField);

        addButton = new JButton("Add Service Order");
        updateButton = new JButton("Update Service Order");
        deleteButton = new JButton("Delete Service Order");
        clearButton = new JButton("Clear");

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT));

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        statusLabel = new JLabel(" ");

        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel southPanel = new JPanel(new BorderLayout(5, 5));
        southPanel.add(bottomPanel, BorderLayout.CENTER);
        southPanel.add(statusLabel, BorderLayout.SOUTH);

        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void addFormRow(JPanel panel, int row,
            String labelText, JComponent component) {

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(4, 5, 4, 10);
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.weightx = 0;
        constraints.fill = GridBagConstraints.NONE;

        panel.add(new JLabel(labelText), constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 5, 4, 5);

        panel.add(component, constraints);
    }

    public JTable getServiceOrderTable() {
        return serviceOrderTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTextField getTicketNumberField() {
        return ticketNumberField;
    }

    public JTextField getCustomerIdField() {
        return customerIdField;
    }

    public JTextField getDeviceIdField() {
        return deviceIdField;
    }

    public JTextField getReceivedByField() {
        return receivedByField;
    }

    public JTextField getTechnicianIdField() {
        return technicianIdField;
    }

    public JTextField getEstimatedCompletionField() {
        return estimatedCompletionField;
    }

    public JTextArea getComplaintArea() {
        return complaintArea;
    }

    public JComboBox<String> getStatusComboBox() {
        return statusComboBox;
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
