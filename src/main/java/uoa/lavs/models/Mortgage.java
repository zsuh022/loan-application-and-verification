package uoa.lavs.models;

import uoa.lavs.utility.AmortizingLoanCalculator;
import uoa.lavs.utility.LoanRepayment;
import uoa.lavs.utility.PaymentFrequency;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Mortgage extends Loan {

    @Override
    public ArrayList<LoanRepayment> getRepaymentSchedule() {
        AmortizingLoanCalculator calculator = new AmortizingLoanCalculator();
        return calculator.generateRepaymentSchedule(principal, rate, paymentAmount, paymentFrequency, startDate);
    }

    @Override
    public void writeLoan(HashMap<String, String> map) {
        // TODO:
    }
}
