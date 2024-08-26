package uoa.lavs.comms.Loan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractWriter;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.mainframe.messages.customer.UpdateCustomer;
import uoa.lavs.mainframe.messages.loan.UpdateLoan;
import uoa.lavs.mainframe.messages.loan.UpdateLoanStatus;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.utility.PaymentFrequency;

import java.util.HashMap;
import java.util.Map;

public class UpdateStatus extends AbstractWriter<LoanStatus> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(UpdateStatus.class);

    // Returns ID for new loan
    @Override
    public String add(Connection conn, LoanStatus statusLoan, String loanID) {
        UpdateLoanStatus newStatus = new UpdateLoanStatus();
        newStatus.setLoanId(loanID);
        newStatus.setStatus(statusLoan);

        return processRequest(conn, newStatus, statusLoan, status -> {
            logger.info(
                    "Loan Status Updated: Loan ID = {}, Transaction ID = {}",
                    loanID,
                    status.getTransactionId());
            // Return new loan ID
            return loanID;
        }, status -> {
            mainframeError(status.getErrorCode(), status.getErrorMessage(), "0", statusLoan);
            return "0";
        }, 2207, "LoanStatus", loanID);
    }

    @Override
    protected Map<String, String> extractLogProperties(LoanStatus loan, String loanID) {
        Map<String, String> properties = new HashMap<>();
        System.out.println("Loan ID: " + loanID);
        properties.put("id", loanID);
        properties.put("status", loan.toString());
        return properties;
    }
}
