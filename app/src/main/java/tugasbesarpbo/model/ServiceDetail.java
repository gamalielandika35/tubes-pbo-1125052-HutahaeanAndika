package tugasbesarpbo.model;
 import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ServiceDetail {
    private long detailId;
    private long serviceId;
    private String diagnosis;
    private String repairAction;
    private BigDecimal Laborcost;
    private String technicianNotes;
    private LocalDateTime updatedAt;

    public ServiceDetail(long detailId, long serviceId, String diagnosis,
            String repairAction, BigDecimal Laborcost,
            String technicianNotes, LocalDateTime updatedAt) {
        this.detailId = detailId;
        this.serviceId = serviceId;
        this.diagnosis = diagnosis;
        this.repairAction = repairAction;
        this.Laborcost = Laborcost;
        this.technicianNotes = technicianNotes;
        this.updatedAt = updatedAt;
    }

    public long getDetailId() {
        return detailId;
    }

    public void setDetailId(long detailId) {
        this.detailId = detailId;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getRepairAction() {
        return repairAction;
    }

    public void setRepairAction(String repairAction) {
        this.repairAction = repairAction;
    }

    public BigDecimal getLaborcost() {
        return Laborcost;
    }

    public void setLaborcost(BigDecimal Laborcost) {
        this.Laborcost = Laborcost;
    }

    public String getTechnicianNotes() {
        return technicianNotes;
    }

    public void setTechnicianNotes(String technicianNotes) {
        this.technicianNotes = technicianNotes;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    
}
