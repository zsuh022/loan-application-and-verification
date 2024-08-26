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

    public String getStatusString() {
        switch (status) {
            case "1" -> {
                return "New";
            }
            case "5" -> {
                return "Active";
            }
            case "8" -> {
                return "Cancelled";
            }
            case "2" -> {
                return "Pending";
            }
            default -> {
                return "Unknown";
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LoanSummary that = (LoanSummary) o;

        if (loanID != null ? !loanID.equals(that.loanID) : that.loanID != null) return false;
        if (customerID != null ? !customerID.equals(that.customerID) : that.customerID != null) return false;
        if (customerName != null ? !customerName.equals(that.customerName) : that.customerName != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return principal != null ? principal.equals(that.principal) : that.principal == null;
    }

    @Override
    public int hashCode() {
        int result = loanID != null ? loanID.hashCode() : 0;
        result = 31 * result + (customerID != null ? customerID.hashCode() : 0);
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (principal != null ? principal.hashCode() : 0);
        return result;
    }
}
