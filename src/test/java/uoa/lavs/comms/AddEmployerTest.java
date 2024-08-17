package uoa.lavs.comms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.MockConnection;
import uoa.lavs.mainframe.Response;
import uoa.lavs.mainframe.Status;
import uoa.lavs.models.CustomerEmployer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddEmployerTest extends AbstractCustomerTest<CustomerEmployer> {

    private final AddEmployer addEmployer = new AddEmployer();
    private final SearchEmployer searchEmployer = new SearchEmployer();

    private final CustomerEmployer employer1 = new CustomerEmployer();
    private final CustomerEmployer employer2 = new CustomerEmployer();
    private final CustomerEmployer employer3 = new CustomerEmployer();
    private final CustomerEmployer employer4 = new CustomerEmployer();
    private final CustomerEmployer employer5 = new CustomerEmployer();

    @Override
    @BeforeEach
    void setup() throws IOException {
        super.setup();

        employer1.setName("Company A");
        employer1.setLine1("123 Main St");
        employer1.setLine2("Suite 100");
        employer1.setSuburb("Suburb A");
        employer1.setCity("City A");
        employer1.setPostCode("12345");
        employer1.setCountry("Country A");
        employer1.setPhone("555-1234");
        employer1.setEmail("contact@companya.com");
        employer1.setWeb("http://www.companya.com");
        employer1.setIsOwner(true);

        employer2.setName("Company B");
        employer2.setLine1("456 Elm St");
        employer2.setLine2("Suite 200");
        employer2.setSuburb("Suburb B");
        employer2.setCity("City B");
        employer2.setPostCode("67890");
        employer2.setCountry("Country B");
        employer2.setPhone("555-5678");
        employer2.setEmail("contact@companyb.com");
        employer2.setWeb("http://www.companyb.com");
        employer2.setIsOwner(false);

        employer3.setName("Company C");
        employer3.setLine1("789 Oak St");
        employer3.setLine2("Suite 300");
        employer3.setSuburb("Suburb C");
        employer3.setCity("City C");
        employer3.setPostCode("13579");
        employer3.setCountry("Country C");
        employer3.setPhone("555-9101");
        employer3.setEmail("contact@companyc.com");
        employer3.setWeb("http://www.companyc.com");
        employer3.setIsOwner(false);

        employer4.setName("Company D");
        employer4.setLine1("321 Pine St");
        employer4.setLine2("Suite 400");
        employer4.setSuburb("Suburb D");
        employer4.setCity("City D");
        employer4.setPostCode("24680");
        employer4.setCountry("Country D");
        employer4.setPhone("555-1122");
        employer4.setEmail("contact@companyd.com");
        employer4.setWeb("http://www.companyd.com");
        employer4.setIsOwner(false);

        employer5.setName("Company E");
        employer5.setLine1("654 Maple St");
        employer5.setLine2("Suite 500");
        employer5.setSuburb("Suburb E");
        employer5.setCity("City E");
        employer5.setPostCode("98765");
        employer5.setCountry("Country E");
        employer5.setPhone("555-3344");
        employer5.setEmail("contact@companye.com");
        employer5.setWeb("http://www.companye.com");
        employer5.setIsOwner(true);

        customer.addEmployer(employer1);
        customer.addEmployer(employer2);
        customer.addEmployer(employer3);
        customer.addEmployer(employer4);
        customer.addEmployer(employer5);
    }

    @Test
    void testAddEmployerSuccess() {
        String customerId = addCustomer.add(conn, customer);

        for (CustomerEmployer employer : customer.getEmployerList()) {
            addEmployer.add(conn, employer, customerId);
        }

        List<CustomerEmployer> employersFromDb = searchEmployer.findAll(conn, customerId);

        List<CustomerEmployer> expectedEmployers = customer.getEmployerList();

        for (CustomerEmployer expectedEmployer : expectedEmployers) {
            boolean matchFound = false;

            for (CustomerEmployer dbEmployer : employersFromDb) {
                if (expectedEmployer.getName().equals(dbEmployer.getName())) {
                    assertDetails(expectedEmployer, dbEmployer);
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {
                fail("Unexpected employer found: " + expectedEmployer.getName());
            }
        }

        for (CustomerEmployer dbEmployer : employersFromDb) {
            boolean matchFound = false;

            for (CustomerEmployer expectedEmployer : expectedEmployers) {
                if (dbEmployer.getName().equals(expectedEmployer.getName())) {
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {
                fail("Expected employer not found: " + dbEmployer.getName());
            }
        }
    }

    @Override
    protected void assertDetails(CustomerEmployer expected, CustomerEmployer actual) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getLine1(), actual.getLine1());
        assertEquals(expected.getLine2(), actual.getLine2());
        assertEquals(expected.getSuburb(), actual.getSuburb());
        assertEquals(expected.getCity(), actual.getCity());
        assertEquals(expected.getPostCode(), actual.getPostCode());
        assertEquals(expected.getCountry(), actual.getCountry());
        assertEquals(expected.getPhone(), actual.getPhone());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getWeb(), actual.getWeb());
        assertEquals(expected.getIsOwner(), actual.getIsOwner());
    }

    @Test
    void testAddEmployerFailure() {
        Status errorStatus = new Status(404, "Some problem", 123456);
        Response errorResponse = new Response(errorStatus, new HashMap<>());

        Connection mockConnection = new MockConnection(errorResponse);

        String customerId = addCustomer.add(mockConnection, customer);

        for (CustomerEmployer employer : customer.getEmployerList()) {
            addEmployer.add(mockConnection, employer, customerId);
        }

        List<CustomerEmployer> employers = searchEmployer.findAll(mockConnection, customerId);
        assertEquals(0, employers.size());
    }
}
