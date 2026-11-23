
package tugasbesarpbo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private long paymentId;
    private long serviceId;
    private long receivedBy;
    private BigDecimal amount;
    private String paymentMethod;
    private String paymentStatus;
    private LocalDateTime paidAt;
    private String ReferenceNo;

    public Payment(long paymentId, long serviceId, long receivedBy, LocalDateTime paidAt, BigDecimal amount,
            String paymentMethod, String paymentStatus, String ReferenceNo) {
        this.paymentId = paymentId;
        this.serviceId = serviceId;
        this.receivedBy = receivedBy;
        this.paidAt = paidAt;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.ReferenceNo = ReferenceNo;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public long getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(long receivedBy) {
        this.receivedBy = receivedBy;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public String getReferenceNo() {
        return ReferenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        ReferenceNo = referenceNo;
    }

    
}
