package uoa.lavs.comms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.LoadCustomerNote;
import uoa.lavs.models.CustomerNote;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
        initialNote.send(conn);

        int totalPages = initialNote.getPageCountFromServer();
        System.out.println("TOTAL PAGES " + totalPages);

        for (int pageIndex = 1; pageIndex <= totalPages; pageIndex++) {

            LoadCustomerNote loadNotes = new LoadCustomerNote();
            loadNotes.setCustomerId(customerId);
            loadNotes.setNumber(pageIndex);
            loadNotes.send(conn);

            int totalLines = loadNotes.getLineCountFromServer();
            System.out.println("Total lines for page " + pageIndex + ": " + totalLines);

            int numQueries = (int) Math.ceil((double) totalLines / 5);

            for (int queryIndex = 0; queryIndex < numQueries; queryIndex++) {
                // We continue to fetch the lines for the current pageIndex
                loadNotes.send(conn);

                int linesFetched = loadNotes.getLineCountFromServer();

                for (int j = 0; j < linesFetched; j++) {
                    String content = loadNotes.getLineFromServer(j + 1);
                    if (content != null) {
                        fullNote.addNote(content);
                    }
                }

                if (linesFetched < 5) {
                    break;
                }
            }
        }

        return fullNote;
    }


    @Override
    public List<CustomerNote> findAll(Connection conn, String customerId) {
        throw new UnsupportedOperationException("findAll is not supported for searching notes.");
    }


}
