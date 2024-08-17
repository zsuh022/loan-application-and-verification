package uoa.lavs.comms;

import org.junit.jupiter.api.Test;
import uoa.lavs.models.CustomerPhoneDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateCustomerTest extends AbstractExtendedCustomerTest<Void> {

    private final CustomerPhoneDTO phone = new CustomerPhoneDTO();


    @Test
    void testUpdatePhone() {

        phone.setNumber("99999999");
        phone.setPrefix("+11");


        String customerID = customer.getId();
        String result = changePhone.add(conn, phone1, customerID);
    }

    @Test
    void testUpdateEmail() {
        email1.setAddress("john.doe@newmail.com");

        String customerID = customer.getId();

        String result = changeEmail.add(conn, email1, customerID);
    }

    @Test
    void testUpdateAddress() {
        address1.setLine1("999 New Address St");

        String customerID = customer.getId();

        String result = changeAddress.add(conn, address1, customerID);
    }

    @Override
    protected void assertDetails(Void expected, Void actual) {

    }
}
