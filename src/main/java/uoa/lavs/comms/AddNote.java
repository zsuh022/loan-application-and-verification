package uoa.lavs.comms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.UpdateCustomer;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerNote;
import uoa.lavs.models.CustomerNote;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AddNote extends AbstractWriter<CustomerNote> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(AddNote.class);

    // Returns ID for customer
    // 0 if failed
    @Override
    public String add(Connection conn, CustomerNote value, String customerID) {
        UpdateCustomerNote newValue = new UpdateCustomerNote();
        newValue.setCustomerId(customerID);

        splitSetLines(value.getNote(), newValue);

        return processRequest(conn, newValue, value, status -> {
            logger.info(
                    "New Note created: Value = {}, Transaction ID = {}",
                    value.getNote(),
                    status.getTransactionId());
            // Return new customer ID
            return customerID;
        }, status -> {
            mainframeError(status.getErrorCode(), status.getErrorMessage());
            return "0";
        }, 1206, "Customer Note", customerID);
    }

    @Override
    protected Map<String, String> extractLogProperties(CustomerNote value, String customerID) {
        Map<String, String> properties = new HashMap<>();
        properties.put("note", value.getNote());
        properties.put("id", customerID);
        return properties;
    }

    private void splitSetLines(String value, UpdateCustomerNote update) {
        int maxLineLength = 70;
        int linesPerPage = 19;
        int totalLines = (int) Math.ceil((double) value.length() / maxLineLength);

        int totalPages = (int) Math.ceil((double) totalLines / linesPerPage);

        for (int page = 1; page <= totalPages; page++) {
            update.setNumber(null);

            for (int i = 0; i < linesPerPage; i++) {
                int lineIndex = (page - 1) * linesPerPage + i;
                if (lineIndex >= totalLines) break;

                int start = lineIndex * maxLineLength;
                int end = Math.min(start + maxLineLength, value.length());
                String lineContent = value.substring(start, end);

                update.setLine(i + 1, lineContent);
            }
        }
    }
}
