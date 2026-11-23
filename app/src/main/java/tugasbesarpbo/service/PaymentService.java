
package tugasbesarpbo.service;

import tugasbesarpbo.model.Payment;
import tugasbesarpbo.repository.PaymentRepository;

import java.sql.SQLException;
import java.util.List;

public class PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentService() {
        paymentRepository = new PaymentRepository();
    }

    public void addPayment(Payment payment)
            throws SQLException {
        paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments()
            throws SQLException {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(long id)
            throws SQLException {
        return paymentRepository.findById(id);
    }

    public void updatePayment(Payment payment)
            throws SQLException {
        paymentRepository.update(payment);
    }

    public void deletePayment(long id)
            throws SQLException {
        paymentRepository.delete(id);
    }
}
