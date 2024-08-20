package uoa.lavs.utility;

import uoa.lavs.models.*;

import java.time.LocalDate;
import java.util.Map;

public class CustomerValidator {

    public Customer createCustomer(Map<String, String> map) {
        Customer customer = new Customer();
        customer.setCustomerId(map.get("customerId"));
        customer.setTitle(map.get("title"));
        customer.setName(map.get("name"));
        customer.setDateOfBirth(LocalDate.parse(map.get("dateOfBirth")));
        customer.setOccupation(map.get("occupation"));
        customer.setCitizenship(map.get("citizenship"));
        customer.setVisa(map.get("visa"));

        int i = 0;
        while (map.containsKey("address type " + i)) {
            CustomerAddress address = new CustomerAddress();
            address.setType(map.get("address type " + i));
            address.setLine1(map.get("address line1 " + i));
            address.setLine2(map.get("address line2 " + i));
            address.setSuburb(map.get("address suburb " + i));
            address.setCity(map.get("address city " + i));
            address.setPostCode(Integer.parseInt(map.get("address postCode " + i)));
            address.setCountry(map.get("address country " + i));
            address.setIsPrimary(Boolean.parseBoolean(map.get("address isPrimary " + i)));
            address.setIsMailing(Boolean.parseBoolean(map.get("address isMailing " + i)));
            customer.addAddress(address);
            i++;
        }

        i = 0;
        while (map.containsKey("email address " + i)) {
            CustomerEmail email = new CustomerEmail();
            email.setAddress(map.get("email address " + i));
            email.setIsPrimary(Boolean.parseBoolean(map.get("email isPrimary " + i)));
            customer.addEmail(email);
            i++;
        }

        i = 0;
        while (map.containsKey("employer name " + i)) {
            CustomerEmployer employer = new CustomerEmployer();
            employer.setName(map.get("employer name " + i));
            employer.setLine1(map.get("employer line1 " + i));
            employer.setLine2(map.get("employer line2 " + i));
            employer.setSuburb(map.get("employer suburb " + i));
            employer.setCity(map.get("employer city " + i));
            employer.setPostCode(Integer.parseInt(map.get("employer postCode " + i)));
            employer.setCountry(map.get("employer country " + i));
            employer.setPhone(map.get("employer phone " + i));
            employer.setEmail(map.get("employer email " + i));
            employer.setWeb(map.get("employer web " + i));
            employer.setIsOwner(Boolean.parseBoolean(map.get("employer isOwner " + i)));
            customer.addEmployer(employer);
            i++;
        }

        // only 1 note
        if (map.containsKey("note")) {
            CustomerNote note = new CustomerNote();
            note.setNote(map.get("note"));
            customer.addNote(note);
        }

        i = 0;
        while (map.containsKey("phone type " + i)) {
            CustomerPhone phone = new CustomerPhone();
            phone.setType(map.get("phone type " + i));
            phone.setPrefix(map.get("phone prefix " + i));
            phone.setNumber(map.get("phone number " + i));
            phone.setIsPrimary(Boolean.parseBoolean(map.get("phone isPrimary " + i)));
            phone.setIsTexting(Boolean.parseBoolean(map.get("phone isTexting " + i)));
            customer.addPhone(phone);
            i++;
        }

        return customer;
    }

    public boolean validateCustomer(Map<String, String> map) {

        if (map.get("customerId") == null || map.get("customerId").isEmpty()) {
            return false;
        }

        if (map.get("name") == null || map.get("name").isEmpty()) {
            return false;
        }

        String[] fullName = map.get("name").split(" ");
        if (fullName.length < 2) {
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

        if (!validateAddress(map)) {
            return false;
        }

        if (!validateEmail(map)) {
            return false;
        }

        if (!validateEmployer(map)) {
            return false;
        }

        if (!validatePhone(map)) {
            return false;
        }

        return true;
    }

    public boolean validateAddress(Map<String, String> map) {

        int i = 0;
        int primaryCount = 0;
        int mailingCount = 0;

        while (map.containsKey("address type " + i)) {

            if (map.get("address type " + i) == null || map.get("address type " + i).isEmpty()) {
                return false;
            }

            if (map.get("address line1 " + i) == null || map.get("address line1 " + i).isEmpty()) {
                return false;
            }

            if (map.get("address suburb" + i) == null || map.get("address suburb " + i).isEmpty()) {
                return false;
            }

            if (map.get("address city " + i) == null || map.get("address city " + i).isEmpty()) {
                return false;
            }

            if (map.get("address postCode " + i) == null || map.get("address postCode " + i).isEmpty() ||
                    !map.get("address postCode " + i).matches("\\d{4}")) {
                // assume post code is 4 digits
                return false;
            }

            if (map.get("address country " + i) == null || map.get("address country " + i).isEmpty()) {
                return false;
            }

            if (Boolean.parseBoolean(map.get("address isPrimary " + i))) {
                primaryCount++;
            }

            if (Boolean.parseBoolean(map.get("address isMailing " + i))) {
                mailingCount++;
            }

            i++;
        }
        
        if (primaryCount != 1 || mailingCount == 0) {
            // only 1 primary address allowed, needs at least 1 mailing address
            return false;
        }

        return true;
    }

    public boolean validateEmail(Map<String, String> map) {

        int i = 0;
        int primaryCount = 0;

        while (map.containsKey("email type " + i)) {

            if (map.get("email address " + i) == null || map.get("email address " + i).isEmpty()) {
                return false;
            }

            String[] email = map.get("email address " + i).split("@");
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

            if (Boolean.parseBoolean(map.get("email isPrimary " + i))) {
                primaryCount++;
            }

            i++;
        }

        if (primaryCount != 1) {
            return false;
        }

        return true;
    }

    public boolean validateEmployer(Map<String, String> map) {

        int i = 0;

        while (map.containsKey("employer name " + i)) {

            if (map.get("employer name " + i) == null || map.get("employer name " + i).isEmpty()) {
                return false;
            }

            String[] fullName = map.get("employer name " + i).split(" ");
            if (fullName.length < 2) {
                return false;
            }

            if (map.get("employer line1 " + i) == null || map.get("employer line1 " + i).isEmpty()) {
                return false;
            }

            if (map.get("employer suburb " + i) == null || map.get("employer suburb " + i).isEmpty()) {
                return false;
            }

            if (map.get("employer city " + i) == null || map.get("employer city " + i).isEmpty()) {
                return false;
            }

            if (map.get("employer postCode " + i) == null || map.get("employer postCode " + i).isEmpty() ||
                    !map.get("employer postCode " + i).matches("\\d{4}")) {
                // assume post code is 4 digits
                return false;
            }

            if (map.get("employer country " + i) == null || map.get("employer country " + i).isEmpty()) {
                return false;
            }

            if (map.get("employer phone " + i) == null || map.get("employer phone " + i).isEmpty() ||
                    !map.get("employer phone " + i).matches("\\d+")) {
                // phone only has digits
                return false;
            }

            if (map.get("employer email " + i) == null || map.get("employer email " + i).isEmpty()) {
                return false;
            }

            if (map.get("employer web " + i) == null || map.get("employer web " + i).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public boolean validatePhone(Map<String, String> map) {

        int i = 0;
        int primaryCount = 0;
        int textingCount = 0;

        while (map.containsKey("phone type " + i)) {

            if (map.get("phone type " + i) == null || map.get("phone type " + i).isEmpty()) {
                return false;
            }

            if (map.get("phone prefix " + i) == null || map.get("phone prefix " + i).isEmpty() ||
                    !map.get("phone prefix " + i).matches("\\d{3}")) {
                // prefix exactly 3 digits
                return false;
            }

            if (map.get("phone number " + i) == null || map.get("phone number " + i).isEmpty() ||
                    !map.get("phone number " + i).matches("\\d+")) {
                // number only has digits
                return false;
            }

            if (Boolean.parseBoolean(map.get("phone isPrimary " + i))) {
                primaryCount++;
            }

            if (Boolean.parseBoolean(map.get("phone isTexting " + i))) {
                textingCount++;
            }
        }

        if (primaryCount != 1 || textingCount == 0) {
            return false;
        }

        return true;
    }
}
