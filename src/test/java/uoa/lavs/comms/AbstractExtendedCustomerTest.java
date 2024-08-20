package uoa.lavs.comms;

import org.junit.jupiter.api.BeforeEach;
import uoa.lavs.comms.Customer.AddAddress;
import uoa.lavs.comms.Customer.AddEmail;
import uoa.lavs.comms.Customer.AddPhone;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.models.Customer.CustomerAddress;
import uoa.lavs.models.Customer.CustomerEmail;
import uoa.lavs.models.Customer.CustomerPhone;
import uoa.lavs.models.Customer.CustomerEmployer;

import java.io.IOException;

public abstract class AbstractExtendedCustomerTest<T> extends AbstractCustomerTest<T> {

    protected CustomerPhone phone1, phone2, phone3;
    protected CustomerEmail email1, email2;
    protected CustomerAddress address1, address2, address3, address4, address5;
    protected CustomerEmployer employer1;

    protected final AddPhone addPhone = new AddPhone();
    protected final AddEmail addEmail = new AddEmail();
    protected final AddAddress addAddress = new AddAddress();

    @Override
    @BeforeEach
    protected void setup() throws IOException {
        super.setup();

        // Initialize phone details
        phone1 = new CustomerPhone();
        phone1.setType("Mobile");
        phone1.setPrefix("+64");
        phone1.setNumber("123456789");
        phone1.setIsPrimary(true);
        phone1.setIsTexting(true);

        phone2 = new CustomerPhone();
        phone2.setType("Home");
        phone2.setPrefix("+64");
        phone2.setNumber("987654321");
        phone2.setIsPrimary(false);
        phone2.setIsTexting(false);

        phone3 = new CustomerPhone();
        phone3.setType("Work");
        phone3.setPrefix("+64");
        phone3.setNumber("555555555");
        phone3.setIsPrimary(false);
        phone3.setIsTexting(false);

        // Initialize email details
        email1 = new CustomerEmail();
        email1.setAddress("john.doe@example.com");
        email1.setIsPrimary(true);

        email2 = new CustomerEmail();
        email2.setAddress("john.doe@work.com");
        email2.setIsPrimary(false);

        // Initialize address details
        address1 = new CustomerAddress();
        address1.setType("Residential");
        address1.setLine1("123 Main St");
        address1.setLine2("Apt 4");
        address1.setSuburb("CBD");
        address1.setCity("Auckland");
        address1.setPostCode("1010");
        address1.setCountry("New Zealand");
        address1.setIsPrimary(true);
        address1.setIsMailing(true);

        address2 = new CustomerAddress();
        address2.setType("Work");
        address2.setLine1("456 Queen St");
        address2.setLine2("Level 2");
        address2.setSuburb("CBD");
        address2.setCity("Auckland");
        address2.setPostCode("1020");
        address2.setCountry("New Zealand");
        address2.setIsPrimary(false);
        address2.setIsMailing(false);

        address3 = new CustomerAddress();
        address3.setType("Mailing");
        address3.setLine1("789 Broadway");
        address3.setLine2("Suite 300");
        address3.setSuburb("CBD");
        address3.setCity("Auckland");
        address3.setPostCode("1030");
        address3.setCountry("New Zealand");
        address3.setIsPrimary(false);
        address3.setIsMailing(true);

        address4 = new CustomerAddress();
        address4.setType("Secondary");
        address4.setLine1("1010 Pine St");
        address4.setLine2("Unit 5");
        address4.setSuburb("CBD");
        address4.setCity("Auckland");
        address4.setPostCode("1040");
        address4.setCountry("New Zealand");
        address4.setIsPrimary(false);
        address4.setIsMailing(false);

        address5 = new CustomerAddress();
        address5.setType("Holiday");
        address5.setLine1("2020 Beach Rd");
        address5.setLine2("Cabin 7");
        address5.setSuburb("North Shore");
        address5.setCity("Auckland");
        address5.setPostCode("1050");
        address5.setCountry("New Zealand");
        address5.setIsPrimary(false);
        address5.setIsMailing(false);

        // Initialize employer details
        employer1 = new CustomerEmployer();
        employer1.setName("Company A");
        employer1.setLine1("123 Main St");
        employer1.setLine2("Suite 100");
        employer1.setSuburb("Suburb A");
        employer1.setCity("City A");
        employer1.setPostCode("12345");
        employer1.setCountry("Country A");
        employer1.setPhone("555-1234");
        employer1.setEmail("contact@companya.com");
        employer1.setWeb("http://www.companya.com");
        employer1.setIsOwner(true);

        // Add these entities to the customer object
        customer.addPhone(phone1);
        customer.addPhone(phone2);
        customer.addPhone(phone3);
        customer.addEmail(email1);
        customer.addEmail(email2);
        customer.addAddress(address1);
        customer.addAddress(address2);
        customer.addAddress(address3);
        customer.addAddress(address4);
        customer.addAddress(address5);
        customer.addEmployer(employer1);
    }

    protected void addPhones(Connection conn, String customerId) {
        addPhone.add(conn, phone1, customerId);
        addPhone.add(conn, phone2, customerId);
        addPhone.add(conn, phone3, customerId);
    }

    protected void addEmails(Connection conn, String customerId) {
        addEmail.add(conn, email1, customerId);
        addEmail.add(conn, email2, customerId);
    }

    protected void addAddresses(Connection conn, String customerId) {
        addAddress.add(conn, address1, customerId);
        addAddress.add(conn, address2, customerId);
        addAddress.add(conn, address3, customerId);
        addAddress.add(conn, address4, customerId);
        addAddress.add(conn, address5, customerId);
    }
}
