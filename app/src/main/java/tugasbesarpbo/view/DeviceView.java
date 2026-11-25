
package tugasbesarpbo.view;

import tugasbesarpbo.controller.DeviceController;
import tugasbesarpbo.model.ElectronicDevice;
import tugasbesarpbo.model.DeviceFactory;
import tugasbesarpbo.model.Laptop;
import tugasbesarpbo.model.Smartphone;
import tugasbesarpbo.model.DesktopPC;
import tugasbesarpbo.model.WashingMachine;
import tugasbesarpbo.util.UIStyle;
import tugasbesarpbo.model.Television;
import tugasbesarpbo.model.Refrigerator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceView extends JPanel {

    private JTextField txtId;
    private JTextField txtCustomerId;
    private JTextField txtBrand;
    private JTextField txtModel;
    private JTextField txtSerialNumber;
    private JTextField txtDescription;

    private JComboBox<String> cboDeviceType;

    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;

    private JTable table;
    private DefaultTableModel tableModel;

    private JPanel detailPanel;

    private Map<String, JTextField> detailFields;

    private DeviceController controller;
    private JLabel statusLabel;
    private boolean confirmDelete;

    public DeviceView() {
        controller = new DeviceController();
        detailFields = new HashMap<>();

        initComponents();
        loadDevices();
    }

    private void initComponents() {

        JPanel commonPanel = new JPanel(
                new GridLayout(0, 4, 8, 8));

        commonPanel.setBorder(
                BorderFactory.createTitledBorder("General Information"));

        txtId = new JTextField();
        txtId.setEditable(false);
        txtCustomerId = new JTextField();
        txtBrand = new JTextField();
        txtModel = new JTextField();
        txtSerialNumber = new JTextField();
        txtDescription = new JTextField();

        cboDeviceType = new JComboBox<>(new String[] {
                "LAPTOP",
                "SMARTPHONE",
                "DESKTOP_PC",
                "WASHING_MACHINE",
                "TELEVISION",
                "REFRIGERATOR"
        });

        commonPanel.add(new JLabel("Device ID"));
        commonPanel.add(txtId);

        commonPanel.add(new JLabel("Customer ID"));
        commonPanel.add(txtCustomerId);

        commonPanel.add(new JLabel("Device Type"));
        commonPanel.add(cboDeviceType);

        commonPanel.add(new JLabel("Brand"));
        commonPanel.add(txtBrand);

        commonPanel.add(new JLabel("Model"));
        commonPanel.add(txtModel);

        commonPanel.add(new JLabel("Serial Number"));
        commonPanel.add(txtSerialNumber);

        commonPanel.add(new JLabel("Description"));
        commonPanel.add(txtDescription);

        detailPanel = new JPanel(
                new GridLayout(0, 2, 8, 8));

        detailPanel.setBorder(
                BorderFactory.createTitledBorder("Device Specific Information"));

        initializeDetailFields();
        showDeviceFields();

        cboDeviceType.addActionListener(e -> {
            showDeviceFields();
            clearDetailFields();
        });

        btnAdd = new JButton("Tambah");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Hapus");
        btnClear = new JButton("Clear");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        statusLabel = new JLabel(" ");
        statusLabel.setForeground(new Color(40, 90, 150));
        statusLabel.setBorder(
                BorderFactory.createEmptyBorder(4, 8, 4, 8));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        tableModel = new DefaultTableModel(
                new Object[] {
                        "Device ID",
                        "Customer ID",
                        "Device Type",
                        "Category",
                        "Brand",
                        "Model",
                        "Serial Number",
                        "Description"
                }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        table.setAutoCreateRowSorter(true);

        JPanel formPanel = new JPanel(new BorderLayout(5, 5));

        formPanel.add(commonPanel, BorderLayout.NORTH);
        formPanel.add(detailPanel, BorderLayout.CENTER);

        JScrollPane formScrollPane = new JScrollPane(formPanel);
        formScrollPane.setPreferredSize(
                new Dimension(1000, 270));

        setLayout(new BorderLayout(10, 10));

        add(formScrollPane, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> addDevice());
        btnUpdate.addActionListener(e -> updateDevice());
        btnDelete.addActionListener(e -> deleteDevice());
        btnClear.addActionListener(e -> clearForm());

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectDevice();
            }
        });
    }

    private void initializeDetailFields() {

        String[] fields = {
                "processor",
                "operatingSystem",
                "ramGb",
                "storageGb",
                "storageType",
                "batteryModel",
                "chargerWattage",
                "chargingPortType",
                "screenTechnology",
                "motherboardFormFactor",
                "powerSupplyWattage",
                "powerRatingWatts",
                "ratedVoltage",
                "loadingType",
                "driveType",
                "screenSizeInches",
                "displayTechnology",
                "doorConfiguration",
                "defrostType",
                "compressorType"
        };

        for (String field : fields) {
            detailFields.put(field, new JTextField());
        }
    }

    private void addDetailRow(String key, String label) {
        detailPanel.add(new JLabel(label));
        detailPanel.add(detailFields.get(key));
    }

    private void showDeviceFields() {

        detailPanel.removeAll();

        String type = cboDeviceType.getSelectedItem().toString();

        switch (type) {

            case "LAPTOP":
                addDetailRow("processor", "Processor");
                addDetailRow("operatingSystem", "Operating System");
                addDetailRow("ramGb", "RAM (GB)");
                addDetailRow("storageGb", "Storage (GB)");
                addDetailRow("storageType", "Storage Type");
                addDetailRow("batteryModel", "Battery Model");
                addDetailRow("chargerWattage", "Charger Wattage");
                break;

            case "SMARTPHONE":
                addDetailRow("processor", "Processor");
                addDetailRow("operatingSystem", "Operating System");
                addDetailRow("ramGb", "RAM (GB)");
                addDetailRow("storageGb", "Storage (GB)");
                addDetailRow("chargingPortType", "Charging Port");
                addDetailRow("screenTechnology", "Screen Technology");
                break;

            case "DESKTOP_PC":
                addDetailRow("processor", "Processor");
                addDetailRow("operatingSystem", "Operating System");
                addDetailRow("ramGb", "RAM (GB)");
                addDetailRow("storageGb", "Storage (GB)");
                addDetailRow("storageType", "Storage Type");
                addDetailRow("motherboardFormFactor", "Motherboard");
                addDetailRow("powerSupplyWattage", "Power Supply Wattage");
                break;

            case "WASHING_MACHINE":
                addDetailRow("powerRatingWatts", "Power Rating (Watt)");
                addDetailRow("ratedVoltage", "Rated Voltage");
                addDetailRow("loadingType", "Loading Type");
                addDetailRow("driveType", "Drive Type");
                break;

            case "TELEVISION":
                addDetailRow("powerRatingWatts", "Power Rating (Watt)");
                addDetailRow("ratedVoltage", "Rated Voltage");
                addDetailRow("screenSizeInches", "Screen Size (Inch)");
                addDetailRow("displayTechnology", "Display Technology");
                break;

            case "REFRIGERATOR":
                addDetailRow("powerRatingWatts", "Power Rating (Watt)");
                addDetailRow("ratedVoltage", "Rated Voltage");
                addDetailRow("doorConfiguration", "Door Configuration");
                addDetailRow("defrostType", "Defrost Type");
                addDetailRow("compressorType", "Compressor Type");
                break;
        }

        detailPanel.revalidate();
        detailPanel.repaint();
    }

    private ElectronicDevice buildDevice(boolean isUpdate) {

        long deviceId = 0;

        if (isUpdate) {
            deviceId = parseLong(
                    txtId.getText(),
                    "Device ID");
        }

        long customerId = parseLong(
                txtCustomerId.getText(),
                "Customer ID");

        String brand = txtBrand.getText().trim();
        String model = txtModel.getText().trim();
        String serialNumber = txtSerialNumber.getText().trim();
        String description = txtDescription.getText().trim();

        if (brand.isEmpty() || model.isEmpty() || serialNumber.isEmpty()) {
            throw new IllegalArgumentException(
                    "Brand, Model, dan Serial Number wajib diisi.");
        }

        String type = cboDeviceType.getSelectedItem().toString();

        switch (type) {

            case "LAPTOP":
                return DeviceFactory.createLaptop(
                        deviceId,
                        customerId,
                        brand,
                        model,
                        serialNumber,
                        description,
                        getDetailText("processor"),
                        getDetailText("operatingSystem"),
                        parseIntField("ramGb", "RAM"),
                        parseIntField("storageGb", "Storage"),
                        getDetailText("storageType"),
                        getDetailText("batteryModel"),
                        parseIntField("chargerWattage", "Charger Wattage"));

            case "SMARTPHONE":
                return DeviceFactory.createSmartphone(
                        deviceId,
                        customerId,
                        brand,
                        model,
                        serialNumber,
                        description,
                        getDetailText("processor"),
                        getDetailText("operatingSystem"),
                        parseIntField("ramGb", "RAM"),
                        parseIntField("storageGb", "Storage"),
                        getDetailText("chargingPortType"),
                        getDetailText("screenTechnology"));

            case "DESKTOP_PC":
                return DeviceFactory.createDekstopPC(
                        deviceId,
                        customerId,
                        brand,
                        model,
                        serialNumber,
                        description,
                        getDetailText("processor"),
                        getDetailText("operatingSystem"),
                        parseIntField("ramGb", "RAM"),
                        parseIntField("storageGb", "Storage"),
                        getDetailText("storageType"),
                        getDetailText("motherboardFormFactor"),
                        parseIntField("powerSupplyWattage", "Power Supply Wattage"));

            case "WASHING_MACHINE":
                return DeviceFactory.createWashingMachine(
                        deviceId,
                        customerId,
                        brand,
                        model,
                        serialNumber,
                        description,
                        parseIntField("powerRatingWatts", "Power Rating"),
                        parseIntField("ratedVoltage", "Rated Voltage"),
                        getDetailText("loadingType"),
                        getDetailText("driveType"));

            case "TELEVISION":
                return DeviceFactory.createTelevision(
                        deviceId,
                        customerId,
                        brand,
                        model,
                        serialNumber,
                        description,
                        parseIntField("powerRatingWatts", "Power Rating"),
                        parseIntField("ratedVoltage", "Rated Voltage"),
                        parseNullableDouble("screenSizeInches", "Screen Size"),
                        getDetailText("displayTechnology"));

            case "REFRIGERATOR":
                return DeviceFactory.createRefrigerator(
                        deviceId,
                        customerId,
                        brand,
                        model,
                        serialNumber,
                        description,
                        parseIntField("powerRatingWatts", "Power Rating"),
                        parseIntField("ratedVoltage", "Rated Voltage"),
                        getDetailText("doorConfiguration"),
                        getDetailText("defrostType"),
                        getDetailText("compressorType"));

            default:
                throw new IllegalArgumentException(
                        "Jenis device tidak valid.");
        }
    }

    private void addDevice() {
        try {
            ElectronicDevice device = buildDevice(false);
            controller.addDevice(device);

            showStatus("Device berhasil ditambahkan.", false);
            clearForm();
            loadDevices();

        } catch (Exception e) {
            showStatus(e.getMessage(), true);
        }
    }

    private void updateDevice() {
        try {
            if (txtId.getText().isEmpty()) {
                showStatus("Pilih device terlebih dahulu.", true);
                return;
            }

            ElectronicDevice device = buildDevice(true);
            controller.updateDevice(device);

            showStatus("Device berhasil diupdate.", false);
            clearForm();
            loadDevices();

        } catch (Exception e) {
            showStatus(e.getMessage(), true);
        }
    }

    private void deleteDevice() {
        try {
            if (txtId.getText().isEmpty()) {
                showStatus("Pilih device terlebih dahulu.", true);
                return;
            }

            if (!confirmDelete) {
                confirmDelete = true;
                btnDelete.setText("Ya, Hapus");

                showStatus(
                        "Klik 'Ya, Hapus' sekali lagi untuk menghapus. Klik Clear untuk batal.",
                        true);

                return;
            }

            long id = Long.parseLong(txtId.getText());
            controller.deleteDevice(id);

            confirmDelete = false;
            btnDelete.setText("Hapus");

            showStatus("Device berhasil dihapus.", false);

            clearForm();
            loadDevices();

        } catch (Exception e) {
            confirmDelete = false;
            btnDelete.setText("Hapus");

            showStatus(e.getMessage(), true);
        }
    }

    private void loadDevices() {
        try {
            tableModel.setRowCount(0);

            List<ElectronicDevice> devices = controller.getAllDevices();

            for (ElectronicDevice device : devices) {
                tableModel.addRow(new Object[] {
                        device.getDeviceId(),
                        device.getCustomerId(),
                        device.getDeviceType(),
                        device.getCategory(),
                        device.getBrand(),
                        device.getModel(),
                        device.getSerialNumber(),
                        device.getDescription()
                });
            }

        } catch (Exception e) {
            showStatus(e.getMessage(), true);
        }
    }

    private void selectDevice() {
        confirmDelete = false;
        btnDelete.setText("Hapus");

        int selectedRow = table.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        try {

            int modelRow = table.convertRowIndexToModel(selectedRow);

            long id = Long.parseLong(
                    tableModel.getValueAt(modelRow, 0).toString());

            ElectronicDevice device = controller.getDeviceById(id);

            if (device == null) {
    showMessage("Device tidak ditemukan.", true);
    return;
}

            txtId.setText(String.valueOf(device.getDeviceId()));
            txtCustomerId.setText(String.valueOf(device.getCustomerId()));
            txtBrand.setText(device.getBrand());
            txtModel.setText(device.getModel());
            txtSerialNumber.setText(device.getSerialNumber());
            txtDescription.setText(device.getDescription());

            cboDeviceType.setSelectedItem(device.getDeviceType());

            showDeviceFields();
            fillDeviceFields(device);

        } catch (Exception e) {
    showMessage(e.getMessage(), true);
}
    }

    private void fillDeviceFields(ElectronicDevice device) {

        switch (device.getDeviceType()) {

            case "LAPTOP": {

                Laptop laptop = (Laptop) device;

                setDetailField("processor", laptop.getProcessor());
                setDetailField("operatingSystem", laptop.getOperatingSystem());
                setDetailField("ramGb", laptop.getRamGb());
                setDetailField("storageGb", laptop.getStorageGb());
                setDetailField("storageType", laptop.getStorageType());
                setDetailField("batteryModel", laptop.getBatteryModel());
                setDetailField("chargerWattage", laptop.getChargerWattage());

                break;
            }

            case "SMARTPHONE": {

                Smartphone smartphone = (Smartphone) device;

                setDetailField("processor", smartphone.getProcessor());
                setDetailField("operatingSystem", smartphone.getOperatingSystem());
                setDetailField("ramGb", smartphone.getRamGb());
                setDetailField("storageGb", smartphone.getStorageGb());
                setDetailField("chargingPortType", smartphone.getChargingPortType());
                setDetailField("screenTechnology", smartphone.getScreenTechnology());

                break;
            }

            case "DESKTOP_PC": {

                DesktopPC desktop = (DesktopPC) device;

                setDetailField("processor", desktop.getProcessor());
                setDetailField("operatingSystem", desktop.getOperatingSystem());
                setDetailField("ramGb", desktop.getRamGb());
                setDetailField("storageGb", desktop.getStorageGb());
                setDetailField("storageType", desktop.getStorageType());
                setDetailField("motherboardFormFactor", desktop.getMotherboardFormFactor());
                setDetailField("powerSupplyWattage", desktop.getPowerSupplyWattage());

                break;
            }

            case "WASHING_MACHINE": {

                WashingMachine washingMachine = (WashingMachine) device;

                setDetailField("powerRatingWatts", washingMachine.getPowerRatingWatts());
                setDetailField("ratedVoltage", washingMachine.getRatedVoltage());
                setDetailField("loadingType", washingMachine.getLoadingType());
                setDetailField("driveType", washingMachine.getDriveType());

                break;
            }

            case "TELEVISION": {

                Television television = (Television) device;

                setDetailField("powerRatingWatts", television.getPowerRatingWatts());
                setDetailField("ratedVoltage", television.getRatedVoltage());
                setDetailField("screenSizeInches", television.getScreenSizeInches());
                setDetailField("displayTechnology", television.getDisplayTechnology());

                break;
            }

            case "REFRIGERATOR": {

                Refrigerator refrigerator = (Refrigerator) device;

                setDetailField("powerRatingWatts", refrigerator.getPowerRatingWatts());
                setDetailField("ratedVoltage", refrigerator.getRatedVoltage());
                setDetailField("doorConfiguration", refrigerator.getDoorConfiguration());
                setDetailField("defrostType", refrigerator.getDefrostType());
                setDetailField("compressorType", refrigerator.getCompressorType());

                break;
            }
        }
    }

    private void clearForm() {
        confirmDelete = false;
        btnDelete.setText("Hapus");

        txtId.setText("");
        txtCustomerId.setText("");
        txtBrand.setText("");
        txtModel.setText("");
        txtSerialNumber.setText("");
        txtDescription.setText("");

        cboDeviceType.setSelectedIndex(0);

        clearDetailFields();
        table.clearSelection();
    }

    private void clearDetailFields() {

        for (JTextField field : detailFields.values()) {
            field.setText("");
        }
    }

    private String getDetailText(String key) {
        return detailFields.get(key).getText().trim();
    }

    private void setDetailField(String key, Object value) {

        if (value == null) {
            detailFields.get(key).setText("");
        } else {
            detailFields.get(key).setText(value.toString());
        }
    }

    private long parseLong(String value, String label) {

        try {
            return Long.parseLong(value.trim());

        } catch (NumberFormatException e) {

            throw new IllegalArgumentException(
                    label + " harus berupa angka.");
        }
    }

    private void showStatus(String message, boolean isError) {
        statusLabel.setText(
                message == null ? "Terjadi kesalahan." : message);

        statusLabel.setForeground(
                isError ? Color.RED : new Color(0, 128, 0));
    }

    private int parseIntField(String key, String label) {

        String value = getDetailText(key);

        if (value.isEmpty()) {
            throw new IllegalArgumentException(
                    label + " wajib diisi.");
        }

        try {
            return Integer.parseInt(value);

        } catch (NumberFormatException e) {

            throw new IllegalArgumentException(
                    label + " harus berupa bilangan bulat.");
        }
    }

    private Double parseNullableDouble(String key, String label) {

        String value = getDetailText(key);

        if (value.isEmpty()) {
            return null;
        }

        try {
            return Double.parseDouble(value);

        } catch (NumberFormatException e) {

            throw new IllegalArgumentException(
                    label + " harus berupa angka.");
        }
    }
    private void showMessage(String message, boolean isError) {
    statusLabel.setForeground(isError ? UIStyle.RED : UIStyle.GREEN);
    statusLabel.setText(message != null ? message : "Terjadi kesalahan.");
}

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new DeviceView().setVisible(true);
        });
    }
}
