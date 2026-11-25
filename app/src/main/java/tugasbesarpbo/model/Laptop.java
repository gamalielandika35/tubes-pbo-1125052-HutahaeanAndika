package tugasbesarpbo.model;
import java.util.Arrays;
import java.util.List;

public class Laptop extends PersonalDevice implements Diagnosable, Repairable {
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
    @Override
    public List<String> getDiagnosticChecklist() {
        return Arrays.asList("Power dan charging", "Kondisi layar", "Deteksi storage", "Keyboard dan touchpad", "Sistem pendingin");
    }

    @Override
    public List<String> getCommonRepairComponents() {
        return Arrays.asList("Battery", "Charging Port", "Storage Drive", "Keyboard", "Display Panel", "Cooling Fan");
    }


}
