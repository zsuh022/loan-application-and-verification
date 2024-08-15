package uoa.lavs.utility;

import uoa.lavs.models.Customer;

import java.time.LocalDate;
import java.util.HashMap;

public class CustomerValidator {

    public Customer createCustomer(HashMap<String, String> map) {
        Customer customer = new Customer();
        customer.setCustomerId(map.get("customerId"));
        customer.setTitle(map.get("title"));
        customer.setName(map.get("name"));
        customer.setDateOfBirth(LocalDate.parse(map.get("dateOfBirth")));
        customer.setOccupation(map.get("occupation"));
        customer.setCitizenship(map.get("citizenship"));
        customer.setVisa(map.get("visa"));

        return customer;
    }

    public Boolean validateCustomer(HashMap<String, String> map) {
        // TODO:
        return true;
    }

    public Boolean validateAddress(HashMap<String, String> map) {
        // TODO:
        return true;
    }

    public Boolean validateEmail(HashMap<String, String> map) {
        // TODO:
        return true;
    }

    public Boolean validatePhone(HashMap<String, String> map) {
        // TODO:
        return true;
    }

    public Boolean validateNote(HashMap<String, String> map) {
        // TODO:
        return true;
    }
}
