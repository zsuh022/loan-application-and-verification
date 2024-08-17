package uoa.lavs.comms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerEmployer;
import uoa.lavs.models.CustomerEmployer;

import java.util.HashMap;
import java.util.Map;

public class AddEmployer extends AbstractWriter<CustomerEmployer> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(AddEmployer.class);

    // Returns ID for customer
    // 0 if failed
    @Override
    public String add(Connection conn, CustomerEmployer value, String customerID) {
        UpdateCustomerEmployer newValue = new UpdateCustomerEmployer();
        newValue.setCustomerId(customerID);
        newValue.setNumber(null);
        newValue.setName(value.getName());
        newValue.setLine1(value.getLine1());
        newValue.setLine2(value.getLine2());
        newValue.setSuburb(value.getSuburb());
        newValue.setCity(value.getCity());
        newValue.setPostCode(value.getPostCode());
        newValue.setCountry(value.getCountry());
        newValue.setPhoneNumber(value.getPhone());
        newValue.setEmailAddress(value.getEmail());
        newValue.setWebsite(value.getWeb());
        newValue.setIsOwner(value.getIsOwner());


        return processRequest(conn, newValue, value, status -> {
            logger.info(
                    "New Employer created: Name = {}, ID = {}, Transaction ID = {}",
                    newValue.getNameFromServer(),
                    customerID,
                    status.getTransactionId());
            // Return new customer ID
            return customerID;
        }, status -> {
            mainframeError(status.getErrorCode(), status.getErrorMessage());
            return "0";
        }, 1205, "Customer Email", customerID);
    }

    @Override
    protected Map<String, String> extractLogProperties(CustomerEmployer value, String customerID) {
        Map<String, String> properties = new HashMap<>();
        properties.put("name", value.getName());
        properties.put("line1", value.getLine1());
        properties.put("line2", value.getLine2());
        properties.put("suburb", value.getSuburb());
        properties.put("city", value.getCity());
        properties.put("postCode", value.getPostCode());
        properties.put("country", value.getCountry());
        properties.put("phone", value.getPhone());
        properties.put("email", value.getEmail());
        properties.put("web", value.getWeb());
        properties.put("isOwner", value.getIsOwner().toString());
        properties.put("id", customerID);
        return properties;
    }
}
