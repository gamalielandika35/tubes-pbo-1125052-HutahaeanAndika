
package tugasbesarpbo.controller;

import tugasbesarpbo.view.ServiceTaskView;
import tugasbesarpbo.model.ServiceTask;
import tugasbesarpbo.model.DiagnosisTask;
import tugasbesarpbo.model.RepairTask;
import tugasbesarpbo.model.ServiceDetail;
import tugasbesarpbo.service.ServiceDetailService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ServiceTaskController {

    private ServiceTaskView view;
    private ServiceDetailService service;

    public ServiceTaskController(ServiceTaskView view) {
        this.view = view;
        service = new ServiceDetailService();

        addListeners();
        updateForm();
        loadServiceDetails();
    }

    private void addListeners() {

        view.getExecuteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeTask();
            }
        });

        view.getClearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        view.getTaskTypeComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateForm();
            }
        });
    }

    private void updateForm() {

        String taskType = view.getTaskTypeComboBox()
                .getSelectedItem().toString();

        boolean isDiagnosis = taskType.equals("DIAGNOSIS");

        view.getDiagnosisArea().setEnabled(isDiagnosis);
        view.getRepairActionArea().setEnabled(!isDiagnosis);
        view.getLaborCostField().setEnabled(!isDiagnosis);

        if (isDiagnosis) {
            view.getRepairActionArea().setText("");
            view.getLaborCostField().setText("");
        } else {
            view.getDiagnosisArea().setText("");
        }
    }

    private void executeTask() {

        try {
            long serviceId = Long.parseLong(
                    view.getServiceIdField().getText().trim()
            );

            long technicianId = Long.parseLong(
                    view.getTechnicianIdField().getText().trim()
            );

            String taskType = view.getTaskTypeComboBox()
                    .getSelectedItem().toString();

            String technicianNotes = view.getTechnicianNotesArea()
                    .getText().trim();

            ServiceTask task;

            if (taskType.equals("DIAGNOSIS")) {

                String diagnosis = view.getDiagnosisArea()
                        .getText().trim();

                if (diagnosis.isEmpty()) {
                    view.getStatusLabel().setText(
                            "Diagnosis tidak boleh kosong."
                    );
                    return;
                }

                task = new DiagnosisTask(
                        serviceId,
                        technicianId,
                        LocalDateTime.now(),
                        diagnosis,
                        technicianNotes
                );

            } else {

                String repairAction = view.getRepairActionArea()
                        .getText().trim();

                if (repairAction.isEmpty()) {
                    view.getStatusLabel().setText(
                            "Repair Action tidak boleh kosong."
                    );
                    return;
                }

                BigDecimal laborCost = new BigDecimal(
                        view.getLaborCostField().getText().trim()
                );

                if (laborCost.compareTo(BigDecimal.ZERO) < 0) {
                    view.getStatusLabel().setText(
                            "Labor Cost tidak boleh negatif."
                    );
                    return;
                }

                task = new RepairTask(
                        serviceId,
                        technicianId,
                        LocalDateTime.now(),
                        repairAction,
                        laborCost,
                        technicianNotes
                );
            }

            ServiceDetail detail = task.executeTask();

            service.addServiceDetail(detail);

            view.getStatusLabel().setText(
                    taskType + " task berhasil disimpan."
            );

            clearForm();
            loadServiceDetails();

        } catch (NumberFormatException e) {
            view.getStatusLabel().setText(
                    "Service ID, Technician ID, dan Labor Cost harus valid."
            );
        } catch (SQLException e) {
            view.getStatusLabel().setText(
                    "Gagal menyimpan task: " + e.getMessage()
            );
        }
    }

    private void loadServiceDetails() {

        try {
            view.getTableModel().setRowCount(0);

            List<ServiceDetail> details =
                    service.getAllServiceDetails();

            for (ServiceDetail detail : details) {

                Object[] row = {
                    detail.getDetailId(),
                    detail.getServiceId(),
                    detail.getDiagnosis(),
                    detail.getRepairAction(),
                    detail.getLaborCost(),
                    detail.getTechnicianNotes(),
                    detail.getUpdatedAt()
                };

                view.getTableModel().addRow(row);
            }

        } catch (SQLException e) {
            view.getStatusLabel().setText(
                    "Gagal memuat data: " + e.getMessage()
            );
        }
    }

    private void clearForm() {

        view.getServiceIdField().setText("");
        view.getTechnicianIdField().setText("");
        view.getDiagnosisArea().setText("");
        view.getRepairActionArea().setText("");
        view.getLaborCostField().setText("");
        view.getTechnicianNotesArea().setText("");

        view.getTaskTypeComboBox().setSelectedIndex(0);

        updateForm();
    }
}
