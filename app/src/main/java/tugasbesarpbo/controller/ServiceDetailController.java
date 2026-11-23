
package tugasbesarpbo.controller;

import tugasbesarpbo.view.ServiceDetailView;
import tugasbesarpbo.model.ServiceDetail;
import tugasbesarpbo.service.ServiceDetailService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ServiceDetailController {

    private ServiceDetailView view;
    private ServiceDetailService service;

    public ServiceDetailController(ServiceDetailView view) {
        this.view = view;
        service = new ServiceDetailService();

        loadServiceDetails();
        addListeners();
    }

    private void addListeners() {

        view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addServiceDetail();
            }
        });

        view.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateServiceDetail();
            }
        });

        view.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteServiceDetail();
            }
        });

        view.getClearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        view.getServiceDetailTable().getSelectionModel()
                .addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectServiceDetail();
                }
            }
        });
    }

    private void loadServiceDetails() {
        try {
            view.getTableModel().setRowCount(0);

            List<ServiceDetail> details = service.getAllServiceDetails();

            for (ServiceDetail detail : details) {
                Object[] row = {
                    detail.getDetailId(),
                    detail.getServiceId(),
                    detail.getDiagnosis(),
                    detail.getRepairAction(),
                    detail.getLaborcost(),
                    detail.getTechnicianNotes(),
                    detail.getUpdatedAt()
                };

                view.getTableModel().addRow(row);
            }

            view.getStatusLabel().setText("Data berhasil dimuat.");

        } catch (SQLException e) {
            view.getStatusLabel().setText(
                    "Gagal memuat data: " + e.getMessage()
            );
        }
    }

    private void addServiceDetail() {
        try {
            long serviceId = Long.parseLong(
                    view.getServiceIdField().getText().trim()
            );

            BigDecimal laborCost = new BigDecimal(
                    view.getLaborCostField().getText().trim()
            );

            String diagnosis = view.getDiagnosisArea().getText().trim();
            String repairAction = view.getRepairActionArea().getText().trim();
            String technicianNotes = view.getTechnicianNotesArea().getText().trim();

            ServiceDetail detail = new ServiceDetail(
                    0,
                    serviceId,
                    diagnosis,
                    repairAction,
                    laborCost,
                    technicianNotes,
                    LocalDateTime.now()
            );

            service.addServiceDetail(detail);

            view.getStatusLabel().setText(
                    "Service detail berhasil ditambahkan."
            );

            clearForm();
            loadServiceDetails();

        } catch (NumberFormatException e) {
            view.getStatusLabel().setText(
                    "Service ID dan Labor Cost harus berupa angka."
            );
        } catch (SQLException e) {
            view.getStatusLabel().setText(
                    "Gagal menambahkan data: " + e.getMessage()
            );
        }
    }

    private void updateServiceDetail() {
        int selectedRow = view.getServiceDetailTable().getSelectedRow();

        if (selectedRow == -1) {
            view.getStatusLabel().setText(
                    "Pilih data yang ingin diubah."
            );
            return;
        }

        try {
            int row = view.getServiceDetailTable()
                    .convertRowIndexToModel(selectedRow);

            long detailId = Long.parseLong(
                    view.getTableModel().getValueAt(row, 0).toString()
            );

            long serviceId = Long.parseLong(
                    view.getServiceIdField().getText().trim()
            );

            BigDecimal laborCost = new BigDecimal(
                    view.getLaborCostField().getText().trim()
            );

            ServiceDetail detail = new ServiceDetail(
                    detailId,
                    serviceId,
                    view.getDiagnosisArea().getText().trim(),
                    view.getRepairActionArea().getText().trim(),
                    laborCost,
                    view.getTechnicianNotesArea().getText().trim(),
                    LocalDateTime.now()
            );

            service.updateServiceDetail(detail);

            view.getStatusLabel().setText(
                    "Service detail berhasil diubah."
            );

            clearForm();
            loadServiceDetails();

        } catch (NumberFormatException e) {
            view.getStatusLabel().setText(
                    "Service ID dan Labor Cost harus berupa angka."
            );
        } catch (SQLException e) {
            view.getStatusLabel().setText(
                    "Gagal mengubah data: " + e.getMessage()
            );
        }
    }

    private void deleteServiceDetail() {
        int selectedRow = view.getServiceDetailTable().getSelectedRow();

        if (selectedRow == -1) {
            view.getStatusLabel().setText(
                    "Pilih data yang ingin dihapus."
            );
            return;
        }

        try {
            int row = view.getServiceDetailTable()
                    .convertRowIndexToModel(selectedRow);

            long detailId = Long.parseLong(
                    view.getTableModel().getValueAt(row, 0).toString()
            );

            service.deleteServiceDetail(detailId);

            view.getStatusLabel().setText(
                    "Service detail berhasil dihapus."
            );

            clearForm();
            loadServiceDetails();

        } catch (SQLException e) {
            view.getStatusLabel().setText(
                    "Gagal menghapus data: " + e.getMessage()
            );
        }
    }

    private void selectServiceDetail() {
        int selectedRow = view.getServiceDetailTable().getSelectedRow();

        if (selectedRow == -1) {
            return;
        }

        int row = view.getServiceDetailTable()
                .convertRowIndexToModel(selectedRow);

        view.getServiceIdField().setText(
                view.getTableModel().getValueAt(row, 1).toString()
        );

        view.getDiagnosisArea().setText(
                view.getTableModel().getValueAt(row, 2).toString()
        );

        view.getRepairActionArea().setText(
                view.getTableModel().getValueAt(row, 3).toString()
        );

        view.getLaborCostField().setText(
                view.getTableModel().getValueAt(row, 4).toString()
        );

        Object notes = view.getTableModel().getValueAt(row, 5);

        view.getTechnicianNotesArea().setText(
                notes == null ? "" : notes.toString()
        );
    }

    private void clearForm() {
        view.getServiceIdField().setText("");
        view.getDiagnosisArea().setText("");
        view.getRepairActionArea().setText("");
        view.getLaborCostField().setText("");
        view.getTechnicianNotesArea().setText("");

        view.getServiceDetailTable().clearSelection();
    }
}
