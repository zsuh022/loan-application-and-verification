package uoa.lavs.models.Loan;

import uoa.lavs.mainframe.Frequency;

import java.time.LocalDate;

public class LoanDetails {

    protected String loanID;
    protected String customerID;
    protected String customerName;
    protected Double principal;
    protected Double rate;
    protected LocalDate payoffDate;
    protected Integer term;
    protected Double interest;
    protected Double total;
    protected Double payment;
    protected Frequency frequency;

    public LoanDetails() {
    }

    public LoanDetails(String loanID, String customerID, String customerName, Double principal, Double rate, LocalDate payoffDate, Integer term, Double interest, Double total, Double payment, Frequency frequency) {
        this.loanID = loanID;
        this.customerID = customerID;
        this.customerName = customerName;
        this.principal = principal;
        this.rate = rate;
        this.payoffDate = payoffDate;
        this.term = term;
        this.interest = interest;
        this.total = total;
        this.payment = payment;
        this.frequency = frequency;
    }

    public String getLoanID() {
        return loanID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Double getPrincipal() {
        return principal;
    }

    public Double getRate() {
        return rate;
    }

    public LocalDate getPayoffDate() {
        return payoffDate;
    }

    public Integer getTerm() {
        return term;
    }

    public Double getInterest() {
        return interest;
    }

    public Double getTotal() {
        return total;
    }

    public Double getPayment() {
        return payment;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public String getFrequencyString() {
        switch (frequency) {
            case Weekly -> {
                return "Weekly";
            }
            case Fortnightly -> {
                return "Fortnightly";
            }
            case Monthly -> {
                return "Monthly";
            }
            default -> {
                return "Unknown";
            }
        }
    }
}
