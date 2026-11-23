
package tugasbesarpbo.service;

import tugasbesarpbo.model.ServiceDetail;
import tugasbesarpbo.repository.ServiceDetailRepository;

import java.sql.SQLException;
import java.util.List;

public class ServiceDetailService {

    private ServiceDetailRepository serviceDetailRepository;

    public ServiceDetailService() {
        serviceDetailRepository = new ServiceDetailRepository();
    }

    public void addServiceDetail(ServiceDetail serviceDetail)
            throws SQLException {
        serviceDetailRepository.save(serviceDetail);
    }

    public List<ServiceDetail> getAllServiceDetails()
            throws SQLException {
        return serviceDetailRepository.findAll();
    }

    public ServiceDetail getServiceDetailById(long id)
            throws SQLException {
        return serviceDetailRepository.findById(id);
    }

    public void updateServiceDetail(ServiceDetail serviceDetail)
            throws SQLException {
        serviceDetailRepository.update(serviceDetail);
    }

    public void deleteServiceDetail(long id)
            throws SQLException {
        serviceDetailRepository.delete(id);
    }
}
