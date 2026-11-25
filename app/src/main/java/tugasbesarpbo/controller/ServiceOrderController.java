package tugasbesarpbo.controller;

import tugasbesarpbo.model.ServiceOrder;
import tugasbesarpbo.model.Status;
import tugasbesarpbo.service.ServiceOrderService;
import tugasbesarpbo.view.ServiceOrderView;
import tugasbesarpbo.exception.InvalidServiceOrderException;
import tugasbesarpbo.exception.ServiceOrderNotFoundException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServiceOrderController {

    private ServiceOrderView view;
    private ServiceOrderService serviceOrderService;

    public ServiceOrderController(ServiceOrderView view) {
        this.view = view;
        serviceOrderService = new ServiceOrderService();

        view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addServiceOrder();
            }
        });

        view.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateServiceOrder();
            }
        });

        view.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteServiceOrder();
            }
        });

        view.getClearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        view.getServiceOrderTable().getSelectionModel()
                .addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectServiceOrder();
                }
            }
        });

        loadServiceOrders();
    }

    public void loadServiceOrders() {
        try {
            List<ServiceOrder> serviceOrders =
                    serviceOrderService.getAllServiceOrders();

            view.getTableModel().setRowCount(0);

            for (ServiceOrder serviceOrder : serviceOrders) {
                view.getTableModel().addRow(new Object[]{
                    serviceOrder.getServiceId(),
                    serviceOrder.getTicketNumber(),
                    serviceOrder.getCustomerId(),
                    serviceOrder.getDeviceId(),
                    serviceOrder.getReceivedBy(),
                    serviceOrder.getTechnicianId(),
                    serviceOrder.getComplaint(),
                    serviceOrder.getStatus(),
                    serviceOrder.getReceivedAt(),
                    serviceOrder.getEstimatedCompletion(),
                    serviceOrder.getCompletedAt()
                });
            }

            view.getStatusLabel().setText("Data berhasil dimuat.");

        } catch (SQLException e) {
            view.getStatusLabel().setText(
                    "Gagal memuat data: " + e.getMessage()
            );
        }
    }

    private void addServiceOrder() {
        try {
            String technicianText =
                    view.getTechnicianIdField().getText().trim();

            Long technicianId = null;

            if (!technicianText.isEmpty()) {
                technicianId = Long.parseLong(technicianText);
            }

            Status status = Status.valueOf(
                    view.getStatusComboBox()
                            .getSelectedItem().toString()
            );

            ServiceOrder serviceOrder = new ServiceOrder(
                    0,
                    view.getTicketNumberField().getText().trim(),
                    Long.parseLong(view.getCustomerIdField().getText()),
                    Long.parseLong(view.getDeviceIdField().getText()),
                    Long.parseLong(view.getReceivedByField().getText()),
                    technicianId,
                    view.getComplaintArea().getText().trim(),
                    status.name(),
                    LocalDateTime.now(),
                    LocalDate.parse(
                            view.getEstimatedCompletionField().getText()
                    ),
                    null,
                    null
            );

            serviceOrderService.addServiceOrder(serviceOrder);

            view.getStatusLabel().setText(
                    "Service order berhasil ditambahkan."
            );

            clearForm();
            loadServiceOrders();

        } catch (InvalidServiceOrderException e) {
            view.getStatusLabel().setText(e.getMessage());

        } catch (IllegalArgumentException e) {
            view.getStatusLabel().setText(
                    "Periksa kembali input angka, tanggal, dan status."
            );

        } catch (SQLException e) {
            view.getStatusLabel().setText(
                    "Gagal menambahkan data: " + e.getMessage()
            );
        }
    }

    private void updateServiceOrder() {
        int selectedRow =
                view.getServiceOrderTable().getSelectedRow();

        if (selectedRow == -1) {
            view.getStatusLabel().setText(
                    "Pilih data yang ingin diperbarui."
            );
            return;
        }

        try {
            long serviceId = Long.parseLong(
                    view.getTableModel()
                            .getValueAt(selectedRow, 0).toString()
            );

            ServiceOrder oldServiceOrder =
                    serviceOrderService.getServiceOrderById(serviceId);

            String technicianText =
                    view.getTechnicianIdField().getText().trim();

            Long technicianId = null;

            if (!technicianText.isEmpty()) {
                technicianId = Long.parseLong(technicianText);
            }

            Status status = Status.valueOf(
                    view.getStatusComboBox()
                            .getSelectedItem().toString()
            );

            ServiceOrder serviceOrder = new ServiceOrder(
                    serviceId,
                    view.getTicketNumberField().getText().trim(),
                    Long.parseLong(view.getCustomerIdField().getText()),
                    Long.parseLong(view.getDeviceIdField().getText()),
                    Long.parseLong(view.getReceivedByField().getText()),
                    technicianId,
                    view.getComplaintArea().getText().trim(),
                    status.name(),
                    oldServiceOrder.getReceivedAt(),
                    LocalDate.parse(
                            view.getEstimatedCompletionField().getText()
                    ),
                    oldServiceOrder.getCompletedAt(),
                    oldServiceOrder.getCreatedAt()
            );

            serviceOrderService.updateServiceOrder(serviceOrder);

            view.getStatusLabel().setText(
                    "Service order berhasil diperbarui."
            );

            clearForm();
            loadServiceOrders();

        } catch (ServiceOrderNotFoundException e) {
            view.getStatusLabel().setText(e.getMessage());

        } catch (InvalidServiceOrderException e) {
            view.getStatusLabel().setText(e.getMessage());

        } catch (IllegalArgumentException e) {
            view.getStatusLabel().setText(
                    "Periksa kembali input angka, tanggal, dan status."
            );

        } catch (SQLException e) {
            view.getStatusLabel().setText(
                    "Gagal memperbarui data: " + e.getMessage()
            );
        }
    }

    private void deleteServiceOrder() {
        int selectedRow =
                view.getServiceOrderTable().getSelectedRow();

        if (selectedRow == -1) {
            view.getStatusLabel().setText(
                    "Pilih data yang ingin dihapus."
            );
            return;
        }

        try {
            long serviceId = Long.parseLong(
                    view.getTableModel()
                            .getValueAt(selectedRow, 0).toString()
            );

            serviceOrderService.deleteServiceOrder(serviceId);

            view.getStatusLabel().setText(
                    "Service order berhasil dihapus."
            );

            clearForm();
            loadServiceOrders();

        } catch (ServiceOrderNotFoundException e) {
            view.getStatusLabel().setText(e.getMessage());

        } catch (InvalidServiceOrderException e) {
            view.getStatusLabel().setText(e.getMessage());

        } catch (SQLException e) {
            view.getStatusLabel().setText(
                    "Gagal menghapus data: " + e.getMessage()
            );
        }
    }

    private void selectServiceOrder() {
        int selectedRow =
                view.getServiceOrderTable().getSelectedRow();

        if (selectedRow == -1) {
            return;
        }

        view.getTicketNumberField().setText(
                view.getTableModel().getValueAt(selectedRow, 1).toString()
        );

        view.getCustomerIdField().setText(
                view.getTableModel().getValueAt(selectedRow, 2).toString()
        );

        view.getDeviceIdField().setText(
                view.getTableModel().getValueAt(selectedRow, 3).toString()
        );

        view.getReceivedByField().setText(
                view.getTableModel().getValueAt(selectedRow, 4).toString()
        );

        Object technician =
                view.getTableModel().getValueAt(selectedRow, 5);

        view.getTechnicianIdField().setText(
                technician == null ? "" : technician.toString()
        );

        view.getComplaintArea().setText(
                view.getTableModel().getValueAt(selectedRow, 6).toString()
        );

        view.getStatusComboBox().setSelectedItem(
                view.getTableModel().getValueAt(selectedRow, 7).toString()
        );

        view.getEstimatedCompletionField().setText(
                view.getTableModel().getValueAt(selectedRow, 9).toString()
        );
    }

    private void clearForm() {
        view.getTicketNumberField().setText("");
        view.getCustomerIdField().setText("");
        view.getDeviceIdField().setText("");
        view.getReceivedByField().setText("");
        view.getTechnicianIdField().setText("");
        view.getComplaintArea().setText("");
        view.getEstimatedCompletionField().setText("");
        view.getStatusComboBox().setSelectedIndex(0);
        view.getServiceOrderTable().clearSelection();
    }
}