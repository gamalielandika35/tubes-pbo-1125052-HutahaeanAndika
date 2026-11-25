package tugasbesarpbo.model;

import java.util.Arrays;
import java.util.List;

public class Television extends HouseholdDevice implements Diagnosable, Repairable {
    private Double screenSizeInches;
    private String displayTechnology;

    public Television(long deviceId, long customerId, String brand, String model, String serialNumber,String description, int powerRatingWatts, int ratedVoltage,Double screenSizeInches,String displayTechnology){
        super(deviceId, customerId, brand, model, serialNumber, description, powerRatingWatts, ratedVoltage);
        this.screenSizeInches = screenSizeInches;
        this.displayTechnology = displayTechnology;
    }

    public Double getScreenSizeInches() {
        return screenSizeInches;
    }

    public void setScreenSizeInches(Double screenSizeInches) {
        this.screenSizeInches = screenSizeInches;
    }

    public String getDisplayTechnology() {
        return displayTechnology;
    }

    public void setDisplayTechnology(String displayTechnology) {
        this.displayTechnology = displayTechnology;
    }
    
    @Override 
    public String getDeviceType(){
        return "TELEVISION";
    }
    @Override
    public List<String> getDiagnosticChecklist() {
        return Arrays.asList("Power Supply", "Display dan Backlight", "Audio", "HDMI dan Input Port", "Remote Control");
    }

    @Override
    public List<String> getCommonRepairComponents() {
        return Arrays.asList("Power Supply Board", "Main Board", "LED Backlight", "Speaker", "Display Panel");
    }
}
