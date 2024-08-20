package uoa.lavs.utility;

import uoa.lavs.models.Customer;

import java.util.ArrayList;

public class CustomerResultReader {

    public boolean processSearch(ArrayList<Customer> search) {
        if (search == null || search.isEmpty()) {
            return false;
        }

        for (Customer customer : search) {
            // do something
        }

        return true;
    }
}
