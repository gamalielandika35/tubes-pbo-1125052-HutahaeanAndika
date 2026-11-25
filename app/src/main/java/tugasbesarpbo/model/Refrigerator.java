package tugasbesarpbo.model;
import java.util.Arrays;
import java.util.List;

public class Refrigerator extends HouseholdDevice implements Diagnosable, Repairable  {
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
    @Override
    public List<String> getDiagnosticChecklist() {
        return Arrays.asList("Cooling Performance", "Compressor", "Fan", "Door Seal", "Defrost System", "Temperature Sensor");
    }

    @Override
    public List<String> getCommonRepairComponents() {
        return Arrays.asList("Compressor", "Start Relay", "Temperature Sensor", "Fan Motor", "Door Gasket", "Defrost Heater");
    }
}
