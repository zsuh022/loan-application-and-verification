package uoa.lavs.utility;

import uoa.lavs.comms.Customer.SearchCustomer;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.models.Customer.Customer;

// class used for testing
class SearchCustomerExceptionMock extends SearchCustomer {
    @Override
    public Customer findById(Connection connection, String customerId) {
        throw new RuntimeException("Simulated exception");
    }
}
