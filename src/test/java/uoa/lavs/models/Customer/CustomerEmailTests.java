package uoa.lavs.models.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerEmailTests {

    private CustomerEmail customerEmail;

    @BeforeEach
    void setUp() {
        customerEmail = new CustomerEmail();
    }

    @Test
    void testSetAndGetAddress() {
        customerEmail.setAddress("test@example.com");
        assertEquals("test@example.com", customerEmail.getAddress());
    }

    @Test
    void testSetAndGetIsPrimary() {
        customerEmail.setIsPrimary(true);
        assertTrue(customerEmail.getIsPrimary());
    }

    @Test
    void testSetAndGetIndex() {
        customerEmail.setIndex(1);
        assertEquals(1, customerEmail.getIndex());
    }

    @Test
    void testEquals_SameObject() {
        assertTrue(customerEmail.equals(customerEmail));
    }

    @Test
    void testEquals_DifferentObject_SameValues() {
        CustomerEmail anotherEmail = new CustomerEmail();
        anotherEmail.setAddress("test@example.com");
        anotherEmail.setIsPrimary(true);

        customerEmail.setAddress("test@example.com");
        customerEmail.setIsPrimary(true);

        assertTrue(customerEmail.equals(anotherEmail));
    }

    @Test
    void testEquals_DifferentObject_DifferentAddress() {
        CustomerEmail anotherEmail = new CustomerEmail();
        anotherEmail.setAddress("other@example.com");

        customerEmail.setAddress("test@example.com");

        assertFalse(customerEmail.equals(anotherEmail));
    }

    @Test
    void testEquals_DifferentObject_DifferentIsPrimary() {
        CustomerEmail anotherEmail = new CustomerEmail();
        anotherEmail.setIsPrimary(false);

        customerEmail.setIsPrimary(true);

        assertFalse(customerEmail.equals(anotherEmail));
    }

    @Test
    void testEquals_NullObject() {
        assertFalse(customerEmail.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        assertFalse(customerEmail.equals("Some String"));
    }
}
