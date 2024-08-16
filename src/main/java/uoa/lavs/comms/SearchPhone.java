package uoa.lavs.comms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.LoadCustomerPhoneNumber;
import uoa.lavs.mainframe.messages.customer.LoadCustomerPhoneNumbers;
import uoa.lavs.models.CustomerPhone;

import java.util.ArrayList;
import java.util.List;

public class SearchPhone extends AbstractSearchable<CustomerPhone> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(SearchPhone.class);

    @Override
    public CustomerPhone findById(Connection conn, String customerId, int index) {
        LoadCustomerPhoneNumber phone = new LoadCustomerPhoneNumber();
        phone.setCustomerId(customerId);
        phone.setNumber(index);

        return processRequest(conn, phone, status -> {
            CustomerPhone cusPhone = new CustomerPhone();
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
            for (int i = 0; i < phones.getCountFromServer(); i++) {
                CustomerPhone phone = findById(conn, customerId, i);
                list.add(phone);
                logger.info("Email: {}, successfully added", phone.getNumber());
            }
            return list;
        }, status -> {
            return new ArrayList<>();
        });
    }

}
