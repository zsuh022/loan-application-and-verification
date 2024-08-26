package uoa.lavs.comms.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uoa.lavs.comms.AbstractCustomerTest;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Customer.CustomerEmail;
import uoa.lavs.models.Customer.CustomerSummary;
import uoa.lavs.logging.Cache;
import uoa.lavs.models.Loan.LoanSummary;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InitialSearchCacheTest extends AbstractCustomerTest<CustomerSummary> {

    String id = null;
    String id1 =null;

    private final CustomerEmail email1 = new CustomerEmail();
    private final CustomerEmail email2 = new CustomerEmail();


    @Override
    @BeforeEach
    public void setup() throws IOException {
        super.setup();

        email1.setAddress("john.doe@example.com");
        email1.setIsPrimary(false);

        email2.setAddress("john.doe@work.com");
        email2.setIsPrimary(true);

        customer.addEmail(email1);
        customer.addEmail(email2);


        id = addCustomer.add(conn, customer);
        id1 = addCustomer.add(conn, customer1);
        customer.setCustomerId(id);

        Cache.cacheCustomer(customer);



    }

    @Override
    protected void assertDetails(CustomerSummary expected, CustomerSummary actual) {

    }

    @Test
    public void testSearchCustomerCacheId() {


        InitialSearch search = new InitialSearch(0);

        CustomerSummary summary = search.obfuscateCustomer(customer);
        CustomerSummary actualCus = search.findAll(conn, id).get(0);

       assertEquals(summary, actualCus);


    }


    @Test
    public void testSearchCustomerCacheName() {

        InitialSearch search = new InitialSearch(1);

        CustomerSummary summary = search.obfuscateCustomer(customer);
        CustomerSummary actualCus = search.findAll(conn, customer.getName()).get(0);

        assertEquals(summary, actualCus);

    }

    @Test
    protected void testFindAllFailure() {
        InitialSearch search = new InitialSearch(1);
        List<CustomerSummary> summaries = search.findAll(mockConnection, String.valueOf(1));
        assertTrue(summaries.isEmpty());
    }

}
