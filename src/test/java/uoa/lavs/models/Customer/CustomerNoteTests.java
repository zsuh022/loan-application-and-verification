package uoa.lavs.models.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerNoteTests {

    private CustomerNote customerNote;

    @BeforeEach
    void setUp() {
        customerNote = new CustomerNote();
    }

    @Test
    void testSetAndGetNote() {
        customerNote.setNote("This is a note.");
        assertEquals("This is a note.", customerNote.getNote());
    }

    @Test
    void testSetAndGetNumber() {
        customerNote.setNumber(1);
        assertEquals(1, customerNote.getNumber());
    }

    @Test
    void testAddNote() {
        customerNote.setNote("First part of the note.");
        customerNote.addNote(" Second part of the note.");
        assertEquals("First part of the note. Second part of the note.", customerNote.getNote());
    }

    @Test
    void testEquals_SameObject() {
        assertTrue(customerNote.equals(customerNote));
    }

    @Test
    void testEquals_DifferentObject_SameNote() {
        CustomerNote anotherNote = new CustomerNote();
        anotherNote.setNote("This is a note.");

        customerNote.setNote("This is a note.");

        assertTrue(customerNote.equals(anotherNote));
    }

    @Test
    void testEquals_DifferentObject_DifferentNote() {
        CustomerNote anotherNote = new CustomerNote();
        anotherNote.setNote("This is a different note.");

        customerNote.setNote("This is a note.");

        assertFalse(customerNote.equals(anotherNote));
    }

    @Test
    void testEquals_NullObject() {
        assertFalse(customerNote.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        assertFalse(customerNote.equals("Some String"));
    }
}
