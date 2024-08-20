package uoa.lavs.comms.Customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractWriter;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerAddress;
import uoa.lavs.models.Customer.CustomerAddress;

import java.util.HashMap;
import java.util.Map;

public class AddAddress extends AbstractWriter<CustomerAddress> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(AddAddress.class);

    // Returns ID for customer
    // 0 if failed
    @Override
    public String add(Connection conn, CustomerAddress addy, String customerID) {
        UpdateCustomerAddress newAddy = new UpdateCustomerAddress();
        newAddy.setNumber(addy.getIndex());
        newAddy.setCustomerId(customerID);
        newAddy.setType(addy.getType());
        newAddy.setLine1(addy.getLine1());
        newAddy.setLine2(addy.getLine2());
        newAddy.setSuburb(addy.getSuburb());
        newAddy.setCity(addy.getCity());
        newAddy.setPostCode(String.valueOf(addy.getPostCode()));
        newAddy.setCountry(addy.getCountry());
        newAddy.setIsPrimary(addy.getIsPrimary());
        newAddy.setIsMailing(addy.getIsMailing());


        return processRequest(conn, newAddy, addy, status -> {
            logger.info(
                    "New Address created: Line1 = {}, ID = {}, Transaction ID = {}",
                    newAddy.getLine1FromServer(),
                    customerID,
                    status.getTransactionId());
            // Return new customer ID
            return customerID;
        }, status -> {
            mainframeError(status.getErrorCode(), status.getErrorMessage(), customerID, addy);
            return "0";
        }, 1202, "Customer Address", customerID);
    }

    @Override
    protected Map<String, String> extractLogProperties(CustomerAddress addy, String customerID) {
        Map<String, String> properties = new HashMap<>();
        properties.put("type", addy.getType());
        properties.put("line1", addy.getLine1());
        properties.put("line2", addy.getLine2());
        properties.put("suburb", addy.getSuburb());
        properties.put("city", addy.getCity());
        properties.put("postCode", String.valueOf(addy.getPostCode()));
        properties.put("country", addy.getCountry());
        properties.put("isPrimary", addy.getIsPrimary().toString());
        properties.put("isMailing", addy.getIsMailing().toString());
        properties.put("id", customerID);
        return properties;
    }


}
