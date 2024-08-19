package uoa.lavs.comms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.MockConnection;
import uoa.lavs.mainframe.Response;
import uoa.lavs.mainframe.Status;
import uoa.lavs.models.CustomerSummary;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InitialSearchTest extends AbstractCustomerTest<CustomerSummary> {

    private InitialSearch initialSearch;

    @Override
    @BeforeEach
    void setup() throws IOException {
        super.setup();
        initialSearch = new InitialSearch(0);
        addCustomer.add(conn, customer);
    }

    @Override
    protected void assertDetails(CustomerSummary expected, CustomerSummary actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDob(), actual.getDob());
        assertEquals(expected.getId(), actual.getId());
    }

    @Test
    protected void testFindAllSuccess() {
        List<CustomerSummary> summaries = initialSearch.findAll(conn, String.valueOf(1));
        assertEquals(1, summaries.size());
        for (CustomerSummary customerSummary : summaries) {
            assertDetails(customerSummary, summaries.get(0));
        }
    }

    @Test
    protected void testFindAllFailure() {
        Status errorStatus = new Status(404, "Customer not found", 123456L);
        Response errorResponse = new Response(errorStatus, new HashMap<>());

        Connection mockConnection = new MockConnection(errorResponse);

        List<CustomerSummary> summaries = initialSearch.findAll(mockConnection, String.valueOf(1));

        assertTrue(summaries.isEmpty());
    }
}
