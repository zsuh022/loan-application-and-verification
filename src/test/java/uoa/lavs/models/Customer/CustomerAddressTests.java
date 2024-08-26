package uoa.lavs.models.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerAddressTests {

    private CustomerAddress customerAddress;

    @BeforeEach
    void setUp() {
        customerAddress = new CustomerAddress();
    }

    @Test
    void testSetAndGetType() {
        customerAddress.setType("Residential");
        assertEquals("Residential", customerAddress.getType());
    }

    @Test
    void testSetAndGetLine1() {
        customerAddress.setLine1("123 Rainbow Road");
        assertEquals("123 Rainbow Road", customerAddress.getLine1());
    }

    @Test
    void testSetAndGetLine2() {
        customerAddress.setLine2("Somewhere");
        assertEquals("Somewhere", customerAddress.getLine2());
    }

    @Test
    void testSetAndGetSuburb() {
        customerAddress.setSuburb("Auckland CBD");
        assertEquals("Auckland CBD", customerAddress.getSuburb());
    }

    @Test
    void testSetAndGetCity() {
        customerAddress.setCity("Auckland");
        assertEquals("Auckland", customerAddress.getCity());
    }

    @Test
    void testSetAndGetPostCode() {
        customerAddress.setPostCode("1234");
        assertEquals("1234", customerAddress.getPostCode());
    }

    @Test
    void testSetAndGetCountry() {
        customerAddress.setCountry("New Zealand");
        assertEquals("New Zealand", customerAddress.getCountry());
    }

    @Test
    void testSetAndGetIsPrimary() {
        customerAddress.setIsPrimary(true);
        assertTrue(customerAddress.getIsPrimary());
    }

    @Test
    void testSetAndGetIsMailing() {
        customerAddress.setIsMailing(true);
        assertTrue(customerAddress.getIsMailing());
    }

    @Test
    void testSetAndGetIndex() {
        customerAddress.setIndex(1);
        assertEquals(1, customerAddress.getIndex());
    }

    @Test
    void testEquals_SameObject() {
        assertTrue(customerAddress.equals(customerAddress));
    }

    @Test
    void testEquals_DifferentObject_SameValues() {
        CustomerAddress anotherAddress = new CustomerAddress();
        anotherAddress.setType("Residential");
        anotherAddress.setLine1("123 Rainbow Road");
        anotherAddress.setLine2("Somewhere");
        anotherAddress.setSuburb("Auckland CBD");
        anotherAddress.setCity("Auckland");
        anotherAddress.setPostCode("1234");
        anotherAddress.setCountry("New Zealand");
        anotherAddress.setIsPrimary(true);
        anotherAddress.setIsMailing(true);

        customerAddress.setType("Residential");
        customerAddress.setLine1("123 Rainbow Road");
        customerAddress.setLine2("Somewhere");
        customerAddress.setSuburb("Auckland CBD");
        customerAddress.setCity("Auckland");
        customerAddress.setPostCode("1234");
        customerAddress.setCountry("New Zealand");
        customerAddress.setIsPrimary(true);
        customerAddress.setIsMailing(true);

        assertTrue(customerAddress.equals(anotherAddress));
    }

    @Test
    void testEquals_DifferentType() {
        CustomerAddress anotherAddress = new CustomerAddress();
        anotherAddress.setType("Commercial");

        customerAddress.setType("Residential");

        assertFalse(customerAddress.equals(anotherAddress));
    }

    @Test
    void testEquals_DifferentLine1() {
        CustomerAddress anotherAddress = new CustomerAddress();
        anotherAddress.setLine1("456 Real Road");

        customerAddress.setLine1("123 Rainbow Road");

        assertFalse(customerAddress.equals(anotherAddress));
    }

    @Test
    void testEquals_DifferentLine2() {
        CustomerAddress anotherAddress = new CustomerAddress();
        anotherAddress.setLine2("Nowhere");

        customerAddress.setLine2("Somewhere");

        assertFalse(customerAddress.equals(anotherAddress));
    }

    @Test
    void testEquals_DifferentSuburb() {
        CustomerAddress anotherAddress = new CustomerAddress();
        anotherAddress.setSuburb("Suburbia");

        customerAddress.setSuburb("Auckland CBD");

        assertFalse(customerAddress.equals(anotherAddress));
    }

    @Test
    void testEquals_DifferentCity() {
        CustomerAddress anotherAddress = new CustomerAddress();
        anotherAddress.setCity("Wellington");

        customerAddress.setCity("Auckland");

        assertFalse(customerAddress.equals(anotherAddress));
    }

    @Test
    void testEquals_DifferentPostCode() {
        CustomerAddress anotherAddress = new CustomerAddress();
        anotherAddress.setPostCode("5678");

        customerAddress.setPostCode("1234");

        assertFalse(customerAddress.equals(anotherAddress));
    }

    @Test
    void testEquals_DifferentCountry() {
        CustomerAddress anotherAddress = new CustomerAddress();
        anotherAddress.setCountry("Australia");

        customerAddress.setCountry("New Zealand");

        assertFalse(customerAddress.equals(anotherAddress));
    }

    @Test
    void testEquals_DifferentIsPrimary() {
        CustomerAddress anotherAddress = new CustomerAddress();
        anotherAddress.setIsPrimary(false);

        customerAddress.setIsPrimary(true);

        assertFalse(customerAddress.equals(anotherAddress));
    }

    @Test
    void testEquals_DifferentIsMailing() {
        CustomerAddress anotherAddress = new CustomerAddress();
        anotherAddress.setIsMailing(false);

        customerAddress.setIsMailing(true);

        assertFalse(customerAddress.equals(anotherAddress));
    }

    @Test
    void testEquals_NullObject() {
        assertFalse(customerAddress.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        assertFalse(customerAddress.equals("Some String"));
    }
}
