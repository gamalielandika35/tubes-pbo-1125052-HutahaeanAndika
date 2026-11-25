package tugasbesarpbo.controller;

import tugasbesarpbo.model.Customer;
import tugasbesarpbo.service.CustomerService;

import java.util.List;

public class CustomerController {
    private final CustomerService service;

    public CustomerController() {
        this.service = new CustomerService();
    }

    public void addCustomer(String name, String phone, String email, String address) throws Exception {
        service.addCustomer(name, phone, email, address);
    }

    public Customer getCustomerById(long id) throws Exception {
        return service.getCustomerById(id);
    }

    public List<Customer> getAllCustomers() throws Exception {
        return service.getAllCustomers();
    }

    public void updateCustomer(long id, String name, String phone, String email, String address) throws Exception {
        service.updateCustomer(id, name, phone, email, address);
    }

    public void deleteCustomer(long id) throws Exception {
        service.deleteCustomer(id);
    }
}