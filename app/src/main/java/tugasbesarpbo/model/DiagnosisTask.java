
package tugasbesarpbo.model;

import java.time.LocalDateTime;

public class DiagnosisTask extends ServiceTask {

    private String diagnosis;
    private String technicianNotes;

    public DiagnosisTask(long serviceId, long technicianId,
            LocalDateTime taskDate, String diagnosis,
            String technicianNotes) {

        super(serviceId, technicianId, taskDate);
        this.diagnosis = diagnosis;
        this.technicianNotes = technicianNotes;
    }

    @Override
    public ServiceDetail executeTask() {

        System.out.println("Executing diagnosis task: " + diagnosis);

        return ServiceDetail.createDiagnosis(
                getServiceId(),
                diagnosis,
                technicianNotes
        );
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
