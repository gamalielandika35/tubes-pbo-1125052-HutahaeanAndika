package tugasbesarpbo.model;

import java.math.BigDecimal;

public class ServicePart {
    private Long serviceId;
    private Long partId;
    private int quantity;
    private BigDecimal unitPrice;

    public ServicePart(Long serviceId, Long partId, int quantity, BigDecimal unitPrice) {
        this.serviceId = serviceId;
        this.partId = partId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    
}
