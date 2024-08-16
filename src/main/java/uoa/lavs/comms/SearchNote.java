package uoa.lavs.comms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.LoadCustomerNote;
import uoa.lavs.models.CustomerNote;

import java.util.ArrayList;
import java.util.List;

public class SearchNote extends AbstractSearchable<CustomerNote> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(SearchNote.class);

    @Override
    public CustomerNote findById(Connection conn, String customerId, int index) {
        LoadCustomerNote note = new LoadCustomerNote();
        note.setCustomerId(customerId);
        note.setNumber(index);

        return processRequest(conn, note, status -> {
            CustomerNote cusNote = new CustomerNote();
            for (int j = 0; j < note.getLineCountFromServer(); j++) {
                cusNote.addLine(note.getLineFromServer(j));
            }
            return cusNote;
        }, status -> {
            return new CustomerNote();
        });
    }

    @Override
    public List<CustomerNote> findAll(Connection conn, String customerId) {
        LoadCustomerNote notes = new LoadCustomerNote();
        notes.setCustomerId(customerId);

        return processRequest(conn, notes, status -> {
            List<CustomerNote> list = new ArrayList<>();
            for (int i = 1; i < notes.getPageCountFromServer(); i++) {
                CustomerNote note = findById(conn, customerId, i);
                list.add(note);
                logger.info("Note: {}, successfully added", note.getNote());
            }
            return list;
        }, status -> {
            return new ArrayList<>();
        });
    }
}
