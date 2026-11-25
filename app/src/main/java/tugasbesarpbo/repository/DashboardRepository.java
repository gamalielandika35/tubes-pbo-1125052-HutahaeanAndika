package tugasbesarpbo.repository;

import tugasbesarpbo.Database.databaseConnection;
import tugasbesarpbo.model.DashboardStatistics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardRepository {

    public DashboardStatistics getStatistics() throws SQLException {

        String sql =
                "SELECT " +
                "(SELECT COUNT(*) FROM service_orders) AS total_service_orders, " +
                "(SELECT COUNT(*) FROM service_orders " +
                "WHERE status = 'COMPLETED') AS completed_orders, " +
                "(SELECT COUNT(*) FROM service_orders " +
                "WHERE status IN ('RECEIVED', 'DIAGNOSING', " +
                "'WAITING_FOR_PARTS', 'IN_REPAIR')) AS in_progress, " +
                "(SELECT COUNT(*) FROM customers) AS total_customers";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            if (result.next()) {
                return new DashboardStatistics(
                        result.getInt("total_service_orders"),
                        result.getInt("completed_orders"),
                        result.getInt("in_progress"),
                        result.getInt("total_customers")
                );
            }
        }

        return new DashboardStatistics(0, 0, 0, 0);
    }
}