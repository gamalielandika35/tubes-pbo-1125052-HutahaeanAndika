package tugasbesarpbo.model;

public class Laptop extends PersonalDevice{
    private String storageType;
    private String batteryModel;
    private int chargerWattage;

    public Laptop(long deviceId,long customerId, String brand,String model,String serialNumber,String description,String processor,String operatingSystem, int ramGb, int storageGb,String storagetype,String batteryModel,int chargerWattage){
        super(deviceId,customerId,brand,model,serialNumber,description,processor,operatingSystem,ramGb,storageGb);
        this.storageType = storagetype;
        this.batteryModel = batteryModel;
        this.chargerWattage = chargerWattage;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getBatteryModel() {
        return batteryModel;
    }

    public void setBatteryModel(String batteryModel) {
        this.batteryModel = batteryModel;
    }

    public int getChargerWattage() {
        return chargerWattage;
    }

    public void setChargerWattage(int chargerWattage) {
        this.chargerWattage = chargerWattage;
    }
    
    @Override 
    public String getDeviceType(){
        return "LAPTOP";
    }


}
