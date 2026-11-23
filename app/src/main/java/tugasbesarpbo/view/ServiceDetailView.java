
package tugasbesarpbo.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ServiceDetailView extends JPanel {

    private JTable serviceDetailTable;
    private DefaultTableModel tableModel;

    private JTextField serviceIdField;
    private JTextField laborCostField;

    private JTextArea diagnosisArea;
    private JTextArea repairActionArea;
    private JTextArea technicianNotesArea;

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;

    private JLabel statusLabel;

    public ServiceDetailView() {

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Service Detail Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        String[] columns = {
            "Detail ID",
            "Service ID",
            "Diagnosis",
            "Repair Action",
            "Labor Cost",
            "Technician Notes",
            "Updated At"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        serviceDetailTable = new JTable(tableModel);
        serviceDetailTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane = new JScrollPane(serviceDetailTable);

        JPanel formPanel = new JPanel(new GridBagLayout());

        serviceIdField = new JTextField(20);
        laborCostField = new JTextField(20);

        diagnosisArea = new JTextArea(3, 20);
        diagnosisArea.setLineWrap(true);
        diagnosisArea.setWrapStyleWord(true);

        repairActionArea = new JTextArea(3, 20);
        repairActionArea.setLineWrap(true);
        repairActionArea.setWrapStyleWord(true);

        technicianNotesArea = new JTextArea(3, 20);
        technicianNotesArea.setLineWrap(true);
        technicianNotesArea.setWrapStyleWord(true);

        addFormRow(formPanel, 0, "Service ID", serviceIdField);

        addFormRow(formPanel, 1, "Diagnosis",
                new JScrollPane(diagnosisArea));

        addFormRow(formPanel, 2, "Repair Action",
                new JScrollPane(repairActionArea));

        addFormRow(formPanel, 3, "Labor Cost", laborCostField);

        addFormRow(formPanel, 4, "Technician Notes",
                new JScrollPane(technicianNotesArea));

        addButton = new JButton("Add Service Detail");
        updateButton = new JButton("Update Service Detail");
        deleteButton = new JButton("Delete Service Detail");
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

    public JTable getServiceDetailTable() {
        return serviceDetailTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTextField getServiceIdField() {
        return serviceIdField;
    }

    public JTextField getLaborCostField() {
        return laborCostField;
    }

    public JTextArea getDiagnosisArea() {
        return diagnosisArea;
    }

    public JTextArea getRepairActionArea() {
        return repairActionArea;
    }

    public JTextArea getTechnicianNotesArea() {
        return technicianNotesArea;
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
