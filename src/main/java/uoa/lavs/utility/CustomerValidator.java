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

        if (map.get("customerId") == null || map.get("customerId").isEmpty()) {
            return false;
        }

        if (map.get("name") == null || map.get("name").isEmpty()) {
            return false;
        }

        String[] fullName = map.get("name").split(" ");
        if (fullName.length <= 1) {
            return false;
        }

        if (map.get("dateOfBirth") == null || map.get("dateOfBirth").isEmpty()) {
            return false;
        }

        if (map.get("occupation") == null || map.get("occupation").isEmpty()) {
            return false;
        }

        if (map.get("citizenship") == null || map.get("citizenship").isEmpty()) {
            return false;
        }

        if (map.get("visa") == null || map.get("visa").isEmpty()) {
            return false;
        }

        // validate array lists?

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

        if (map.get("type") == null || map.get("type").isEmpty()) {
            return false;
        }

        if (map.get("line1") == null || map.get("line1").isEmpty()) {
            // line1 compulsory, line2 optional
            return false;
        }

        if (map.get("suburb") == null || map.get("suburb").isEmpty()) {
            return false;
        }

        if (map.get("city") == null || map.get("city").isEmpty()) {
            return false;
        }

        if (map.get("postCode") == null || map.get("postCode").isEmpty() || map.get("postCode").matches("\\d{4}")) {
            // assume post code is 4 digits
            return false;
        }

        if (map.get("country") == null || map.get("country").isEmpty()) {
            return false;
        }

        if (map.get("isPrimary") == null || map.get("isPrimary").isEmpty()) {
            return false;
        }

        if (map.get("isMailing") == null || map.get("isMailing").isEmpty()) {
            return false;
        }

        return true;
    }

    public Boolean validateEmail(HashMap<String, String> map) {

        // private String address;
        // private Boolean isPrimary;

        if (map.get("address") == null || map.get("address").isEmpty()) {
            return false;
        }

        String[] email = map.get("address").split("@");
        if (email.length != 2) {
            // email split into prefix and domain
            return false;
        }

        String prefix = email[0];
        if (!prefix.matches("^[a-zA-Z0-9._-]+$")) {
            return false;
        }

        if (!Character.isLetter(prefix.charAt(0)) || !Character.isLetterOrDigit(prefix.charAt(prefix.length() - 1))) {
            return false;
        }

        if (prefix.contains("..") || prefix.contains("__") || prefix.contains("--")) {
            return false;
        }

        String[] domain = email[1].split("\\.");
        if (domain.length <= 1) {
            return false;
        }

        for (String domainPart : domain) {
            if (domainPart.isEmpty()) {
                return false;
            }

            if (!domainPart.matches("^[a-zA-Z0-9-]+$")) {
                return false;
            }

            if (!Character.isLetterOrDigit(domainPart.charAt(0)) || !Character.isLetterOrDigit(domainPart.charAt(domainPart.length() - 1))) {
                return false;
            }

            if (domainPart.contains("..") || domainPart.contains("--")) {
                return false;
            }
        }

        if (map.get("isPrimary") == null || map.get("isPrimary").isEmpty()) {
            return false;
        }

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
