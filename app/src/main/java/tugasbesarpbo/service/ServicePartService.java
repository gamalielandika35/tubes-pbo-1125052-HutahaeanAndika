
package tugasbesarpbo.service;

import tugasbesarpbo.model.ServicePart;
import tugasbesarpbo.repository.ServicePartRepository;

import java.sql.SQLException;
import java.util.List;

public class ServicePartService {

    private ServicePartRepository servicePartRepository;

    public ServicePartService() {
        servicePartRepository = new ServicePartRepository();
    }

    public void addServicePart(ServicePart servicePart)
            throws SQLException {
        servicePartRepository.save(servicePart);
    }

    public List<ServicePart> getAllServiceParts()
            throws SQLException {
        return servicePartRepository.findAll();
    }

    public ServicePart getServicePartById(long id)
            throws SQLException {
        return servicePartRepository.findById(id);
    }

    public void updateServicePart(ServicePart servicePart)
            throws SQLException {
        servicePartRepository.update(servicePart);
    }

    public void deleteServicePart(long id)
            throws SQLException {
        servicePartRepository.delete(id);
    }
}
