package tugasbesarpbo.model;

public class Refrigerator extends HouseholdDevice {
    private String doorConfiguration;
    private String defrostType;
    private String compressorType;

    public Refrigerator(long deviceId,long customerId, String brand,String model,String serialNumber,String description,int powerRatingWatts, int ratedVoltage,String doorConfiguration,String deFrostType,String compressorType){
        super(deviceId, customerId, brand, model, serialNumber, description, powerRatingWatts, ratedVoltage);
        this.doorConfiguration = doorConfiguration;
        this.defrostType = deFrostType;
        this.compressorType = compressorType;
    }

    public String getDoorConfiguration() {
        return doorConfiguration;
    }

    public void setDoorConfiguration(String doorConfiguration) {
        this.doorConfiguration = doorConfiguration;
    }

    public String getDefrostType() {
        return defrostType;
    }

    public void setDefrostType(String defrostType) {
        this.defrostType = defrostType;
    }

    public String getCompressorType() {
        return compressorType;
    }

    public void setCompressorType(String compressorType) {
        this.compressorType = compressorType;
    }
    
    @Override 
    public String getDeviceType(){
        return "REFRIGERATOR";
    }
}
