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

        // private String customerId;
        // private String title;
        // private String name;
        // private LocalDate dateOfBirth;
        // private String occupation;
        // private String citizenship;
        // private String visa;
        // private List<CustomerAddress> addressList = new ArrayList<>();
        // private List<CustomerEmail> emailList = new ArrayList<>();
        // private List<CustomerNote> noteList = new ArrayList<>();
        // private List<CustomerPhone> phoneList = new ArrayList<>();

        return true;
    }

    public Boolean validateAddress(HashMap<String, String> map) {

        // private String type;
        // private String line1;
        // private String line2;
        // private String suburb;
        // private String city;
        // private int postCode;
        // private String country;
        // private Boolean isPrimary;
        // private Boolean isMailing;

        return true;
    }

    public Boolean validateEmail(HashMap<String, String> map) {

        // private String address;
        // private Boolean isPrimary;

        return true;
    }

    public Boolean validatePhone(HashMap<String, String> map) {

        // private String type;
        // private String prefix;
        // private String number;
        // private Boolean isPrimary;
        // private Boolean isTexting;

        if (map.get("type") == null || map.get("type").isEmpty()) {
            return false;
        }

        if (map.get("prefix") == null || map.get("prefix").matches("\\d{3}")) {
            // prefix exactly 3 digits
            return false;
        }

        if (map.get("number") == null || map.get("number").matches("\\d+")) {
            // number only has digits
            return false;
        }

        if (map.get("isPrimary") == null || map.get("isPrimary").isEmpty()) {
            return false;
        }

        if (map.get("isTexting") == null || map.get("isTexting").isEmpty()) {
            return false;
        }

        return true;
    }

    public Boolean validateNote(HashMap<String, String> map) {

        // private String note;

        return true;
    }
}
