package uoa.lavs.comms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.models.CustomerPhone;
import uoa.lavs.models.CustomerPhoneDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

public class UpdateCustomerTest extends AbstractExtendedCustomerTest<Void> {

    private final CustomerPhoneDTO phone = new CustomerPhoneDTO();
    private final ChangePhone change = new ChangePhone();
    private final SearchPhone search = new SearchPhone();

    @Override
    @BeforeEach
    void setup() throws IOException {
        super.setup();
    }

    @Test
    void testUpdatePhone() {

        String id = addCustomer.add(conn, customer);
        addPhones(conn, id);

        phone.setNumber("99999999");
        phone.setPrefix("+11");
        phone.setIndex(1);
        phone.setPrimary(false);
        phone.setTexting(false);

        change.add(conn, phone, id);

        List<CustomerPhone> list = search.findAll(conn, id);

        for (CustomerPhone e : list) {
            System.out.println(e.getNumber());
        }


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
