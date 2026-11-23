
package tugasbesarpbo.repository;

import tugasbesarpbo.Database.databaseConnection;
import tugasbesarpbo.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository implements GenericRepository<Payment> {

    @Override
    public void save(Payment payment) throws SQLException {
        String sql = "INSERT INTO payments "
                + "(service_id, received_by, amount, payment_method, "
                + "payment_status, paid_at, reference_no) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, payment.getServiceId());
            statement.setLong(2, payment.getReceivedBy());
            statement.setBigDecimal(3, payment.getAmount());
            statement.setString(4, payment.getPaymentMethod());
            statement.setString(5, payment.getPaymentStatus());
            statement.setTimestamp(6, Timestamp.valueOf(payment.getPaidAt()));
            statement.setString(7, payment.getReferenceNo());

            statement.executeUpdate();
        }
    }

    @Override
    public Payment findById(long id) throws SQLException {
        String sql = "SELECT * FROM payments WHERE payment_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return getPaymentFromResult(result);
                }
            }
        }

        return null;
    }

    @Override
    public List<Payment> findAll() throws SQLException {
        List<Payment> payments = new ArrayList<Payment>();

        String sql = "SELECT * FROM payments ORDER BY payment_id";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                payments.add(getPaymentFromResult(result));
            }
        }

        return payments;
    }

    @Override
    public void update(Payment payment) throws SQLException {
        String sql = "UPDATE payments SET "
                + "service_id = ?, received_by = ?, amount = ?, "
                + "payment_method = ?, payment_status = ?, "
                + "paid_at = ?, reference_no = ? "
                + "WHERE payment_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, payment.getServiceId());
            statement.setLong(2, payment.getReceivedBy());
            statement.setBigDecimal(3, payment.getAmount());
            statement.setString(4, payment.getPaymentMethod());
            statement.setString(5, payment.getPaymentStatus());
            statement.setTimestamp(6, Timestamp.valueOf(payment.getPaidAt()));
            statement.setString(7, payment.getReferenceNo());
            statement.setLong(8, payment.getPaymentId());

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM payments WHERE payment_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    private Payment getPaymentFromResult(ResultSet result)
            throws SQLException {

        Timestamp paidAt = result.getTimestamp("paid_at");

        return new Payment(
                result.getLong("payment_id"),
                result.getLong("service_id"),
                result.getLong("received_by"),
                paidAt == null ? null : paidAt.toLocalDateTime(),
                result.getBigDecimal("amount"),
                result.getString("payment_method"),
                result.getString("payment_status"),
                result.getString("reference_no")
        );
    }
}
