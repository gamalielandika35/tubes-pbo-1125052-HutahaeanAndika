
package tugasbesarpbo.repository;

import tugasbesarpbo.Database.databaseConnection;
import tugasbesarpbo.model.ServiceDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ServiceDetailRepository
        implements GenericRepository<ServiceDetail> {

    @Override
    public void save(ServiceDetail serviceDetail) throws SQLException {
        String sql = "INSERT INTO service_details "
                + "(service_id, diagnosis, repair_action, labor_cost, "
                + "technician_notes) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, serviceDetail.getServiceId());
            statement.setString(2, serviceDetail.getDiagnosis());
            statement.setString(3, serviceDetail.getRepairAction());
            statement.setBigDecimal(4, serviceDetail.getLaborCost());
            statement.setString(5, serviceDetail.getTechnicianNotes());

            statement.executeUpdate();
        }
    }

    @Override
    public ServiceDetail findById(long id) throws SQLException {
        String sql = "SELECT * FROM service_details WHERE detail_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return getServiceDetailFromResult(result);
                }
            }
        }

        return null;
    }

    @Override
    public List<ServiceDetail> findAll() throws SQLException {
        List<ServiceDetail> serviceDetails =
                new ArrayList<ServiceDetail>();

        String sql = "SELECT * FROM service_details ORDER BY detail_id";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                serviceDetails.add(getServiceDetailFromResult(result));
            }
        }

        return serviceDetails;
    }

    @Override
    public void update(ServiceDetail serviceDetail) throws SQLException {
        String sql = "UPDATE service_details SET "
                + "service_id = ?, diagnosis = ?, repair_action = ?, "
                + "labor_cost = ?, technician_notes = ? "
                + "WHERE detail_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, serviceDetail.getServiceId());
            statement.setString(2, serviceDetail.getDiagnosis());
            statement.setString(3, serviceDetail.getRepairAction());
            statement.setBigDecimal(4, serviceDetail.getLaborCost());
            statement.setString(5, serviceDetail.getTechnicianNotes());
            statement.setLong(6, serviceDetail.getDetailId());

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM service_details WHERE detail_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    private ServiceDetail getServiceDetailFromResult(ResultSet result)
            throws SQLException {

        Timestamp updatedAt = result.getTimestamp("updated_at");

        return new ServiceDetail(
                result.getLong("detail_id"),
                result.getLong("service_id"),
                result.getString("diagnosis"),
                result.getString("repair_action"),
                result.getBigDecimal("labor_cost"),
                result.getString("technician_notes"),
                updatedAt == null ? null : updatedAt.toLocalDateTime()
        );
    }
}
