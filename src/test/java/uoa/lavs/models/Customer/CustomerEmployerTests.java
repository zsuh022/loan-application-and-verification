package uoa.lavs.models.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerEmployerTests {

    private CustomerEmployer customerEmployer;

    @BeforeEach
    void setUp() {
        customerEmployer = new CustomerEmployer();
    }

    @Test
    void testSetAndGetName() {
        customerEmployer.setName("ABC");
        assertEquals("ABC", customerEmployer.getName());
    }

    @Test
    void testSetAndGetLine1() {
        customerEmployer.setLine1("123 Corporate Road");
        assertEquals("123 Corporate Road", customerEmployer.getLine1());
    }

    @Test
    void testSetAndGetLine2() {
        customerEmployer.setLine2("Suite 200");
        assertEquals("Suite 200", customerEmployer.getLine2());
    }

    @Test
    void testSetAndGetSuburb() {
        customerEmployer.setSuburb("Silicon Valley");
        assertEquals("Silicon Valley", customerEmployer.getSuburb());
    }

    @Test
    void testSetAndGetCity() {
        customerEmployer.setCity("Auckland");
        assertEquals("Auckland", customerEmployer.getCity());
    }

    @Test
    void testSetAndGetPostCode() {
        customerEmployer.setPostCode("1010");
        assertEquals("1010", customerEmployer.getPostCode());
    }

    @Test
    void testSetAndGetCountry() {
        customerEmployer.setCountry("New Zealand");
        assertEquals("New Zealand", customerEmployer.getCountry());
    }

    @Test
    void testSetAndGetPhone() {
        customerEmployer.setPhone("091234567");
        assertEquals("091234567", customerEmployer.getPhone());
    }

    @Test
    void testSetAndGetEmail() {
        customerEmployer.setEmail("contact@abc.com");
        assertEquals("contact@abc.com", customerEmployer.getEmail());
    }

    @Test
    void testSetAndGetWeb() {
        customerEmployer.setWeb("www.abc.com");
        assertEquals("www.abc.com", customerEmployer.getWeb());
    }

    @Test
    void testSetAndGetIsOwner() {
        customerEmployer.setIsOwner(true);
        assertTrue(customerEmployer.getIsOwner());
    }

    @Test
    void testSetAndGetIndex() {
        customerEmployer.setIndex(1);
        assertEquals(1, customerEmployer.getIndex());
    }

    @Test
    void testEquals_SameObject() {
        assertTrue(customerEmployer.equals(customerEmployer));
    }

    @Test
    void testEquals_SameValues() {
        CustomerEmployer anotherEmployer = new CustomerEmployer();
        anotherEmployer.setName("ABC");
        anotherEmployer.setLine1("123 Corporate Road");
        anotherEmployer.setLine2("Suite 200");
        anotherEmployer.setSuburb("Silicon Valley");
        anotherEmployer.setCity("Auckland");
        anotherEmployer.setPostCode("1010");
        anotherEmployer.setCountry("New Zealand");
        anotherEmployer.setPhone("091234567");
        anotherEmployer.setEmail("contact@abc.com");
        anotherEmployer.setWeb("www.abc.com");
        anotherEmployer.setIsOwner(true);

        customerEmployer.setName("ABC");
        customerEmployer.setLine1("123 Corporate Road");
        customerEmployer.setLine2("Suite 200");
        customerEmployer.setSuburb("Silicon Valley");
        customerEmployer.setCity("Auckland");
        customerEmployer.setPostCode("1010");
        customerEmployer.setCountry("New Zealand");
        customerEmployer.setPhone("091234567");
        customerEmployer.setEmail("contact@abc.com");
        customerEmployer.setWeb("www.abc.com");
        customerEmployer.setIsOwner(true);

        assertTrue(customerEmployer.equals(anotherEmployer));
    }

    @Test
    void testEquals_DifferentName() {
        CustomerEmployer anotherEmployer = new CustomerEmployer();
        anotherEmployer.setName("Google");

        customerEmployer.setName("ABC");

        assertFalse(customerEmployer.equals(anotherEmployer));
    }

    @Test
    void testEquals_DifferentLine1() {
        CustomerEmployer anotherEmployer = new CustomerEmployer();
        anotherEmployer.setLine1("456 Business Road");

        customerEmployer.setLine1("123 Corporate Road");

        assertFalse(customerEmployer.equals(anotherEmployer));
    }

    @Test
    void testEquals_DifferentLine2() {
        CustomerEmployer anotherEmployer = new CustomerEmployer();
        anotherEmployer.setLine2("Suite 100");

        customerEmployer.setLine2("Suite 200");

        assertFalse(customerEmployer.equals(anotherEmployer));
    }

    @Test
    void testEquals_DifferentSuburb() {
        CustomerEmployer anotherEmployer = new CustomerEmployer();
        anotherEmployer.setSuburb("Auckland CBD");

        customerEmployer.setSuburb("Silicon Valley");

        assertFalse(customerEmployer.equals(anotherEmployer));
    }

    @Test
    void testEquals_DifferentCity() {
        CustomerEmployer anotherEmployer = new CustomerEmployer();
        anotherEmployer.setCity("Wellington");

        customerEmployer.setCity("Auckland");

        assertFalse(customerEmployer.equals(anotherEmployer));
    }

    @Test
    void testEquals_DifferentPostCode() {
        CustomerEmployer anotherEmployer = new CustomerEmployer();
        anotherEmployer.setPostCode("5678");

        customerEmployer.setPostCode("1234");

        assertFalse(customerEmployer.equals(anotherEmployer));
    }

    @Test
    void testEquals_DifferentCountry() {
        CustomerEmployer anotherEmployer = new CustomerEmployer();
        anotherEmployer.setCountry("USA");

        customerEmployer.setCountry("New Zealand");

        assertFalse(customerEmployer.equals(anotherEmployer));
    }

    @Test
    void testEquals_DifferentPhone() {
        CustomerEmployer anotherEmployer = new CustomerEmployer();
        anotherEmployer.setPhone("987654321");

        customerEmployer.setPhone("123456789");

        assertFalse(customerEmployer.equals(anotherEmployer));
    }

    @Test
    void testEquals_DifferentEmail() {
        CustomerEmployer anotherEmployer = new CustomerEmployer();
        anotherEmployer.setEmail("contact@google.com");

        customerEmployer.setEmail("contact@abc.com");

        assertFalse(customerEmployer.equals(anotherEmployer));
    }

    @Test
    void testEquals_DifferentWeb() {
        CustomerEmployer anotherEmployer = new CustomerEmployer();
        anotherEmployer.setWeb("www.google.com");

        customerEmployer.setWeb("www.abc.com");

        assertFalse(customerEmployer.equals(anotherEmployer));
    }

    @Test
    void testEquals_DifferentIsOwner() {
        CustomerEmployer anotherEmployer = new CustomerEmployer();
        anotherEmployer.setIsOwner(false);

        customerEmployer.setIsOwner(true);

        assertFalse(customerEmployer.equals(anotherEmployer));
    }

    @Test
    void testEquals_NullObject() {
        assertFalse(customerEmployer.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        assertFalse(customerEmployer.equals("Some String"));
    }
}
