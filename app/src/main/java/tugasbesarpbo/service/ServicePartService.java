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

        if (servicePart.getComponentName() == null
                || servicePart.getComponentName().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Nama komponen tidak boleh kosong."
            );
        }

        if (servicePart.getQuantity() <= 0) {
            throw new IllegalArgumentException(
                    "Jumlah komponen harus lebih dari 0."
            );
        }

        if (servicePart.getUnitPrice() == null
                || servicePart.getUnitPrice().signum() < 0) {
            throw new IllegalArgumentException(
                    "Biaya komponen tidak valid."
            );
        }

        servicePartRepository.save(servicePart);
    }

    public List<ServicePart> getAllServiceParts()
            throws SQLException {
        return servicePartRepository.findAll();
    }

    public List<ServicePart> getServicePartsByServiceId(long serviceId)
            throws SQLException {
        return servicePartRepository.findByServiceId(serviceId);
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