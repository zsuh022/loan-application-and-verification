package uoa.lavs.models.Loan;

import uoa.lavs.utility.AmortizingLoanCalculator;
import uoa.lavs.utility.LoanRepayment;

import java.util.ArrayList;
import java.util.HashMap;

public class Mortgage extends Loan {

    @Override
    public ArrayList<LoanRepayment> getRepaymentSchedule() {
        AmortizingLoanCalculator calculator = new AmortizingLoanCalculator();
        return calculator.generateRepaymentSchedule(principal, rate, paymentAmount, paymentFrequency, startDate);
    }

}
