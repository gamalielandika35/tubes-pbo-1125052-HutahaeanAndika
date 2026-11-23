
package tugasbesarpbo.controller;

import tugasbesarpbo.model.Payment;
import tugasbesarpbo.service.PaymentService;
import tugasbesarpbo.service.PaymentProcessor;
import tugasbesarpbo.Strategy.CashPayment;
import tugasbesarpbo.Strategy.TransferPayment;
import tugasbesarpbo.Strategy.QrisPayment;
import tugasbesarpbo.view.PaymentView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.SQLException;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PaymentController {

    private PaymentView paymentView;
    private PaymentService paymentService;
    private PaymentProcessor paymentProcessor;

    public PaymentController(PaymentView paymentView) {
        this.paymentView = paymentView;
        paymentService = new PaymentService();
        paymentProcessor = new PaymentProcessor();

        paymentView.getAddButton().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPayment();
            }
        });

        paymentView.getUpdateButton().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePayment();
            }
        });

        paymentView.getDeleteButton().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePayment();
            }
        });

        paymentView.getClearButton().addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        paymentView.getPaymentTable().getSelectionModel()
                .addListSelectionListener(
                new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectPayment();
                }
            }
        });

        loadPayments();
    }

    private void addPayment() {
        try {
            long serviceId = Long.parseLong(
                    paymentView.getServiceIdField().getText()
            );

            long receivedBy = Long.parseLong(
                    paymentView.getReceivedByField().getText()
            );

            BigDecimal amount = new BigDecimal(
                    paymentView.getAmountField().getText()
            );

            String method = paymentView.getPaymentMethodComboBox()
                    .getSelectedItem().toString();

            selectStrategy(method);

            boolean success = paymentProcessor.processPayment(amount);

            if (!success) {
                paymentView.getStatusLabel().setText(
                        "Payment failed!"
                );
                return;
            }

            String paymentStatus = paymentView
                    .getPaymentStatusComboBox()
                    .getSelectedItem().toString();

            String referenceNo = paymentView
                    .getReferenceNoField().getText();

            Payment payment = new Payment(
                    0,
                    serviceId,
                    receivedBy,
                    LocalDateTime.now(),
                    amount,
                    paymentProcessor.getPaymentMethod(),
                    paymentStatus,
                    referenceNo
            );

            paymentService.addPayment(payment);

            paymentView.getStatusLabel().setText(
                    "Payment added successfully!"
            );

            loadPayments();
            clearForm();

        } catch (NumberFormatException e) {
            paymentView.getStatusLabel().setText(
                    "Please enter valid numeric values!"
            );
        } catch (SQLException e) {
            paymentView.getStatusLabel().setText(
                    "Database error: " + e.getMessage()
            );
        } catch (Exception e) {
            paymentView.getStatusLabel().setText(
                    "Error: " + e.getMessage()
            );
        }
    }

    private void updatePayment() {
        int selectedRow = paymentView.getPaymentTable()
                .getSelectedRow();

        if (selectedRow == -1) {
            paymentView.getStatusLabel().setText(
                    "Select a payment first!"
            );
            return;
        }

        try {
            long paymentId = Long.parseLong(
                    paymentView.getTableModel()
                            .getValueAt(selectedRow, 0).toString()
            );

            Payment oldPayment = paymentService
                    .getPaymentById(paymentId);

            if (oldPayment == null) {
                paymentView.getStatusLabel().setText(
                        "Payment not found!"
                );
                return;
            }

            long serviceId = Long.parseLong(
                    paymentView.getServiceIdField().getText()
            );

            long receivedBy = Long.parseLong(
                    paymentView.getReceivedByField().getText()
            );

            BigDecimal amount = new BigDecimal(
                    paymentView.getAmountField().getText()
            );

            String method = paymentView.getPaymentMethodComboBox()
                    .getSelectedItem().toString();

            selectStrategy(method);

            boolean success = paymentProcessor.processPayment(amount);

            if (!success) {
                paymentView.getStatusLabel().setText(
                        "Payment failed!"
                );
                return;
            }

            Payment payment = new Payment(
                    paymentId,
                    serviceId,
                    receivedBy,
                    oldPayment.getPaidAt(),
                    amount,
                    paymentProcessor.getPaymentMethod(),
                    paymentView.getPaymentStatusComboBox()
                            .getSelectedItem().toString(),
                    paymentView.getReferenceNoField().getText()
            );

            paymentService.updatePayment(payment);

            paymentView.getStatusLabel().setText(
                    "Payment updated successfully!"
            );

            loadPayments();
            clearForm();

        } catch (NumberFormatException e) {
            paymentView.getStatusLabel().setText(
                    "Please enter valid numeric values!"
            );
        } catch (SQLException e) {
            paymentView.getStatusLabel().setText(
                    "Database error: " + e.getMessage()
            );
        } catch (Exception e) {
            paymentView.getStatusLabel().setText(
                    "Error: " + e.getMessage()
            );
        }
    }

    private void deletePayment() {
        int selectedRow = paymentView.getPaymentTable()
                .getSelectedRow();

        if (selectedRow == -1) {
            paymentView.getStatusLabel().setText(
                    "Select a payment first!"
            );
            return;
        }

        try {
            long paymentId = Long.parseLong(
                    paymentView.getTableModel()
                            .getValueAt(selectedRow, 0).toString()
            );

            paymentService.deletePayment(paymentId);

            paymentView.getStatusLabel().setText(
                    "Payment deleted successfully!"
            );

            loadPayments();
            clearForm();

        } catch (SQLException e) {
            paymentView.getStatusLabel().setText(
                    "Database error: " + e.getMessage()
            );
        }
    }

    private void loadPayments() {
        try {
            List<Payment> payments = paymentService.getAllPayments();

            paymentView.getTableModel().setRowCount(0);

            for (Payment payment : payments) {
                paymentView.getTableModel().addRow(
                        new Object[]{
                            payment.getPaymentId(),
                            payment.getServiceId(),
                            payment.getReceivedBy(),
                            payment.getAmount(),
                            payment.getPaymentMethod(),
                            payment.getPaymentStatus(),
                            payment.getPaidAt(),
                            payment.getReferenceNo()
                        }
                );
            }

        } catch (SQLException e) {
            paymentView.getStatusLabel().setText(
                    "Failed to load payments: " + e.getMessage()
            );
        }
    }

    private void selectPayment() {
        int selectedRow = paymentView.getPaymentTable()
                .getSelectedRow();

        if (selectedRow == -1) {
            return;
        }

        paymentView.getServiceIdField().setText(
                paymentView.getTableModel()
                        .getValueAt(selectedRow, 1).toString()
        );

        paymentView.getReceivedByField().setText(
                paymentView.getTableModel()
                        .getValueAt(selectedRow, 2).toString()
        );

        paymentView.getAmountField().setText(
                paymentView.getTableModel()
                        .getValueAt(selectedRow, 3).toString()
        );

        paymentView.getPaymentMethodComboBox().setSelectedItem(
                paymentView.getTableModel()
                        .getValueAt(selectedRow, 4).toString()
        );

        paymentView.getPaymentStatusComboBox().setSelectedItem(
                paymentView.getTableModel()
                        .getValueAt(selectedRow, 5).toString()
        );

        Object reference = paymentView.getTableModel()
                .getValueAt(selectedRow, 7);

        paymentView.getReferenceNoField().setText(
                reference == null ? "" : reference.toString()
        );
    }

    private void selectStrategy(String method) {

        if (method.equals("CASH")) {
            paymentProcessor.setPaymentStrategy(
                    new CashPayment()
            );

        } else if (method.equals("TRANSFER")) {
            paymentProcessor.setPaymentStrategy(
                    new TransferPayment()
            );

        } else if (method.equals("QRIS")) {
            paymentProcessor.setPaymentStrategy(
                    new QrisPayment()
            );
        }
    }

    private void clearForm() {
        paymentView.getPaymentTable().clearSelection();

        paymentView.getServiceIdField().setText("");
        paymentView.getReceivedByField().setText("");
        paymentView.getAmountField().setText("");
        paymentView.getReferenceNoField().setText("");

        paymentView.getPaymentMethodComboBox().setSelectedIndex(0);
        paymentView.getPaymentStatusComboBox().setSelectedIndex(0);

        paymentView.getStatusLabel().setText(" ");
    }
}
