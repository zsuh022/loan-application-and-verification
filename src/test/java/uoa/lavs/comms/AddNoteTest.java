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
    private final CustomerNote note2 = new CustomerNote();
    private final CustomerNote note3 = new CustomerNote();
    private final CustomerNote note4 = new CustomerNote();
    private final CustomerNote note5 = new CustomerNote();

    @Override
    @BeforeEach
    void setup() {
        super.setup();

        note1.addLine("First note content.");
        note2.addLine("Second note content.");
        note3.addLine("Third note content.");
        note4.addLine("Fourth note content.");
        note5.addLine("Fifth note content.");

        customer.addNote(note1);
        customer.addNote(note2);
        customer.addNote(note3);
        customer.addNote(note4);
        customer.addNote(note5);
    }

    @Test
    void testAddNoteSuccess() {
        String customerId = addCustomer.add(conn, customer);

        for (CustomerNote note : customer.getNoteList()) {
            addNote.add(conn, note, customerId);
        }

        List<CustomerNote> notesFromDb = searchNote.findAll(conn, customerId);

        List<CustomerNote> expectedNotes = customer.getNoteList();

        for (CustomerNote expectedNote : expectedNotes) {
            boolean matchFound = false;

            for (CustomerNote dbNote : notesFromDb) {
                if (expectedNote.getNote().equals(dbNote.getNote())) {
                    assertDetails(expectedNote, dbNote);
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {
                fail("Unexpected note found: " + expectedNote.getNote());
            }
        }

        for (CustomerNote dbNote : notesFromDb) {
            boolean matchFound = false;

            for (CustomerNote expectedNote : expectedNotes) {
                if (dbNote.getNote().equals(expectedNote.getNote())) {
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {
                fail("Expected note not found: " + dbNote.getNote());
            }
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

        String customerId = addCustomer.add(mockConnection, customer);

        for (CustomerNote note : customer.getNoteList()) {
            addNote.add(mockConnection, note, customerId);
        }

        List<CustomerNote> notes = searchNote.findAll(mockConnection, customerId);
        assertEquals(0, notes.size());
    }
}
