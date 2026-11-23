package tugasbesarpbo.Strategy;

import java.math.BigDecimal;

public class CashPayment implements PaymentStrategy {

    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("Pembayaran tunai: " + amount);
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "CASH";
    }
}