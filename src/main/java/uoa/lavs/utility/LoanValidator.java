package uoa.lavs.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.Customer.SearchCustomer;
import uoa.lavs.logging.Cache;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.mainframe.RateType;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Loan.Coborrower;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.models.Loan.Mortgage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

    public Loan createLoan(Map<String, String> loanMap) {
        logger.info("Creating loan for customer id {}", loanMap.get("customerId"));

        // assume loan is mortgage
        Loan loan = new Mortgage();

        loan.setLoanId(generateTemporaryLoanId());
        loan.setCustomerID(loanMap.get("customerId"));
        loan.setPrincipal(Double.parseDouble(loanMap.get("principal")));
        loan.setRate(Double.parseDouble(loanMap.get("rate")));
        loan.setRateType(discoverRateType(loanMap));
        loan.setStartDate(LocalDate.parse(loanMap.get("startDate")));
        loan.setTerm(Integer.parseInt(loanMap.get("term")));
        loan.setPeriod(Integer.parseInt(loanMap.get("period")));
        loan.setCompoundingFrequency(discoverCompoundingFrequency(loanMap));
        loan.setPaymentFrequency(discoverPaymentFrequency(loanMap));
        loan.setPaymentAmount(Double.parseDouble(loanMap.get("amount")));
        loan.setInterestOnly(Boolean.valueOf(loanMap.get("isInterestOnly")));

        for (int i = 0; i < 18; i++) {
            String coborrowerId = loanMap.get("coborrowerId" + i);
            if (coborrowerId != null) {
                Coborrower coborrower = new Coborrower();
                coborrower.setId(coborrowerId);
                List<Customer> cacheList = Cache.searchCustomerCacheId(coborrowerId);
                if (cacheList.size() == 1) {
                    coborrower.setName(cacheList.get(0).getName());
                } else {
                    SearchCustomer search = new SearchCustomer();
                    coborrower.setName(search.findById(Instance.getConnection(), coborrowerId).getName());
                }
                loan.addCoborrower(coborrower);
            }
        }

        logger.info("Created loan for customer id {}", loanMap.get("customerId"));
        return loan;
    }

    private RateType discoverRateType(Map<String, String> loanMap) {
        if ("true".equals(loanMap.get("isFloating"))) {
            logger.info("Create loan method: isFloating rate type selected");
            return RateType.Floating;
        } else if ("true".equals(loanMap.get("isFixed"))) {
            logger.info("Create loan method: isFixed rate type selected");
            return RateType.Fixed;
        } else if ("true".equals(loanMap.get("isInterestOnly"))) {
            logger.info("Create loan method: isInterestOnly rate type selected");
            return RateType.InterestOnly;
        }
        logger.info("Create loan method: null rate type selected");
        return null;
    }

    private Frequency discoverCompoundingFrequency(Map<String, String> loanMap) {
        if ("true".equals(loanMap.get("compoundingWeekly"))) {
            logger.info("Create loan method: compoundingWeekly frequency selected");
            return Frequency.Weekly;
        } else if ("true".equals(loanMap.get("compoundingMonthly"))) {
            logger.info("Create loan method: compoundingMonthly frequency selected");
            return Frequency.Monthly;
        } else if ("true".equals(loanMap.get("compoundingAnnually"))) {
            logger.info("Create loan method: compoundingAnnually frequency selected");
            return Frequency.Yearly;
        }
        logger.info("Create loan method: null compoundingFrequency selected");
        return null;
    }

    private PaymentFrequency discoverPaymentFrequency(Map<String, String> loanMap) {
        if ("true".equals(loanMap.get("frequencyWeekly"))) {
            logger.info("Create loan method: frequencyWeekly frequency selected");
            return PaymentFrequency.Weekly;
        } else if ("true".equals(loanMap.get("frequencyFortnightly"))) {
            logger.info("Create loan method: frequencyFortnight frequency selected");
            return PaymentFrequency.Fortnightly;
        } else if ("true".equals(loanMap.get("frequencyMonthly"))) {
            logger.info("Create loan method: frequencyMonthly frequency selected");
            return PaymentFrequency.Monthly;
        }
        logger.info("Create loan method: null paymentFrequency selected");
        return null;
    }

    public boolean validateLoan(Map<String, String> loanMap) {
        logger.info("Validating loan for customer id {}", loanMap.get("customerId"));

        if (loanMap.get("customerId") == null || loanMap.get("customerId").isEmpty()) {
            logger.error("ValidateLoan method failed: Customer ID is empty");
            return false;
        }

        // check customer id valid
        if (!isCustomerIdValid(loanMap.get("customerId"))) {
            logger.error("ValidateLoan method failed: Customer ID is invalid");
            return false;
        }

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
        if (loanMap.get("isInterestOnly").equals("true")) {
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

        if (loanMap.get("term") == null || loanMap.get("term").isEmpty()) {
            logger.error("ValidateLoan method failed: term is empty");
            return false;
        }

        int term = Integer.parseInt(loanMap.get("term"));
        int period = Integer.parseInt(loanMap.get("period"));
        if (term < period) {
            logger.error("ValidateLoan method failed: term is less than period");
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

        // check coborrower id valid
        for (int i = 0; i < 18; i++) {
            String coborrowerId = loanMap.get("coborrowerId" + i);
            if (coborrowerId != null && !isCustomerIdValid(coborrowerId)) {
                logger.error("ValidateLoan method failed: Customer ID of coborrower is invalid");
                return false;
            }
        }

        logger.info("Validating loan for customer id {}", loanMap.get("customerId"));
        return true;
    }

    private boolean isCustomerIdValid(String customerId) {
        logger.info("Validating customer id in isCustomerIdValid method {}", customerId);

        // search using SearchCustomer
        SearchCustomer search = new SearchCustomer();

        try {
            Customer customers = search.findById(Instance.getConnection(), customerId);

            if (customers != null) {
                logger.info("isCustomerIdValid method: Customer ID {} is valid", customerId);
                return true;
            } else {
                logger.error("isCustomerIdValid method: Customer ID {} is invalid. No customer found", customerId);
                return false;
            }
        } catch (Exception e) {
            logger.error("isCustomerIdValid method: error occurred");
            return false;
        }
    }
}
