package tugasbesarpbo.model;

import java.math.BigDecimal;

public class ServicePart {

    private Long servicePartId;
    private Long serviceId;
    private String componentName;
    private String action;
    private int quantity;
    private BigDecimal unitPrice;

    public ServicePart(
            Long servicePartId,
            Long serviceId,
            String componentName,
            String action,
            int quantity,
            BigDecimal unitPrice) {

        this.servicePartId = servicePartId;
        this.serviceId = serviceId;
        this.componentName = componentName;
        this.action = action;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getServicePartId() {
        return servicePartId;
    }

    public void setServicePartId(Long servicePartId) {
        this.servicePartId = servicePartId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public BigDecimal getSubtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}