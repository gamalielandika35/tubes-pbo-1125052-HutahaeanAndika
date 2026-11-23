package tugasbesarpbo.model;

import java.time.LocalDateTime;

public class DiagnosisTask extends ServiceTask {

    private String diagnosis;

    public DiagnosisTask(long serviceId, long technicianId,
            LocalDateTime taskDate, String diagnosis) {

        super(serviceId, technicianId, taskDate);
        this.diagnosis = diagnosis;
    }

    @Override
    public ServiceDetail executeTask() {

        return ServiceDetail.createDiagnosis(
                getServiceId(),
                diagnosis
        );
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}