package uoa.lavs.comms;

import org.junit.jupiter.api.Test;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Message;
import uoa.lavs.mainframe.Request;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.customer.LoadCustomerEmail;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Customer.CustomerEmail;

import java.io.IOException;
import java.util.function.Function;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestSearchTest extends AbstractCustomerTest<Customer> {

    private final TestSearch testSearch = new TestSearch();

    @Test
    void testAddWithoutCustomerIDThrowsException() {

        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> {
            testSearch.findById(conn, "1");
        });

        assertEquals("findById without an index is not supported for this entity.", exception.getMessage());
    }

    @Test
    void testAddWithCustomerIDThrowsException() {

        UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> {
            testSearch.findById(conn, "1", 1, 1);
        });

        assertEquals("findById with index is not supported for this entity.", exception.getMessage());
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

        String result = testSearch.processRequest(mockConnection, mockMessage, onSuccess, onFailure);

        assertNull(result);
    }
}
