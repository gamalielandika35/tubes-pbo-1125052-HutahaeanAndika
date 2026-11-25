package tugasbesarpbo.repository;

import tugasbesarpbo.model.Customer;
import tugasbesarpbo.Database.databaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements GenericRepository<Customer> {

    @Override
    public void save(Customer customer) throws Exception {
        String sql = "INSERT INTO customers (full_name, phone, email, address) VALUES (?, ?, ?, ?) RETURNING customer_id";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getPhone());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getAddress());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    customer.setCustomerId(rs.getLong("customer_id"));
                }
            }
        }
    }

    @Override
    public Customer findById(long id) throws Exception {
        String sql = "SELECT * FROM customers WHERE customer_id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapCustomer(rs);
                }
            }
        }

        return null;
    }

    @Override
    public List<Customer> findAll() throws Exception {
        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT * FROM customers ORDER BY customer_id";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                customers.add(mapCustomer(rs));
            }
        }

        return customers;
    }

    @Override
    public void update(Customer customer) throws Exception {
        String sql = "UPDATE customers SET full_name = ?, phone = ?, email = ?, address = ? WHERE customer_id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getPhone());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getAddress());
            stmt.setLong(5, customer.getCustomerId());

            if (stmt.executeUpdate() == 0) {
                throw new SQLException("Customer tidak ditemukan.");
            }
        }
    }

    @Override
    public void delete(long id) throws Exception {
        String sql = "DELETE FROM customers WHERE customer_id = ?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            if (stmt.executeUpdate() == 0) {
                throw new SQLException("Customer tidak ditemukan.");
            }
        }
    }

    private Customer mapCustomer(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getLong("customer_id"),
                rs.getString("full_name"),
                rs.getString("phone"),
                rs.getString("email"),
                rs.getString("address")
        );
    }
}