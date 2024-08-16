package uoa.lavs.comms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.LoadCustomerAddress;
import uoa.lavs.mainframe.messages.customer.LoadCustomerAddresses;
import uoa.lavs.models.CustomerAddress;

import java.util.ArrayList;
import java.util.List;

public class SearchAddress extends AbstractSearchable<CustomerAddress> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(SearchAddress.class);

    @Override
    public CustomerAddress findById(Connection conn, String customerId, int index) {
        LoadCustomerAddress address = new LoadCustomerAddress();
        address.setCustomerId(customerId);
        address.setNumber(index);

        return processRequest(conn, address, status -> {
            CustomerAddress cusAddress = new CustomerAddress();
            cusAddress.setType(address.getTypeFromServer());
            cusAddress.setLine1(address.getLine1FromServer());
            cusAddress.setLine2(address.getLine2FromServer());
            cusAddress.setSuburb(address.getSuburbFromServer());
            cusAddress.setCity(address.getCityFromServer());
            cusAddress.setPostCode(Integer.parseInt(address.getPostCodeFromServer()));
            cusAddress.setCountry(address.getCountryFromServer());
            cusAddress.setIsPrimary(address.getIsPrimaryFromServer());
            cusAddress.setIsMailing(address.getIsMailingFromServer());
            return cusAddress;
        }, status -> {
            return new CustomerAddress();
        });
    }

    @Override
    public List<CustomerAddress> findAll(Connection conn, String customerId) {
        LoadCustomerAddresses addresses = new LoadCustomerAddresses();
        addresses.setCustomerId(customerId);
        return processRequest(conn, addresses, status -> {
            List<CustomerAddress> list = new ArrayList<>();
            // Eager loading all the address when customer is first loaded
            for (int i = 0; i < addresses.getCountFromServer(); i++) {
                CustomerAddress address = findById(conn, customerId, i);
                list.add(address);
                logger.info("Address: {}, successfully added", address.getLine1());
            }
            return list;
        }, status -> {
            return new ArrayList<>();
        });
    }
}
