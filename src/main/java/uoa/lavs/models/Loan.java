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

    public abstract void writeLoan(HashMap<String, String> map);
}
