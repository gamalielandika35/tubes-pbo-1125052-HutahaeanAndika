package tugasbesarpbo.service;

import tugasbesarpbo.model.Customer;
import tugasbesarpbo.respository.CustomerRepository;
import java.util.List;

public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService() {
        this.repository = new CustomerRepository();
    }

    public void addCustomer(String name, String phone, String email, String address) throws Exception {
        Customer customer = new Customer(0, name, phone, email, address);
        repository.save(customer);
    }

    public Customer getCustomerById(long id) throws Exception {
        return repository.findById(id);
    }

    public List<Customer> getAllCustomers() throws Exception {
        return repository.findAll();
    }

    public void updateCustomer(long id, String name, String phone, String email, String address) throws Exception {
        Customer customer = new Customer(id, name, phone, email, address);
        repository.update(customer);
    }

    public void deleteCustomer(long id) throws Exception {
        repository.delete(id);
    }
}