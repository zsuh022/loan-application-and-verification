package uoa.lavs.utility;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.controllers.AlertManager;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.RateType;
import uoa.lavs.models.Loan.Loan;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static uoa.lavs.logging.LocalLogManager.TEMPORARY_LOAN_ID_PREFIX;

public class LoanValidatorTests {

    private LoanValidator loanValidator;
    private Map<String, String> validLoanMap;

    @BeforeAll
    static void setUpAll() {
        AlertManager.setTesting(true);
    }

    @BeforeEach
    void setUp() {
        loanValidator = new LoanValidator();
        validLoanMap = new HashMap<>();
        validLoanMap.put("customerId", "123");
        validLoanMap.put("principal", "500000");
        validLoanMap.put("rate", "4.5");
        validLoanMap.put("isFloating", "true");
        validLoanMap.put("isFixed", "false");
        validLoanMap.put("isInterestOnly", "false");
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
    }

    @Test
    void testGenerateTemporaryLoanId() {
        String tempLoanId = LoanValidator.generateTemporaryLoanId();
        assertNotNull(tempLoanId);
        assertTrue(tempLoanId.startsWith(TEMPORARY_LOAN_ID_PREFIX));
    }

    @Test
    void testCreateLoan() {
        Loan loan = loanValidator.createLoan(validLoanMap);

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
    }
}
