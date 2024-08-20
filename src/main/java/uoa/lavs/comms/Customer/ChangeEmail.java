package uoa.lavs.comms.Customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractWriter;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerEmail;
import uoa.lavs.models.Customer.CustomerEmailDTO;

import java.util.HashMap;
import java.util.Map;

public class ChangeEmail extends AbstractWriter<CustomerEmailDTO> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(ChangeEmail.class);

    @Override
    protected String add(Connection conn, CustomerEmailDTO entity, String customerID) {
        UpdateCustomerEmail newEmail = new UpdateCustomerEmail();
        newEmail.setCustomerId(customerID);

        if (entity.getAddress() != null) {
            newEmail.setAddress(entity.getAddress());
        }
        newEmail.setNumber(entity.getNumber());
        if (entity.getPrimary() != null) {
            newEmail.setIsPrimary(entity.getPrimary());
        }

        return processRequest(conn, newEmail, entity, status -> {
            logger.info(
                    "Email updated: Address = {}, ID = {}, Transaction ID = {}",
                    newEmail.getAddressFromServer(),
                    customerID,
                    status.getTransactionId());
            // Return new customer ID
            return customerID;
        }, status -> {
            mainframeError(status.getErrorCode(), status.getErrorMessage(), customerID, entity);
            return "0";
        }, 1203, "Customer Email", customerID);
    }

    @Override
    protected Map<String, String> extractLogProperties(CustomerEmailDTO email, String customerID) {
        Map<String, String> properties = new HashMap<>();
        properties.put("id", customerID);
        properties.put("number", String.valueOf(email.getNumber()));
        if (email.getAddress() != null) {
            properties.put("address", email.getAddress());
        }
        if (email.getPrimary() != null) {
            properties.put("isPrimary", email.getPrimary().toString());
        }

        return properties;
    }
}
