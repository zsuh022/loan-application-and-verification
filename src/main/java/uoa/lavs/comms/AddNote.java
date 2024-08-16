package uoa.lavs.comms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerNote;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerPhoneNumber;
import uoa.lavs.models.CustomerEmail;
import uoa.lavs.models.CustomerNote;
import uoa.lavs.models.CustomerPhone;

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
        newValue.setNumber(null);

        String[] lines = value.getNote().split("\n");
        for (int i = 0; i < lines.length && i < 20; i++) {
            newValue.setLine(i, lines[i]);
        }

        return processRequest(conn, newValue, value, status -> {
            logger.info(
                    "New Note created: ID = {}, Transaction ID = {}",
                    customerID,
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

}
