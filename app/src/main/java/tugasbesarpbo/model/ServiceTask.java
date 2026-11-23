package tugasbesarpbo.model;

import java.time.LocalDateTime;

public abstract class ServiceTask {
    private long serviceId;
    private long technicianId;
    private LocalDateTime taskDate;

    public ServiceTask(long serviceId, long technicianId, LocalDateTime taskDate) {
        this.serviceId = serviceId;
        this.technicianId = technicianId;
        this.taskDate = taskDate;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public long getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(long technicianId) {
        this.technicianId = technicianId;
    }

    public LocalDateTime getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(LocalDateTime taskDate) {
        this.taskDate = taskDate;
    }


    public abstract void executeTask();
}
