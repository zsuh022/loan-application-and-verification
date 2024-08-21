package uoa.lavs.comms.Loan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractSearchable;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.RateType;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.mainframe.messages.loan.LoadLoan;
import uoa.lavs.models.Loan.Mortgage;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.utility.PaymentFrequency;

import java.util.List;

public class SearchLoan extends AbstractSearchable<Loan> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(SearchLoan.class);

    @Override
    public Loan findById(Connection conn, String loanID) {
        LoadLoan loan = new LoadLoan();
        loan.setLoanId(loanID);

        return (Loan) processRequest(conn, loan, status -> {
            Loan newLoan = new Mortgage();
            newLoan.setLoanId(loanID);
            newLoan.setPrincipal(loan.getPrincipalFromServer());
            newLoan.setPeriod(loan.getPeriodFromServer());
            newLoan.setCustomerID(loan.getCustomerIdFromServer());
            newLoan.setCustomerName(loan.getCustomerNameFromServer());
            newLoan.setRate(loan.getRateValueFromServer());

            newLoan.setRateType(loan.getRateTypeFromServer());
            LoanStatus stat = null;
            String statFromSvr = loan.getStatusFromServer();
            switch (statFromSvr) {
                case "1" -> stat = LoanStatus.New;
                case "2" -> stat = LoanStatus.Pending;
                case "5" -> stat = LoanStatus.Active;
                case "8" -> stat = LoanStatus.Cancelled;
            }
            newLoan.setStatus(stat);
            newLoan.setTerm(loan.getTermFromServer());
            newLoan.setStartDate(loan.getStartDateFromServer());
            newLoan.setCompoundingFrequency(loan.getCompoundingFromServer());
            newLoan.setPaymentAmount(loan.getPaymentAmountFromServer());
            PaymentFrequency freq = null;
            Frequency freqFromSvr = loan.getPaymentFrequencyFromServer();
            switch (freqFromSvr) {
                case Weekly -> freq = PaymentFrequency.Weekly;
                case Fortnightly -> freq = PaymentFrequency.Fortnightly;
                case Monthly -> freq = PaymentFrequency.Monthly;
            }
            newLoan.setPaymentFrequency(freq);
            if (loan.getRateTypeFromServer() == RateType.InterestOnly) {
                newLoan.setInterestOnly(true);
            } else {
                newLoan.setInterestOnly(false);
            }

            logger.info("Loan for customer ID {}, successfully loaded", loan.getCustomerIdFromServer());
            return newLoan;
        }, status -> {
            return new Mortgage();
        });
    }

    @Override
    public List<Loan> findAll(Connection conn, String customerId) {
        throw new UnsupportedOperationException("findAll is not supported for searching loan.");
    }
}
