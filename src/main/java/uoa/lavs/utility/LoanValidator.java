package uoa.lavs.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.RateType;
import uoa.lavs.models.Loan.Coborrower;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.models.Loan.Mortgage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class LoanValidator {

    public static final String TEMPORARY_LOAN_ID_PREFIX = "TEMP_LOAN_";
    // Log4J2
    private static final Logger logger = LogManager.getLogger(LoanValidator.class);

    public static String generateTemporaryLoanId() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timeAsString = LocalDateTime.now().format(dtf);
        return TEMPORARY_LOAN_ID_PREFIX + timeAsString;
    }

    public Loan createLoan(Map<String, String> loanValuesMap) {
        // assume loan is mortgage
        Loan loan = new Mortgage();

        loan.setLoanId(generateTemporaryLoanId());
        loan.setCustomerID(loanValuesMap.get("customerId"));
        loan.setPrincipal(Double.parseDouble(loanValuesMap.get("principal")));
        loan.setRate(Double.parseDouble(loanValuesMap.get("rate")));
        loan.setRateType(discoverRateType(loanValuesMap));
        loan.setStartDate(LocalDate.parse(loanValuesMap.get("startDate")));
        loan.setPeriod(Integer.parseInt(loanValuesMap.get("period")));
        loan.setCompoundingFrequency(discoverCompoundingFrequency(loanValuesMap));
        loan.setPaymentFrequency(discoverPaymentFrequency(loanValuesMap));
        loan.setPaymentAmount(Double.parseDouble(loanValuesMap.get("amount")));

        // TODO: loan.setStatus(LoanStatus.Active);

        for (int i = 0; i < 18; i++) {
            String coborrowerId = loanValuesMap.get("coborrowerId" + i);
            if (coborrowerId != null) {
                Coborrower coborrower = new Coborrower();
                coborrower.setId(coborrowerId);
            }
        }

        return loan;
    }

    private RateType discoverRateType(Map<String, String> loanMap) {
        if ("true".equals(loanMap.get("isFloating"))) {
            return RateType.Floating;
        } else if ("true".equals(loanMap.get("isFixed"))) {
            return RateType.Fixed;
        } else if ("true".equals(loanMap.get("isInterestOnly"))) {
            return RateType.InterestOnly;
        }
        return null;
    }

    private Frequency discoverCompoundingFrequency(Map<String, String> loanMap) {
        if ("true".equals(loanMap.get("compoundingWeekly"))) {
            return Frequency.Weekly;
        } else if ("true".equals(loanMap.get("compoundingMonthly"))) {
            return Frequency.Monthly;
        } else if ("true".equals(loanMap.get("compoundingAnnually"))) {
            return Frequency.Yearly;
        }
        return null;
    }

    private PaymentFrequency discoverPaymentFrequency(Map<String, String> loanMap) {
        if ("true".equals(loanMap.get("frequencyWeekly"))) {
            return PaymentFrequency.Weekly;
        } else if ("true".equals(loanMap.get("frequencyFortnightly"))) {
            return PaymentFrequency.Fortnightly;
        } else if ("true".equals(loanMap.get("frequencyMonthly"))) {
            return PaymentFrequency.Monthly;
        }
        return null;
    }

    public boolean validateLoan(Map<String, String> loanMap) {
        logger.info("Validating loan for customer id {}", loanMap.get("customerId"));

        if (loanMap.get("customerId") == null || loanMap.get("customerId").isEmpty()) {
            logger.error("ValidateLoan method failed: Customer ID is empty");
            return false;
        }

        // check customer id valid

        if (loanMap.get("principal") == null || loanMap.get("principal").isEmpty()) {
            logger.error("ValidateLoan method failed: Principal is empty");
            return false;
        }

        if (loanMap.get("rate") == null || loanMap.get("rate").isEmpty()) {
            logger.error("ValidateLoan method failed: Rate is empty");
            return false;
        }

        int rateTypeCounter = 0;
        if (loanMap.get("isFloating").equals("true")) {
            rateTypeCounter++;
        }
        if (loanMap.get("isFixed").equals("true")) {
            rateTypeCounter++;
        }

        if (rateTypeCounter != 1) {
            logger.error("ValidateLoan method failed: Select exactly one rate type");
        }

        if (loanMap.get("startDate") == null || loanMap.get("startDate").isEmpty()) {
            logger.error("ValidateLoan method failed: startDate is empty");
            return false;
        }

        if (loanMap.get("period") == null || loanMap.get("period").isEmpty()) {
            logger.error("ValidateLoan method failed: period is empty");
            return false;
        }

        int compoundingCount = 0;
        if (loanMap.get("compoundingWeekly").equals("true")) {
            compoundingCount++;
        }
        if (loanMap.get("compoundingMonthly").equals("true")) {
            compoundingCount++;
        }
        if (loanMap.get("compoundingAnnually").equals("true")) {
            compoundingCount++;
        }

        if (compoundingCount != 1) {
            logger.error("ValidateLoan method failed: Select exactly one compounding frequency");
            return false;
        }

        int frequencyCount = 0;
        if (loanMap.get("frequencyWeekly").equals("true")) {
            frequencyCount++;
        }
        if (loanMap.get("frequencyFortnightly").equals("true")) {
            frequencyCount++;
        }
        if (loanMap.get("frequencyMonthly").equals("true")) {
            frequencyCount++;
        }

        if (frequencyCount != 1) {
            logger.error("ValidateLoan method failed: Select exactly one payment frequency");
            return false;
        }

        if (loanMap.get("amount") == null || loanMap.get("amount").isEmpty()) {
            logger.error("ValidateLoan method failed: amount is empty");
            return false;
        }

        // TODO: discussion
        if (loanMap.get("isInterestOnly") == null || loanMap.get("isInterestOnly").isEmpty()) {
            logger.error("ValidateLoan method failed: isInterestOnly is empty");
            return false;
        }

        // check coborrower id valid

        return true;
    }
}
