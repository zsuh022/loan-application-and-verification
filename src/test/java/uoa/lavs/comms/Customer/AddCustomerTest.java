package uoa.lavs.comms.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.comms.AbstractCustomerTest;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.MockConnection;
import uoa.lavs.mainframe.Response;
import uoa.lavs.mainframe.Status;
import uoa.lavs.models.Customer.Customer;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class AddCustomerTest extends AbstractCustomerTest<Customer> {


    @Override
    @BeforeEach
    protected void setup() throws IOException {
        super.setup();
        addCustomer.add(conn, customer);
    }

    @Override
    protected void assertDetails(Customer expected, Customer actual) {
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDob(), actual.getDob());
        assertEquals(expected.getOccupation(), actual.getOccupation());
        assertEquals(expected.getCitizenship(), actual.getCitizenship());
        assertNull(actual.getVisa());
    }


    @Test
    protected void testCustomerSuccess() {
        Customer cus = searchCustomer.findById(conn, "1");

        assertDetails(customer, cus);
    }

    @Test
    protected void testCustomerFailure() {
        Status errorStatus = new Status(404, "Some problem", 123456L);
        Response errorResponse = new Response(errorStatus, new HashMap<>());

        Connection mockConnection = new MockConnection(errorResponse);

        Customer cus = searchCustomer.findById(mockConnection, String.valueOf(1));

        assertNull(cus.getTitle());
        assertNull(cus.getName());
        assertNull(cus.getDob());
        assertNull(cus.getOccupation());
        assertNull(cus.getCitizenship());
        assertNull(cus.getVisa());
    }

}
