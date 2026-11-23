
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
                + "(service_id, part_id, quantity, unit_price) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, servicePart.getServiceId());
            statement.setLong(2, servicePart.getPartId());
            statement.setInt(3, servicePart.getQuantity());
            statement.setBigDecimal(4, servicePart.getUnitPrice());

            statement.executeUpdate();
        }
    }

    @Override
    public ServicePart findById(long id) throws SQLException {
        String sql = "SELECT * FROM service_parts WHERE service_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return getServicePartFromResult(result);
                }
            }
        }

        return null;
    }

    @Override
    public List<ServicePart> findAll() throws SQLException {
        List<ServicePart> serviceParts = new ArrayList<ServicePart>();

        String sql = "SELECT * FROM service_parts "
                + "ORDER BY service_id, part_id";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                serviceParts.add(getServicePartFromResult(result));
            }
        }

        return serviceParts;
    }

    @Override
    public void update(ServicePart servicePart) throws SQLException {
        String sql = "UPDATE service_parts SET "
                + "quantity = ?, unit_price = ? "
                + "WHERE service_id = ? AND part_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, servicePart.getQuantity());
            statement.setBigDecimal(2, servicePart.getUnitPrice());
            statement.setLong(3, servicePart.getServiceId());
            statement.setLong(4, servicePart.getPartId());

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM service_parts WHERE service_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    private ServicePart getServicePartFromResult(ResultSet result)
            throws SQLException {

        return new ServicePart(
                result.getLong("service_id"),
                result.getLong("part_id"),
                result.getInt("quantity"),
                result.getBigDecimal("unit_price")
        );
    }
}
