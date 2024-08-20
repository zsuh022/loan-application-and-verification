package uoa.lavs.models;

import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.utility.LoanRepayment;
import uoa.lavs.utility.LoanSummary;
import uoa.lavs.utility.PaymentFrequency;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Loan {

    protected String loanId;
    protected List<String> customerList = new ArrayList<>();
    protected Double principal;
    protected String rateType;
    protected Double rate;
    protected LocalDate startDate;
    protected String period;
    protected Frequency compoundingFrequency;
    protected PaymentFrequency paymentFrequency;
    protected Double paymentAmount;
    protected LoanStatus status;
    protected LoanSummary summary;

    public String getId() {
        return loanId;
    }

    public abstract ArrayList<LoanRepayment> getRepaymentSchedule();

    public void writeLoan(HashMap<String, String> map) {
        map.put("loanId", loanId);
        map.put("customerList", String.join(",", customerList));
        map.put("principal", Double.toString(principal));
        map.put("rateType", rateType);
        map.put("rate", Double.toString(rate));
        map.put("startDate", startDate.toString());
        map.put("period", period);
        map.put("compoundingFrequency", compoundingFrequency.toString());
        map.put("paymentFrequency", paymentFrequency.toString());
        map.put("paymentAmount", Double.toString(paymentAmount));
        map.put("status", status.toString());
        map.put("summary", summary.toString());
    }

    public abstract ArrayList<LoanRepayment> getRepaymentSchedule();

    public abstract void writeLoan(HashMap<String, String> map);
}
