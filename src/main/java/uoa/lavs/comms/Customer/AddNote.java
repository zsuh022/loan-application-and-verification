package uoa.lavs.comms.Customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractWriter;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerNote;
import uoa.lavs.models.Customer.CustomerNote;

import java.util.*;

public class AddNote extends AbstractWriter<CustomerNote> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(AddNote.class);

    @Override
    public String add(Connection conn, CustomerNote value, String customerID) {
        List<String> noteLines = splitIntoLines(value.getNote());

        int lineCount = noteLines.size();

        for (int i = 0; i < lineCount; i += 5) {
            UpdateCustomerNote newValue = new UpdateCustomerNote();
            newValue.setCustomerId(customerID);
            newValue.setNumber(null);

            List<String> chunk = noteLines.subList(i, Math.min(i + 5, lineCount));
            assignLinesToNoteChunk(newValue, chunk);


            processRequest(conn, newValue, value, status -> {
                logger.info(
                        "New Note created: Number = {}, Transaction ID = {}",
                        newValue.getNumberFromServer(),
                        status.getTransactionId());
                return customerID;
            }, status -> {
                return null;
            }, 1206, "Customer Note", customerID);
        }

        return customerID;
    }

    @Override
    protected Map<String, String> extractLogProperties(CustomerNote value, String customerID) {
        Map<String, String> properties = new HashMap<>();
        properties.put("note", value.getNote());
        properties.put("id", customerID);
        return properties;
    }

    private List<String> splitIntoLines(String note) {
        List<String> lines = new ArrayList<>();
        int length = note.length();
        int start = 0;

        while (start < length) {
            int end = Math.min(start + 70, length);
            String line;
            if (end < length) {
                int lastSpace = note.lastIndexOf(' ', end);
                if (note.charAt(end) == ' ') {
                    line = note.substring(start, end);
                    start = end;
                } else if (lastSpace > start) {
                    line = note.substring(start, lastSpace + 1);
                    start = lastSpace + 1;
                } else {
                    line = note.substring(start, end);
                    start = end;
                }
            } else {
                line = note.substring(start, end);
                start = end;
            }
            lines.add(line);
        }
        return lines;
    }

    private void assignLinesToNoteChunk(UpdateCustomerNote newValue, List<String> noteChunk) {
        for (int i = 0; i < noteChunk.size(); i++) {
            newValue.setLine(i + 1, noteChunk.get(i));
        }
    }
}
