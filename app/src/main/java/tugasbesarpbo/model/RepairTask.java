package tugasbesarpbo.model;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public class RepairTask extends ServiceTask {
    private String repairAction;
    private BigDecimal laborCost;

    public RepairTask( long serviceId, long technicianId, LocalDateTime taskDate,String repairAction, BigDecimal laborCost) {
        super(serviceId, technicianId, taskDate);
        this.repairAction = repairAction;
        this.laborCost = laborCost;
    }

    public String getRepairAction() {
        return repairAction;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setRepairAction(String repairAction) {
        this.repairAction = repairAction;
    }

    

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }
    
    @Override
    public void executeTask() {
        System.out.println("Executing repair task for service order: " + repairAction);
    }

}
