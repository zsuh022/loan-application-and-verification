package uoa.lavs.comms.Loan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import uoa.lavs.comms.AbstractCustomerTest;
import uoa.lavs.comms.AbstractLoanTest;
import uoa.lavs.logging.Cache;
import uoa.lavs.mainframe.*;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.models.Loan.Mortgage;
import uoa.lavs.utility.PaymentFrequency;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static uoa.lavs.logging.LocalLogManager.TEMPORARY_LOAN_ID_PREFIX;


public class AddLoanTest extends AbstractLoanTest<Loan> {

    String id = null;

    UpdateStatus statusUpdate = new UpdateStatus();

    Loan loan1 = new Mortgage();
    Loan loan4 = new Mortgage();

    @Override
    @BeforeEach
    protected void setup() throws IOException {
        super.setup();

        loan1.setLoanId(TEMPORARY_LOAN_ID_PREFIX + "1");
        loan1.setCustomerID(customerId);
        loan1.setCustomerName(customer.getName());
        loan1.setPrincipal(10000.0);
        loan1.setRateType(RateType.Fixed);
        loan1.setRate(10.0);
        loan1.setStartDate(java.time.LocalDate.of(2024, 2, 11));
        loan1.setPeriod(5);
        loan1.setCompoundingFrequency(Frequency.Yearly);
        loan1.setPaymentFrequency(PaymentFrequency.Weekly);
        loan1.setPaymentAmount(1000.0);
        loan1.setStatus(LoanStatus.Active);
        loan1.setTerm(360);

        loan4.setLoanId(TEMPORARY_LOAN_ID_PREFIX + "4");
        loan4.setCustomerID(customerId);
        loan4.setCustomerName(customer.getName());
        loan4.setPrincipal(10000.0);
        loan4.setRateType(RateType.InterestOnly);
        loan4.setRate(10.0);
        loan4.setStartDate(java.time.LocalDate.of(2024, 2, 11));
        loan4.setPeriod(5);
        loan4.setCompoundingFrequency(Frequency.Yearly);
        loan4.setPaymentFrequency(PaymentFrequency.Weekly);
        loan4.setPaymentAmount(1000.0);
        loan4.setStatus(LoanStatus.Cancelled);
        loan4.setTerm(360);
    }

    @Override
    protected void assertDetails(Loan expected, Loan actual) {
        if (!expected.equals(actual)) {
            fail();
        }
    }

    @Test
    protected void testLoanSuccess1() {
        id = addLoan.add(conn, loan);
        System.out.println(id);
        loan.setLoanId(id);
        statusUpdate.add(conn, LoanStatus.New, id);
        Loan newLoan = searchLoan.findById(conn, id);
        assertDetails(loan, newLoan);
    }

    @Test
    protected void testLoanSuccess2() {
        id = addLoan.add(conn, loan1);
        loan1.setLoanId(id);
        statusUpdate.add(conn, LoanStatus.Active, id);
        Loan newLoan = searchLoan.findById(conn, id);
        assertDetails(loan1, newLoan);
    }

    @Test
    protected void testLoanSuccess3() {
        id = addLoan.add(conn, loan4);
        loan4.setLoanId(id);
        statusUpdate.add(conn, LoanStatus.Cancelled, id);
        Loan newLoan = searchLoan.findById(conn, id);
        assertDetails(loan4, newLoan);
    }

    @Test
    protected void testLoanUpdate() {
        id = addLoan.add(conn, loan1);
        Loan loan2 = new Mortgage();
        loan2.setLoanId(id);
        loan2.setCustomerID(customerId);
        loan2.setCustomerName(customer.getName());
        loan2.setPrincipal(10000.0);
        loan2.setRateType(RateType.Fixed);
        loan2.setRate(10.0);
        loan2.setStartDate(java.time.LocalDate.of(2024, 2, 11));
        loan2.setPeriod(5);
        loan2.setCompoundingFrequency(Frequency.Yearly);
        loan2.setPaymentFrequency(PaymentFrequency.Monthly);
        loan2.setPaymentAmount(1000.0);
        loan2.setStatus(LoanStatus.Pending);
        loan2.setTerm(360);
        id = addLoan.add(conn, loan2);
        statusUpdate.add(conn, LoanStatus.Pending, id);
        Loan newLoan = searchLoan.findById(conn, id);
        assertDetails(loan2, newLoan);
    }


    @Test
    protected void testLoanFailure() {
        id = addLoan.add(mockConnection, loan);
        loan.setLoanId(id);
        Loan newLoan = searchLoan.findById(mockConnection, id);
        assertNull(newLoan.getId());
    }

    @Test
    protected void testLoanUpdateFail() {
        id = addLoan.add(conn, loan);
        System.out.println(id);
        loan.setLoanId(id);
        statusUpdate.add(conn, LoanStatus.New, id);
        statusUpdate.add(mockConnection, LoanStatus.New, id);
        Loan newLoan = searchLoan.findById(conn, id);
        assertDetails(loan, newLoan);
    }

    @Test
    protected void testCacheLoan(){
        SearchLoan searchLoan = new SearchLoan();
        Connection mockConnection = mock(Connection.class);

        Loan loan = new Mortgage();
        loan.setLoanId("loan123");
        loan.setCustomerID("customer123");
        loan.setPrincipal(10000.0);
        loan.setRateType(RateType.InterestOnly);
        loan.setRate(5.0);
        loan.setStartDate(java.time.LocalDate.of(2024, 1, 1));
        loan.setPeriod(5);
        loan.setCompoundingFrequency(Frequency.Yearly);
        loan.setPaymentFrequency(PaymentFrequency.Monthly);
        loan.setPaymentAmount(500.0);
        loan.setStatus(LoanStatus.Active);
        loan.setTerm(360);
        Cache.cacheLoan(loan);
        Loan cachedLoan = searchLoan.findById(mockConnection, "loan123");

        assertEquals(loan, cachedLoan, "The loan returned should be the one cached.");

    }

    @Test
    protected void testUnsupportedMethod() {
        Executable executable = () -> {
            List<Loan> result = searchLoan.findAll(conn, "1");
        };

        assertThrows(UnsupportedOperationException.class, executable, "findAll should throw UnsupportedOperationException");
    }
}
