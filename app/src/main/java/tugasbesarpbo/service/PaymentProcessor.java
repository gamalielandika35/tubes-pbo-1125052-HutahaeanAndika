package tugasbesarpbo.service;

import tugasbesarpbo.Strategy.PaymentStrategy;
import java.math.BigDecimal;

public class PaymentProcessor {

    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public boolean processPayment(BigDecimal amount) {
        if (paymentStrategy == null) {
            return false;
        }

        return paymentStrategy.pay(amount);
    }

    public String getPaymentMethod() {
        return paymentStrategy.getPaymentMethod();
    }
}