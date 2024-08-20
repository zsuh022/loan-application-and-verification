package uoa.lavs.comms.Loan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractWriter;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.messages.customer.UpdateCustomer;
import uoa.lavs.mainframe.messages.loan.UpdateLoan;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.utility.PaymentFrequency;

import java.util.HashMap;
import java.util.Map;

public class AddLoan extends AbstractWriter<Loan> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(AddLoan.class);

    // Returns ID for new loan
    @Override
    public String add(Connection conn, Loan loan) {
        UpdateLoan newLoan = new UpdateLoan();
        // null to indicate new loan
        newLoan.setLoanId(null);
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
                    "New Loan created: Customer = {}, ID = {}, Transaction ID = {}",
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
        properties.put("customerId", loan.getCustomerId());
        properties.put("status", loan.getStatus().toString());
        properties.put("principal", loan.getPrincipal().toString());
        properties.put("rate.value", loan.getRate().toString());
        properties.put("rate.type", loan.getRate().toString());
        properties.put("date", loan.getStartDate().toString());
        properties.put("period", loan.getPeriod().toString());
        properties.put("term", loan.getTerm().toString());
        properties.put("payment.amount", loan.getPaymentAmount().toString());
        properties.put("payment.freq", loan.getPaymentFrequency().toString());
        properties.put("compounding", loan.getCompoundingFrequency().toString());
        return properties;
    }
}
