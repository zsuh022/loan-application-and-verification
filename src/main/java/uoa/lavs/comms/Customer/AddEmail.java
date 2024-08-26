package uoa.lavs.comms.Customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractWriter;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerEmail;
import uoa.lavs.models.Customer.CustomerEmail;

import java.util.HashMap;
import java.util.Map;

public class AddEmail extends AbstractWriter<CustomerEmail> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(AddEmail.class);

    // Returns ID for customer
    // 0 if failed
    @Override
    public String add(Connection conn, CustomerEmail value, String customerID) {
        UpdateCustomerEmail newValue = new UpdateCustomerEmail();
        newValue.setNumber(value.getIndex());
        newValue.setCustomerId(customerID);
        newValue.setAddress(value.getAddress());
        newValue.setIsPrimary(value.getIsPrimary());


        return processRequest(conn, newValue, value, status -> {
            logger.info(
                    "New Email created: Address = {}, ID = {}, Transaction ID = {}",
                    newValue.getAddressFromServer(),
                    customerID,
                    status.getTransactionId());
            // Return new customer ID
            return customerID;
        }, status -> {
            mainframeError(status.getErrorCode(), status.getErrorMessage(), customerID, value);
            return "0";
        }, 1204, "Customer Email", customerID);
    }

    @Override
    protected Map<String, String> extractLogProperties(CustomerEmail value, String customerID) {
        Map<String, String> properties = new HashMap<>();
        properties.put("address", value.getAddress());
        int flags = 0;
        flags &= 254;
        if (value.getIsPrimary()) flags |= 1;
        properties.put("flags", Integer.toString(flags));
        properties.put("id", customerID);
        return properties;
    }
}
