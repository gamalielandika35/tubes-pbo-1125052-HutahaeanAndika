
package tugasbesarpbo.service;

import tugasbesarpbo.model.ElectronicDevice;
import tugasbesarpbo.repository.DeviceRepository;

import java.util.List;

public class DeviceService {

    private final DeviceRepository repository;

    public DeviceService() {
        this.repository = new DeviceRepository();
    }

    public void addDevice(ElectronicDevice device) throws Exception {
        repository.save(device);
    }

    public ElectronicDevice getDeviceById(long id) throws Exception {
        return repository.findById(id);
    }

    public List<ElectronicDevice> getAllDevices() throws Exception {
        return repository.findAll();
    }

    public void updateDevice(ElectronicDevice device) throws Exception {
        repository.update(device);
    }

    public void deleteDevice(long id) throws Exception {
        repository.delete(id);
    }
}
