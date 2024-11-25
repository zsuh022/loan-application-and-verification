package uoa.lavs.comms.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.comms.AbstractCustomerTest;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.MockConnection;
import uoa.lavs.mainframe.Response;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.simulator.IntermittentConnection;
import uoa.lavs.mainframe.simulator.NitriteConnection;
import uoa.lavs.mainframe.simulator.failures.NFailsPerMRequestsPolicy;
import uoa.lavs.models.Customer.CustomerAddress;
import uoa.lavs.models.Customer.CustomerEmail;
import uoa.lavs.models.Customer.CustomerPhone;
import uoa.lavs.models.Customer.CustomerSummary;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import static java.nio.file.Files.deleteIfExists;
import static org.junit.jupiter.api.Assertions.*;

public class CombinedInitialSearchTest extends AbstractCustomerTest<CustomerSummary> {

    private final AddEmail addEmail = new AddEmail();
    private final CustomerEmail email1 = new CustomerEmail();
    private final CustomerEmail email2 = new CustomerEmail();
    private InitialSearch initialSearch;

    @Override
    @BeforeEach
    protected void setup() throws IOException {
        super.setup();

        email1.setAddress("john.doe@example.com");
        email1.setIsPrimary(true);
        customer.addEmail(email1);

        email2.setAddress("123@example.com");
        email2.setIsPrimary(false);
        customer.addEmail(email2);


        initialSearch = new InitialSearch(0);
    }

    @Test
    void testCombinedAddAndSearchSuccess() throws IOException {

        String customerId = addCustomer.add(conn, customer);
        addEmail.add(conn, email1, customerId);
        addEmail.add(conn, email2, customerId);

        List<CustomerSummary> summariesFromDb = initialSearch.findAll(conn, customerId);
        for(CustomerSummary s: summariesFromDb){
            System.out.println(s.getName());
        }
        assertEquals(1, summariesFromDb.size());

        CustomerSummary retrievedSummary = summariesFromDb.get(0);

        assertEquals(email1.getAddress(), retrievedSummary.getEmail());
    }

    @Test
    void testCombinedAddAndSearchFailure() throws IOException {
        String customerId = addCustomer.add(mockConnection, customer);

        addEmail.add(mockConnection, email1, customerId);

        List<CustomerSummary> summaries = initialSearch.findAll(mockConnection, customerId);
        assertTrue(summaries.isEmpty());
    }

    @Test
    void testCombinedAddAndSearchFailureWrongEmail() throws IOException {
        String customerId = addCustomer.add(conn, customer);

        addEmail.add(conn, email2, customerId);

        List<CustomerSummary> summaries = initialSearch.findAll(mockConnection, customerId);
        assertTrue(summaries.isEmpty());
    }

    @Test
    void testCombinedAddAndSearchFailureAfterCustomer() throws IOException {
        conn.close();

        Connection unrealiableConn = new IntermittentConnection(new NitriteConnection("lavs-data.db"), new NFailsPerMRequestsPolicy(1, 4));
        String customerId = addCustomer.add(unrealiableConn, customer);

        addEmail.add(unrealiableConn, email1, customerId);

        List<CustomerSummary> summaries = initialSearch.findAll(unrealiableConn, customerId);
        assertEquals(summaries.get(0).getEmail(), "");
    }

    @Override
    protected void assertDetails(CustomerSummary expected, CustomerSummary actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDob(), actual.getDob());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getEmail(), actual.getEmail());
    }
}
