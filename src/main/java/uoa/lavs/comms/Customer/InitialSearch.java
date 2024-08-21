package uoa.lavs.comms.Customer;

import uoa.lavs.comms.AbstractSearchable;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.*;
import uoa.lavs.models.Customer.CustomerSummary;

import java.util.ArrayList;
import java.util.List;

public class InitialSearch extends AbstractSearchable<CustomerSummary> {
    private int type;

    public InitialSearch(int type) {
        this.type = type;
    }

    @Override
    public List<CustomerSummary> findAll(Connection conn, String customerId) {
        if (this.type == 0) {
            FindCustomer customer = new FindCustomer();
            customer.setCustomerId(customerId);
            return processRequest(conn, customer, status -> executeCommon(conn, customer), status -> new ArrayList<>());
        } else {
            FindCustomerAdvanced customer = new FindCustomerAdvanced();
            customer.setSearchName(customerId);
            return processRequest(conn, customer, status -> executeCommon(conn, customer), status -> new ArrayList<>());
        }
    }


    private List<CustomerSummary> executeCommon(Connection conn, FindCustomer customer) {
        List<CustomerSummary> summaries = new ArrayList<>();
        int length = customer.getCustomerCountFromServer();
        for (int i = 1; i < length + 1; i++) {
            CustomerSummary summary = new CustomerSummary();
            populateSummary(conn, summary, customer, i);
            summaries.add(summary);
        }
        return summaries;
    }

    private List<CustomerSummary> executeCommon(Connection conn, FindCustomerAdvanced customer) {
        List<CustomerSummary> summaries = new ArrayList<>();
        int length = customer.getCustomerCountFromServer();
        for (int i = 1; i < length + 1; i++) {
            CustomerSummary summary = new CustomerSummary();
            populateSummaryAdvanced(conn, summary, customer, i);
            summaries.add(summary);
        }
        return summaries;
    }


    private void populateSummary(Connection conn, CustomerSummary summary, FindCustomer customer, int index) {
        String id = customer.getIdFromServer(index);
        summary.setId(id);
        summary.setName(customer.getNameFromServer(index));
        summary.setDob(customer.getDateofBirthFromServer(index));
        summary.setEmail(findEmail(conn, id));
    }

    private void populateSummaryAdvanced(Connection conn, CustomerSummary summary, FindCustomerAdvanced customer, int index) {
        String id = customer.getIdFromServer(index);
        summary.setId(id);
        summary.setName(customer.getNameFromServer(index));
        summary.setDob(customer.getDateofBirthFromServer(index));
        summary.setEmail(findEmail(conn, id));
    }


    private String findEmail(Connection conn, String id) {
        FindCustomerEmail email = new FindCustomerEmail();
        email.setCustomerId(id);
        return processRequest(conn, email, status -> {
            for (int j = 1; j < email.getCountFromServer() + 1; j++) {
                if (email.getIsPrimaryFromServer(j)) {
                    return email.getAddressFromServer(j);
                }
            }
            return "";
        }, status -> {
            return "";
        });
    }

}
