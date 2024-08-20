package uoa.lavs.models.Loan;

import java.time.LocalDate;

public class Payments {

    protected String customerId;
    protected String customerName;
    protected Integer paymentCount;
    protected Double paymentInterest;
    protected Double paymentPrincipal;
    protected Double paymentRemaining;
    protected Integer paymentNumber;
    protected LocalDate paymentDate;

    public Payments(String customerId, String customerName, Integer paymentCount, Double paymentInterest, Double paymentPrincipal, Double paymentRemaining, Integer paymentNumber, LocalDate paymentDate) {

        this.customerId = customerId;
        this.customerName = customerName;
        this.paymentCount = paymentCount;
        this.paymentInterest = paymentInterest;
        this.paymentPrincipal = paymentPrincipal;
        this.paymentRemaining = paymentRemaining;
        this.paymentNumber = paymentNumber;
        this.paymentDate = paymentDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Integer getPaymentCount() {
        return paymentCount;
    }

    public Double getPaymentInterest() {
        return paymentInterest;
    }

    public Double getPaymentPrincipal() {
        return paymentPrincipal;
    }

    public Double getPaymentRemaining() {
        return paymentRemaining;
    }

    public Integer getPaymentNumber() {
        return paymentNumber;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }
}
