package tugasbesarpbo.service;

import tugasbesarpbo.model.ServiceOrder;
import tugasbesarpbo.repository.ServiceOrderRepository;
import tugasbesarpbo.exception.InvalidServiceOrderException;
import tugasbesarpbo.exception.ServiceOrderNotFoundException;

import java.sql.SQLException;
import java.util.List;

public class ServiceOrderService {

    private ServiceOrderRepository serviceOrderRepository;

    public ServiceOrderService() {
        serviceOrderRepository = new ServiceOrderRepository();
    }

    public void addServiceOrder(ServiceOrder serviceOrder)
            throws SQLException, InvalidServiceOrderException {

        validateServiceOrder(serviceOrder);

        if (serviceOrder.getServiceId() < 0) {
            throw new InvalidServiceOrderException(
                    "Service ID tidak valid."
            );
        }

        serviceOrderRepository.save(serviceOrder);
    }

    public List<ServiceOrder> getAllServiceOrders()
            throws SQLException {

        return serviceOrderRepository.findAll();
    }

    public ServiceOrder getServiceOrderById(long id)
            throws SQLException, InvalidServiceOrderException,
            ServiceOrderNotFoundException {

        if (id <= 0) {
            throw new InvalidServiceOrderException(
                    "Service ID harus lebih dari 0."
            );
        }

        ServiceOrder serviceOrder =
                serviceOrderRepository.findById(id);

        if (serviceOrder == null) {
            throw new ServiceOrderNotFoundException(
                    "Service order tidak ditemukan."
            );
        }

        return serviceOrder;
    }

    public void updateServiceOrder(ServiceOrder serviceOrder)
            throws SQLException, InvalidServiceOrderException,
            ServiceOrderNotFoundException {

        validateServiceOrder(serviceOrder);

        if (serviceOrder.getServiceId() <= 0) {
            throw new InvalidServiceOrderException(
                    "Service ID harus lebih dari 0."
            );
        }

        getServiceOrderById(serviceOrder.getServiceId());

        serviceOrderRepository.update(serviceOrder);
    }

    public void deleteServiceOrder(long id)
            throws SQLException, InvalidServiceOrderException,
            ServiceOrderNotFoundException {

        getServiceOrderById(id);

        serviceOrderRepository.delete(id);
    }

    private void validateServiceOrder(ServiceOrder serviceOrder)
            throws InvalidServiceOrderException {

        if (serviceOrder == null) {
            throw new InvalidServiceOrderException(
                    "Data service order tidak boleh kosong."
            );
        }

        if (serviceOrder.getTicketNumber() == null
                || serviceOrder.getTicketNumber().trim().isEmpty()) {
            throw new InvalidServiceOrderException(
                    "Ticket number wajib diisi."
            );
        }

        if (serviceOrder.getCustomerId() <= 0) {
            throw new InvalidServiceOrderException(
                    "Customer ID harus lebih dari 0."
            );
        }

        if (serviceOrder.getDeviceId() <= 0) {
            throw new InvalidServiceOrderException(
                    "Device ID harus lebih dari 0."
            );
        }

        if (serviceOrder.getReceivedBy() <= 0) {
            throw new InvalidServiceOrderException(
                    "Received By harus lebih dari 0."
            );
        }

        if (serviceOrder.getTechnicianId() != null
                && serviceOrder.getTechnicianId() <= 0) {
            throw new InvalidServiceOrderException(
                    "Technician ID harus lebih dari 0."
            );
        }

        if (serviceOrder.getComplaint() == null
                || serviceOrder.getComplaint().trim().isEmpty()) {
            throw new InvalidServiceOrderException(
                    "Complaint wajib diisi."
            );
        }

        if (serviceOrder.getStatus() == null
                || serviceOrder.getStatus().trim().isEmpty()) {
            throw new InvalidServiceOrderException(
                    "Status wajib dipilih."
            );
        }

        if (serviceOrder.getEstimatedCompletion() == null) {
            throw new InvalidServiceOrderException(
                    "Estimated completion wajib diisi."
            );
        }
    }
}