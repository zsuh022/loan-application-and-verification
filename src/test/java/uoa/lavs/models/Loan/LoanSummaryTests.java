package uoa.lavs.models.Loan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanSummaryTests {

    private LoanSummary loanSummary;

    @BeforeEach
    void setUp() {
        loanSummary = new LoanSummary("L123", "C123", "John Doe", "1", 100000.0);
    }

    @Test
    void testGetLoanID() {
        assertEquals("L123", loanSummary.getLoanID());
    }

    @Test
    void testGetCustomerID() {
        assertEquals("C123", loanSummary.getCustomerID());
    }

    @Test
    void testGetCustomerName() {
        assertEquals("John Doe", loanSummary.getCustomerName());
    }

    @Test
    void testGetStatus() {
        assertEquals("1", loanSummary.getStatus());
    }

    @Test
    void testGetPrincipal() {
        assertEquals(100000.0, loanSummary.getPrincipal());
    }

    @Test
    void testGetStatusString_New() {
        LoanSummary summary = new LoanSummary("L123", "C123", "John Doe", "1", 100000.0);
        assertEquals("New", summary.getStatusString());
    }

    @Test
    void testGetStatusString_Active() {
        LoanSummary summary = new LoanSummary("L123", "C123", "John Doe", "5", 100000.0);
        assertEquals("Active", summary.getStatusString());
    }

    @Test
    void testGetStatusString_Cancelled() {
        LoanSummary summary = new LoanSummary("L123", "C123", "John Doe", "8", 100000.0);
        assertEquals("Cancelled", summary.getStatusString());
    }

    @Test
    void testGetStatusString_Pending() {
        LoanSummary summary = new LoanSummary("L123", "C123", "John Doe", "2", 100000.0);
        assertEquals("Pending", summary.getStatusString());
    }

    @Test
    void testGetStatusString_Unknown() {
        LoanSummary summary = new LoanSummary("L123", "C123", "John Doe", "100", 100000.0);
        assertEquals("Unknown", summary.getStatusString());
    }

    @Test
    void testEquals_SameObject() {
        assertTrue(loanSummary.equals(loanSummary));
    }

    @Test
    void testEquals_SameValues() {
        LoanSummary anotherSummary = new LoanSummary("L123", "C123", "John Doe", "1", 100000.0);
        assertTrue(loanSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_DifferentLoanId() {
        LoanSummary anotherSummary = new LoanSummary("L456", "C123", "John Doe", "1", 100000.0);
        assertFalse(loanSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_SameLoanId() {
        LoanSummary anotherSummary = new LoanSummary("L123", "C123", "John Doe", "1", 100000.0);
        assertTrue(loanSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_NullLoanId() {
        LoanSummary anotherSummary = new LoanSummary(null, "C123", "John Doe", "1", 100000.0);
        assertFalse(loanSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_LoanIdNull_BothObjects() {
        LoanSummary summary1 = new LoanSummary(null, "C123", "John Doe", "1", 100000.0);
        LoanSummary summary2 = new LoanSummary(null, "C123", "John Doe", "1", 100000.0);
        assertTrue(summary1.equals(summary2));
    }

    @Test
    void testEquals_LoanIdNull_OneObject() {
        LoanSummary summary1 = new LoanSummary(null, "C123", "John Doe", "1", 100000.0);
        LoanSummary summary2 = new LoanSummary("L123", "C123", "John Doe", "1", 100000.0);
        assertFalse(summary1.equals(summary2));
    }

    @Test
    void testEquals_DifferentCustomerId() {
        LoanSummary anotherSummary = new LoanSummary("L123", "C456", "John Doe", "1", 100000.0);
        assertFalse(loanSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_SameCustomerId() {
        LoanSummary anotherSummary = new LoanSummary("L123", "C123", "John Doe", "1", 100000.0);
        assertTrue(loanSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_NullCustomerId() {
        LoanSummary anotherSummary = new LoanSummary("L123", null, "John Doe", "1", 100000.0);
        assertFalse(loanSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_CustomerIdNull_BothObjects() {
        LoanSummary summary1 = new LoanSummary("L123", null, "John Doe", "1", 100000.0);
        LoanSummary summary2 = new LoanSummary("L123", null, "John Doe", "1", 100000.0);
        assertTrue(summary1.equals(summary2));
    }

    @Test
    void testEquals_CustomerIdNull_OneObject() {
        LoanSummary summary1 = new LoanSummary("L123", null, "John Doe", "1", 100000.0);
        LoanSummary summary2 = new LoanSummary("L123", "C123", "John Doe", "1", 100000.0);
        assertFalse(summary1.equals(summary2));
    }

    @Test
    void testEquals_DifferentCustomerName() {
        LoanSummary anotherSummary = new LoanSummary("L123", "C123", "Jane Doe", "1", 100000.0);
        assertFalse(loanSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_SameCustomerName() {
        LoanSummary anotherSummary = new LoanSummary("L123", "C123", "John Doe", "1", 100000.0);
        assertTrue(loanSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_NullCustomerName() {
        LoanSummary anotherSummary = new LoanSummary("L123", "C123", null, "1", 100000.0);
        assertFalse(loanSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_CustomerNameNull_BothObjects() {
        LoanSummary summary1 = new LoanSummary("L123", "C123", null, "1", 100000.0);
        LoanSummary summary2 = new LoanSummary("L123", "C123", null, "1", 100000.0);
        assertTrue(summary1.equals(summary2));
    }

    @Test
    void testEquals_CustomerNameNull_OneObject() {
        LoanSummary summary1 = new LoanSummary("L123", "C123", null, "1", 100000.0);
        LoanSummary summary2 = new LoanSummary("L123", "C123", "John Doe", "1", 100000.0);
        assertFalse(summary1.equals(summary2));
    }

    @Test
    void testEquals_DifferentStatus() {
        LoanSummary anotherSummary = new LoanSummary("L123", "C123", "John Doe", "5", 100000.0);
        assertFalse(loanSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_SameStatus() {
        LoanSummary anotherSummary = new LoanSummary("L123", "C123", "John Doe", "1", 100000.0);
        assertTrue(loanSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_NullStatus() {
        LoanSummary anotherSummary = new LoanSummary("L123", "C123", "John Doe", null, 100000.0);
        assertFalse(loanSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_StatusNull_BothObjects() {
        LoanSummary summary1 = new LoanSummary("L123", "C123", "John Doe", null, 100000.0);
        LoanSummary summary2 = new LoanSummary("L123", "C123", "John Doe", null, 100000.0);
        assertTrue(summary1.equals(summary2));
    }

    @Test
    void testEquals_StatusNull_OneObject() {
        LoanSummary summary1 = new LoanSummary("L123", "C123", "John Doe", null, 100000.0);
        LoanSummary summary2 = new LoanSummary("L123", "C123", "John Doe", "1", 100000.0);
        assertFalse(summary1.equals(summary2));
    }

    @Test
    void testEquals_DifferentPrincipal() {
        LoanSummary anotherSummary = new LoanSummary("L123", "C123", "John Doe", "1", 200000.0);
        assertFalse(loanSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_SamePrincipal() {
        LoanSummary anotherSummary = new LoanSummary("L123", "C123", "John Doe", "1", 100000.0);
        assertTrue(loanSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_NullPrincipal() {
        LoanSummary anotherSummary = new LoanSummary("L123", "C123", "John Doe", "1", null);
        assertFalse(loanSummary.equals(anotherSummary));
    }

    @Test
    void testEquals_PrincipalNull_BothObjects() {
        LoanSummary summary1 = new LoanSummary("L123", "C123", "John Doe", "1", null);
        LoanSummary summary2 = new LoanSummary("L123", "C123", "John Doe", "1", null);
        assertTrue(summary1.equals(summary2));
    }

    @Test
    void testEquals_PrincipalNull_OneObject() {
        LoanSummary summary1 = new LoanSummary("L123", "C123", "John Doe", "1", null);
        LoanSummary summary2 = new LoanSummary("L123", "C123", "John Doe", "1", 100000.0);
        assertFalse(summary1.equals(summary2));
    }

    @Test
    void testEquals_NullObject() {
        assertFalse(loanSummary.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        assertFalse(loanSummary.equals("Some String"));
    }

    @Test
    void testHashCode_SameValues() {
        LoanSummary anotherSummary = new LoanSummary("L123", "C123", "John Doe", "1", 100000.0);
        assertEquals(loanSummary.hashCode(), anotherSummary.hashCode());
    }

    @Test
    void testHashCode_DifferentValues() {
        LoanSummary anotherSummary = new LoanSummary("L456", "C123", "John Doe", "1", 100000.0);
        assertNotEquals(loanSummary.hashCode(), anotherSummary.hashCode());
    }

    @Test
    void testHashCode_AllFieldsNonNull() {
        int expectedHash = "L123".hashCode();
        expectedHash = 31 * expectedHash + "C123".hashCode();
        expectedHash = 31 * expectedHash + "John Doe".hashCode();
        expectedHash = 31 * expectedHash + "1".hashCode();
        expectedHash = 31 * expectedHash + Double.hashCode(100000.0);

        assertEquals(expectedHash, loanSummary.hashCode());
    }

    @Test
    void testHashCode_LoanIDNull() {
        loanSummary = new LoanSummary(null, "C123", "John Doe", "1", 100000.0);
        int expectedHash = 0;
        expectedHash = 31 * expectedHash + "C123".hashCode();
        expectedHash = 31 * expectedHash + "John Doe".hashCode();
        expectedHash = 31 * expectedHash + "1".hashCode();
        expectedHash = 31 * expectedHash + Double.hashCode(100000.0);

        assertEquals(expectedHash, loanSummary.hashCode());
    }

    @Test
    void testHashCode_CustomerIDNull() {
        loanSummary = new LoanSummary("L123", null, "John Doe", "1", 100000.0);
        int expectedHash = "L123".hashCode();
        expectedHash = 31 * expectedHash + 0;
        expectedHash = 31 * expectedHash + "John Doe".hashCode();
        expectedHash = 31 * expectedHash + "1".hashCode();
        expectedHash = 31 * expectedHash + Double.hashCode(100000.0);

        assertEquals(expectedHash, loanSummary.hashCode());
    }

    @Test
    void testHashCode_CustomerNameNull() {
        loanSummary = new LoanSummary("L123", "C123", null, "1", 100000.0);
        int expectedHash = "L123".hashCode();
        expectedHash = 31 * expectedHash + "C123".hashCode();
        expectedHash = 31 * expectedHash + 0;
        expectedHash = 31 * expectedHash + "1".hashCode();
        expectedHash = 31 * expectedHash + Double.hashCode(100000.0);

        assertEquals(expectedHash, loanSummary.hashCode());
    }

    @Test
    void testHashCode_StatusNull() {
        loanSummary = new LoanSummary("L123", "C123", "John Doe", null, 100000.0);
        int expectedHash = "L123".hashCode();
        expectedHash = 31 * expectedHash + "C123".hashCode();
        expectedHash = 31 * expectedHash + "John Doe".hashCode();
        expectedHash = 31 * expectedHash + 0;
        expectedHash = 31 * expectedHash + Double.hashCode(100000.0);

        assertEquals(expectedHash, loanSummary.hashCode());
    }

    @Test
    void testHashCode_PrincipalNull() {
        loanSummary = new LoanSummary("L123", "C123", "John Doe", "1", null);
        int expectedHash = "L123".hashCode();
        expectedHash = 31 * expectedHash + "C123".hashCode();
        expectedHash = 31 * expectedHash + "John Doe".hashCode();
        expectedHash = 31 * expectedHash + "1".hashCode();
        expectedHash = 31 * expectedHash + 0;

        assertEquals(expectedHash, loanSummary.hashCode());
    }

    @Test
    void testHashCode_AllFieldsNull() {
        loanSummary = new LoanSummary(null, null, null, null, null);
        int expectedHash = 0;

        assertEquals(expectedHash, loanSummary.hashCode());
    }
}
