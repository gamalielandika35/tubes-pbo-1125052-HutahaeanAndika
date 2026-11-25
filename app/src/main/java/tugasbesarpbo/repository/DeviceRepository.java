
package tugasbesarpbo.repository;

import tugasbesarpbo.Database.databaseConnection;
import tugasbesarpbo.model.DeviceFactory;
import tugasbesarpbo.model.ElectronicDevice;
import tugasbesarpbo.model.Laptop;
import tugasbesarpbo.model.Smartphone;
import tugasbesarpbo.model.DesktopPC;
import tugasbesarpbo.model.WashingMachine;
import tugasbesarpbo.model.Television;
import tugasbesarpbo.model.Refrigerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class DeviceRepository implements GenericRepository<ElectronicDevice> {

    @Override
    public void save(ElectronicDevice device) throws SQLException {
        String sql = "INSERT INTO devices (customer_id, device_type, brand, model, serial_number, notes, processor, operating_system, ram_gb, storage_gb, power_rating_watts, rated_voltage, storage_type, battery_model, charger_wattage, charging_port_type, screen_technology, motherboard_form_factor, power_supply_wattage, loading_type, drive_type, screen_size_inches, display_technology, door_configuration, defrost_type, compressor_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, device.getCustomerId());
            ps.setString(2, device.getDeviceType());
            ps.setString(3, device.getBrand());
            ps.setString(4, device.getModel());
            ps.setString(5, device.getSerialNumber());
            ps.setString(6, device.getDescription());

            setDeviceSpecificValues(ps, device);
            ps.executeUpdate();
        }
    }

    @Override
    public ElectronicDevice findById(long id) throws SQLException {
        String sql = "SELECT * FROM devices WHERE device_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToDevice(rs);
                }
            }
        }

        return null;
    }

    @Override
    public List<ElectronicDevice> findAll() throws SQLException {
        List<ElectronicDevice> devices = new ArrayList<ElectronicDevice>();

        String sql = "SELECT * FROM devices ORDER BY device_id";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ElectronicDevice device = mapResultSetToDevice(rs);

                if (device != null) {
                    devices.add(device);
                }
            }
        }

        return devices;
    }

    @Override
    public void update(ElectronicDevice device) throws SQLException {
        String sql = "UPDATE devices SET customer_id = ?, device_type = ?, brand = ?, model = ?, serial_number = ?, notes = ?, processor = ?, operating_system = ?, ram_gb = ?, storage_gb = ?, power_rating_watts = ?, rated_voltage = ?, storage_type = ?, battery_model = ?, charger_wattage = ?, charging_port_type = ?, screen_technology = ?, motherboard_form_factor = ?, power_supply_wattage = ?, loading_type = ?, drive_type = ?, screen_size_inches = ?, display_technology = ?, door_configuration = ?, defrost_type = ?, compressor_type = ? WHERE device_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, device.getCustomerId());
            ps.setString(2, device.getDeviceType());
            ps.setString(3, device.getBrand());
            ps.setString(4, device.getModel());
            ps.setString(5, device.getSerialNumber());
            ps.setString(6, device.getDescription());

            setDeviceSpecificValues(ps, device);
            ps.setLong(27, device.getDeviceId());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM devices WHERE device_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    private void setDeviceSpecificValues(
            PreparedStatement ps,
            ElectronicDevice device) throws SQLException {

        switch (device.getDeviceType()) {
            case "LAPTOP":
                Laptop laptop = (Laptop) device;

                ps.setString(7, laptop.getProcessor());
                ps.setString(8, laptop.getOperatingSystem());
                ps.setInt(9, laptop.getRamGb());
                ps.setInt(10, laptop.getStorageGb());
                ps.setNull(11, Types.INTEGER);
                ps.setNull(12, Types.INTEGER);
                ps.setString(13, laptop.getStorageType());
                ps.setString(14, laptop.getBatteryModel());
                ps.setInt(15, laptop.getChargerWattage());
                ps.setNull(16, Types.VARCHAR);
                ps.setNull(17, Types.VARCHAR);
                ps.setNull(18, Types.VARCHAR);
                ps.setNull(19, Types.INTEGER);
                ps.setNull(20, Types.VARCHAR);
                ps.setNull(21, Types.VARCHAR);
                ps.setNull(22, Types.NUMERIC);
                ps.setNull(23, Types.VARCHAR);
                ps.setNull(24, Types.VARCHAR);
                ps.setNull(25, Types.VARCHAR);
                ps.setNull(26, Types.VARCHAR);
                break;

            case "SMARTPHONE":
                Smartphone smartphone = (Smartphone) device;

                ps.setString(7, smartphone.getProcessor());
                ps.setString(8, smartphone.getOperatingSystem());
                ps.setInt(9, smartphone.getRamGb());
                ps.setInt(10, smartphone.getStorageGb());
                ps.setNull(11, Types.INTEGER);
                ps.setNull(12, Types.INTEGER);
                ps.setNull(13, Types.VARCHAR);
                ps.setNull(14, Types.VARCHAR);
                ps.setNull(15, Types.INTEGER);
                ps.setString(16, smartphone.getChargingPortType());
                ps.setString(17, smartphone.getScreenTechnology());
                ps.setNull(18, Types.VARCHAR);
                ps.setNull(19, Types.INTEGER);
                ps.setNull(20, Types.VARCHAR);
                ps.setNull(21, Types.VARCHAR);
                ps.setNull(22, Types.NUMERIC);
                ps.setNull(23, Types.VARCHAR);
                ps.setNull(24, Types.VARCHAR);
                ps.setNull(25, Types.VARCHAR);
                ps.setNull(26, Types.VARCHAR);
                break;

            case "DESKTOP_PC":
                DesktopPC desktop = (DesktopPC) device;

                ps.setString(7, desktop.getProcessor());
                ps.setString(8, desktop.getOperatingSystem());
                ps.setInt(9, desktop.getRamGb());
                ps.setInt(10, desktop.getStorageGb());
                ps.setNull(11, Types.INTEGER);
                ps.setNull(12, Types.INTEGER);
                ps.setString(13, desktop.getStorageType());
                ps.setNull(14, Types.VARCHAR);
                ps.setNull(15, Types.INTEGER);
                ps.setNull(16, Types.VARCHAR);
                ps.setNull(17, Types.VARCHAR);
                ps.setString(18, desktop.getMotherboardFormFactor());
                ps.setInt(19, desktop.getPowerSupplyWattage());
                ps.setNull(20, Types.VARCHAR);
                ps.setNull(21, Types.VARCHAR);
                ps.setNull(22, Types.NUMERIC);
                ps.setNull(23, Types.VARCHAR);
                ps.setNull(24, Types.VARCHAR);
                ps.setNull(25, Types.VARCHAR);
                ps.setNull(26, Types.VARCHAR);
                break;

            case "WASHING_MACHINE":
                WashingMachine washingMachine = (WashingMachine) device;

                ps.setNull(7, Types.VARCHAR);
                ps.setNull(8, Types.VARCHAR);
                ps.setNull(9, Types.INTEGER);
                ps.setNull(10, Types.INTEGER);
                ps.setInt(11, washingMachine.getPowerRatingWatts());
                ps.setInt(12, washingMachine.getRatedVoltage());
                ps.setNull(13, Types.VARCHAR);
                ps.setNull(14, Types.VARCHAR);
                ps.setNull(15, Types.INTEGER);
                ps.setNull(16, Types.VARCHAR);
                ps.setNull(17, Types.VARCHAR);
                ps.setNull(18, Types.VARCHAR);
                ps.setNull(19, Types.INTEGER);
                ps.setString(20, washingMachine.getLoadingType());
                ps.setString(21, washingMachine.getDriveType());
                ps.setNull(22, Types.NUMERIC);
                ps.setNull(23, Types.VARCHAR);
                ps.setNull(24, Types.VARCHAR);
                ps.setNull(25, Types.VARCHAR);
                ps.setNull(26, Types.VARCHAR);
                break;

            case "TELEVISION":
                Television television = (Television) device;

                ps.setNull(7, Types.VARCHAR);
                ps.setNull(8, Types.VARCHAR);
                ps.setNull(9, Types.INTEGER);
                ps.setNull(10, Types.INTEGER);
                ps.setInt(11, television.getPowerRatingWatts());
                ps.setInt(12, television.getRatedVoltage());
                ps.setNull(13, Types.VARCHAR);
                ps.setNull(14, Types.VARCHAR);
                ps.setNull(15, Types.INTEGER);
                ps.setNull(16, Types.VARCHAR);
                ps.setNull(17, Types.VARCHAR);
                ps.setNull(18, Types.VARCHAR);
                ps.setNull(19, Types.INTEGER);
                ps.setNull(20, Types.VARCHAR);
                ps.setNull(21, Types.VARCHAR);
                ps.setDouble(22, television.getScreenSizeInches());
                ps.setString(23, television.getDisplayTechnology());
                ps.setNull(24, Types.VARCHAR);
                ps.setNull(25, Types.VARCHAR);
                ps.setNull(26, Types.VARCHAR);
                break;

            case "REFRIGERATOR":
                Refrigerator refrigerator = (Refrigerator) device;

                ps.setNull(7, Types.VARCHAR);
                ps.setNull(8, Types.VARCHAR);
                ps.setNull(9, Types.INTEGER);
                ps.setNull(10, Types.INTEGER);
                ps.setInt(11, refrigerator.getPowerRatingWatts());
                ps.setInt(12, refrigerator.getRatedVoltage());
                ps.setNull(13, Types.VARCHAR);
                ps.setNull(14, Types.VARCHAR);
                ps.setNull(15, Types.INTEGER);
                ps.setNull(16, Types.VARCHAR);
                ps.setNull(17, Types.VARCHAR);
                ps.setNull(18, Types.VARCHAR);
                ps.setNull(19, Types.INTEGER);
                ps.setNull(20, Types.VARCHAR);
                ps.setNull(21, Types.VARCHAR);
                ps.setNull(22, Types.NUMERIC);
                ps.setNull(23, Types.VARCHAR);
                ps.setString(24, refrigerator.getDoorConfiguration());
                ps.setString(25, refrigerator.getDefrostType());
                ps.setString(26, refrigerator.getCompressorType());
                break;
        }
    }

    private ElectronicDevice mapResultSetToDevice(ResultSet rs)
            throws SQLException {

        switch (rs.getString("device_type")) {
            case "LAPTOP":
                return mapLaptop(rs);
            case "SMARTPHONE":
                return mapSmartphone(rs);
            case "DESKTOP_PC":
                return mapDesktopPC(rs);
            case "WASHING_MACHINE":
                return mapWashingMachine(rs);
            case "TELEVISION":
                return mapTelevision(rs);
            case "REFRIGERATOR":
                return mapRefrigerator(rs);
            default:
                return null;
        }
    }

    private ElectronicDevice mapLaptop(ResultSet rs) throws SQLException {
        return DeviceFactory.createLaptop(
                rs.getLong("device_id"),
                rs.getLong("customer_id"),
                rs.getString("brand"),
                rs.getString("model"),
                rs.getString("serial_number"),
                rs.getString("notes"),
                rs.getString("processor"),
                rs.getString("operating_system"),
                rs.getInt("ram_gb"),
                rs.getInt("storage_gb"),
                rs.getString("storage_type"),
                rs.getString("battery_model"),
                rs.getInt("charger_wattage")
        );
    }

    private ElectronicDevice mapSmartphone(ResultSet rs) throws SQLException {
        return DeviceFactory.createSmartphone(
                rs.getLong("device_id"),
                rs.getLong("customer_id"),
                rs.getString("brand"),
                rs.getString("model"),
                rs.getString("serial_number"),
                rs.getString("notes"),
                rs.getString("processor"),
                rs.getString("operating_system"),
                rs.getInt("ram_gb"),
                rs.getInt("storage_gb"),
                rs.getString("charging_port_type"),
                rs.getString("screen_technology")
        );
    }

    private ElectronicDevice mapDesktopPC(ResultSet rs) throws SQLException {
        return DeviceFactory.createDekstopPC(
                rs.getLong("device_id"),
                rs.getLong("customer_id"),
                rs.getString("brand"),
                rs.getString("model"),
                rs.getString("serial_number"),
                rs.getString("notes"),
                rs.getString("processor"),
                rs.getString("operating_system"),
                rs.getInt("ram_gb"),
                rs.getInt("storage_gb"),
                rs.getString("storage_type"),
                rs.getString("motherboard_form_factor"),
                rs.getInt("power_supply_wattage")
        );
    }

    private ElectronicDevice mapWashingMachine(ResultSet rs) throws SQLException {
        return DeviceFactory.createWashingMachine(
                rs.getLong("device_id"),
                rs.getLong("customer_id"),
                rs.getString("brand"),
                rs.getString("model"),
                rs.getString("serial_number"),
                rs.getString("notes"),
                rs.getInt("power_rating_watts"),
                rs.getInt("rated_voltage"),
                rs.getString("loading_type"),
                rs.getString("drive_type")
        );
    }

    private ElectronicDevice mapTelevision(ResultSet rs) throws SQLException {
        return DeviceFactory.createTelevision(
                rs.getLong("device_id"),
                rs.getLong("customer_id"),
                rs.getString("brand"),
                rs.getString("model"),
                rs.getString("serial_number"),
                rs.getString("notes"),
                rs.getInt("power_rating_watts"),
                rs.getInt("rated_voltage"),
                rs.getDouble("screen_size_inches"),
                rs.getString("display_technology")
        );
    }

    private ElectronicDevice mapRefrigerator(ResultSet rs) throws SQLException {
        return DeviceFactory.createRefrigerator(
                rs.getLong("device_id"),
                rs.getLong("customer_id"),
                rs.getString("brand"),
                rs.getString("model"),
                rs.getString("serial_number"),
                rs.getString("notes"),
                rs.getInt("power_rating_watts"),
                rs.getInt("rated_voltage"),
                rs.getString("door_configuration"),
                rs.getString("defrost_type"),
                rs.getString("compressor_type")
        );
    }
}
