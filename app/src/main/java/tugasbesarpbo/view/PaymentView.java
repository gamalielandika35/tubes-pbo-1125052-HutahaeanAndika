
package tugasbesarpbo.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PaymentView extends JPanel {

    private JTable paymentTable;
    private DefaultTableModel tableModel;

    private JTextField serviceIdField;
    private JTextField receivedByField;
    private JTextField amountField;
    private JTextField referenceNoField;

    private JComboBox<String> paymentMethodComboBox;
    private JComboBox<String> paymentStatusComboBox;

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;

    private JLabel statusLabel;

    public PaymentView() {

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Payment Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        String[] columns = {
            "Payment ID", "Service ID", "Received By",
            "Total Price", "Payment Method", "Payment Status",
            "Paid At", "Reference No"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        paymentTable = new JTable(tableModel);
        paymentTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane = new JScrollPane(paymentTable);

        JPanel formPanel = new JPanel(new GridBagLayout());

        serviceIdField = new JTextField(20);
        receivedByField = new JTextField(20);
        amountField = new JTextField(20);
        referenceNoField = new JTextField(20);

        paymentMethodComboBox = new JComboBox<String>(
                new String[]{"CASH", "TRANSFER", "QRIS"}
        );

        paymentStatusComboBox = new JComboBox<String>(
                new String[]{"PAID", "PENDING", "FAILED"}
        );

        addFormRow(formPanel, 0, "Service ID", serviceIdField);
        addFormRow(formPanel, 1, "Received By", receivedByField);
        addFormRow(formPanel, 2, "Total Price", amountField);
        addFormRow(formPanel, 3, "Payment Method", paymentMethodComboBox);
        addFormRow(formPanel, 4, "Payment Status", paymentStatusComboBox);
        addFormRow(formPanel, 5, "Reference No", referenceNoField);

        addButton = new JButton("Add Payment");
        updateButton = new JButton("Update Payment");
        deleteButton = new JButton("Delete Payment");
        clearButton = new JButton("Clear");

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT)
        );

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

    public JTable getPaymentTable() {
        return paymentTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTextField getServiceIdField() {
        return serviceIdField;
    }

    public JTextField getReceivedByField() {
        return receivedByField;
    }

    public JTextField getAmountField() {
        return amountField;
    }

    public JTextField getReferenceNoField() {
        return referenceNoField;
    }

    public JComboBox<String> getPaymentMethodComboBox() {
        return paymentMethodComboBox;
    }

    public JComboBox<String> getPaymentStatusComboBox() {
        return paymentStatusComboBox;
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
