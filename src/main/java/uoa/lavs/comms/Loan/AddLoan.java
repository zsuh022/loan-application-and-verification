package uoa.lavs.comms.Loan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractWriter;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.mainframe.messages.customer.UpdateCustomer;
import uoa.lavs.mainframe.messages.loan.UpdateLoan;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.utility.PaymentFrequency;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static uoa.lavs.logging.LocalLogManager.TEMPORARY_LOAN_ID_PREFIX;

public class AddLoan extends AbstractWriter<Loan> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(AddLoan.class);

    // Returns ID for new loan
    @Override
    public String add(Connection conn, Loan loan) {
        UpdateLoan newLoan = new UpdateLoan();
        // null to indicate new loan
        if (loan.getId().contains(TEMPORARY_LOAN_ID_PREFIX)) {
            newLoan.setLoanId(null);
        } else {
            newLoan.setLoanId(loan.getId());
        }
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
            mainframeError(status.getErrorCode(), status.getErrorMessage(), loan.getId(), loan);
            return loan.getId();
        }, 2201, "Loan", null);
    }

    @Override
    protected Map<String, String> extractLogProperties(Loan loan, String loanID) {
        Map<String, String> properties = new HashMap<>();
        properties.put("id", loanID);
        properties.put("customerId", loan.getCustomerId());
        properties.put("status", String.valueOf(LoanStatus.New));
        properties.put("principal", loan.getPrincipal().toString());
        properties.put("rate.value", loan.getRate().toString());
        properties.put("rate.type", Integer.toString(loan.getRateType().ordinal()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        properties.put("date", loan.getStartDate().format(formatter));
        properties.put("period", loan.getPeriod().toString());
        properties.put("term", loan.getTerm().toString());
        properties.put("payment.amount", loan.getPaymentAmount().toString());
        properties.put("payment.freq", Integer.toString(loan.getPaymentFrequency().ordinal()));
        properties.put("compounding", Integer.toString(loan.getCompoundingFrequency().ordinal()));
        return properties;
    }
}
