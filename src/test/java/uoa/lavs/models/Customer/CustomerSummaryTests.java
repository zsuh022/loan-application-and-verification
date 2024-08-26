package uoa.lavs.models.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class CustomerSummaryTests {

    private CustomerSummary customerSummary;

    @BeforeEach
    void setUp() {
        customerSummary = new CustomerSummary();
    }

    @Test
    void testSetAndGetId() {
        customerSummary.setId("C123");
        assertEquals("C123", customerSummary.getId());
    }

    @Test
    void testSetAndGetName() {
        customerSummary.setName("John Doe");
        assertEquals("John Doe", customerSummary.getName());
    }

    @Test
    void testSetAndGetDob() {
        LocalDate dob = LocalDate.of(1990, 1, 1);
        customerSummary.setDob(dob);
        assertEquals(dob, customerSummary.getDob());
    }

    @Test
    void testSetAndGetEmail() {
        customerSummary.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", customerSummary.getEmail());
    }

    @Test
    void testEquals_SameObject() {
        assertTrue(customerSummary.equals(customerSummary));
    }

    @Test
    void testEquals_SameValues() {
        CustomerSummary anotherSummary = new CustomerSummary();
        anotherSummary.setId("C123");
        anotherSummary.setName("John Doe");
        anotherSummary.setDob(LocalDate.of(1990, 1, 1));
        anotherSummary.setEmail("john.doe@example.com");

        customerSummary.setId("C123");
        customerSummary.setName("John Doe");
        customerSummary.setDob(LocalDate.of(1990, 1, 1));
        customerSummary.setEmail("john.doe@example.com");

        assertTrue(customerSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_DifferentId() {
        CustomerSummary anotherSummary = new CustomerSummary();
        anotherSummary.setId("456");

        customerSummary.setId("123");

        assertFalse(customerSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_SameId() {
        CustomerSummary anotherSummary = new CustomerSummary();
        anotherSummary.setId("123");

        customerSummary.setId("123");

        assertTrue(customerSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_nullId() {
        CustomerSummary anotherSummary = new CustomerSummary();
        anotherSummary.setId("123");

        assertFalse(customerSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_DifferentName() {
        CustomerSummary anotherSummary = new CustomerSummary();
        anotherSummary.setName("Jane Smith");

        customerSummary.setName("John Doe");

        assertFalse(customerSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_SameName() {
        CustomerSummary anotherSummary = new CustomerSummary();
        anotherSummary.setName("John Doe");

        customerSummary.setName("John Doe");

        assertTrue(customerSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_nullName() {
        CustomerSummary anotherSummary = new CustomerSummary();
        anotherSummary.setName("John Doe");

        assertFalse(customerSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_DifferentDob() {
        CustomerSummary anotherSummary = new CustomerSummary();
        anotherSummary.setDob(LocalDate.of(1990, 1, 1));

        customerSummary.setDob(LocalDate.of(2000, 1, 1));

        assertFalse(customerSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_SameDob() {
        CustomerSummary anotherSummary = new CustomerSummary();
        anotherSummary.setDob(LocalDate.of(1990, 1, 1));

        customerSummary.setDob(LocalDate.of(1990, 1, 1));

        assertTrue(customerSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_nullDob() {
        CustomerSummary anotherSummary = new CustomerSummary();
        anotherSummary.setDob(LocalDate.of(1990, 1, 1));

        assertFalse(customerSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_DifferentEmail() {
        CustomerSummary anotherSummary = new CustomerSummary();
        anotherSummary.setEmail("jane@google.com");

        customerSummary.setEmail("john@example.com");

        assertFalse(customerSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_SameEmail() {
        CustomerSummary anotherSummary = new CustomerSummary();
        anotherSummary.setEmail("john@example.com");

        customerSummary.setEmail("john@example.com");

        assertTrue(customerSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_nullEmail() {
        CustomerSummary anotherSummary = new CustomerSummary();
        anotherSummary.setEmail("john@example.com");

        assertFalse(customerSummary.equals(anotherSummary));
    }


    @Test
    void testEquals_NullObject() {
        assertFalse(customerSummary.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        assertFalse(customerSummary.equals("Some String"));
    }

    @Test
    void testHashCode_SameValues() {
        CustomerSummary anotherSummary = new CustomerSummary();
        anotherSummary.setId("C123");
        anotherSummary.setName("John Doe");
        anotherSummary.setDob(LocalDate.of(1990, 1, 1));
        anotherSummary.setEmail("john.doe@example.com");

        customerSummary.setId("C123");
        customerSummary.setName("John Doe");
        customerSummary.setDob(LocalDate.of(1990, 1, 1));
        customerSummary.setEmail("john.doe@example.com");

        assertEquals(customerSummary.hashCode(), anotherSummary.hashCode());
    }

    @Test
    void testHashCode_DifferentValues() {
        CustomerSummary anotherSummary = new CustomerSummary();
        anotherSummary.setId("C456");

        customerSummary.setId("C123");

        assertNotEquals(customerSummary.hashCode(), anotherSummary.hashCode());
    }

    @Test
    void testHashCode_WithId() {
        CustomerSummary summary = new CustomerSummary();
        summary.setId("123");

        int expectedHash = "123".hashCode();
        assertEquals(expectedHash, summary.getId().hashCode());
    }

    @Test
    void testHashCode_NullId() {
        CustomerSummary summary = new CustomerSummary();

        int expectedHash = 0;
        assertEquals(expectedHash, summary.hashCode());
    }
}
