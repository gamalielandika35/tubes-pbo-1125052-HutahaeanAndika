
package tugasbesarpbo.view;
import tugasbesarpbo.service.ServiceOrderService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ServiceTaskView extends JPanel {

    private JComboBox<String> taskTypeComboBox;
    private JTextField serviceIdField;
    private JTextField technicianIdField;
    private JTextField laborCostField;
    private JButton completeButton;
    private ServiceOrderService serviceOrderService;

    private JTextArea diagnosisArea;
    private JTextArea repairActionArea;
    private JTextArea technicianNotesArea;

    private JButton executeButton;
    private JButton clearButton;

    private JTable taskTable;
    private DefaultTableModel tableModel;

    private JLabel statusLabel;

    public ServiceTaskView() {

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        

        JLabel titleLabel = new JLabel("Service Task Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        String[] columns = {
                "Detail ID", "Service ID", "Diagnosis",
                "Repair Action", "Estimated Cost",
                "Technician Notes", "Updated At"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        taskTable = new JTable(tableModel);
        taskTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel formPanel = new JPanel(new GridBagLayout());

        taskTypeComboBox = new JComboBox<>(
                new String[] { "DIAGNOSIS", "REPAIR" });

        serviceIdField = new JTextField(20);
        technicianIdField = new JTextField(20);
        laborCostField = new JTextField(20);

        diagnosisArea = new JTextArea(3, 20);
        repairActionArea = new JTextArea(3, 20);
        technicianNotesArea = new JTextArea(3, 20);

        diagnosisArea.setLineWrap(true);
        repairActionArea.setLineWrap(true);
        technicianNotesArea.setLineWrap(true);

        diagnosisArea.setWrapStyleWord(true);
        repairActionArea.setWrapStyleWord(true);
        technicianNotesArea.setWrapStyleWord(true);

        addFormRow(formPanel, 0, "Task Type", taskTypeComboBox);
        addFormRow(formPanel, 1, "Service ID", serviceIdField);
        addFormRow(formPanel, 2, "Technician ID", technicianIdField);
        addFormRow(formPanel, 3, "Diagnosis",
                new JScrollPane(diagnosisArea));
        addFormRow(formPanel, 4, "Repair Action",
                new JScrollPane(repairActionArea));
        addFormRow(formPanel, 5, "Estimated Cost", laborCostField);
        addFormRow(formPanel, 6, "Technician Notes",
                new JScrollPane(technicianNotesArea));

        executeButton = new JButton("Execute Task");
        clearButton = new JButton("Clear");
        completeButton = new JButton("Complete Service");

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT));

        buttonPanel.add(executeButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(clearButton);

        statusLabel = new JLabel(" ");

        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.add(formPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel southPanel = new JPanel(new BorderLayout(5, 5));
        southPanel.add(bottomPanel, BorderLayout.CENTER);
        southPanel.add(statusLabel, BorderLayout.SOUTH);

        add(titleLabel, BorderLayout.NORTH);
        add(new JScrollPane(taskTable), BorderLayout.CENTER);
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

    public JComboBox<String> getTaskTypeComboBox() {
        return taskTypeComboBox;
    }

    public JTextField getServiceIdField() {
        return serviceIdField;
    }

    public JTextField getTechnicianIdField() {
        return technicianIdField;
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

    public JButton getExecuteButton() {
        return executeButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JTable getTaskTable() {
        return taskTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }
    public JButton getCompleteButton() {
    return completeButton;
}
}
