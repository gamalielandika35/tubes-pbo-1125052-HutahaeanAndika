
package tugasbesarpbo.model;

public abstract class ElectronicDevice {
    private long deviceId;
    private long customerId;
    private String brand;
    private String model;
    private String serialNumber;
    private String description;

    public ElectronicDevice(long deviceId, long customerId, String brand, String model, String serialNumber, String description) {
        this.deviceId = deviceId;
        this.customerId = customerId;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.description = description;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return brand + " " + model + " (" + serialNumber + ")";
    }

    public abstract String getDeviceType();

    public abstract String getCategory();
}
