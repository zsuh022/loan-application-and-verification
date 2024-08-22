package uoa.lavs.comms.Customer;

import uoa.lavs.comms.AbstractSearchable;
import uoa.lavs.logging.Cache;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.messages.customer.*;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Customer.CustomerEmail;
import uoa.lavs.models.Customer.CustomerSummary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class InitialSearch extends AbstractSearchable<CustomerSummary> {
    private int type;

    public InitialSearch(int type) {
        this.type = type;
    }

    @Override
    public List<CustomerSummary> findAll(Connection conn, String customerId) {
        if (this.type == 0) {
            HashSet<String> foundIDs = new HashSet<>();
            ArrayList<CustomerSummary> summaries = new ArrayList<>();
            for(Customer customer : Cache.searchCustomerCacheId(customerId)) {
                foundIDs.add(customer.getId());
                summaries.add(obfuscateCustomer(customer));
                System.out.println("Found in cache");
            }
            try{
                FindCustomer customer = new FindCustomer();
                customer.setCustomerId(customerId);
                for(CustomerSummary summary : (List<CustomerSummary>) processRequest(conn, customer, status -> executeCommon(conn, customer), status -> new ArrayList<>())) {
                    if(!foundIDs.contains(summary.getId())) {
                        summaries.add(summary);
                        foundIDs.add(summary.getId());
                    }
                }
            } catch (Exception e) {
                // do nothing
            }
            return summaries;
        } else {
            HashSet<String> foundIDs = new HashSet<>();
            ArrayList<CustomerSummary> summaries = new ArrayList<>();
            for(Customer customer : Cache.searchCustomerName(customerId)) {
                foundIDs.add(customer.getId());
                summaries.add(obfuscateCustomer(customer));
            }
            try{
                FindCustomerAdvanced customer = new FindCustomerAdvanced();
                customer.setSearchName(customerId);
                for(CustomerSummary summary : (List<CustomerSummary>) processRequest(conn, customer, status -> executeCommon(conn, customer), status -> new ArrayList<>())) {
                    if(!foundIDs.contains(summary.getId())) {
                        summaries.add(summary);
                        foundIDs.add(summary.getId());
                    }
                }
            } catch (Exception e) {
                // do nothing
            }

            return summaries;
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

    private CustomerSummary obfuscateCustomer(Customer customer) {
        CustomerSummary summary = new CustomerSummary();
        summary.setId(customer.getId());
        summary.setName(customer.getName());
        summary.setDob(customer.getDateOfBirth());
        List<CustomerEmail> emails = customer.getEmailList();
        for(CustomerEmail email : emails) {
            if(email.getIsPrimary()) {
                summary.setEmail(email.getAddress());
                break;
            }
        }
        return summary;
    }

}
