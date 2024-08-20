package uoa.lavs.comms.Loan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractWriter;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.messages.loan.UpdateLoan;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.utility.PaymentFrequency;

import java.util.HashMap;
import java.util.Map;

public class ChangeLoan extends AbstractWriter<Loan> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(ChangeLoan.class);

    @Override
    public String add(Connection conn, Loan loan) {
        UpdateLoan newLoan = new UpdateLoan();
        newLoan.setLoanId(loan.getId());
        newLoan.setCustomerId(loan.getCustomerId());
        newLoan.setCompounding(loan.getCompoundingFrequency());
        newLoan.setStartDate(loan.getStartDate());
        newLoan.setPrincipal(loan.getPrincipal());
        Frequency freq = null;
        PaymentFrequency payment = loan.getPaymentFrequency();
        switch (payment) {
            case Weekly -> freq = Frequency.Weekly;
            case Fortnightly -> freq = Frequency.Fortnightly;
            case Monthly -> freq = Frequency.Monthly;
        }
        newLoan.setPaymentFrequency(freq);
        newLoan.setPaymentAmount(loan.getPaymentAmount());
        newLoan.setPeriod(loan.getPeriod());
        newLoan.setRateType(loan.getRateType());
        newLoan.setRateValue(loan.getRate());
        newLoan.setTerm(loan.getTerm());

        return processRequest(conn, newLoan, loan, status -> {
            logger.info(
                    "Loan Updated: Customer = {}, ID = {}, Transaction ID = {}",
                    newLoan.getCustomerNameFromServer(),
                    newLoan.getLoanIdFromServer(),
                    status.getTransactionId());
            // Return new loan ID
            return newLoan.getLoanIdFromServer();
        }, status -> {
            mainframeError(status.getErrorCode(), status.getErrorMessage(), "0", loan);
            return "0";
        }, 2201, "Loan", null);
    }

    @Override
    protected Map<String, String> extractLogProperties(Loan loan, String loanID) {
        Map<String, String> properties = new HashMap<>();
        if (loan.getId() != null) {
            properties.put("loanId", loan.getId());
        }
        if (loan.getCustomerId() != null) {
            properties.put("customerId", loan.getCustomerId());
        }
        if (loan.getStatus() != null) {
            properties.put("status", loan.getStatus().toString());
        }
        if (loan.getPrincipal() != null) {
            properties.put("principal", loan.getPrincipal().toString());
        }
        if (loan.getRate() != null) {
            properties.put("rate.value", loan.getRate().toString());
        }
        if (loan.getRateType() != null) {
            properties.put("rate.type", loan.getRateType().name());
        }
        if (loan.getStartDate() != null) {
            properties.put("date", loan.getStartDate().toString());
        }
        if (loan.getPeriod() != null) {
            properties.put("period", loan.getPeriod().toString());
        }
        if (loan.getTerm() != null) {
            properties.put("term", loan.getTerm().toString());
        }
        if (loan.getPaymentAmount() != null) {
            properties.put("payment.amount", loan.getPaymentAmount().toString());
        }
        if (loan.getPaymentFrequency() != null) {
            properties.put("payment.freq", loan.getPaymentFrequency().toString());
        }
        if (loan.getCompoundingFrequency() != null) {
            properties.put("compounding", loan.getCompoundingFrequency().toString());
        }

        return properties;
    }
}
