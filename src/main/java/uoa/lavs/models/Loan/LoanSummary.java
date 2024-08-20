package uoa.lavs.models.Loan;

public class LoanSummary {

    private String loanID;
    private String customerID;
    private String customerName;
    private String status;
    private Double principal;

    public LoanSummary(String loanID, String customerID, String customerName, String status, Double principal) {
        this.loanID = loanID;
        this.customerID = customerID;
        this.customerName = customerName;
        this.status = status;
        this.principal = principal;
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

    public String getStatus() {
        return status;
    }

    public Double getPrincipal() {
        return principal;
    }
}
