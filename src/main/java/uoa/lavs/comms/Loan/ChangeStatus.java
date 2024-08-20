package uoa.lavs.comms.Loan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractWriter;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.mainframe.messages.loan.UpdateLoanCoborrower;
import uoa.lavs.mainframe.messages.loan.UpdateLoanStatus;
import uoa.lavs.models.Loan.Coborrower;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.utility.PaymentFrequency;

import java.util.HashMap;
import java.util.Map;

public class ChangeStatus extends AbstractWriter<LoanStatus> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(ChangeStatus.class);

    @Override
    public String add(Connection conn, LoanStatus statusLoan, String loanID) {
        UpdateLoanStatus value = new UpdateLoanStatus();

        value.setLoanId(loanID);
        value.setStatus(statusLoan);

        return processRequest(conn, value, statusLoan, status -> {
            logger.info(
                    "Loan ID: {} Status updated Transaction ID {}",
                    loanID,
                    status.getTransactionId());
            return "1";
        }, status -> {
            mainframeError(status.getErrorCode(), status.getErrorMessage(), "0", statusLoan);
            return "0";
        }, 2207, "Loan Status", null);
    }

    @Override
    protected Map<String, String> extractLogProperties(LoanStatus co, String loanID) {
        Map<String, String> properties = new HashMap<>();
        properties.put("id", loanID);
        properties.put("status", co.name());
        return properties;
    }
}
