package uoa.lavs.utility;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.comms.Customer.SearchCustomer;
import uoa.lavs.controllers.LoanBucket;
import uoa.lavs.logging.Cache;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.mainframe.RateType;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Loan.Coborrower;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.models.Loan.Mortgage;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static uoa.lavs.logging.LocalLogManager.TEMPORARY_LOAN_ID_PREFIX;

public class LoanValidatorTests {

    private Loan loan;
    private LoanValidator validator;
    private Map<String, String> validLoanMap;

    @BeforeAll
    static void setUpAll() {
        LoanValidator.setTesting(true);
    }

    @BeforeEach
    void setUp() {
        validator = new LoanValidator();

        loan = new Mortgage();
        LoanBucket.getInstance().setLoan(loan);

        validLoanMap = new HashMap<>();
        validLoanMap.put("customerId", "123");
        validLoanMap.put("principal", "500000");
        validLoanMap.put("rate", "4.5");
        validLoanMap.put("isFloating", "true");
        validLoanMap.put("isFixed", "false");
        validLoanMap.put("startDate", "2024-01-01");
        validLoanMap.put("term", "30");
        validLoanMap.put("period", "15");
        validLoanMap.put("compoundingWeekly", "true");
        validLoanMap.put("compoundingMonthly", "false");
        validLoanMap.put("compoundingAnnually", "false");
        validLoanMap.put("frequencyWeekly", "true");
        validLoanMap.put("frequencyFortnightly", "false");
        validLoanMap.put("frequencyMonthly", "false");
        validLoanMap.put("amount", "2000");
        validLoanMap.put("isInterestOnly", "false");
        validLoanMap.put("status", "3");

        // coborrowers
        validLoanMap.put("coborrowerId0", "10000");
    }

    @Test
    void testGenerateTemporaryLoanId() {
        String tempLoanId = LoanValidator.generateTemporaryLoanId();
        assertNotNull(tempLoanId);
        assertTrue(tempLoanId.startsWith(TEMPORARY_LOAN_ID_PREFIX));
    }

    @Test
    void testCreateLoan() {
        Loan loan = validator.createLoan(validLoanMap);

        assertNotNull(loan);
        assertEquals("123", loan.getCustomerId());
        assertEquals(500000, loan.getPrincipal());
        assertEquals(4.5, loan.getRate());
        assertEquals(LocalDate.of(2024, 1, 1), loan.getStartDate());
        assertEquals(30, loan.getTerm());
        assertEquals(15, loan.getPeriod());
        assertEquals(2000, loan.getPaymentAmount());
        assertEquals(RateType.Floating, loan.getRateType());
        assertEquals(Frequency.Weekly, loan.getCompoundingFrequency());
        assertEquals(PaymentFrequency.Weekly, loan.getPaymentFrequency());
        assertFalse(loan.getInterestOnly());

        // coborrowers
        assertEquals(1, loan.getCoborrowerList().size());
        assertEquals("10000", loan.getCoborrowerList().get(0).getId());
    }

    @Test
    void testValidateLoan_discoverRateType_isFixed() {
        validLoanMap.put("isFloating", "false");
        validLoanMap.put("isFixed", "true");
        Loan loan = validator.createLoan(validLoanMap);
        assertNotNull(loan);
        assertEquals(RateType.Fixed, loan.getRateType());
    }

    @Test
    void testValidateLoan_discoverRateType_null() {
        validLoanMap.put("isFloating", "false");
        validLoanMap.put("isFixed", "false");
        Loan loan = validator.createLoan(validLoanMap);
        assertNotNull(loan);
        assertNull(loan.getRateType());
    }

    @Test
    void testValidateLoan_discoverCompoundingFrequency_compoundingMonthly() {
        validLoanMap.put("compoundingWeekly", "false");
        validLoanMap.put("compoundingMonthly", "true");
        validLoanMap.put("compoundingAnnually", "false");
        Loan loan = validator.createLoan(validLoanMap);
        assertNotNull(loan);
        assertEquals(Frequency.Monthly, loan.getCompoundingFrequency());
    }

    @Test
    void testValidateLoan_discoverCompoundingFrequency_compoundingAnnually() {
        validLoanMap.put("compoundingWeekly", "false");
        validLoanMap.put("compoundingMonthly", "false");
        validLoanMap.put("compoundingAnnually", "true");
        Loan loan = validator.createLoan(validLoanMap);
        assertNotNull(loan);
        assertEquals(Frequency.Yearly, loan.getCompoundingFrequency());
    }

    @Test
    void testValidateLoan_discoverCompoundingFrequency_null() {
        validLoanMap.put("compoundingWeekly", "false");
        validLoanMap.put("compoundingMonthly", "false");
        validLoanMap.put("compoundingAnnually", "false");
        Loan loan = validator.createLoan(validLoanMap);
        assertNotNull(loan);
        assertNull(loan.getCompoundingFrequency());
    }

    @Test
    void testValidateLoan_discoverPaymentFrequency_frequencyFortnightly() {
        validLoanMap.put("frequencyWeekly", "false");
        validLoanMap.put("frequencyFortnightly", "true");
        validLoanMap.put("frequencyMonthly", "false");
        Loan loan = validator.createLoan(validLoanMap);
        assertNotNull(loan);
        assertEquals(PaymentFrequency.Fortnightly, loan.getPaymentFrequency());
    }

    @Test
    void testValidateLoan_discoverPaymentFrequency_frequencyMonthly() {
        validLoanMap.put("frequencyWeekly", "false");
        validLoanMap.put("frequencyFortnightly", "false");
        validLoanMap.put("frequencyMonthly", "true");
        Loan loan = validator.createLoan(validLoanMap);
        assertNotNull(loan);
        assertEquals(PaymentFrequency.Monthly, loan.getPaymentFrequency());
    }

    @Test
    void testValidateLoan_discoverPaymentFrequency_null() {
        validLoanMap.put("frequencyWeekly", "false");
        validLoanMap.put("frequencyFortnightly", "false");
        validLoanMap.put("frequencyMonthly", "false");
        Loan loan = validator.createLoan(validLoanMap);
        assertNotNull(loan);
        assertNull(loan.getPaymentFrequency());
    }

    @Test
    void testValidateLoan_valid() {
        assertTrue(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_nullCustomerId() {
        validLoanMap.put("customerId", null);
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_emptyCustomerId() {
        validLoanMap.put("customerId", "");
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_nullPrincipal() {
        validLoanMap.put("principal", null);
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_emptyPrincipal() {
        validLoanMap.put("principal", "");
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_nullRate() {
        validLoanMap.put("rate", null);
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_emptyRate() {
        validLoanMap.put("rate", "");
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_invalidRateType_noneSelected() {
        validLoanMap.put("isFloating", "false");
        validLoanMap.put("isFixed", "false");
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_invalidRateType_bothSelected() {
        validLoanMap.put("isFloating", "true");
        validLoanMap.put("isFixed", "true");
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_nullStartDate() {
        validLoanMap.put("startDate", null);
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_emptyStartDate() {
        validLoanMap.put("startDate", "");
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_nullTerm() {
        validLoanMap.put("term", null);
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_emptyTerm() {
        validLoanMap.put("term", "");
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_nullPeriod() {
        validLoanMap.put("period", null);
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_emptyPeriod() {
        validLoanMap.put("period", "");
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_termLessThanPeriod() {
        validLoanMap.put("term", "15");
        validLoanMap.put("period", "30");
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_invalidCompoundingFrequency_noneSelected() {
        validLoanMap.put("compoundingWeekly", "false");
        validLoanMap.put("compoundingMonthly", "false");
        validLoanMap.put("compoundingAnnually", "false");
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_invalidCompoundingFrequency_multipleSelected() {
        validLoanMap.put("compoundingWeekly", "true");
        validLoanMap.put("compoundingMonthly", "true");
        validLoanMap.put("compoundingAnnually", "false");
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_invalidCompoundingFrequency_allSelected() {
        validLoanMap.put("compoundingWeekly", "true");
        validLoanMap.put("compoundingMonthly", "true");
        validLoanMap.put("compoundingAnnually", "true");
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_invalidPaymentFrequency_noneSelected() {
        validLoanMap.put("frequencyWeekly", "false");
        validLoanMap.put("frequencyFortnightly", "false");
        validLoanMap.put("frequencyMonthly", "false");
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_invalidPaymentFrequency_multipleSelected() {
        validLoanMap.put("frequencyWeekly", "true");
        validLoanMap.put("frequencyFortnightly", "true");
        validLoanMap.put("frequencyMonthly", "false");
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_invalidPaymentFrequency_allSelected() {
        validLoanMap.put("frequencyWeekly", "true");
        validLoanMap.put("frequencyFortnightly", "true");
        validLoanMap.put("frequencyMonthly", "true");
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_nullPaymentAmount() {
        validLoanMap.put("amount", null);
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_emptyPaymentAmount() {
        validLoanMap.put("amount", "");
        assertFalse(validator.validateLoan(validLoanMap));
    }

    @Test
    void testValidateLoan_updateLoan() {
        validLoanMap.put("coborrowerId1", "10001");
        validator.updateLoan(validLoanMap);

        assertEquals("123", loan.getCustomerId());
        assertEquals(500000, loan.getPrincipal());
        assertEquals(4.5, loan.getRate());
        assertEquals(RateType.Floating, loan.getRateType());
        assertEquals(LocalDate.of(2024, 1, 1), loan.getStartDate());
        assertEquals(30, loan.getTerm());
        assertEquals(15, loan.getPeriod());
        assertEquals(Frequency.Weekly, loan.getCompoundingFrequency());
        assertEquals(PaymentFrequency.Weekly, loan.getPaymentFrequency());
        assertEquals(2000, loan.getPaymentAmount());
        assertFalse(loan.getInterestOnly());
        assertEquals(LoanStatus.Active.name(), loan.getStatus().name());

        assertEquals(2, loan.getCoborrowerList().size());
        assertEquals("10000", loan.getCoborrowerList().get(0).getId());
        assertEquals("10001", loan.getCoborrowerList().get(1).getId());
    }

    @Test
    void testValidateLoan_updateLoan_invalidStatus() {
        validLoanMap.put("status", "5");

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            validator.updateLoan(validLoanMap);
        });
    }
}
