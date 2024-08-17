package uoa.lavs.comms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerEmployer;
import uoa.lavs.models.CustomerEmployerDTO;

import java.util.HashMap;
import java.util.Map;

public class ChangeEmployer extends AbstractWriter<CustomerEmployerDTO> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(ChangeEmployer.class);

    @Override
    protected String add(Connection conn, CustomerEmployerDTO entity, String customerID) {
        UpdateCustomerEmployer value = new UpdateCustomerEmployer();

        if (entity.getName() != null) {
            value.setName(entity.getName());
        }
        value.setNumber(entity.getNumber());
        if (entity.getLine1() != null) {
            value.setLine1(entity.getLine1());
        }
        if (entity.getLine2() != null) {
            value.setLine2(entity.getLine2());
        }
        if (entity.getSuburb() != null) {
            value.setSuburb(entity.getSuburb());
        }
        if (entity.getCity() != null) {
            value.setCity(entity.getCity());
        }
        if (entity.getPostCode() != null) {
            value.setPostCode(entity.getPostCode());
        }
        if (entity.getCountry() != null) {
            value.setCountry(entity.getCountry());
        }
        if (entity.getPhone() != null) {
            value.setPhoneNumber(entity.getPhone());
        }
        if (entity.getEmail() != null) {
            value.setEmailAddress(entity.getEmail());
        }
        if (entity.getWeb() != null) {
            value.setWebsite(entity.getWeb());
        }
        if (entity.getIsOwner() != null) {
            value.setIsOwner(entity.getIsOwner());
        }
        value.setNumber(entity.getNumber());

        return processRequest(conn, value, entity, status -> {
            logger.info(
                    "Employer updated: Name = {}, ID = {}, Transaction ID = {}",
                    value.getNameFromServer(),
                    customerID,
                    status.getTransactionId());
            // Return customer ID
            return customerID;
        }, status -> {
            mainframeError(status.getErrorCode(), status.getErrorMessage());
            return "0";
        }, 1202, "Customer Employer", customerID);
    }


    @Override
    protected Map<String, String> extractLogProperties(CustomerEmployerDTO value, String customerID) {
        Map<String, String> properties = new HashMap<>();
        properties.put("number", String.valueOf(value.getName()));
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
