package uoa.lavs.models.Loan;

import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.RateType;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.utility.LoanRepayment;
import uoa.lavs.utility.PaymentFrequency;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Loan {

    protected String loanId;
    protected String customerID;
    protected String customerName;
    protected List<Coborrower> coborrowerList = new ArrayList<>();
    protected Double principal;
    protected RateType rateType;
    protected Double rate;
    protected LocalDate startDate;
    protected Integer period;
    protected Frequency compoundingFrequency;
    protected PaymentFrequency paymentFrequency;
    protected Double paymentAmount;
    protected LoanStatus status;
    protected LoanDetails summary;
    protected Integer term;

    public String getId() {
        return loanId;
    }

    public String getCustomerId() {
        return customerID;
    }

    public List<Coborrower> getCoborrowerList() {
        return coborrowerList;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Double getPrincipal() {
        return principal;
    }

    public RateType getRateType() {
        return rateType;
    }

    public Double getRate() {
        return rate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Integer getPeriod() {
        return period;
    }

    public Frequency getCompoundingFrequency() {
        return compoundingFrequency;
    }

    public PaymentFrequency getPaymentFrequency() {
        return paymentFrequency;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public Integer getTerm() {
        return term;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void addCoborrower(Coborrower coborrower) {
        this.coborrowerList.add(coborrower);
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPrincipal(Double principal) {
        this.principal = principal;
    }

    public void setRateType(RateType rateType) {
        this.rateType = rateType;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public void setCompoundingFrequency(Frequency compoundingFrequency) {
        this.compoundingFrequency = compoundingFrequency;
    }

    public void setPaymentFrequency(PaymentFrequency paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public void setSummary(LoanDetails summary) {
        this.summary = summary;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public LoanDetails getSummary() {
        return summary;
    }

    public abstract ArrayList<LoanRepayment> getRepaymentSchedule();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            System.out.println("Class mismatch or null object.");
            return false;
        }
        Loan loan = (Loan) obj;

        if (!Objects.equals(loanId, loan.loanId)) {
            System.out.println("Loan ID mismatch: " + loanId + " vs " + loan.loanId);
            return false;
        }
        if (!Objects.equals(customerID, loan.customerID)) {
            System.out.println("Customer ID mismatch: " + customerID + " vs " + loan.customerID);
            return false;
        }
        if (!Objects.equals(customerName, loan.customerName)) {
            System.out.println("Customer Name mismatch: " + customerName + " vs " + loan.customerName);
            return false;
        }
        if (!Objects.equals(coborrowerList, loan.coborrowerList)) {
            System.out.println("Coborrower List mismatch: " + coborrowerList + " vs " + loan.coborrowerList);
            return false;
        }
        if (!Objects.equals(principal, loan.principal)) {
            System.out.println("Principal mismatch: " + principal + " vs " + loan.principal);
            return false;
        }
        if (rateType != loan.rateType) {
            System.out.println("Rate Type mismatch: " + rateType + " vs " + loan.rateType);
            return false;
        }
        if (!Objects.equals(rate, loan.rate)) {
            System.out.println("Rate mismatch: " + rate + " vs " + loan.rate);
            return false;
        }
        if (!Objects.equals(startDate, loan.startDate)) {
            System.out.println("Start Date mismatch: " + startDate + " vs " + loan.startDate);
            return false;
        }
        if (!Objects.equals(period, loan.period)) {
            System.out.println("Period mismatch: " + period + " vs " + loan.period);
            return false;
        }
        if (compoundingFrequency != loan.compoundingFrequency) {
            System.out.println("Compounding Frequency mismatch: " + compoundingFrequency + " vs " + loan.compoundingFrequency);
            return false;
        }
        if (paymentFrequency != loan.paymentFrequency) {
            System.out.println("Payment Frequency mismatch: " + paymentFrequency + " vs " + loan.paymentFrequency);
            return false;
        }
        if (!Objects.equals(paymentAmount, loan.paymentAmount)) {
            System.out.println("Payment Amount mismatch: " + paymentAmount + " vs " + loan.paymentAmount);
            return false;
        }
        if (status != loan.status) {
            System.out.println("Status mismatch: " + status + " vs " + loan.status);
            return false;
        }
        if (!Objects.equals(summary, loan.summary)) {
            System.out.println("Summary mismatch: " + summary + " vs " + loan.summary);
            return false;
        }
        if (!Objects.equals(term, loan.term)) {
            System.out.println("Term mismatch: " + term + " vs " + loan.term);
            return false;
        }

        return true;
    }


    @Override
    public int hashCode() {
        return Objects.hash(loanId, customerID, customerName, coborrowerList, principal, rateType, rate, startDate, period, compoundingFrequency, paymentFrequency, paymentAmount, status, summary, term);
    }


}
