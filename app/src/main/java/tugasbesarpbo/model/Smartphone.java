package tugasbesarpbo.model;

public class Smartphone extends PersonalDevice{
    private String chargingPortType;
    private String screenTechnology;

    public Smartphone(long deviceId,long customerId, String brand,String model,String serialNumber,String description,String processor,String operatingSystem, int ramGb, int storageGb,String chargingPortType,String screenTechnology){
        super(deviceId,customerId,brand,model,serialNumber,description,processor,operatingSystem,ramGb,storageGb);
        this.chargingPortType = chargingPortType;
        this.screenTechnology = screenTechnology;
        
    }

    public String getChargingPortType() {
        return chargingPortType;
    }

    public void setChargingPortType(String chargingPortType) {
        this.chargingPortType = chargingPortType;
    }

    public String getScreenTechnology() {
        return screenTechnology;
    }

    public void setScreenTechnology(String screenTechnology) {
        this.screenTechnology = screenTechnology;
    }

    @Override 
    public String getDeviceType(){
        return "SMARTPHONE";
    }
    
}
