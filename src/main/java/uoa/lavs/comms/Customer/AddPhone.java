package uoa.lavs.comms.Customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractWriter;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerPhoneNumber;
import uoa.lavs.models.Customer.CustomerPhone;

import java.util.HashMap;
import java.util.Map;

public class AddPhone extends AbstractWriter<CustomerPhone> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(AddPhone.class);

    // Returns ID for customer
    // 0 if failed
    @Override
    public String add(Connection conn, CustomerPhone value, String customerID) {
        UpdateCustomerPhoneNumber newValue = new UpdateCustomerPhoneNumber();
        newValue.setCustomerId(customerID);
        newValue.setNumber(value.getIndex());
        newValue.setPhoneNumber(value.getNumber());
        newValue.setType(value.getType());
        newValue.setPrefix(value.getPrefix());
        newValue.setIsPrimary(value.getIsPrimary());
        newValue.setCanSendTxt(value.getIsTexting());

        return processRequest(conn, newValue, value, status -> {
            logger.info(
                    "New Phone created: Number = {}, ID = {}, Transaction ID = {}",
                    newValue.getPhoneNumberFromServer(),
                    customerID,
                    status.getTransactionId());
            // Return new customer ID
            return customerID;
        }, status -> {
            mainframeError(status.getErrorCode(), status.getErrorMessage(), customerID, value);
            return "0";
        }, 1203, "Customer Phone", customerID);
    }

    @Override
    protected Map<String, String> extractLogProperties(CustomerPhone value, String customerID) {
        Map<String, String> properties = new HashMap<>();
        properties.put("phoneNumber", value.getNumber());
        properties.put("type", value.getType());
        properties.put("prefix", value.getPrefix());
        int flags = 0;
        flags &= 253;
        if (value.getIsPrimary()) flags |= 1;
        flags &= 254;
        if (value.getIsTexting()) flags |= 2;
        properties.put("flags", Integer.toString(flags));
        properties.put("id", customerID);
        return properties;
    }

}
