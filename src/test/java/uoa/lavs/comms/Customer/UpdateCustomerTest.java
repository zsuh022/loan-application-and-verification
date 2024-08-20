package uoa.lavs.comms.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.comms.AbstractExtendedCustomerTest;
import uoa.lavs.models.Customer.CustomerPhone;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

public class UpdateCustomerTest extends AbstractExtendedCustomerTest<Void> {

    private final CustomerPhone phone = new CustomerPhone();
    private final SearchPhone search = new SearchPhone();

    @Override
    @BeforeEach
    protected void setup() throws IOException {
        super.setup();
    }

    @Test
    void testUpdatePhone() {

        String id = addCustomer.add(conn, customer);
        addPhones(conn, id);

        phone.setNumber("99999999");
        phone.setPrefix("+11");
        phone.setIndex(1);
        phone.setIsPrimary(false);
        phone.setIsTexting(false);

        addPhone.add(conn, phone, id);

        List<CustomerPhone> list = search.findAll(conn, id);
        
    }

//    @Test
//    void testUpdateEmail() {
//        email1.setAddress("john.doe@newmail.com");
//
//        String customerID = customer.getId();
//
//        String result = changeEmail.add(conn, email1, customerID);
//    }
//
//    @Test
//    void testUpdateAddress() {
//        address1.setLine1("999 New Address St");
//
//        String customerID = customer.getId();
//
//        String result = changeAddress.add(conn, address1, customerID);
//    }

    @Override
    protected void assertDetails(Void expected, Void actual) {

    }
}