package uoa.lavs.comms.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.comms.AbstractExtendedCustomerTest;
import uoa.lavs.logging.Cache;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Customer.CustomerPhone;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

public class UpdateCustomerTest extends AbstractExtendedCustomerTest<Customer> {

    String customerID = null;
    Customer customer2 = new Customer();

    @Override
    @BeforeEach
    protected void setup() throws IOException {
        super.setup();
        customerID = addCustomer.add(conn, customer);
        customer.setCustomerId(customerID);
        Cache.cacheCustomer(customer);
        customer2 = Cache.searchCustomerCacheId(customerID).get(0);
        customer2.setTitle("Mr");
        customer2.setName("Apple Jeans");
        customer2.setDateOfBirth(java.time.LocalDate.of(1999, 1, 11));
        customer2.setOccupation("Chef");
        customer2.setCitizenship("New Zealand");
        customer2.setVisa(null);
    }

    @Test
    protected void updateCustomerTest() throws IOException {
        addCustomer.add(conn, customer2);
        Customer dbCustomer = searchCustomer.findById(conn, customerID);
        assertDetails(customer2, dbCustomer);
    }


    @Override
    protected void assertDetails(Customer expected, Customer actual) {
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDateOfBirth(), actual.getDateOfBirth());
        assertEquals(expected.getOccupation(), actual.getOccupation());
        assertEquals(expected.getCitizenship(), actual.getCitizenship());
    }
}
