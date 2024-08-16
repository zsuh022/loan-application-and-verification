package uoa.lavs.comms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.MockConnection;
import uoa.lavs.mainframe.Response;
import uoa.lavs.mainframe.Status;
import uoa.lavs.models.CustomerNote;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddNoteTest extends AbstractCustomerTest<CustomerNote> {

    private final AddNote addNote = new AddNote();
    private final SearchNote searchNote = new SearchNote();

    private final CustomerNote note1 = new CustomerNote();

    @Override
    @BeforeEach
    void setup() {
        super.setup();

        note1.setNote("First note content.First note content.First note content.First note content.First note content.First note content.First note content.First note content.First note content.First note content.First note content.First note content.First note content.First note content.First note content.First note content.First note content.First note content.First note content.First note content.");

        customer.addNote(note1);
    }

    @Test
    void testAddNoteSuccess() {
        String customerId = addCustomer.add(conn, customer);
        addNote.add(conn, note1, customerId);


        CustomerNote dbNote = searchNote.findById(conn, customerId);

        CustomerNote expectedNote = customer.getNote();

        boolean matchFound = false;

        System.out.println("EXPECTED\n " + expectedNote.getNote() + "\nACTUAL\n " + dbNote.getNote());
        if (expectedNote.getNote().equals(dbNote.getNote())) {
            assertDetails(expectedNote, dbNote);
            matchFound = true;
        }
        if (!matchFound) {
            fail("Unexpected note found: " + expectedNote.getNote());
        }

    }

    @Override
    protected void assertDetails(CustomerNote expected, CustomerNote actual) {
        assertEquals(expected.getNote(), actual.getNote());
    }

    @Test
    void testAddNoteFailure() {
        Status errorStatus = new Status(404, "Some problem", 123456);
        Response errorResponse = new Response(errorStatus, new HashMap<>());

        Connection mockConnection = new MockConnection(errorResponse);

        String customerId = addNote.add(mockConnection, note1);

        CustomerNote notes = searchNote.findById(mockConnection, customerId);
        assertNull(notes);
    }
}
