package uoa.lavs.comms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.MockConnection;
import uoa.lavs.mainframe.Response;
import uoa.lavs.mainframe.Status;
import uoa.lavs.models.CustomerEmail;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddEmailTest extends AbstractCustomerTest<CustomerEmail> {

    private final AddEmail addEmail = new AddEmail();
    private final SearchEmail searchEmail = new SearchEmail();

    private final CustomerEmail email1 = new CustomerEmail();
    private final CustomerEmail email2 = new CustomerEmail();
    private final CustomerEmail email3 = new CustomerEmail();
    private final CustomerEmail email4 = new CustomerEmail();
    private final CustomerEmail email5 = new CustomerEmail();

    @Override
    @BeforeEach
    void setup() {
        super.setup();

        email1.setAddress("john.doe@example.com");
        email1.setIsPrimary(true);

        email2.setAddress("john.doe@work.com");
        email2.setIsPrimary(false);

        email3.setAddress("john.doe@home.com");
        email3.setIsPrimary(false);

        email4.setAddress("john.doe@other.com");
        email4.setIsPrimary(false);

        email5.setAddress("john.doe@backup.com");
        email5.setIsPrimary(false);

        customer.addEmail(email1);
        customer.addEmail(email2);
        customer.addEmail(email3);
        customer.addEmail(email4);
        customer.addEmail(email5);
    }

    @Test
    void testAddEmailSuccess() {
        String customerId = addCustomer.add(conn, customer);

        for (CustomerEmail email : customer.getEmailList()) {
            addEmail.add(conn, email, customerId);
        }

        List<CustomerEmail> emailsFromDb = searchEmail.findAll(conn, customerId);

        List<CustomerEmail> expectedEmails = customer.getEmailList();

        for (CustomerEmail expectedEmail : expectedEmails) {
            boolean matchFound = false;

            for (CustomerEmail dbEmail : emailsFromDb) {
                if (expectedEmail.getAddress().equals(dbEmail.getAddress())) {
                    assertDetails(expectedEmail, dbEmail);
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {
                fail("Unexpected email found: " + expectedEmail.getAddress());
            }
        }

        for (CustomerEmail dbEmail : emailsFromDb) {
            boolean matchFound = false;

            for (CustomerEmail expectedEmail : expectedEmails) {
                if (dbEmail.getAddress().equals(expectedEmail.getAddress())) {
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {
                fail("Expected email not found: " + dbEmail.getAddress());
            }
        }
    }

    @Override
    protected void assertDetails(CustomerEmail expected, CustomerEmail actual) {
        assertEquals(expected.getAddress(), actual.getAddress());
        assertEquals(expected.getIsPrimary(), actual.getIsPrimary());
    }

    @Test
    void testAddEmailFailure() {
        Status errorStatus = new Status(404, "Some problem", 123456);
        Response errorResponse = new Response(errorStatus, new HashMap<>());

        Connection mockConnection = new MockConnection(errorResponse);

        String customerId = addCustomer.add(mockConnection, customer);

        for (CustomerEmail email : customer.getEmailList()) {
            addEmail.add(mockConnection, email, customerId);
        }

        List<CustomerEmail> emails = searchEmail.findAll(mockConnection, customerId);
        assertEquals(0, emails.size());
    }
}
