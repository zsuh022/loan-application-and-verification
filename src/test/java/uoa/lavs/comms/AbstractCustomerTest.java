package uoa.lavs.comms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.mainframe.simulator.NitriteConnection;
import uoa.lavs.mainframe.simulator.failures.NFailsPerMRequestsPolicy;
import uoa.lavs.mainframe.simulator.failures.RandomPolicy;
import uoa.lavs.models.Customer;
import uoa.lavs.mainframe.simulator.IntermittentConnection;
import uoa.lavs.mainframe.Response;
import uoa.lavs.mainframe.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.deleteIfExists;

public abstract class AbstractCustomerTest<T> {


    protected final AddCustomer addCustomer = new AddCustomer();
    protected final SearchCustomer searchCustomer = new SearchCustomer();

    protected final Customer customer = new Customer();
    protected Connection conn;

    @BeforeEach
    void setup() throws IOException {

        conn = Instance.getConnection();

        deleteIfExists(Path.of("lavs-data.db"));
        customer.setTitle("Mr");
        customer.setName("John Doe");
        customer.setDateOfBirth(java.time.LocalDate.of(2024, 2, 11));
        customer.setOccupation("Engineer");
        customer.setCitizenship("New Zealand");
        customer.setVisa(null);
    }

    protected abstract void assertDetails(T expected, T actual);
}
