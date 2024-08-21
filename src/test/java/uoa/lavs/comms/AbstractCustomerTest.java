package uoa.lavs.comms;

import org.junit.jupiter.api.BeforeEach;
import uoa.lavs.comms.Customer.AddCustomer;
import uoa.lavs.comms.Customer.SearchCustomer;
import uoa.lavs.mainframe.*;
import uoa.lavs.mainframe.simulator.NitriteConnection;
import uoa.lavs.models.Customer.Customer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import static java.nio.file.Files.deleteIfExists;

public abstract class AbstractCustomerTest<T> {


    protected final AddCustomer addCustomer = new AddCustomer();
    protected final SearchCustomer searchCustomer = new SearchCustomer();

    protected final Customer customer = new Customer();
    protected final Customer customer1 = new Customer();
    protected Connection conn;
    protected Connection mockConnection;

    @BeforeEach
    protected void setup() throws IOException {

//        conn = Instance.getConnection();

        deleteIfExists(Path.of("lavs-data.db"));
        conn = new NitriteConnection("lavs-data.db");
        customer.setCustomerId("TEMP_CUSTOMER_");
        customer.setTitle("Mr");
        customer.setName("John Doe");
        customer.setDateOfBirth(java.time.LocalDate.of(2024, 2, 11));
        customer.setOccupation("Engineer");
        customer.setCitizenship("New Zealand");
        customer.setVisa(null);

        customer1.setCustomerId("TEMP_CUSTOMER_");
        customer1.setTitle("Mrs");
        customer1.setName("Susan Doe");
        customer1.setDateOfBirth(java.time.LocalDate.of(2024, 2, 11));
        customer1.setOccupation("Engineer");
        customer1.setCitizenship("New Zealand");
        customer1.setVisa(null);

        Status errorStatus = new Status(404, "Some problem", 123456);
        Response errorResponse = new Response(errorStatus, new HashMap<>());

        mockConnection = new MockConnection(errorResponse);
    }

    protected abstract void assertDetails(T expected, T actual);


}
