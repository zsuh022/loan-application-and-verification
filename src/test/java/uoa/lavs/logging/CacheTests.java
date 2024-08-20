package uoa.lavs.logging;

import org.junit.Test;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Loan.Loan;

public class CacheTests {
//    @Test
//    public void testSingleLoan() {
//        // test cache
//        Loan loan = new Loan("1");
//        Cache.cacheLoan(loan);
//        Loan loan2 = new Loan("2");
//        Cache.cacheLoan(loan2);
//        Loan loan3 = new Loan("2");
//        Cache.cacheLoan(loan3);
//        assert Cache.searchLoanCache("1").get(0).getId().equals("1");
//    }
//
//    @Test
//    public void testMultipleLoan() {
//        // test cache
//        Loan loan = new Loan("1");
//        Cache.cacheLoan(loan);
//        Loan loan2 = new Loan("2");
//        Cache.cacheLoan(loan2);
//        Loan loan3 = new Loan("2");
//        Cache.cacheLoan(loan3);
//        assert Cache.searchLoanCache("2").size() == 2;
//    }

    @Test
    public void testSingleCustomerId() {
        // test cache
        Customer customer = new Customer();
        customer.setCustomerId("1");
        Cache.cacheCustomer(customer);
        Customer customer2 = new Customer();
        customer2.setCustomerId("2");
        Cache.cacheCustomer(customer2);
        Customer customer3 = new Customer();
        customer3.setCustomerId("2");
        Cache.cacheCustomer(customer3);
        assert Cache.searchCustomerCacheId("1").get(0).getId().equals("1");
    }

    @Test
    public void testMultipleCustomerId() {
        // test cache
        Customer customer = new Customer();
        customer.setCustomerId("1");
        Cache.cacheCustomer(customer);
        Customer customer2 = new Customer();
        customer2.setCustomerId("2");
        Cache.cacheCustomer(customer2);
        Customer customer3 = new Customer();
        customer3.setCustomerId("2");
        Cache.cacheCustomer(customer3);
        assert Cache.searchCustomerCacheId("2").size() == 2;
    }
}
