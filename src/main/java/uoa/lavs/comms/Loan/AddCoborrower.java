package uoa.lavs.comms.Loan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractWriter;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.messages.loan.UpdateLoanCoborrower;
import uoa.lavs.models.Loan.Coborrower;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.utility.PaymentFrequency;

import java.util.HashMap;
import java.util.Map;

public class AddCoborrower extends AbstractWriter<Coborrower> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(AddCoborrower.class);

    // Returns ID for new loan
    @Override
    public String add(Connection conn, Coborrower co, String loanID) {
        UpdateLoanCoborrower newCo = new UpdateLoanCoborrower();
        
        newCo.setCoborrowerId(co.getId());
        newCo.setNumber(null);
        newCo.setLoanId(loanID);

        return processRequest(conn, newCo, co, status -> {
            logger.info(
                    "Coborrower {} created for Loan ID {}, Transaction ID {}",
                    newCo.getCoborrowerNameFromServer(),
                    loanID,
                    status.getTransactionId());
            // Return coborrower ID
            return newCo.getCoborrowerIdFromServer();
        }, status -> {
            mainframeError(status.getErrorCode(), status.getErrorMessage(), "0", co);
            return "0";
        }, 2206, "Coborrower", null);
    }

    @Override
    protected Map<String, String> extractLogProperties(Coborrower co, String loanID) {
        Map<String, String> properties = new HashMap<>();
        properties.put("coborrowerId", co.getId());
        properties.put("id", loanID);
        properties.put("number", null);
        return properties;
    }
}
