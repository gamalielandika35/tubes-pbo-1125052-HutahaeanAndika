package tugasbesarpbo.model;

public class DeviceFactory {

    private DeviceFactory() {}

    public static Laptop createLaptop(long deviceId,long customerId,String brand,String model,String serialNumber,String description,String processor,String operatingSystem,int ramGb,int storageGb,String storageType,String batteryModel,int chargerWattage) {
        return new Laptop(deviceId,customerId,brand,model,serialNumber,description,processor,operatingSystem,ramGb,storageGb,storageType,batteryModel,chargerWattage);
    }

    public static Smartphone createSmartphone(long deviceId,long customerId,String brand,String model,String serialNumber,String description,String processor,String operatingSystem,int ramGb,int storageGb,String chargingPortType,String screenTechnology) {
        return new Smartphone(deviceId,customerId,brand,model,serialNumber,description,processor,operatingSystem,ramGb,storageGb,chargingPortType,screenTechnology);
    }

    public static DesktopPC createDekstopPC(long deviceId,long customerId,String brand,String model,String serialNumber,String description,String processor,String operatingSystem,int ramGb,int storageGb,String storageType,String motherboardFormFactor,int powerSupplyWattage) {
        return new DesktopPC(deviceId,customerId,brand,model,serialNumber,description,processor,operatingSystem,ramGb,storageGb,storageType,motherboardFormFactor,powerSupplyWattage);
    }

    public static WashingMachine createWashingMachine(long deviceId,long customerId,String brand,String model,String serialNumber,String description,int powerRatingWatts,int ratedVoltage,String loadingType,String driveType) {
        return new WashingMachine(deviceId,customerId,brand,model,serialNumber,description,powerRatingWatts,ratedVoltage,loadingType,driveType);
    }

    public static Television createTelevision(long deviceId,long customerId,String brand,String model,String serialNumber,String description,int powerRatingWatts,int ratedVoltage,Double screenSizeInches,String displayTechnology) {
        return new Television(deviceId,customerId,brand,model,serialNumber,description,powerRatingWatts,ratedVoltage,screenSizeInches,displayTechnology);
    }

    public static Refrigerator createRefrigerator(long deviceId,long customerId,String brand,String model,String serialNumber,String description,int powerRatingWatts,int ratedVoltage,String doorConfiguration,String defrostType,String compressorType) {
        return new Refrigerator(deviceId,customerId,brand,model,serialNumber,description,powerRatingWatts,ratedVoltage,doorConfiguration,defrostType,compressorType);
    }
}