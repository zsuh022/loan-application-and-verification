package uoa.lavs.logging;

import org.junit.Test;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Loan;

public class CacheTests {
    @Test
    public void testSingleLoan() {
        // test cache
        Loan loan = new Loan("1");
        Cache.cacheLoan(loan);
        Loan loan2 = new Loan("2");
        Cache.cacheLoan(loan2);
        Loan loan3 = new Loan("2");
        Cache.cacheLoan(loan3);
        assert Cache.searchLoanCache("1").get(0).getId().equals("1");
    }

    @Test
    public void testMultipleLoan() {
        // test cache
        Loan loan = new Loan("1");
        Cache.cacheLoan(loan);
        Loan loan2 = new Loan("2");
        Cache.cacheLoan(loan2);
        Loan loan3 = new Loan("2");
        Cache.cacheLoan(loan3);
        assert Cache.searchLoanCache("2").size() == 2;
    }

    @Test
    public void testSingleCustomer() {
        // test cache
        Customer customer = new Customer("1");
        Cache.cacheCustomer(customer);
        Customer customer2 = new Customer("2");
        Cache.cacheCustomer(customer2);
        Customer customer3 = new Customer("2");
        Cache.cacheCustomer(customer3);
        assert Cache.searchCustomerCache("1").get(0).getId().equals("1");
    }

    @Test
    public void testMultipleCustomer() {
        // test cache
        Customer customer = new Customer("1");
        Cache.cacheCustomer(customer);
        Customer customer2 = new Customer("2");
        Cache.cacheCustomer(customer2);
        Customer customer3 = new Customer("2");
        Cache.cacheCustomer(customer3);
        assert Cache.searchCustomerCache("2").size() == 2;
    }
}
