package tugasbesarpbo.model;

import java.time.LocalDateTime;

public abstract class ServiceTask {

    private long serviceId;
    private long technicianId;
    private LocalDateTime taskDate;

    public ServiceTask(long serviceId, long technicianId,
            LocalDateTime taskDate) {

        this.serviceId = serviceId;
        this.technicianId = technicianId;
        this.taskDate = taskDate;
    }

    public long getServiceId() {
        return serviceId;
    }

    public long getTechnicianId() {
        return technicianId;
    }

    public LocalDateTime getTaskDate() {
        return taskDate;
    }

    public abstract ServiceDetail executeTask();
}