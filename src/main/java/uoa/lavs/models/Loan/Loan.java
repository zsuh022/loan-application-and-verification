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

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

}
