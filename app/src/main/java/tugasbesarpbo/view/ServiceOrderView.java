
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
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane = new JScrollPane(serviceOrderTable);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 8));

        ticketNumberField = new JTextField();
        customerIdField = new JTextField();
        deviceIdField = new JTextField();
        receivedByField = new JTextField();
        technicianIdField = new JTextField();
        estimatedCompletionField = new JTextField();

        complaintArea = new JTextArea(3, 20);
        complaintArea.setLineWrap(true);
        complaintArea.setWrapStyleWord(true);

        statusComboBox = new JComboBox<String>(
                new String[]{
                    "PENDING",
                    "IN_PROGRESS",
                    "COMPLETED",
                    "CANCELLED"
                }
        );

        formPanel.add(new JLabel("Ticket Number"));
        formPanel.add(ticketNumberField);

        formPanel.add(new JLabel("Customer ID"));
        formPanel.add(customerIdField);

        formPanel.add(new JLabel("Device ID"));
        formPanel.add(deviceIdField);

        formPanel.add(new JLabel("Received By"));
        formPanel.add(receivedByField);

        formPanel.add(new JLabel("Technician ID"));
        formPanel.add(technicianIdField);

        formPanel.add(new JLabel("Complaint"));
        formPanel.add(new JScrollPane(complaintArea));

        formPanel.add(new JLabel("Status"));
        formPanel.add(statusComboBox);

        formPanel.add(new JLabel("Estimated Completion (yyyy-MM-dd)"));
        formPanel.add(estimatedCompletionField);

        addButton = new JButton("Add Service Order");
        updateButton = new JButton("Update Service Order");
        deleteButton = new JButton("Delete Service Order");
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
