package uoa.lavs.utility;

import javafx.scene.control.Alert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import uoa.lavs.logging.Cache;
import uoa.lavs.models.Customer.*;
import static uoa.lavs.controllers.AlertManager.errorPopUp;
import static uoa.lavs.logging.LocalLogManager.TEMPORARY_CUSTOMER_ID_PREFIX;

public class CustomerValidator {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(CustomerValidator.class);

    public static String generateTemporaryCustomerId() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmss");
        String timeAsString = LocalDateTime.now().format(dtf);
        return TEMPORARY_CUSTOMER_ID_PREFIX + timeAsString;
    }

    public Customer createCustomer(Map<String, String> customerMap, List<Map<String, String>> addressList,
                                   List<Map<String, String>> emailList, List<Map<String, String>> employerList,
                                   List<Map<String, String>> phoneList) {
        logger.info("Creating customer in CustomerValidator with name {}", customerMap.get("firstName"));

        Customer customer = new Customer();
        customer.setCustomerId(generateTemporaryCustomerId());
        populateCustomer(customer, customerMap, addressList, emailList, employerList, phoneList);
        logger.info("Created customer in CustomerValidator with name {}", customerMap.get("firstName"));
        Cache.cacheCustomer(customer);
        return customer;
    }

    public Customer updateCustomer(Customer customer, Map<String, String> customerMap, List<Map<String, String>> addressList,
                                   List<Map<String, String>> emailList, List<Map<String, String>> employerList,
                                   List<Map<String, String>> phoneList) {
        populateCustomer(customer, customerMap, addressList, emailList, employerList, phoneList);
        logger.info("Updated customer in CustomerValidator with name {}", customerMap.get("firstName"));
        return customer;
    }

    private void populateCustomer(Customer customer, Map<String, String> customerMap, List<Map<String, String>> addressList,
                                  List<Map<String, String>> emailList, List<Map<String, String>> employerList,
                                  List<Map<String, String>> phoneList){
        if (customerMap.get("fullName") != null) {
            customer.setName(customerMap.get("fullName"));
        } else {
            customer.setTitle(customerMap.get("title"));
            String firstName = customerMap.get("firstName");
            String middleName = customerMap.get("middleName");
            String lastName = customerMap.get("lastName");
            String fullName = firstName + (middleName != null && !middleName.isEmpty() ? " " + middleName : "") + " " + lastName;
            customer.setName(fullName);
        }

        customer.setDateOfBirth(LocalDate.parse(customerMap.get("dob")));
        customer.setOccupation(customerMap.get("occupation"));
        customer.setCitizenship(customerMap.get("citizenship"));
        customer.setVisa(customerMap.get("visa"));
        customer.getAddressList().clear();
        for (Map<String, String> addressMap : addressList) {
            CustomerAddress address = new CustomerAddress();
            address.setType(addressMap.get("type"));
            address.setLine1(addressMap.get("line1"));
            address.setLine2(addressMap.get("line2"));
            address.setSuburb(addressMap.get("suburb"));
            address.setCity(addressMap.get("city"));
            address.setPostCode(addressMap.get("postCode"));
            address.setCountry(addressMap.get("country"));
            address.setIsPrimary(Boolean.parseBoolean(addressMap.get("isPrimary")));
            address.setIsMailing(Boolean.parseBoolean(addressMap.get("isMailing")));
            customer.addAddress(address);
        }
        customer.getEmailList().clear();
        for (Map<String, String> emailMap : emailList) {
            CustomerEmail email = new CustomerEmail();
            email.setAddress(emailMap.get("address"));
            email.setIsPrimary(Boolean.parseBoolean(emailMap.get("isPrimary")));
            customer.addEmail(email);
        }
        customer.getEmployerList().clear();
        if (!customerMap.get("occupation").equalsIgnoreCase("unemployed")) {
            for (Map<String, String> employerMap : employerList) {
                CustomerEmployer employer = new CustomerEmployer();
                employer.setName(employerMap.get("name"));
                employer.setLine1(employerMap.get("line1"));
                employer.setLine2(employerMap.get("line2"));
                employer.setSuburb(employerMap.get("suburb"));
                employer.setCity(employerMap.get("city"));
                employer.setPostCode(employerMap.get("postCode"));
                employer.setCountry(employerMap.get("country"));
                employer.setPhone(employerMap.get("phone"));
                employer.setEmail(employerMap.get("email"));
                employer.setWeb(employerMap.get("web"));
                employer.setIsOwner(Boolean.parseBoolean(employerMap.get("isOwner")));
                customer.addEmployer(employer);
            }
        }

        // only 1 note
        if (customerMap.containsKey("note")) {
            CustomerNote note = new CustomerNote();
            note.setNote(customerMap.get("note"));
            customer.setNote(note);
        }
        customer.getPhoneList().clear();
        for (Map<String, String> phoneMap : phoneList) {
            CustomerPhone phone = new CustomerPhone();
            phone.setType(phoneMap.get("type"));
            phone.setPrefix(phoneMap.get("prefix"));
            phone.setNumber(phoneMap.get("number"));
            phone.setIsPrimary(Boolean.parseBoolean(phoneMap.get("isPrimary")));
            phone.setIsTexting(Boolean.parseBoolean(phoneMap.get("isTexting")));
            customer.addPhone(phone);
        }
    }



    public boolean validateCustomer(Map<String, String> customerMap, List<Map<String, String>> addressList,
                                    List<Map<String, String>> emailList, List<Map<String, String>> employerList,
                                    List<Map<String, String>> phoneList) {

        if (customerMap.get("fullName") != null) {
            logger.info("Validating customer with name {}", customerMap.get("fullName"));
            if (customerMap.get("fullName") == null || customerMap.get("fullName").isEmpty()) {
                logger.error("ValidateCustomer method failed: Customer name is empty");
                errorPopUp("Name is empty", "Please enter a name.");
                return false;
            }
        } else {
            logger.info("Validating customer with name {}", customerMap.get("firstName"));

            if (customerMap.get("firstName") == null || customerMap.get("firstName").isEmpty()) {
                logger.error("ValidateCustomer method failed: Customer first name is empty");
                errorPopUp("First Name is empty", "Please enter a first name.");
                return false;
            }

            if (customerMap.get("lastName") == null || customerMap.get("lastName").isEmpty()) {
                logger.error("ValidateCustomer method failed: Customer last name is empty");
                errorPopUp("Last Name is empty", "Please enter a last name.");
                return false;
            }
        }

        if (customerMap.get("dob") == null || customerMap.get("dob").isEmpty()) {
            logger.error("ValidateCustomer method failed: Customer dob is empty");
            errorPopUp("Date of Birth is empty", "Please enter a date of birth.");
            return false;
        }

        LocalDate dob = LocalDate.parse(customerMap.get("dob"));
        if (dob.isAfter(LocalDate.now())) {
            logger.error("ValidateCustomer method failed: Customer dob is after current date");
            errorPopUp("Date of Birth is invalid", "Please enter a date of birth before the current day.");
            return false;
        }

        if (customerMap.get("occupation") == null || customerMap.get("occupation").isEmpty()) {
            logger.error("ValidateCustomer method failed: Customer occupation is empty");
            errorPopUp("Occupation is empty", "Please enter an occupation. Please enter 'unemployed' if applicable.");
            return false;
        }

        if (customerMap.get("citizenship") == null || customerMap.get("citizenship").isEmpty()) {
            logger.error("ValidateCustomer method failed: Customer citizenship is empty");
            errorPopUp("Citizenship is empty", "Please enter a citizenship.");
            return false;
        }

        if (customerMap.get("visa") == null || customerMap.get("visa").isEmpty()) {
            logger.error("ValidateCustomer method failed: Customer visa is empty");
            errorPopUp("Visa is empty", "Please enter a visa.");
            return false;
        }

        if (!validateAddress(addressList)) {
            return false;
        }

        if (!validateEmail(emailList)) {
            return false;
        }

        // if unemployed, employer is optional
        if (!customerMap.get("occupation").equals("unemployed")) {
            if (!validateEmployer(employerList)) {
                return false;
            }
        }

        if (!validatePhone(phoneList)) {
            return false;
        }

        logger.info("Validated customer with name {}", customerMap.get("firstName"));
        return true;
    }

    public boolean validateAddress(List<Map<String, String>> addressList) {
        logger.info("Validating address in CustomerValidator");

        int primaryCount = 0;
        int mailingCount = 0;

        for (Map<String, String> addressMap : addressList) {

            if (addressMap.get("type") == null || addressMap.get("type").isEmpty()) {
                logger.error("ValidateAddress method failed: Customer address type is empty");
                errorPopUp("Address Type is empty", "Please enter an address type.");
                return false;
            }

            if (addressMap.get("line1") == null || addressMap.get("line1").isEmpty()) {
                logger.error("ValidateAddress method failed: Customer address line1 is empty");
                errorPopUp("Address Line 1 is empty", "Please enter an address line 1.");
                return false;
            }

            if (addressMap.get("suburb") == null || addressMap.get("suburb").isEmpty()) {
                logger.error("ValidateAddress method failed: Customer address suburb is empty");
                errorPopUp("Address Suburb is empty", "Please enter an address suburb.");
                return false;
            }

            if (addressMap.get("city") == null || addressMap.get("city").isEmpty()) {
                logger.error("ValidateAddress method failed: Customer address city is empty");
                errorPopUp("Address City is empty", "Please enter an address city.");
                return false;
            }

            if (addressMap.get("postCode") == null || addressMap.get("postCode").isEmpty() ||
                    !addressMap.get("postCode").matches("\\d{4}")) {
                // assume post code is exactly 4 digits
                logger.error("ValidateAddress method failed: Customer address postCode is empty or is not 4 digits");
                errorPopUp("Address Post Code is empty or invalid", "Please enter a 4-digit post code.");
                return false;
            }

            if (addressMap.get("country") == null || addressMap.get("country").isEmpty()) {
                logger.error("ValidateAddress method failed: Customer address country is empty");
                errorPopUp("Address Country is empty", "Please enter an address country.");
                return false;
            }

            if (Boolean.parseBoolean(addressMap.get("isPrimary"))) {
                primaryCount++;
            }

            if (Boolean.parseBoolean(addressMap.get("isMailing"))) {
                mailingCount++;
            }
        }

        if (primaryCount != 1) {
            logger.error("ValidateAddress method failed: there should be exactly one primary address, found {}", primaryCount);
            errorPopUp("Primary Address error", "Please select exactly one primary address.");
            return false;
        }

        if (mailingCount == 0) {
            logger.error("ValidateAddress method failed: there should be at least one mailing address, found {}", mailingCount);
            errorPopUp("Mailing Address error", "Please select at least one mailing address.");
            return false;
        }

        logger.info("Validated address in CustomerValidator successfully");
        return true;
    }

    public boolean validateEmail(List<Map<String, String>> emailList) {
        logger.info("Validating email in CustomerValidator");

        int primaryCount = 0;

        for (Map<String, String> emailMap : emailList) {
            if (emailMap.get("address") == null || emailMap.get("address").isEmpty()) {
                logger.error("ValidateEmail method failed: Customer email address is empty");
                errorPopUp("Email Address is empty", "Please enter an email address.");
                return false;
            }

            String[] email = emailMap.get("address").split("@");
            if (email.length != 2) {
                // email split into prefix and domain
                logger.error("ValidateEmail method failed: Customer email address needs exactly one @");
                errorPopUp("Email Address error", "Please enter a valid email address. (e.g. johndoe@gmail.com)");
                return false;
            }

            String prefix = email[0];
            if (!prefix.matches("^[a-zA-Z0-9._-]+$")) {
                logger.error("ValidateEmail method failed: Customer email address prefix contains invalid characters");
                errorPopUp("Email Address error", "Email address contains invalid characters.");
                return false;
            }

            if (!Character.isLetter(prefix.charAt(0)) || !Character.isLetterOrDigit(prefix.charAt(prefix.length() - 1))) {
                logger.error("ValidateEmail method failed: Customer email address prefix starts or ends with invalid characters");
                errorPopUp("Email Address error", "Email address starts or ends with invalid characters.");
                return false;
            }

            if (prefix.contains("..") || prefix.contains("__") || prefix.contains("--")) {
                logger.error("ValidateEmail method failed: Customer email address prefix contains invalid adjacent characters");
                errorPopUp("Email Address error", "Email address contains invalid adjacent characters.");
                return false;
            }

            String[] domain = email[1].split("\\.");
            if (domain.length <= 1) {
                logger.error("ValidateEmail method failed: Customer email address domain needs at least one full stop");
                errorPopUp("Email Address error", "Email address domain needs at least one full stop.");
                return false;
            }

            for (String domainPart : domain) {
                if (domainPart.isEmpty()) {
                    logger.error("ValidateEmail method failed: Email domain part is empty");
                    errorPopUp("Email Address error", "Email address domain is blank.");
                    return false;
                }

                if (!domainPart.matches("^[a-zA-Z0-9-]+$")) {
                    logger.error("ValidateEmail method failed: Email domain part contains invalid characters");
                    errorPopUp("Email Address error", "Email address domain contains invalid characters.");
                    return false;
                }

                if (!Character.isLetterOrDigit(domainPart.charAt(0)) || !Character.isLetterOrDigit(domainPart.charAt(domainPart.length() - 1))) {
                    logger.error("ValidateEmail method failed: Email domain part starts or ends with invalid characters");
                    errorPopUp("Email Address error", "Email address domain starts or ends with invalid characters.");
                    return false;
                }

                if (domainPart.contains("--")) {
                    logger.error("ValidateEmail method failed: Email domain part contains invalid adjacent characters");
                    errorPopUp("Email Address error", "Email address domain contains invalid adjacent characters.");
                    return false;
                }
            }

            if (Boolean.parseBoolean(emailMap.get("isPrimary"))) {
                primaryCount++;
            }
        }

        if (primaryCount != 1) {
            logger.error("ValidateEmail method failed: there should be exactly one primary email, found {}", primaryCount);
            errorPopUp("Primary Email error", "Please select exactly one primary email.");
            return false;
        }

        logger.info("Validated email in CustomerValidator successfully");
        return true;
    }

    public boolean validateEmployer(List<Map<String, String>> employerList) {
        logger.info("Validating employer in CustomerValidator");

        for (Map<String, String> employerMap : employerList) {
            if (employerMap.get("name") == null || employerMap.get("name").isEmpty()) {
                logger.error("ValidateEmployer method failed: Customer employer name is empty");
                errorPopUp("Employer Name is empty", "Please enter an employer name.");
                return false;
            }

            if (employerMap.get("line1") == null || employerMap.get("line1").isEmpty()) {
                logger.error("ValidateEmployer method failed: Customer employer line1 is empty");
                errorPopUp("Employer Line 1 is empty", "Please enter an employer line 1.");
                return false;
            }

            if (employerMap.get("suburb") == null || employerMap.get("suburb").isEmpty()) {
                logger.error("ValidateEmployer method failed: Customer employer suburb is empty");
                errorPopUp("Employer Suburb is empty", "Please enter an employer suburb.");
                return false;
            }

            if (employerMap.get("city") == null || employerMap.get("city").isEmpty()) {
                logger.error("ValidateEmployer method failed: Customer employer city is empty");
                errorPopUp("Employer City is empty", "Please enter an employer city.");
                return false;
            }

            if (employerMap.get("postCode") == null || employerMap.get("postCode").isEmpty() ||
                    !employerMap.get("postCode").matches("\\d{4}")) {
                // assume post code is 4 digits
                logger.error("ValidateEmployer method failed: Customer employer postCode is empty or is not 4 digits");
                errorPopUp("Employer Post Code is empty or invalid", "Please enter a 4-digit post code.");
                return false;
            }

            if (employerMap.get("country") == null || employerMap.get("country").isEmpty()) {
                logger.error("ValidateEmployer method failed: Customer employer country is empty");
                errorPopUp("Employer Country is empty", "Please enter an employer country.");
                return false;
            }

            if (employerMap.get("phone") == null || employerMap.get("phone").isEmpty() ||
                    !employerMap.get("phone").matches("\\d+")) {
                // phone only has digits
                logger.error("ValidateEmployer method failed: Customer employer phone is empty or is not digits");
                errorPopUp("Employer Phone is empty or invalid", "Please enter a valid phone number.");
                return false;
            }

            if (employerMap.get("email") == null || employerMap.get("email").isEmpty()) {
                logger.error("ValidateEmployer method failed: Customer employer email is empty");
                errorPopUp("Employer Email is empty", "Please enter an employer email.");
                return false;
            }
        }

        logger.info("Validated employer in CustomerValidator successfully");
        return true;
    }

    public boolean validatePhone(List<Map<String, String>> phoneList) {
        logger.info("Validating phone in CustomerValidator");

        int primaryCount = 0;
        int textingCount = 0;

        for (Map<String, String> phoneMap : phoneList) {
            if (phoneMap.get("type") == null || phoneMap.get("type").isEmpty()) {
                logger.error("ValidatePhone method failed: Customer phone type is empty");
                errorPopUp("Phone Type is empty", "Please enter a phone type.");
                return false;
            }

            if (phoneMap.get("prefix") == null || phoneMap.get("prefix").isEmpty() ||
                    !phoneMap.get("prefix").matches("\\d{3}")) {
                // prefix exactly 3 digits
                logger.error("ValidatePhone method failed: Customer phone prefix is empty or is not 3 digits");
                errorPopUp("Phone Prefix is empty or invalid", "Please enter a 3-digit phone prefix.");
                return false;
            }

            if (phoneMap.get("number") == null || phoneMap.get("number").isEmpty() ||
                    !phoneMap.get("number").matches("\\d+")) {
                // number only has digits
                logger.error("ValidatePhone method failed: Customer phone number is empty or is not digits");
                errorPopUp("Phone Number is empty or invalid", "Please enter a valid phone number.");
                return false;
            }

            if (Boolean.parseBoolean(phoneMap.get("isPrimary"))) {
                primaryCount++;
            }

            if (Boolean.parseBoolean(phoneMap.get("isTexting"))) {
                textingCount++;
            }
        }

        if (primaryCount != 1) {
            logger.error("ValidatePhone method failed: there should be exactly one primary phone, found {}", primaryCount);
            errorPopUp("Primary Phone error", "Please select exactly one primary phone.");
            return false;
        }

        if (textingCount == 0) {
            logger.error("ValidatePhone method failed: there should be at least one texting phone, found {}", textingCount);
            errorPopUp("Texting Phone error", "Please select at least one texting phone.");
            return false;
        }

        logger.info("Validated phone in CustomerValidator successfully");
        return true;
    }
}
