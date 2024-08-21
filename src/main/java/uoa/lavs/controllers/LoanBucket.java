package uoa.lavs.controllers;

import uoa.lavs.models.Loan.Loan;

public class LoanBucket {

    private Loan Loan;

    private LoanBucket() {
    }

    private static class SingletonHelper {
        private static final LoanBucket INSTANCE = new LoanBucket();
    }

    public static LoanBucket getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public void setLoan(Loan c) {
        this.Loan = c;
    }

    public Loan getLoan() {
        return Loan;
    }
}
