
package tugasbesarpbo.service;

import tugasbesarpbo.model.ServiceOrder;
import tugasbesarpbo.repository.ServiceOrderRepository;

import java.sql.SQLException;
import java.util.List;

public class ServiceOrderService {

    private ServiceOrderRepository serviceOrderRepository;

    public ServiceOrderService() {
        serviceOrderRepository = new ServiceOrderRepository();
    }

    public void addServiceOrder(ServiceOrder serviceOrder)
            throws SQLException {
        serviceOrderRepository.save(serviceOrder);
    }

    public List<ServiceOrder> getAllServiceOrders()
            throws SQLException {
        return serviceOrderRepository.findAll();
    }

    public ServiceOrder getServiceOrderById(long id)
            throws SQLException {
        return serviceOrderRepository.findById(id);
    }

    public void updateServiceOrder(ServiceOrder serviceOrder)
            throws SQLException {
        serviceOrderRepository.update(serviceOrder);
    }

    public void deleteServiceOrder(long id)
            throws SQLException {
        serviceOrderRepository.delete(id);
    }
}
