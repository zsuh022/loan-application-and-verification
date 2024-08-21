package uoa.lavs.logging;

import org.junit.jupiter.api.Test;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.mainframe.RateType;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.utility.LoanFactory;
import uoa.lavs.utility.LoanType;
import uoa.lavs.utility.PaymentFrequency;

public class CacheTests {

    @Test
    public void testSingleLoan() {
        Loan loan = new LoanFactory().getLoan(LoanType.Mortgage);
        loan.setLoanId("1");
        loan.setCustomerID(LocalLogManager.TEMPORARY_CUSTOMER_ID_PREFIX);
        loan.setCustomerName("Terry");
        loan.setPrincipal(10000.0);
        loan.setRateType(RateType.Fixed);
        loan.setRate(10.0);
        loan.setStartDate(java.time.LocalDate.of(2024, 2, 11));
        loan.setPeriod(5);
        loan.setCompoundingFrequency(Frequency.Yearly);
        loan.setPaymentFrequency(PaymentFrequency.Fortnightly);
        loan.setPaymentAmount(1000.0);
        loan.setStatus(LoanStatus.Active);
        loan.setTerm(360);
        Cache.cacheLoan(loan);
        assert Cache.searchLoanCache("1").get(0).getId().equals("1");
    }

    @Test
    public void testOverwriteLoan() {
        Loan loan2 = new LoanFactory().getLoan(LoanType.Mortgage);
        loan2.setLoanId("2");
        loan2.setCustomerID(LocalLogManager.TEMPORARY_CUSTOMER_ID_PREFIX);
        loan2.setCustomerName("Terry");
        loan2.setPrincipal(10000.0);
        loan2.setRateType(RateType.Fixed);
        loan2.setRate(10.0);
        loan2.setStartDate(java.time.LocalDate.of(2024, 2, 11));
        loan2.setPeriod(5);
        loan2.setCompoundingFrequency(Frequency.Yearly);
        loan2.setPaymentFrequency(PaymentFrequency.Fortnightly);
        loan2.setPaymentAmount(1000.0);
        loan2.setStatus(LoanStatus.Active);
        loan2.setTerm(360);
        Cache.cacheLoan(loan2);
        loan2.setTerm(470);
        assert Cache.searchLoanCache("2").get(0).getTerm().equals(470);
    }

    @Test
    public void testSingleCustomerId() {
        Customer customer = new Customer();
        customer.setCustomerId("1");
        customer.setTitle("Mr");
        customer.setName("John Doe");
        customer.setDateOfBirth(java.time.LocalDate.of(2024, 2, 11));
        customer.setOccupation("Engineer");
        customer.setCitizenship("New Zealand");
        customer.setVisa(null);
        Cache.cacheCustomer(customer);
        assert Cache.searchCustomerCacheId("1").get(0).getId().equals("1");
    }

    @Test
    public void testOverwriteCustomerId() {
        Customer customer = new Customer();
        customer.setCustomerId("2");
        customer.setTitle("Mr");
        customer.setName("John Doe");
        customer.setDateOfBirth(java.time.LocalDate.of(2024, 2, 11));
        customer.setOccupation("Engineer");
        customer.setCitizenship("New Zealand");
        customer.setVisa(null);
        Cache.cacheCustomer(customer);
        customer.setName("Doe John");
        assert Cache.searchCustomerCacheId("2").get(0).getName().equals("Doe John");
    }

    @Test
    public void testSingleCustomerName() {
        Customer customer = new Customer();
        customer.setCustomerId("3");
        customer.setTitle("Mr");
        customer.setName("Terry");
        customer.setDateOfBirth(java.time.LocalDate.of(2024, 2, 11));
        customer.setOccupation("Engineer");
        customer.setCitizenship("New Zealand");
        customer.setVisa(null);
        Cache.cacheCustomer(customer);
        assert Cache.searchCustomerName("Terry").get(0).getName().equals("Terry");
    }
}
