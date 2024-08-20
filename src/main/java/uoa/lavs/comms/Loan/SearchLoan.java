package uoa.lavs.comms.Loan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractSearchable;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.mainframe.messages.loan.LoadLoan;
import uoa.lavs.models.Loan.Mortgage;
import uoa.lavs.mainframe.LoanStatus;

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
                case "New" -> stat = LoanStatus.New;
                case "Pending" -> stat = LoanStatus.Pending;
                case "Cancelled" -> stat = LoanStatus.Cancelled;
                case "Active" -> stat = LoanStatus.Active;
                case "Unknown" -> stat = LoanStatus.Unknown;
            }
            newLoan.setStatus(stat);
            newLoan.setTerm(loan.getTermFromServer());
            newLoan.setStartDate(newLoan.getStartDate());
            newLoan.setCompoundingFrequency(newLoan.getCompoundingFrequency());
            newLoan.setPaymentAmount(newLoan.getPaymentAmount());
            newLoan.setPaymentFrequency(newLoan.getPaymentFrequency());

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
