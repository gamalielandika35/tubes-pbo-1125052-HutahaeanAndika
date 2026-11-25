package tugasbesarpbo.model;
import java.util.Arrays;
import java.util.List;

public class WashingMachine extends HouseholdDevice implements Diagnosable, Repairable {
    private String loadingType;
    private String driveType;

    public WashingMachine (long deviceId, long customerId, String brand, String model, String serialNumber,String description, int powerRatingWatts, int ratedVoltage, String loadingType, String driveType){
        super(deviceId, customerId, brand, model, serialNumber, description, powerRatingWatts, ratedVoltage);
        this.loadingType = loadingType;
        this.driveType = driveType;
    }

    public String getLoadingType() {
        return loadingType;
    }

    public void setLoadingType(String loadingType) {
        this.loadingType = loadingType;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    @Override 
    public String getDeviceType(){
        return "WASHING_MACHINE";
    }
    @Override
    public List<String> getDiagnosticChecklist() {
        return Arrays.asList("Water Inlet", "Drain System", "Drum Rotation", "Motor", "Noise dan Vibration", "Control Panel");
    }

    @Override
    public List<String> getCommonRepairComponents() {
        return Arrays.asList("Water Inlet Valve", "Drain Pump", "Drive Belt", "Motor", "Suspension", "Control Board");
    }
}
