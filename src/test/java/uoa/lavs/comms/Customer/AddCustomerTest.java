package uoa.lavs.comms.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import uoa.lavs.comms.AbstractCustomerTest;
import uoa.lavs.mainframe.*;
import uoa.lavs.mainframe.messages.customer.LoadCustomerEmail;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Customer.CustomerEmail;
import uoa.lavs.models.Loan.LoanDetails;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddCustomerTest extends AbstractCustomerTest<Customer> {
    String id = null;

    @Override
    @BeforeEach
    protected void setup() throws IOException {
        super.setup();
        id = addCustomer.add(conn, customer);
    }

    @Override
    protected void assertDetails(Customer expected, Customer actual) {
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDateOfBirth(), actual.getDateOfBirth());
        assertEquals(expected.getOccupation(), actual.getOccupation());
        assertEquals(expected.getCitizenship(), actual.getCitizenship());
        assertNull(actual.getVisa());
    }


    @Test
    protected void testCustomerSuccess() {
        Customer cus = searchCustomer.findById(conn, id);

        assertDetails(customer, cus);
    }

    @Test
    protected void testCustomerFailure() {
        Customer cus = searchCustomer.findById(mockConnection, "1");

        assertNull(cus.getTitle());
        assertNull(cus.getName());
        assertNull(cus.getDateOfBirth());
        assertNull(cus.getOccupation());
        assertNull(cus.getCitizenship());
        assertNull(cus.getVisa());
    }

    @Test
    protected void testUnsupportedMethod() {
        Executable executable = () -> {
            List<Customer> result = searchCustomer.findAll(conn, "1");
        };

        assertThrows(UnsupportedOperationException.class, executable, "findAll should throw UnsupportedOperationException");
    }

}
