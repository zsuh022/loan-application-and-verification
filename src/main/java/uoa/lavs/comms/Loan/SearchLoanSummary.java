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
        LoadLoanSummary summary = new LoadLoanSummary();
        summary.setLoanId(loanID);

        return processRequest(conn, summary, status -> {
            LoanDetails value = new LoanDetails(
                    loanID, summary.getCustomerIdFromServer(), summary.getCustomerNameFromServer(),
                    summary.getPrincipalFromServer(), summary.getRateValueFromServer(),
                    summary.getPayoffDateFromServer(), summary.getTermFromServer(),
                    summary.getTotalInterestFromServer(), summary.getTotalLoanCostFromServer(),
                    summary.getPaymentAmountFromServer(), summary.getPaymentFrequencyFromServer()
            );
            logger.info("Loan Summary for customer ID {}, successfully loaded",
                    summary.getCustomerIdFromServer());
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
