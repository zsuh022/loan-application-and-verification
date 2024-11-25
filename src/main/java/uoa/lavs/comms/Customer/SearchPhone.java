package uoa.lavs.comms.Customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.comms.AbstractSearchable;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.LoadCustomerPhoneNumber;
import uoa.lavs.mainframe.messages.customer.LoadCustomerPhoneNumbers;
import uoa.lavs.models.Customer.CustomerPhone;

import java.util.ArrayList;
import java.util.List;

public class SearchPhone extends AbstractSearchable<CustomerPhone> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(SearchPhone.class);

    @Override
    public CustomerPhone findById(Connection conn, String customerId, int index, int number) {
        LoadCustomerPhoneNumber phone = new LoadCustomerPhoneNumber();
        phone.setCustomerId(customerId);
        phone.setNumber(index);

        return processRequest(conn, phone, status -> {
            CustomerPhone cusPhone = new CustomerPhone();
            cusPhone.setIndex(number);
            cusPhone.setType(phone.getTypeFromServer());
            cusPhone.setPrefix(phone.getPrefixFromServer());
            cusPhone.setNumber(phone.getPhoneNumberFromServer());
            cusPhone.setIsPrimary(phone.getIsPrimaryFromServer());
            cusPhone.setIsTexting(phone.getCanSendTxtFromServer());
            return cusPhone;
        }, status -> {
            return new CustomerPhone();
        });
    }

    @Override
    public List<CustomerPhone> findAll(Connection conn, String customerId) {
        LoadCustomerPhoneNumbers phones = new LoadCustomerPhoneNumbers();
        phones.setCustomerId(customerId);
        return processRequest(conn, phones, status -> {
            List<CustomerPhone> list = new ArrayList<>();
            // Eager loading all the phones when customer is first loaded
            for (int i = 1; i < phones.getCountFromServer() + 1; i++) {
                CustomerPhone phone = findById(conn, customerId, i, phones.getNumberFromServer(i));
                list.add(phone);
                logger.info("Phone: {}, successfully loaded", phone.getNumber());

            }
            return list;
        }, status -> {
            return new ArrayList<>();
        });
    }

}
