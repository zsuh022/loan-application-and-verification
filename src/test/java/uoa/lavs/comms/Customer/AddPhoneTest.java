package uoa.lavs.comms.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.comms.AbstractCustomerTest;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.MockConnection;
import uoa.lavs.mainframe.Response;
import uoa.lavs.mainframe.Status;
import uoa.lavs.models.Customer.CustomerPhone;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddPhoneTest extends AbstractCustomerTest<CustomerPhone> {

    private final AddPhone addPhone = new AddPhone();
    private final SearchPhone searchPhone = new SearchPhone();

    private final CustomerPhone phone1 = new CustomerPhone();
    private final CustomerPhone phone2 = new CustomerPhone();
    private final CustomerPhone phone3 = new CustomerPhone();
    private final CustomerPhone phone4 = new CustomerPhone();
    private final CustomerPhone phone5 = new CustomerPhone();

    @Override
    @BeforeEach
    protected void setup() throws IOException {
        super.setup();

        phone1.setType("Mobile");
        phone1.setPrefix("+1");
        phone1.setNumber("5551234567");
        phone1.setIsPrimary(true);
        phone1.setIsTexting(true);

        phone2.setType("Home");
        phone2.setPrefix("+1");
        phone2.setNumber("5559876543");
        phone2.setIsPrimary(false);
        phone2.setIsTexting(false);

        phone3.setType("Work");
        phone3.setPrefix("+44");
        phone3.setNumber("2034456789");
        phone3.setIsPrimary(false);
        phone3.setIsTexting(true);

        phone4.setType("Fax");
        phone4.setPrefix("+1");
        phone4.setNumber("5553332211");
        phone4.setIsPrimary(false);
        phone4.setIsTexting(false);

        phone5.setType("Other");
        phone5.setPrefix("+81");
        phone5.setNumber("9098765432");
        phone5.setIsPrimary(false);
        phone5.setIsTexting(false);

        customer.addPhone(phone1);
        customer.addPhone(phone2);
        customer.addPhone(phone3);
        customer.addPhone(phone4);
        customer.addPhone(phone5);
    }

    @Test
    void testAddPhoneSuccess() {
        String customerId = addCustomer.add(conn, customer);

        for (CustomerPhone phone : customer.getPhoneList()) {
            addPhone.add(conn, phone, customerId);
        }

        List<CustomerPhone> phonesFromDb = searchPhone.findAll(conn, customerId);

        List<CustomerPhone> expectedPhones = customer.getPhoneList();

        for (CustomerPhone expectedPhone : expectedPhones) {
            boolean matchFound = false;

            for (CustomerPhone dbPhone : phonesFromDb) {
                if (expectedPhone.getNumber().equals(dbPhone.getNumber())) {
                    assertDetails(expectedPhone, dbPhone);
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {
                fail("Unexpected phone found: " + expectedPhone.getNumber());
            }
        }

        for (CustomerPhone dbPhone : phonesFromDb) {
            boolean matchFound = false;

            for (CustomerPhone expectedPhone : expectedPhones) {
                if (dbPhone.getNumber().equals(expectedPhone.getNumber())) {
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {
                fail("Expected phone not found: " + dbPhone.getNumber());
            }
        }
    }

    @Override
    protected void assertDetails(CustomerPhone expected, CustomerPhone actual) {
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getPrefix(), actual.getPrefix());
        assertEquals(expected.getNumber(), actual.getNumber());
        assertEquals(expected.getIsPrimary(), actual.getIsPrimary());
        assertEquals(expected.getIsTexting(), actual.getIsTexting());
    }

    @Test
    void testAddPhoneFailure() {
        Status errorStatus = new Status(404, "Some problem", 123456);
        Response errorResponse = new Response(errorStatus, new HashMap<>());

        Connection mockConnection = new MockConnection(errorResponse);

        String customerId = addCustomer.add(mockConnection, customer);

        for (CustomerPhone phone : customer.getPhoneList()) {
            addPhone.add(mockConnection, phone, customerId);
        }

        List<CustomerPhone> phones = searchPhone.findAll(mockConnection, customerId);
        assertEquals(0, phones.size());
    }
}
