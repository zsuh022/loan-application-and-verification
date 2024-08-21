package uoa.lavs.comms;

import org.junit.jupiter.api.Test;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Message;
import uoa.lavs.mainframe.Status;
import uoa.lavs.models.Customer.Customer;

import java.io.IOException;
import java.util.function.Function;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestWriterTest extends AbstractCustomerTest<Customer> {

    private final TestWriter testWriter = new TestWriter();

    @Test
    void testAddWithoutCustomerIDThrowsException() {

        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> {
            testWriter.add(conn, customer);
        });

        assertEquals("add with No customerID is not supported for this entity.", exception.getMessage());
    }

    @Test
    void testAddWithCustomerIDThrowsException() {
        String customerID = "12345";

        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> {
            testWriter.add(conn, customer, customerID);
        });

        assertEquals("add with customerID is not supported for this entity.", exception.getMessage());
    }


    @Override
    protected void assertDetails(Customer expected, Customer actual) {

    }

    @Test
    void testProcessRequestHandlesIOException() throws IOException {

        Message mockMessage = mock(Message.class);
        Connection mockConnection = mock(Connection.class);

        when(mockMessage.send(mockConnection)).thenThrow(new IOException("Test IOException"));

        Function<Status, String> onSuccess = status -> "Success";
        Function<Status, String> onFailure = status -> "Failure";


        String result = testWriter.processRequest(mockConnection, mockMessage, new Customer(), onSuccess, onFailure, 1206, "Customer", "12345");

        assertNull(result);
    }
}
