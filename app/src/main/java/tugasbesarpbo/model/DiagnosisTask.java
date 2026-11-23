package tugasbesarpbo.model;

import java.time.LocalDateTime;

public class DiagnosisTask extends ServiceTask {
    private String diagnosis;

    public DiagnosisTask(   long serviceId, long technicianId, LocalDateTime taskDate, String description,  
                            String diagnosis) {
        super(serviceId, technicianId, taskDate);
        this.diagnosis = diagnosis;
    }

    
    @Override
    public void executeTask() {
        System.out.println("Executing diagnosis task for service order: " + diagnosis);
    }


    public String getDiagnosis() {
        return diagnosis;
    }


    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
