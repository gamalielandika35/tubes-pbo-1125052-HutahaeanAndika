package tugasbesarpbo.model;

public class DesktopPC extends PersonalDevice {
    private String storageType;
    private String motherboardFormFactor;
    private int powerSupplyWattage;
    

    public DesktopPC(long deviceId,long customerId, String brand,String model,String serialNumber,String description,String processor,String operatingSystem, int ramGb, int storageGb,String storagetype,String motherboardFormFactor,int powerSupplyWattage){
        super(deviceId,customerId,brand,model,serialNumber,description,processor,operatingSystem,ramGb,storageGb);
        this.storageType = storagetype;
        this.motherboardFormFactor = motherboardFormFactor;
        this.powerSupplyWattage = powerSupplyWattage;
    }


    public String getStorageType() {
        return storageType;
    }


    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }


    public String getMotherboardFormFactor() {
        return motherboardFormFactor;
    }


    public void setMotherboardFormFactor(String motherboardFormFactor) {
        this.motherboardFormFactor = motherboardFormFactor;
    }


    public int getPowerSupplyWattage() {
        return powerSupplyWattage;
    }


    public void setPowerSupplyWattage(int powerSupplyWattage) {
        this.powerSupplyWattage = powerSupplyWattage;
    }
    
    @Override 
    public String getDeviceType(){
        return "DESKTOP_PC";
    }
}
