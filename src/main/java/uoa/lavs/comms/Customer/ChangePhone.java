package uoa.lavs.comms.Customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractWriter;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerPhoneNumber;
import uoa.lavs.models.Customer.CustomerPhoneDTO;

import java.util.HashMap;
import java.util.Map;

public class ChangePhone extends AbstractWriter<CustomerPhoneDTO> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(ChangePhone.class);

    @Override
    protected String add(Connection conn, CustomerPhoneDTO entity, String customerID) {
        UpdateCustomerPhoneNumber newPhone = new UpdateCustomerPhoneNumber();
        newPhone.setCustomerId(customerID);

        if (entity.getType() != null) {
            newPhone.setType(entity.getType());
        }
        newPhone.setPrefix(entity.getPrefix());
        if (entity.getNumber() != null) {
            newPhone.setPhoneNumber(entity.getNumber());
        }
        if (entity.getPrimary() != null) {
            newPhone.setIsPrimary(entity.getPrimary());
        }
        if (entity.getTexting() != null) {
            newPhone.setCanSendTxt(entity.getTexting());
        }
        newPhone.setNumber(entity.getIndex());

        return processRequest(conn, newPhone, entity, status -> {
            logger.info(
                    "Phone updated: Number = {}, ID = {}, Transaction ID = {}",
                    newPhone.getNumberFromServer(),
                    customerID,
                    status.getTransactionId());
            // Return new customer ID
            return customerID;
        }, status -> {
            mainframeError(status.getErrorCode(), status.getErrorMessage(), customerID, entity);
            return "0";
        }, 1204, "Customer Phone", customerID);
    }

    @Override
    protected Map<String, String> extractLogProperties(CustomerPhoneDTO phone, String customerID) {
        Map<String, String> properties = new HashMap<>();
        properties.put("id", customerID);
        properties.put("index", String.valueOf(phone.getIndex()));
        properties.put("type", phone.getType());
        properties.put("prefix", phone.getPrefix());
        properties.put("phone", phone.getNumber());
        properties.put("isPrimary", phone.getPrimary().toString());
        properties.put("isTexting", phone.getTexting().toString());
        return properties;
    }
}
