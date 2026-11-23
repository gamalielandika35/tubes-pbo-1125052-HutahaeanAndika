package tugasbesarpbo.Strategy;

import java.math.BigDecimal;

public class QrisPayment implements PaymentStrategy {

    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("Pembayaran QRIS: " + amount);
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "QRIS";
    }
}