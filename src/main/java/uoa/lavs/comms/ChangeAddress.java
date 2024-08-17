package uoa.lavs.comms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerAddress;
import uoa.lavs.models.CustomerAddressDTO;

import java.util.HashMap;
import java.util.Map;

public class ChangeAddress extends AbstractWriter<CustomerAddressDTO> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(ChangeAddress.class);

    @Override
    protected String add(Connection conn, CustomerAddressDTO entity, String customerID) {
        UpdateCustomerAddress newAddy = new UpdateCustomerAddress();

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
            mainframeError(status.getErrorCode(), status.getErrorMessage());
            return "0";
        }, 1202, "Customer Address", customerID);
    }

    @Override
    protected Map<String, String> extractLogProperties(CustomerAddressDTO addy, String customerID) {
        Map<String, String> properties = new HashMap<>();
        properties.put("number", String.valueOf(addy.getNumber()));
        properties.put("type", addy.getType());
        properties.put("line1", addy.getLine1());
        properties.put("line2", addy.getLine2());
        properties.put("suburb", addy.getSuburb());
        properties.put("city", addy.getCity());
        properties.put("postCode", String.valueOf(addy.getPostCode()));
        properties.put("country", addy.getCountry());
        properties.put("isPrimary", addy.getPrimary().toString());
        properties.put("isMailing", addy.getMailing().toString());
        properties.put("id", customerID);
        return properties;
    }
}
