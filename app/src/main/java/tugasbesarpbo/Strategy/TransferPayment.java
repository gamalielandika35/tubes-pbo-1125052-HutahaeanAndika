package tugasbesarpbo.Strategy;

import java.math.BigDecimal;

public class TransferPayment implements PaymentStrategy {

    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("Pembayaran transfer: " + amount);
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "TRANSFER";
    }
}