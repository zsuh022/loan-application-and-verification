package uoa.lavs.comms.Customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractWriter;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.UpdateCustomer;
import uoa.lavs.models.Customer.Customer;

import java.util.HashMap;
import java.util.Map;

public class AddCustomer extends AbstractWriter<Customer> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(AbstractWriter.class);

    // Returns ID for new customer
    // 0 if failed
    @Override
    public String add(Connection conn, Customer customer) {
        UpdateCustomer newCustomer = new UpdateCustomer();
        if (customer.getId().contains(TEMPORARY_CUSTOMER_ID_PREFIX)) {
            newCustomer.setCustomerId(null);
        } else {
            newCustomer.setCustomerId(customer.getId());
        }
        newCustomer.setTitle(customer.getTitle());
        newCustomer.setName(customer.getName());
        newCustomer.setDateofBirth(customer.getDob());
        newCustomer.setOccupation(customer.getOccupation());
        newCustomer.setCitizenship(customer.getCitizenship());
        newCustomer.setVisa(customer.getVisa());

        return processRequest(conn, newCustomer, customer, status -> {
            logger.info(
                    "New customer created: Name = {}, ID = {}, Transaction ID = {}",
                    newCustomer.getNameFromServer(),
                    newCustomer.getCustomerIdFromServer(),
                    status.getTransactionId());
            // Return new customer ID
            return newCustomer.getCustomerIdFromServer();
        }, status -> {
            mainframeError(status.getErrorCode(), status.getErrorMessage(), "0", customer);
            return "0";
        }, 1201, "Customer", null);
    }

    @Override
    protected Map<String, String> extractLogProperties(Customer customer, String customerID) {
        Map<String, String> properties = new HashMap<>();
        properties.put("id", customer.getId());
        properties.put("title", customer.getTitle());
        properties.put("name", customer.getName());
        properties.put("dateOfBirth", customer.getDob().toString());
        properties.put("occupation", customer.getOccupation());
        properties.put("citizenship", customer.getCitizenship());
        properties.put("visa", customer.getVisa());
        return properties;
    }
}
