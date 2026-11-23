
package tugasbesarpbo.model;

import java.time.LocalDate;

public class Invoice {
    private long invoiceId;
    private long paymentId;
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private double totalAmount;

    public Invoice(long invoiceId, long paymentId,
            String invoiceNumber, LocalDate invoiceDate,
            double totalAmount) {
        this.invoiceId = invoiceId;
        this.paymentId = paymentId;
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.totalAmount = totalAmount;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
