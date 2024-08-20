package uoa.lavs.comms.Customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractSearchable;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.LoadCustomerEmail;
import uoa.lavs.mainframe.messages.customer.LoadCustomerEmails;
import uoa.lavs.models.Customer.CustomerEmail;

import java.util.ArrayList;
import java.util.List;

public class SearchEmail extends AbstractSearchable<CustomerEmail> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(SearchEmail.class);

    @Override
    public CustomerEmail findById(Connection conn, String customerId, int index, int number) {
        LoadCustomerEmail email = new LoadCustomerEmail();
        email.setCustomerId(customerId);
        email.setNumber(index);

        return processRequest(conn, email, status -> {
            CustomerEmail cusEmail = new CustomerEmail();
            cusEmail.setIndex(number);
            cusEmail.setAddress(email.getAddressFromServer());
            cusEmail.setIsPrimary(email.getIsPrimaryFromServer());
            return cusEmail;
        }, status -> {
            return new CustomerEmail();
        });
    }

    @Override
    public List<CustomerEmail> findAll(Connection conn, String customerId) {
        LoadCustomerEmails emails = new LoadCustomerEmails();
        emails.setCustomerId(customerId);
        return processRequest(conn, emails, status -> {
            List<CustomerEmail> list = new ArrayList<>();
            // Eager loading all the emails when customer is first loaded
            for (int i = 1; i < emails.getCountFromServer() + 1; i++) {
                CustomerEmail email = findById(conn, customerId, i, emails.getNumberFromServer(i));
                list.add(email);
                if (email.getAddress() != null) logger.info("Email: {}, successfully loaded", email.getAddress());
            }
            return list;
        }, status -> {
            return new ArrayList<>();
        });
    }

}
