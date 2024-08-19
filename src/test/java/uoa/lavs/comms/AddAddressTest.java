package uoa.lavs.comms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.MockConnection;
import uoa.lavs.mainframe.Response;
import uoa.lavs.mainframe.Status;
import uoa.lavs.models.CustomerAddress;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddAddressTest extends AbstractCustomerTest<CustomerAddress> {

    private final AddAddress addAddy = new AddAddress();
    private final SearchAddress searchAddy = new SearchAddress();

    private final CustomerAddress addy1 = new CustomerAddress();
    private final CustomerAddress addy2 = new CustomerAddress();
    private final CustomerAddress addy3 = new CustomerAddress();
    private final CustomerAddress addy4 = new CustomerAddress();
    private final CustomerAddress addy5 = new CustomerAddress();

    @Override
    @BeforeEach
    void setup() throws IOException {
        super.setup();

        addy1.setType("Residential");
        addy1.setLine1("123 Apple Road");
        addy1.setLine2("Edith");
        addy1.setSuburb("CBD");
        addy1.setCity("Auckland");
        addy1.setPostCode(1234);
        addy1.setCountry("New Zealand");
        addy1.setIsMailing(true);
        addy1.setIsPrimary(true);

        addy2.setType("Work");
        addy2.setLine1("456 Banana Street");
        addy2.setLine2("Ellerslie");
        addy2.setSuburb("Suburb");
        addy2.setCity("Auckland");
        addy2.setPostCode(5678);
        addy2.setCountry("New Zealand");
        addy2.setIsMailing(false);
        addy2.setIsPrimary(false);

        addy3.setType("Mailing");
        addy3.setLine1("789 Cherry Avenue");
        addy3.setLine2("Mt Eden");
        addy3.setSuburb("Suburb");
        addy3.setCity("Auckland");
        addy3.setPostCode(9876);
        addy3.setCountry("New Zealand");
        addy3.setIsMailing(true);
        addy3.setIsPrimary(false);

        addy4.setType("Secondary");
        addy4.setLine1("1010 Orange Blvd");
        addy4.setLine2("Ponsonby");
        addy4.setSuburb("Suburb");
        addy4.setCity("Auckland");
        addy4.setPostCode(1010);
        addy4.setCountry("New Zealand");
        addy4.setIsMailing(false);
        addy4.setIsPrimary(false);

        addy5.setType("Holiday");
        addy5.setLine1("1112 Pine Crescent");
        addy5.setLine2("Devonport");
        addy5.setSuburb("Suburb");
        addy5.setCity("Auckland");
        addy5.setPostCode(12345);
        addy5.setCountry("New Zealand");
        addy5.setIsMailing(false);
        addy5.setIsPrimary(false);

        customer.addAddress(addy1);
        customer.addAddress(addy2);
        customer.addAddress(addy3);
        customer.addAddress(addy4);
        customer.addAddress(addy5);
    }


    @Test
    void testAddAddressSuccess() {
        String customerId = addCustomer.add(conn, customer);

        for (CustomerAddress ad : customer.getAddressList()) {
            addAddy.add(conn, ad, customerId);
        }

        List<CustomerAddress> addressesFromDb = searchAddy.findAll(conn, customerId);

        List<CustomerAddress> expectedAddresses = customer.getAddressList();

        for (CustomerAddress expectedAddress : expectedAddresses) {
            boolean matchFound = false;

            for (CustomerAddress dbAddress : addressesFromDb) {
                if (expectedAddress.getLine1().equals(dbAddress.getLine1())) {
                    assertDetails(expectedAddress, dbAddress);
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {
                fail("Unexpected address found: " + expectedAddress.getLine1());
            }
        }

        for (CustomerAddress dbAddress : addressesFromDb) {
            boolean matchFound = false;

            for (CustomerAddress expectedAddress : expectedAddresses) {
                if (dbAddress.getLine1().equals(expectedAddress.getLine1())) {
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {
                fail("Expected address not found: " + dbAddress.getLine1());
            }
        }
    }

    @Override
    protected void assertDetails(CustomerAddress expected, CustomerAddress actual) {
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getLine1(), actual.getLine1());
        assertEquals(expected.getLine2(), actual.getLine2());
        assertEquals(expected.getSuburb(), actual.getSuburb());
        assertEquals(expected.getCity(), actual.getCity());
        assertEquals(expected.getPostCode(), actual.getPostCode());
        assertEquals(expected.getCountry(), actual.getCountry());
        assertEquals(expected.getIsPrimary(), actual.getIsPrimary());
        assertEquals(expected.getIsMailing(), actual.getIsMailing());
    }

    @Test
    void testAddAddressFailure() {
        Status errorStatus = new Status(404, "Some problem", 123456);
        Response errorResponse = new Response(errorStatus, new HashMap<>());

        Connection mockConnection = new MockConnection(errorResponse);

        String customerId = addCustomer.add(mockConnection, customer);

        for (CustomerAddress ad : customer.getAddressList()) {
            addAddy.add(mockConnection, ad, customerId);
        }

        List<CustomerAddress> addresses = searchAddy.findAll(mockConnection, customerId);
        assertEquals(0, addresses.size());
    }
}
