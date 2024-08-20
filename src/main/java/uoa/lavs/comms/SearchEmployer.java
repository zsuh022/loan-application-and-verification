package uoa.lavs.comms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.LoadCustomerEmployer;
import uoa.lavs.mainframe.messages.customer.LoadCustomerEmployers;
import uoa.lavs.models.CustomerEmployer;

import java.util.ArrayList;
import java.util.List;

public class SearchEmployer extends AbstractSearchable<CustomerEmployer> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(SearchEmployer.class);

    @Override
    public CustomerEmployer findById(Connection conn, String customerId, int index, int number) {
        LoadCustomerEmployer employer = new LoadCustomerEmployer();
        employer.setCustomerId(customerId);
        employer.setNumber(index);

        return processRequest(conn, employer, status -> {
            CustomerEmployer cusEmployer = new CustomerEmployer();
            cusEmployer.setIndex(number);
            cusEmployer.setName(employer.getNameFromServer());
            cusEmployer.setLine1(employer.getLine1FromServer());
            cusEmployer.setLine2(employer.getLine2FromServer());
            cusEmployer.setSuburb(employer.getSuburbFromServer());
            cusEmployer.setCity(employer.getCityFromServer());
            cusEmployer.setPostCode(employer.getPostCodeFromServer());
            cusEmployer.setCountry(employer.getCountryFromServer());
            cusEmployer.setPhone(employer.getPhoneNumberFromServer());
            cusEmployer.setEmail(employer.getEmailAddressFromServer());
            cusEmployer.setWeb(employer.getWebsiteFromServer());
            cusEmployer.setIsOwner(employer.getIsOwnerFromServer());
            return cusEmployer;
        }, status -> {
            return new CustomerEmployer();
        });
    }

    @Override
    public List<CustomerEmployer> findAll(Connection conn, String customerId) {
        LoadCustomerEmployers employers = new LoadCustomerEmployers();
        employers.setCustomerId(customerId);
        return processRequest(conn, employers, status -> {
            List<CustomerEmployer> list = new ArrayList<>();
            // Eager loading all the employers when customer is first loaded
            for (int i = 1; i < employers.getCountFromServer() + 1; i++) {
                CustomerEmployer employer = findById(conn, customerId, i, employers.getNumberFromServer(i));
                list.add(employer);
                if (employer.getName() != null) logger.info("Employer: {}, successfully loaded", employer.getName());

            }
            return list;
        }, status -> {
            return new ArrayList<>();
        });
    }

}
