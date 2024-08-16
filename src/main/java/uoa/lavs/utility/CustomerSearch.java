package uoa.lavs.utility;

import uoa.lavs.models.Customer;

import java.util.ArrayList;

public class CustomerSearch {

    private ArrayList<Customer> customerArrayList = new ArrayList<>();

    public CustomerSearch(ArrayList<Customer> customerArrayList) {
        this.customerArrayList = customerArrayList;
    }

    public ArrayList<Customer> getSearchResults(String search) {
        ArrayList<Customer> searchResult = new ArrayList<>();

        for (Customer customer : customerArrayList) {
            if (customer.getName().toLowerCase().contains(search.toLowerCase())) {
                searchResult.add(customer);
            }
        }

        return searchResult;
    }
}
