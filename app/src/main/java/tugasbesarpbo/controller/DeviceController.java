
package tugasbesarpbo.controller;

import tugasbesarpbo.model.ElectronicDevice;
import tugasbesarpbo.service.DeviceService;

import java.util.List;

public class DeviceController {
    private final DeviceService service;

    public DeviceController() {
        this.service = new DeviceService();
    }

    public void addDevice(ElectronicDevice device) throws Exception {
        service.addDevice(device);
    }

    public ElectronicDevice getDeviceById(long id) throws Exception {
        return service.getDeviceById(id);
    }

    public List<ElectronicDevice> getAllDevices() throws Exception {
        return service.getAllDevices();
    }

    public void updateDevice(ElectronicDevice device) throws Exception {
        service.updateDevice(device);
    }

    public void deleteDevice(long id) throws Exception {
        service.deleteDevice(id);
    }
}
