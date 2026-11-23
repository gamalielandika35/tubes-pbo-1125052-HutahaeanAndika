
package tugasbesarpbo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RepairTask extends ServiceTask {

    private String repairAction;
    private BigDecimal laborCost;
    private String technicianNotes;

    public RepairTask(long serviceId, long technicianId,
            LocalDateTime taskDate, String repairAction,
            BigDecimal laborCost, String technicianNotes) {

        super(serviceId, technicianId, taskDate);
        this.repairAction = repairAction;
        this.laborCost = laborCost;
        this.technicianNotes = technicianNotes;
    }

    @Override
    public ServiceDetail executeTask() {

        System.out.println("Executing repair task: " + repairAction);

        return ServiceDetail.createRepair(
                getServiceId(),
                repairAction,
                laborCost,
                technicianNotes
        );
    }

    public String getRepairAction() {
        return repairAction;
    }

    public void setRepairAction(String repairAction) {
        this.repairAction = repairAction;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }
}
