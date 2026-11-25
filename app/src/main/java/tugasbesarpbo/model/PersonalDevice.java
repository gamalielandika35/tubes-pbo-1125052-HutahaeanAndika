
package tugasbesarpbo.model;

public abstract class PersonalDevice extends ElectronicDevice {
    private String processor;
    private String operatingSystem;
    private int ramGb;
    private int storageGb;

    public PersonalDevice(long deviceId, long customerId, String brand, String model, String serialNumber, String description, String processor, String operatingSystem, int ramGb, int storageGb) {
        super(deviceId, customerId, brand, model, serialNumber, description);
        this.processor = processor;
        this.operatingSystem = operatingSystem;
        this.ramGb = ramGb;
        this.storageGb = storageGb;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public int getRamGb() {
        return ramGb;
    }

    public void setRamGb(int ramGb) {
        this.ramGb = ramGb;
    }

    public int getStorageGb() {
        return storageGb;
    }

    public void setStorageGb(int storageGb) {
        this.storageGb = storageGb;
    }

    @Override
    public String getCategory() {
        return "Personal";
    }
}
