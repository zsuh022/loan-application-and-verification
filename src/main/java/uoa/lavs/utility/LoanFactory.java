package uoa.lavs.utility;

import uoa.lavs.models.Loan.Loan;
import uoa.lavs.models.Loan.Mortgage;

public class LoanFactory {

    public Loan getLoan(LoanType loanType) {
        switch (loanType) {
            case Mortgage:
                return new Mortgage();
            default:
                // invalid loan type
                return null;
        }
    }
}
