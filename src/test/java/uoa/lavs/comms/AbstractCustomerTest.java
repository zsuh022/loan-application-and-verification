package uoa.lavs.comms;

import org.junit.jupiter.api.BeforeEach;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.models.Customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstractCustomerTest<T> {

    private static final String DB_PATH = "lavs-data.db";

    protected final AddCustomer addCustomer = new AddCustomer();
    protected final SearchCustomer searchCustomer = new SearchCustomer();

    protected final Customer customer = new Customer();
    protected Connection conn;

    @BeforeEach
    void setup() {
        deleteLogDB();

        customer.setTitle("Mr");
        customer.setName("John Doe");
        customer.setDateOfBirth(java.time.LocalDate.of(2024, 2, 11));
        customer.setOccupation("Engineer");
        customer.setCitizenship("New Zealand");
        customer.setVisa(null);

        conn = Instance.getConnection();
    }

    protected void deleteLogDB() {
        Path path = Paths.get(DB_PATH);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            System.err.println("Failed to delete file: " + e.getMessage());
        }
    }

    protected abstract void assertDetails(T expected, T actual);
}
