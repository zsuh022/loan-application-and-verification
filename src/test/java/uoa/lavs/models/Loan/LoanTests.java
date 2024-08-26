package uoa.lavs.models.Loan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.mainframe.RateType;
import uoa.lavs.utility.PaymentFrequency;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoanTests {

    private Loan loan;
    private LoanDetails loanDetails;

    @BeforeEach
    void setUp() {
        loan = new Mortgage();
        loanDetails = new LoanDetails("L123", "C123", "John Doe", 100000.0, 3.5, null, 30, 20000.0, 120000.0, 1500.0, Frequency.Monthly);
    }

    @Test
    void testSetAndGetLoanId() {
        loan.setLoanId("L123");
        assertEquals("L123", loan.getId());
    }

    @Test
    void testSetAndGetCustomerID() {
        loan.setCustomerID("C123");
        assertEquals("C123", loan.getCustomerId());
    }

    @Test
    void testSetAndGetCustomerName() {
        loan.setCustomerName("John Doe");
        assertEquals("John Doe", loan.getCustomerName());
    }

    @Test
    void testAddAndGetCoborrowers() {
        Coborrower coborrower = new Coborrower();
        coborrower.setId("C123");
        loan.addCoborrower(coborrower);
        assertEquals(1, loan.getCoborrowerList().size());
        assertEquals("C123", loan.getCoborrowerList().get(0).getId());
    }

    @Test
    void testSetAndGetPaymentsList() {
        List<Payments> payments = new ArrayList<>();
        Payments payment1 = new Payments("C123", "John Doe", 1, 100.0, 200.0, 300.0, 1, LocalDate.of(2024, 1, 1));
        Payments payment2 = new Payments("C124", "Jane Doe", 2, 150.0, 250.0, 350.0, 2, LocalDate.of(2024, 2, 1));
        payments.add(payment1);
        payments.add(payment2);
        loan.setPaymentsList(payments);

        List<Payments> retrievedPayments = loan.getPaymentsList();
        assertEquals(2, retrievedPayments.size());
        assertEquals(payment1, retrievedPayments.get(0));
        assertEquals(payment2, retrievedPayments.get(1));
    }

    @Test
    void testAddPaymentsList() {
        Payments payment = new Payments("C123", "John Doe", 1, 100.0, 200.0, 300.0, 1, LocalDate.of(2024, 1, 1));
        loan.addPaymentsList(payment);

        List<Payments> retrievedPayments = loan.getPaymentsList();
        assertEquals(1, retrievedPayments.size());
        assertEquals(payment, retrievedPayments.get(0));
    }

    @Test
    void testSetAndGetPrincipal() {
        loan.setPrincipal(50000.0);
        assertEquals(50000.0, loan.getPrincipal());
    }

    @Test
    void testSetAndGetRateType() {
        loan.setRateType(RateType.Fixed);
        assertEquals(RateType.Fixed, loan.getRateType());
    }

    @Test
    void testSetAndGetRate() {
        loan.setRate(4.5);
        assertEquals(4.5, loan.getRate());
    }

    @Test
    void testSetAndGetStartDate() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        loan.setStartDate(date);
        assertEquals(date, loan.getStartDate());
    }

    @Test
    void testSetAndGetPeriod() {
        loan.setPeriod(30);
        assertEquals(30, loan.getPeriod());
    }

    @Test
    void testSetAndGetCompoundingFrequency() {
        loan.setCompoundingFrequency(Frequency.Monthly);
        assertEquals(Frequency.Monthly, loan.getCompoundingFrequency());
    }

    @Test
    void testSetAndGetPaymentFrequency() {
        loan.setPaymentFrequency(PaymentFrequency.Monthly);
        assertEquals(PaymentFrequency.Monthly, loan.getPaymentFrequency());
    }

    @Test
    void testSetAndGetPaymentAmount() {
        loan.setPaymentAmount(2000.0);
        assertEquals(2000.0, loan.getPaymentAmount());
    }

    @Test
    void testSetAndGetStatus() {
        loan.setStatus(LoanStatus.Active);
        assertEquals(LoanStatus.Active, loan.getStatus());
    }

    @Test
    void testSetAndGetSummary() {
        loan.setSummary(loanDetails);
        assertEquals(loanDetails, loan.getSummary());
    }

    @Test
    void testSetAndGetTerm() {
        loan.setTerm(15);
        assertEquals(15, loan.getTerm());
    }

    @Test
    void testSetAndGetInterestOnly() {
        loan.setInterestOnly(true);
        assertTrue(loan.getInterestOnly());
    }

    @Test
    void testGetStatusString_New() {
        loan.setStatus(LoanStatus.New);
        assertEquals("New", loan.getStatusString());
    }

    @Test
    void testGetStatusString_Active() {
        loan.setStatus(LoanStatus.Active);
        assertEquals("Active", loan.getStatusString());
    }

    @Test
    void testGetStatusString_Cancelled() {
        loan.setStatus(LoanStatus.Cancelled);
        assertEquals("Cancelled", loan.getStatusString());
    }

    @Test
    void testGetStatusString_Pending() {
        loan.setStatus(LoanStatus.Pending);
        assertEquals("Pending", loan.getStatusString());
    }

    @Test
    void testGetCompoundingString_Weekly() {
        loan.setCompoundingFrequency(Frequency.Weekly);
        assertEquals("Weekly", loan.getCompoundingString());
    }

    @Test
    void testGetCompoundingString_Fortnightly() {
        loan.setCompoundingFrequency(Frequency.Fortnightly);
        assertEquals("Fortnightly", loan.getCompoundingString());
    }

    @Test
    void testGetCompoundingString_Yearly() {
        loan.setCompoundingFrequency(Frequency.Yearly);
        assertEquals("Annually", loan.getCompoundingString());
    }

    @Test
    void testGetPaymentFrequencyString_Weekly() {
        loan.setPaymentFrequency(PaymentFrequency.Weekly);
        assertEquals("Weekly", loan.getPaymentFrequencyString());
    }

    @Test
    void testGetPaymentFrequencyString_Fortnightly() {
        loan.setPaymentFrequency(PaymentFrequency.Fortnightly);
        assertEquals("Fortnightly", loan.getPaymentFrequencyString());
    }

    @Test
    void testGetPaymentFrequencyString_Monthly() {
        loan.setPaymentFrequency(PaymentFrequency.Monthly);
        assertEquals("Monthly", loan.getPaymentFrequencyString());
    }

    @Test
    void testEquals_SameInstance() {
        assertTrue(loan.equals(loan));
    }

    @Test
    void testEquals_NullObject() {
        Loan anotherLoan = null;
        assertFalse(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_DifferentClass() {
        String notALoan = "Not a loan";
        assertFalse(loan.equals(notALoan));
    }

    @Test
    void testEquals_SameValues() {
        Loan anotherLoan = new Mortgage();

        loan.setLoanId("L123");
        anotherLoan.setLoanId("L123");
        loan.setCustomerID("C123");
        anotherLoan.setCustomerID("C123");
        loan.setCustomerName("John Doe");
        anotherLoan.setCustomerName("John Doe");
        loan.setPrincipal(50000.0);
        anotherLoan.setPrincipal(50000.0);
        loan.setRateType(RateType.Fixed);
        anotherLoan.setRateType(RateType.Fixed);
        loan.setRate(4.5);
        anotherLoan.setRate(4.5);
        LocalDate date = LocalDate.of(2024, 1, 1);
        loan.setStartDate(date);
        anotherLoan.setStartDate(date);
        loan.setPeriod(30);
        anotherLoan.setPeriod(30);
        loan.setCompoundingFrequency(Frequency.Monthly);
        anotherLoan.setCompoundingFrequency(Frequency.Monthly);
        loan.setPaymentFrequency(PaymentFrequency.Monthly);
        anotherLoan.setPaymentFrequency(PaymentFrequency.Monthly);
        loan.setPaymentAmount(2000.0);
        anotherLoan.setPaymentAmount(2000.0);
        loan.setStatus(LoanStatus.Active);
        anotherLoan.setStatus(LoanStatus.Active);
        loan.setTerm(15);
        anotherLoan.setTerm(15);
        loan.setInterestOnly(true);
        anotherLoan.setInterestOnly(true);

        assertTrue(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_DifferentLoanId() {
        Loan anotherLoan = new Mortgage();
        loan.setLoanId("L123");
        anotherLoan.setLoanId("L456");
        assertFalse(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_SameLoanId() {
        Loan anotherLoan = new Mortgage();
        loan.setLoanId("L123");
        anotherLoan.setLoanId("L123");
        assertTrue(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_DifferentCustomerID() {
        Loan anotherLoan = new Mortgage();
        loan.setCustomerID("C123");
        anotherLoan.setCustomerID("C456");
        assertFalse(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_SameCustomerID() {
        Loan anotherLoan = new Mortgage();
        loan.setCustomerID("C123");
        anotherLoan.setCustomerID("C123");
        assertTrue(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_DifferentCustomerName() {
        Loan anotherLoan = new Mortgage();
        loan.setCustomerName("John Doe");
        anotherLoan.setCustomerName("Jane Doe");
        assertFalse(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_SameCustomerName() {
        Loan anotherLoan = new Mortgage();
        loan.setCustomerName("John Doe");
        anotherLoan.setCustomerName("John Doe");
        assertTrue(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_DifferentPrincipal() {
        Loan anotherLoan = new Mortgage();
        loan.setPrincipal(100000.0);
        anotherLoan.setPrincipal(200000.0);
        assertFalse(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_SamePrincipal() {
        Loan anotherLoan = new Mortgage();
        loan.setPrincipal(100000.0);
        anotherLoan.setPrincipal(100000.0);
        assertTrue(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_DifferentRateType() {
        Loan anotherLoan = new Mortgage();
        loan.setRateType(RateType.Fixed);
        anotherLoan.setRateType(RateType.Floating);
        assertFalse(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_SameRateType() {
        Loan anotherLoan = new Mortgage();
        loan.setRateType(RateType.Fixed);
        anotherLoan.setRateType(RateType.Fixed);
        assertTrue(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_DifferentRate() {
        Loan anotherLoan = new Mortgage();
        loan.setRate(5.0);
        anotherLoan.setRate(6.0);
        assertFalse(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_SameRate() {
        Loan anotherLoan = new Mortgage();
        loan.setRate(5.0);
        anotherLoan.setRate(5.0);
        assertTrue(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_DifferentStartDate() {
        Loan anotherLoan = new Mortgage();
        loan.setStartDate(LocalDate.of(2024, 1, 1));
        anotherLoan.setStartDate(LocalDate.of(2025, 1, 1));
        assertFalse(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_SameStartDate() {
        Loan anotherLoan = new Mortgage();
        loan.setStartDate(LocalDate.of(2024, 1, 1));
        anotherLoan.setStartDate(LocalDate.of(2024, 1, 1));
        assertTrue(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_DifferentPeriod() {
        Loan anotherLoan = new Mortgage();
        loan.setPeriod(30);
        anotherLoan.setPeriod(15);
        assertFalse(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_SamePeriod() {
        Loan anotherLoan = new Mortgage();
        loan.setPeriod(30);
        anotherLoan.setPeriod(30);
        assertTrue(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_DifferentCompoundingFrequency() {
        Loan anotherLoan = new Mortgage();
        loan.setCompoundingFrequency(Frequency.Weekly);
        anotherLoan.setCompoundingFrequency(Frequency.Yearly);
        assertFalse(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_SameCompoundingFrequency() {
        Loan anotherLoan = new Mortgage();
        loan.setCompoundingFrequency(Frequency.Weekly);
        anotherLoan.setCompoundingFrequency(Frequency.Weekly);
        assertTrue(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_DifferentPaymentFrequency() {
        Loan anotherLoan = new Mortgage();
        loan.setPaymentFrequency(PaymentFrequency.Weekly);
        anotherLoan.setPaymentFrequency(PaymentFrequency.Monthly);
        assertFalse(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_SamePaymentFrequency() {
        Loan anotherLoan = new Mortgage();
        loan.setPaymentFrequency(PaymentFrequency.Weekly);
        anotherLoan.setPaymentFrequency(PaymentFrequency.Weekly);
        assertTrue(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_DifferentPaymentAmount() {
        Loan anotherLoan = new Mortgage();
        loan.setPaymentAmount(1000.0);
        anotherLoan.setPaymentAmount(2000.0);
        assertFalse(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_SamePaymentAmount() {
        Loan anotherLoan = new Mortgage();
        loan.setPaymentAmount(1000.0);
        anotherLoan.setPaymentAmount(1000.0);
        assertTrue(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_DifferentStatus() {
        Loan anotherLoan = new Mortgage();
        loan.setStatus(LoanStatus.New);
        anotherLoan.setStatus(LoanStatus.Active);
        assertFalse(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_SameStatus() {
        Loan anotherLoan = new Mortgage();
        loan.setStatus(LoanStatus.New);
        anotherLoan.setStatus(LoanStatus.New);
        assertTrue(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_DifferentTerm() {
        Loan anotherLoan = new Mortgage();
        loan.setTerm(30);
        anotherLoan.setTerm(15);
        assertFalse(loan.equals(anotherLoan));
    }

    @Test
    void testEquals_SameTerm() {
        Loan anotherLoan = new Mortgage();
        loan.setTerm(30);
        anotherLoan.setTerm(30);
        assertTrue(loan.equals(anotherLoan));
    }
}
