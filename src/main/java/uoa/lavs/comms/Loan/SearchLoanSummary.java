package uoa.lavs.comms.Loan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractSearchable;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.loan.LoadLoanSummary;
import uoa.lavs.models.Loan.LoanDetails;

import java.util.List;

public class SearchLoanSummary extends AbstractSearchable<LoanDetails> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(SearchLoanSummary.class);

    @Override
    public LoanDetails findById(Connection conn, String loanID) {
        LoadLoanSummary loan = new LoadLoanSummary();
        loan.setLoanId(loanID);

        return processRequest(conn, loan, status -> {
            LoanDetails value = new LoanDetails(
                    loanID, loan.getCustomerIdFromServer(), loan.getCustomerNameFromServer(), loan.getPrincipalFromServer(),
                    loan.getRateValueFromServer(), loan.getPayoffDateFromServer(), loan.getTermFromServer(), loan.getTotalInterestFromServer(),
                    loan.getTotalLoanCostFromServer(), loan.getPaymentAmountFromServer(), loan.getPaymentFrequencyFromServer()
            );

            logger.info("Loan Summary for customer ID {}, successfully loaded", loan.getCustomerIdFromServer());
            return value;
        }, status -> {
            return new LoanDetails();
        });
    }

    @Override
    public List<LoanDetails> findAll(Connection conn, String customerId) {
        throw new UnsupportedOperationException("findAll is not supported for searching loan.");
    }
}
