
package tugasbesarpbo.repository;

import tugasbesarpbo.Database.databaseConnection;
import tugasbesarpbo.model.ServiceOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ServiceOrderRepository
        implements GenericRepository<ServiceOrder> {

    @Override
    public void save(ServiceOrder serviceOrder) throws SQLException {
        String sql = "INSERT INTO service_orders "
                + "(ticket_number, customer_id, device_id, "
                + "received_by, technician_id, complaint, status, "
                + "received_at, estimated_completion, completed_at) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = databaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, serviceOrder.getTicketNumber());
            statement.setLong(2, serviceOrder.getCustomerId());
            statement.setLong(3, serviceOrder.getDeviceId());
            statement.setLong(4, serviceOrder.getReceivedBy());

            if (serviceOrder.getTechnicianId() == null) {
                statement.setNull(5, java.sql.Types.BIGINT);
            } else {
                statement.setLong(5, serviceOrder.getTechnicianId());
            }

            statement.setString(6, serviceOrder.getComplaint());
            statement.setString(7, serviceOrder.getStatus());

            if (serviceOrder.getReceivedAt() == null) {
                statement.setNull(8, java.sql.Types.TIMESTAMP);
            } else {
                statement.setTimestamp(8,
                        Timestamp.valueOf(serviceOrder.getReceivedAt()));
            }

            if (serviceOrder.getEstimatedCompletion() == null) {
                statement.setNull(9, java.sql.Types.DATE);
            } else {
                statement.setDate(9,
                        Date.valueOf(serviceOrder.getEstimatedCompletion()));
            }

            if (serviceOrder.getCompletedAt() == null) {
                statement.setNull(10, java.sql.Types.TIMESTAMP);
            } else {
                statement.setTimestamp(10,
                        Timestamp.valueOf(serviceOrder.getCompletedAt()));
            }

            statement.executeUpdate();

            try (ResultSet result = statement.getGeneratedKeys()) {
                if (result.next()) {
                    serviceOrder.setServiceId(result.getLong(1));
                }
            }
        }
    }

    @Override
    public ServiceOrder findById(long id) throws SQLException {
        String sql = "SELECT * FROM service_orders WHERE service_id = ?";

        try (Connection connection = databaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return getServiceOrderFromResult(result);
                }
            }
        }

        return null;
    }

    @Override
    public List<ServiceOrder> findAll() throws SQLException {
        List<ServiceOrder> serviceOrders = new ArrayList<ServiceOrder>();

        String sql = "SELECT * FROM service_orders ORDER BY service_id";

        try (Connection connection = databaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                serviceOrders.add(getServiceOrderFromResult(result));
            }
        }

        return serviceOrders;
    }

    @Override
    public void update(ServiceOrder serviceOrder) throws SQLException {
        String sql = "UPDATE service_orders SET "
                + "ticket_number = ?, customer_id = ?, device_id = ?, "
                + "received_by = ?, technician_id = ?, complaint = ?, "
                + "status = ?, received_at = ?, estimated_completion = ?, "
                + "completed_at = ? WHERE service_id = ?";

        try (Connection connection = databaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, serviceOrder.getTicketNumber());
            statement.setLong(2, serviceOrder.getCustomerId());
            statement.setLong(3, serviceOrder.getDeviceId());
            statement.setLong(4, serviceOrder.getReceivedBy());

            if (serviceOrder.getTechnicianId() == null) {
                statement.setNull(5, java.sql.Types.BIGINT);
            } else {
                statement.setLong(5, serviceOrder.getTechnicianId());
            }

            statement.setString(6, serviceOrder.getComplaint());
            statement.setString(7, serviceOrder.getStatus());

            if (serviceOrder.getReceivedAt() == null) {
                statement.setNull(8, java.sql.Types.TIMESTAMP);
            } else {
                statement.setTimestamp(8,
                        Timestamp.valueOf(serviceOrder.getReceivedAt()));
            }

            if (serviceOrder.getEstimatedCompletion() == null) {
                statement.setNull(9, java.sql.Types.DATE);
            } else {
                statement.setDate(9,
                        Date.valueOf(serviceOrder.getEstimatedCompletion()));
            }

            if (serviceOrder.getCompletedAt() == null) {
                statement.setNull(10, java.sql.Types.TIMESTAMP);
            } else {
                statement.setTimestamp(10,
                        Timestamp.valueOf(serviceOrder.getCompletedAt()));
            }

            statement.setLong(11, serviceOrder.getServiceId());

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM service_orders WHERE service_id = ?";

        try (Connection connection = databaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    private ServiceOrder getServiceOrderFromResult(ResultSet result)
            throws SQLException {

        long technicianId = result.getLong("technician_id");
        Long technician = null;

        if (!result.wasNull()) {
            technician = technicianId;
        }

        Timestamp receivedAt = result.getTimestamp("received_at");
        Date estimatedCompletion = result.getDate("estimated_completion");
        Timestamp completedAt = result.getTimestamp("completed_at");
        Timestamp createdAt = result.getTimestamp("created_at");

        return new ServiceOrder(
                result.getLong("service_id"),
                result.getString("ticket_number"),
                result.getLong("customer_id"),
                result.getLong("device_id"),
                result.getLong("received_by"),
                technician,
                result.getString("complaint"),
                result.getString("status"),
                receivedAt == null ? null : receivedAt.toLocalDateTime(),
                estimatedCompletion == null
                        ? null
                        : estimatedCompletion.toLocalDate(),
                completedAt == null ? null : completedAt.toLocalDateTime(),
                createdAt == null ? null : createdAt.toLocalDateTime());
    }
}
