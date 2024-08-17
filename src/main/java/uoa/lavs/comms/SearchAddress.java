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
    public CustomerAddress findById(Connection conn, String customerId, int index, int number) {
        LoadCustomerAddress address = new LoadCustomerAddress();
        address.setCustomerId(customerId);
        address.setNumber(index);

        return processRequest(conn, address, status -> {
            CustomerAddress newAddress = new CustomerAddress();
            newAddress.setIndex(number);
            newAddress.setType(address.getTypeFromServer());
            newAddress.setLine1(address.getLine1FromServer());
            newAddress.setLine2(address.getLine2FromServer());
            newAddress.setSuburb(address.getSuburbFromServer());
            newAddress.setCity(address.getCityFromServer());
            newAddress.setPostCode(Integer.parseInt(address.getPostCodeFromServer()));
            newAddress.setCountry(address.getCountryFromServer());
            newAddress.setIsPrimary(address.getIsPrimaryFromServer());
            newAddress.setIsMailing(address.getIsMailingFromServer());
            return newAddress;
        }, status -> {
            return null;
        });
    }

    @Override
    public List<CustomerAddress> findAll(Connection conn, String customerId) {
        LoadCustomerAddresses addresses = new LoadCustomerAddresses();
        addresses.setCustomerId(customerId);
        return processRequest(conn, addresses, status -> {
            List<CustomerAddress> list = new ArrayList<>();
            // Eager loading all the address when customer is first loaded
            for (int i = 1; i < addresses.getCountFromServer() + 1; i++) {
                CustomerAddress address = findById(conn, customerId, i,
                        addresses.getNumberFromServer(i));
                if (address != null) {
                    list.add(address);
                    logger.info("Address: {}, successfully loaded", address.getLine1());
                }
            }
            return list;
        }, status -> {
            return new ArrayList<>();
        });
    }
}
