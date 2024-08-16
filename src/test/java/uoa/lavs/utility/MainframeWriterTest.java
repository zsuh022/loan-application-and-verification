package uoa.lavs.utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.comms.MainframeWriter;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.mainframe.Response;
import uoa.lavs.mainframe.Status;
import uoa.lavs.models.Customer;
import uoa.lavs.mainframe.MockConnection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MainframeWriterTest {

    private static final String DB_PATH = "lavs-data.db";

    private final MainframeWriter mainframeWriter = MainframeWriter.getInstance();

    private final Customer customer = new Customer();

    private final Connection conn = Instance.getConnection();

    @BeforeEach
    void setup() {

        deleteDatabaseFile();

        customer.setTitle("Mr");
        customer.setName("John Doe");
        customer.setDateOfBirth(LocalDate.of(2024, 2, 11));
        customer.setOccupation("Engineer");
        customer.setCitizenship("New Zealand");
        customer.setVisa(null);
    }

    private void deleteDatabaseFile() {
        Path path = Paths.get(DB_PATH);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            System.err.println("Failed to delete database file: " + e.getMessage());
        }
    }

    @Test
    void testNewCustomerSuccess() throws IOException {

        String result = mainframeWriter.newCustomer(conn, customer);

        assertEquals(result, "1");
    }

    @Test
    void testNewCustomerFailure() throws IOException {

        Status errorStatus = new Status(404, "Some problem", 123456L);
        Response errorResponse = new Response(errorStatus, new HashMap<>());

        Connection mockConnection = new MockConnection(errorResponse);

        String result = mainframeWriter.newCustomer(mockConnection, customer);

        assertNull(result);
    }
}
