
package tugasbesarpbo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ServiceOrder {
    private long serviceId;
    private String ticketNumber;
    private long customerId;
    private long deviceId;
    private long receivedBy;
    private Long technicianId;
    private String complaint;
    private String status;
    private LocalDateTime receivedAt;
    private LocalDate estimatedCompletion;
    private LocalDateTime completedAt;
    private LocalDateTime createdAt;

    public ServiceOrder(long serviceId, String ticketNumber,
            long customerId, long deviceId, long receivedBy,
            Long technicianId, String complaint, String status,
            LocalDateTime receivedAt, LocalDate estimatedCompletion,
            LocalDateTime completedAt, LocalDateTime createdAt) {

        this.serviceId = serviceId;
        this.ticketNumber = ticketNumber;
        this.customerId = customerId;
        this.deviceId = deviceId;
        this.receivedBy = receivedBy;
        this.technicianId = technicianId;
        this.complaint = complaint;
        this.status = status;
        this.receivedAt = receivedAt;
        this.estimatedCompletion = estimatedCompletion;
        this.completedAt = completedAt;
        this.createdAt = createdAt;
    }

    public long getServiceId() {
        return serviceId;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public long getCustomerId() {
        return customerId;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public long getReceivedBy() {
        return receivedBy;
    }

    public Long getTechnicianId() {
        return technicianId;
    }

    public String getComplaint() {
        return complaint;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public LocalDate getEstimatedCompletion() {
        return estimatedCompletion;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public void setReceivedBy(long receivedBy) {
        this.receivedBy = receivedBy;
    }

    public void setTechnicianId(Long technicianId) {
        this.technicianId = technicianId;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    public void setEstimatedCompletion(LocalDate estimatedCompletion) {
        this.estimatedCompletion = estimatedCompletion;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
