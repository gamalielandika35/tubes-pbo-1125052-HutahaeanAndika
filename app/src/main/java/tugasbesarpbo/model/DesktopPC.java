package tugasbesarpbo.model;
import java.util.Arrays;
import java.util.List;

public class DesktopPC extends PersonalDevice implements Diagnosable, Repairable {
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
    @Override
    public List<String> getDiagnosticChecklist() {
        return Arrays.asList("Power Supply", "Proses POST dan booting", "Deteksi storage", "RAM", "Motherboard", "Sistem pendingin");
    }

    @Override
    public List<String> getCommonRepairComponents() {
        return Arrays.asList("Power Supply", "Storage Drive", "RAM", "Motherboard", "Cooling Fan", "Power Switch");
    }
}
