package uoa.lavs.comms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.MockConnection;
import uoa.lavs.mainframe.Response;
import uoa.lavs.mainframe.Status;
import uoa.lavs.models.CustomerAddress;
import uoa.lavs.models.CustomerEmail;
import uoa.lavs.models.CustomerPhone;
import uoa.lavs.models.CustomerSummary;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CombinedInitialSearchTest extends AbstractCustomerTest<CustomerSummary> {

    private final AddEmail addEmail = new AddEmail();

    private final AddAddress addAddy = new AddAddress();

    private final AddPhone addPhone = new AddPhone();

    private final CustomerEmail email1 = new CustomerEmail();
    private final CustomerAddress addy1 = new CustomerAddress();
    private final CustomerPhone phone1 = new CustomerPhone();

    private InitialSearch initialSearch;

    @Override
    @BeforeEach
    void setup() throws IOException {
        super.setup();

        email1.setAddress("john.doe@example.com");
        email1.setIsPrimary(true);
        customer.addEmail(email1);

        addy1.setType("Residential");
        addy1.setLine1("123 Apple Road");
        addy1.setCity("Auckland");
        addy1.setPostCode(1234);
        addy1.setCountry("New Zealand");
        addy1.setIsPrimary(true);
        addy1.setIsMailing(true);
        customer.addAddress(addy1);

        phone1.setType("Mobile");
        phone1.setPrefix("+64");
        phone1.setNumber("9123456789");
        phone1.setIsPrimary(true);
        phone1.setIsTexting(true);
        customer.addPhone(phone1);

        initialSearch = new InitialSearch(0);
    }

    @Test
    void testCombinedAddAndSearchSuccess() {
        String customerId = addCustomer.add(conn, customer);
        addEmail.add(conn, email1, customerId);
        addAddy.add(conn, addy1, customerId);
        addPhone.add(conn, phone1, customerId);

        List<CustomerSummary> summariesFromDb = initialSearch.findAll(conn, customerId);
        assertEquals(1, summariesFromDb.size());

        CustomerSummary retrievedSummary = summariesFromDb.get(0);

        assertEquals(email1.getAddress(), retrievedSummary.getEmail());
        assertEquals(addy1.getType(), retrievedSummary.getAddress());
        assertEquals(phone1.getType(), retrievedSummary.getPhone());
    }

    @Test
    void testCombinedAddAndSearchFailure() {
        Status errorStatus = new Status(404, "Some problem", 123456);
        Response errorResponse = new Response(errorStatus, new HashMap<>());

        Connection mockConnection = new MockConnection(errorResponse);

        String customerId = addCustomer.add(mockConnection, customer);

        addEmail.add(mockConnection, email1, customerId);
        addAddy.add(mockConnection, addy1, customerId);
        addPhone.add(mockConnection, phone1, customerId);

        List<CustomerSummary> summaries = initialSearch.findAll(mockConnection, customerId);
        assertTrue(summaries.isEmpty());
    }

    @Override
    protected void assertDetails(CustomerSummary expected, CustomerSummary actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDob(), actual.getDob());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getAddress(), actual.getAddress());
        assertEquals(expected.getPhone(), actual.getPhone());
    }
}
