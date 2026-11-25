package tugasbesarpbo.view;

import tugasbesarpbo.controller.CustomerController;
import tugasbesarpbo.model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerView extends JPanel {
    private JTextField txtId, txtName, txtPhone, txtEmail, txtAddress;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear;
    private JTable table;
    private DefaultTableModel tableModel;
    private CustomerController controller;
    private JLabel statusLabel;

    public CustomerView() {
        controller = new CustomerController();
        initComponents();
        loadCustomers();
    }

    private void initComponents() {
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        txtId = new JTextField();
        txtId.setEditable(false);
        txtName = new JTextField();
        txtPhone = new JTextField();
        txtEmail = new JTextField();
        txtAddress = new JTextField();

        formPanel.add(new JLabel("ID"));
        formPanel.add(txtId);
        formPanel.add(new JLabel("Name"));
        formPanel.add(txtName);
        formPanel.add(new JLabel("Phone"));
        formPanel.add(txtPhone);
        formPanel.add(new JLabel("Email"));
        formPanel.add(txtEmail);
        formPanel.add(new JLabel("Address"));
        formPanel.add(txtAddress);

        btnAdd = new JButton("Tambah");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Hapus");
        btnClear = new JButton("Clear");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        statusLabel = new JLabel(" ");
        statusLabel.setForeground(new Color(40, 90, 150));
        statusLabel.setBorder(
                BorderFactory.createEmptyBorder(4, 8, 4, 8)
        );

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Name", "Phone", "Email", "Address"}, 0
        );

        table = new JTable(tableModel);

        setLayout(new BorderLayout(10, 10));
        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> addCustomer());
        btnUpdate.addActionListener(e -> updateCustomer());
        btnDelete.addActionListener(e -> deleteCustomer());
        btnClear.addActionListener(e -> clearForm());

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectCustomer();
            }
        });
    }

    private void addCustomer() {
        try {
            String name = txtName.getText().trim();
            String phone = txtPhone.getText().trim();
            String email = txtEmail.getText().trim();
            String address = txtAddress.getText().trim();

            if (name.isEmpty() || phone.isEmpty()
                    || email.isEmpty() || address.isEmpty()) {
                showStatus("Semua data harus diisi.", true);
                return;
            }

            controller.addCustomer(name, phone, email, address);

            showStatus("Customer berhasil ditambahkan.", false);
            clearForm();
            loadCustomers();

        } catch (Exception e) {
            showStatus(e.getMessage(), true);
        }
    }

    private void updateCustomer() {
        try {
            if (txtId.getText().isEmpty()) {
                showStatus("Pilih customer terlebih dahulu.", true);
                return;
            }

            String name = txtName.getText().trim();
            String phone = txtPhone.getText().trim();
            String email = txtEmail.getText().trim();
            String address = txtAddress.getText().trim();

            if (name.isEmpty() || phone.isEmpty()
                    || email.isEmpty() || address.isEmpty()) {
                showStatus("Semua data harus diisi.", true);
                return;
            }

            controller.updateCustomer(
                    Long.parseLong(txtId.getText()),
                    name,
                    phone,
                    email,
                    address
            );

            showStatus("Customer berhasil diupdate.", false);
            clearForm();
            loadCustomers();

        } catch (Exception e) {
            showStatus(e.getMessage(), true);
        }
    }

    private void deleteCustomer() {
        try {
            if (txtId.getText().isEmpty()) {
                showStatus("Pilih customer terlebih dahulu.", true);
                return;
            }

            controller.deleteCustomer(
                    Long.parseLong(txtId.getText())
            );

            showStatus("Customer berhasil dihapus.", false);
            clearForm();
            loadCustomers();

        } catch (Exception e) {
            showStatus(e.getMessage(), true);
        }
    }

    private void selectCustomer() {
        int row = table.getSelectedRow();

        if (row >= 0) {
            txtId.setText(tableModel.getValueAt(row, 0).toString());
            txtName.setText(tableModel.getValueAt(row, 1).toString());
            txtPhone.setText(tableModel.getValueAt(row, 2).toString());
            txtEmail.setText(tableModel.getValueAt(row, 3).toString());
            txtAddress.setText(tableModel.getValueAt(row, 4).toString());
        }
    }

    private void loadCustomers() {
        try {
            tableModel.setRowCount(0);

            List<Customer> customers = controller.getAllCustomers();

            for (Customer customer : customers) {
                tableModel.addRow(new Object[]{
                        customer.getCustomerId(),
                        customer.getName(),
                        customer.getPhone(),
                        customer.getEmail(),
                        customer.getAddress()
                });
            }

        } catch (Exception e) {
            showStatus(e.getMessage(), true);
        }
    }

    private void clearForm() {
        txtId.setText("");
        txtName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        table.clearSelection();
    }

    private void showStatus(String message, boolean isError) {
        statusLabel.setText(
                message == null ? "Terjadi kesalahan." : message
        );

        statusLabel.setForeground(
                isError ? Color.RED : new Color(0, 128, 0)
        );
    }
}