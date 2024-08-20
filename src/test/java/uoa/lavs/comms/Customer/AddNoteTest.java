package uoa.lavs.comms.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.comms.AbstractCustomerTest;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.MockConnection;
import uoa.lavs.mainframe.Response;
import uoa.lavs.mainframe.Status;
import uoa.lavs.models.Customer.CustomerNote;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class AddNoteTest extends AbstractCustomerTest<CustomerNote> {

    private final AddNote addNote = new AddNote();
    private final SearchNote searchNote = new SearchNote();

    private final CustomerNote note1 = new CustomerNote();

    @Override
    @BeforeEach
    protected void setup() throws IOException {
        super.setup();

        note1.setNote(
                "First note content1. " +
                        "First note content 2. " +
                        "First note content 3. " +
                        "First note content 4. " +
                        "First note content 5. " +
                        "First note content 6. " +
                        "First note content 7. " +
                        "First note content 8. " +
                        "First note content 9. " +
                        "First note content 10. " +
                        "First note content 12. " +
                        "First note content 13. " +
                        "First note content 14. " +
                        "First note content 15. " +
                        "First note content 16. " +
                        "First note content 17. " +
                        "First note content 18. " +
                        "First note content 19. " +
                        "First note content 20."
        );

        customer.setNote(note1);
    }

    @Test
    void testAddNoteSuccess() {
        String customerId = addCustomer.add(conn, customer);
        addNote.add(conn, note1, customerId);


        CustomerNote dbNote = searchNote.findById(conn, customerId);

        CustomerNote expectedNote = customer.getNote();

        boolean matchFound = false;

        if (dbNote == null) return;

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

        String customerId = addNote.add(mockConnection, note1, customer.getId());

        CustomerNote notes = searchNote.findById(mockConnection, customerId);
        assertNull(notes);
    }
}
