package uoa.lavs.comms.Customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractSearchable;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.LoadCustomerNote;
import uoa.lavs.models.Customer.CustomerNote;

import java.util.List;

public class SearchNote extends AbstractSearchable<CustomerNote> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(SearchNote.class);

    @Override
    public CustomerNote findById(Connection conn, String customerId) {
        CustomerNote fullNote = new CustomerNote();
        fullNote.setNote("");

        LoadCustomerNote initialNote = new LoadCustomerNote();
        initialNote.setCustomerId(customerId);
        initialNote.setNumber(1);

        return processRequest(conn, initialNote, status -> {
            int totalPages = initialNote.getPageCountFromServer();

            for (int pageIndex = 1; pageIndex <= totalPages; pageIndex++) {
                LoadCustomerNote loadNotes = new LoadCustomerNote();
                loadNotes.setCustomerId(customerId);
                loadNotes.setNumber(pageIndex);
                loadNotes.send(conn);

                int totalLines = loadNotes.getLineCountFromServer();

                for (int lineIndex = 1; lineIndex <= totalLines; lineIndex++) {
                    String content = loadNotes.getLineFromServer(lineIndex);
                    fullNote.addNote(content);

                }
            }
            return fullNote;
        }, status -> {
            return null;
        });
    }

    @Override
    public List<CustomerNote> findAll(Connection conn, String customerId) {
        throw new UnsupportedOperationException("findAll is not supported for searching notes.");
    }
}
