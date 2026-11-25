
package tugasbesarpbo.model;

public abstract class HouseholdDevice extends ElectronicDevice {
    private int powerRatingWatts;
    private int ratedVoltage;

    public HouseholdDevice(long deviceId, long customerId, String brand, String model, String serialNumber, String description, int powerRatingWatts, int ratedVoltage) {
        super(deviceId, customerId, brand, model, serialNumber, description);
        this.powerRatingWatts = powerRatingWatts;
        this.ratedVoltage = ratedVoltage;
    }

    public int getPowerRatingWatts() {
        return powerRatingWatts;
    }

    public void setPowerRatingWatts(int powerRatingWatts) {
        this.powerRatingWatts = powerRatingWatts;
    }

    public int getRatedVoltage() {
        return ratedVoltage;
    }

    public void setRatedVoltage(int ratedVoltage) {
        this.ratedVoltage = ratedVoltage;
    }

    @Override
    public String getCategory() {
        return "Household";
    }
}
