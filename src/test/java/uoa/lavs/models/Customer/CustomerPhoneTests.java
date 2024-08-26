package uoa.lavs.models.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerPhoneTests {

    private CustomerPhone customerPhone;

    @BeforeEach
    void setUp() {
        customerPhone = new CustomerPhone();
    }

    @Test
    void testSetAndGetType() {
        customerPhone.setType("Mobile");
        assertEquals("Mobile", customerPhone.getType());
    }

    @Test
    void testSetAndGetPrefix() {
        customerPhone.setPrefix("021");
        assertEquals("021", customerPhone.getPrefix());
    }

    @Test
    void testSetAndGetNumber() {
        customerPhone.setNumber("1234567");
        assertEquals("1234567", customerPhone.getNumber());
    }

    @Test
    void testSetAndGetIsPrimary() {
        customerPhone.setIsPrimary(true);
        assertTrue(customerPhone.getIsPrimary());
    }

    @Test
    void testSetAndGetIsTexting() {
        customerPhone.setIsTexting(true);
        assertTrue(customerPhone.getIsTexting());
    }

    @Test
    void testSetAndGetIndex() {
        customerPhone.setIndex(1);
        assertEquals(1, customerPhone.getIndex());
    }

    @Test
    void testEquals_SameObject() {
        assertTrue(customerPhone.equals(customerPhone));
    }

    @Test
    void testEquals_SameValues() {
        CustomerPhone anotherPhone = new CustomerPhone();
        anotherPhone.setType("Mobile");
        anotherPhone.setPrefix("021");
        anotherPhone.setNumber("1234567");
        anotherPhone.setIsPrimary(true);
        anotherPhone.setIsTexting(true);

        customerPhone.setType("Mobile");
        customerPhone.setPrefix("021");
        customerPhone.setNumber("1234567");
        customerPhone.setIsPrimary(true);
        customerPhone.setIsTexting(true);

        assertTrue(customerPhone.equals(anotherPhone));
    }

    @Test
    void testEquals_DifferentType() {
        CustomerPhone anotherPhone = new CustomerPhone();
        anotherPhone.setType("Landline");

        customerPhone.setType("Mobile");

        assertFalse(customerPhone.equals(anotherPhone));
    }

    @Test
    void testEquals_DifferentPrefix() {
        CustomerPhone anotherPhone = new CustomerPhone();
        anotherPhone.setPrefix("090");

        customerPhone.setPrefix("021");

        assertFalse(customerPhone.equals(anotherPhone));
    }

    @Test
    void testEquals_DifferentNumber() {
        CustomerPhone anotherPhone = new CustomerPhone();
        anotherPhone.setNumber("9876543");

        customerPhone.setNumber("1234567");

        assertFalse(customerPhone.equals(anotherPhone));
    }

    @Test
    void testEquals_DifferentIsPrimary() {
        CustomerPhone anotherPhone = new CustomerPhone();
        anotherPhone.setIsPrimary(false);

        customerPhone.setIsPrimary(true);

        assertFalse(customerPhone.equals(anotherPhone));
    }

    @Test
    void testEquals_DifferentIsTexting() {
        CustomerPhone anotherPhone = new CustomerPhone();
        anotherPhone.setIsTexting(false);

        customerPhone.setIsTexting(true);

        assertFalse(customerPhone.equals(anotherPhone));
    }

    @Test
    void testEquals_NullObject() {
        assertFalse(customerPhone.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        assertFalse(customerPhone.equals("Some String"));
    }
}
