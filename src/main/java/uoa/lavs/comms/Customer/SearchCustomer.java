package uoa.lavs.comms.Customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractSearchable;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.LoadCustomer;
import uoa.lavs.models.Customer.Customer;

import java.util.List;

public class SearchCustomer extends AbstractSearchable<Customer> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(SearchCustomer.class);

    @Override
    public Customer findById(Connection conn, String customerId) {
        LoadCustomer customer = new LoadCustomer();
        customer.setCustomerId(customerId);

        return processRequest(conn, customer, status -> {
            Customer newCustomer = new Customer();
            newCustomer.setTitle(customer.getTitleFromServer());
            newCustomer.setName(customer.getNameFromServer());
            newCustomer.setDateOfBirth(customer.getDateofBirthFromServer());
            newCustomer.setOccupation(customer.getOccupationFromServer());
            newCustomer.setCitizenship(customer.getCitizenshipFromServer());
            newCustomer.setVisa(customer.getVisaFromServer());
            logger.info("Customer: {}, successfully loaded", customer.getNameFromServer());
            return newCustomer;
        }, status -> {
            return new Customer();
        });
    }

    @Override
    public List<Customer> findAll(Connection conn, String customerId) {
        throw new UnsupportedOperationException("findAll is not supported for searching customers.");
    }
}
