package uoa.lavs.comms.Customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractWriter;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerAddress;
import uoa.lavs.models.Customer.CustomerAddressDTO;

import java.util.HashMap;
import java.util.Map;

public class ChangeAddress extends AbstractWriter<CustomerAddressDTO> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(ChangeAddress.class);

    @Override
    protected String add(Connection conn, CustomerAddressDTO entity, String customerID) {
        UpdateCustomerAddress newAddy = new UpdateCustomerAddress();
        newAddy.setCustomerId(customerID);

        if (entity.getType() != null) {
            newAddy.setType(entity.getType());
        }
        newAddy.setNumber(entity.getNumber());
        if (entity.getLine1() != null) {
            newAddy.setLine1(entity.getLine1());
        }
        if (entity.getLine2() != null) {
            newAddy.setLine2(entity.getLine2());
        }
        if (entity.getSuburb() != null) {
            newAddy.setSuburb(entity.getSuburb());
        }
        if (entity.getCity() != null) {
            newAddy.setCity(entity.getCity());
        }
        if (entity.getPostCode() != null) {
            newAddy.setPostCode(String.valueOf(entity.getPostCode()));
        }
        if (entity.getCountry() != null) {
            newAddy.setCountry(entity.getCountry());
        }
        if (entity.getPrimary() != null) {
            newAddy.setIsPrimary(entity.getPrimary());
        }
        if (entity.getMailing() != null) {
            newAddy.setIsMailing(entity.getMailing());
        }

        return processRequest(conn, newAddy, entity, status -> {
            logger.info(
                    "Address updated: Line1 = {}, ID = {}, Transaction ID = {}",
                    newAddy.getLine1FromServer(),
                    customerID,
                    status.getTransactionId());
            // Return new customer ID
            return customerID;
        }, status -> {
            mainframeError(status.getErrorCode(), status.getErrorMessage(), customerID, entity);
            return "0";
        }, 1202, "Customer Address", customerID);
    }

    @Override
    protected Map<String, String> extractLogProperties(CustomerAddressDTO addy, String customerID) {
        Map<String, String> properties = new HashMap<>();
        properties.put("id", customerID);
        properties.put("number", String.valueOf(addy.getNumber()));
        if (addy.getType() != null) {
            properties.put("type", addy.getType());
        }
        if (addy.getLine1() != null) {
            properties.put("line1", addy.getLine1());
        }
        if (addy.getLine2() != null) {
            properties.put("line2", addy.getLine2());
        }
        if (addy.getSuburb() != null) {
            properties.put("suburb", addy.getSuburb());
        }
        if (addy.getCity() != null) {
            properties.put("city", addy.getCity());
        }
        if (addy.getPostCode() != null) {
            properties.put("postCode", String.valueOf(addy.getPostCode()));
        }
        if (addy.getCountry() != null) {
            properties.put("country", addy.getCountry());
        }
        if (addy.getPrimary() != null) {
            properties.put("isPrimary", addy.getPrimary().toString());
        }
        if (addy.getMailing() != null) {
            properties.put("isMailing", addy.getMailing().toString());
        }

        return properties;
    }
}
