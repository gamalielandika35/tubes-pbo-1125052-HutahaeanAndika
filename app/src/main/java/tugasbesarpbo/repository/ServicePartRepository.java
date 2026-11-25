package tugasbesarpbo.repository;

import tugasbesarpbo.Database.databaseConnection;
import tugasbesarpbo.model.ServicePart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicePartRepository
        implements GenericRepository<ServicePart> {

    @Override
    public void save(ServicePart servicePart) throws SQLException {
        String sql = "INSERT INTO service_parts "
                + "(service_id, component_name, action, quantity, unit_price) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, servicePart.getServiceId());
            statement.setString(2, servicePart.getComponentName());
            statement.setString(3, servicePart.getAction());
            statement.setInt(4, servicePart.getQuantity());
            statement.setBigDecimal(5, servicePart.getUnitPrice());

            statement.executeUpdate();
        }
    }

    @Override
    public ServicePart findById(long id) throws SQLException {
        String sql = "SELECT * FROM service_parts WHERE service_part_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return mapResult(result);
                }
            }
        }

        return null;
    }

    public List<ServicePart> findByServiceId(long serviceId)
            throws SQLException {

        List<ServicePart> serviceParts = new ArrayList<>();

        String sql = "SELECT * FROM service_parts "
                + "WHERE service_id = ? ORDER BY service_part_id";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, serviceId);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    serviceParts.add(mapResult(result));
                }
            }
        }

        return serviceParts;
    }

    @Override
    public List<ServicePart> findAll() throws SQLException {
        List<ServicePart> serviceParts = new ArrayList<>();

        String sql = "SELECT * FROM service_parts "
                + "ORDER BY service_id, service_part_id";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                serviceParts.add(mapResult(result));
            }
        }

        return serviceParts;
    }

    @Override
    public void update(ServicePart servicePart) throws SQLException {
        String sql = "UPDATE service_parts SET "
                + "service_id = ?, component_name = ?, action = ?, "
                + "quantity = ?, unit_price = ? "
                + "WHERE service_part_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, servicePart.getServiceId());
            statement.setString(2, servicePart.getComponentName());
            statement.setString(3, servicePart.getAction());
            statement.setInt(4, servicePart.getQuantity());
            statement.setBigDecimal(5, servicePart.getUnitPrice());
            statement.setLong(6, servicePart.getServicePartId());

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM service_parts WHERE service_part_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    private ServicePart mapResult(ResultSet result)
            throws SQLException {

        return new ServicePart(
                result.getLong("service_part_id"),
                result.getLong("service_id"),
                result.getString("component_name"),
                result.getString("action"),
                result.getInt("quantity"),
                result.getBigDecimal("unit_price")
        );
    }
}