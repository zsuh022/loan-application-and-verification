package uoa.lavs.logging;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Loan;

public class CacheTests {

    @BeforeEach
    void setup() {
        Cache.clearCache();
        Customer customer1 = new Customer();
        customer1.setCustomerId("1");
        customer1.setName("John Doe");
        Customer customer2 = new Customer();
        customer2.setCustomerId("2");
        customer2.setName("Kohn Doe");
        Customer customer3 = new Customer();
        customer3.setCustomerId("2");
        customer3.setName("Lohn Doe");
        Cache.cacheCustomer(customer1);
        Cache.cacheCustomer(customer2);
        Cache.cacheCustomer(customer3);
        Loan loan1 = new Loan("1");
        Loan loan2 = new Loan("2");
        Loan loan3 = new Loan("2");
        Cache.cacheLoan(loan1);
        Cache.cacheLoan(loan2);
        Cache.cacheLoan(loan3);
    }

    @Test
    public void testSingleLoan() {
        assert Cache.searchLoanCache("1").get(0).getId().equals("1");
    }

    @Test
    public void testMultipleLoan() {
        System.out.println(Cache.searchLoanCache("2").size());
        assert Cache.searchLoanCache("2").size() == 2;
    }

    @Test
    public void testSingleCustomerId() {
        assert Cache.searchCustomerCacheId("1").get(0).getId().equals("1");
    }

    @Test
    public void testMultipleCustomerId() {
        assert Cache.searchCustomerCacheId("2").size() == 2;
    }

    @Test
    public void testSingleCustomerName() {
        assert (Cache.searchCustomerName("John Doe").get(0).getName()).equals("John Doe");
    }
}
