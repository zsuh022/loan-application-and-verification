package uoa.lavs.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.models.Customer.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class CustomerValidator {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(CustomerValidator.class);
    public static final String TEMPORARY_CUSTOMER_ID_PREFIX = "TEMP_CUSTOMER_";

    public static String generateTemporaryCustomerId() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timeAsString = LocalDateTime.now().format(dtf);
        return TEMPORARY_CUSTOMER_ID_PREFIX + timeAsString;
    }

    public Customer createCustomer(Map<String, String> map) {
        logger.info("Creating customer");

        Customer customer = new Customer();
        customer.setCustomerId(generateTemporaryCustomerId());
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
            address.setPostCode(map.get("address postCode " + i));
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
            employer.setPostCode(map.get("employer postCode " + i));
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
            customer.setNote(note);
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

        logger.info("Created customer");
        return customer;
    }

    public boolean validateCustomer(Map<String, String> customerMap, List<Map<String, String>> addressList,
                                    List<Map<String, String>> emailList, List<Map<String, String>> employerList,
                                    List<Map<String, String>> phoneList) {
        logger.info("Validating customer");

        if (customerMap.get("name") == null || customerMap.get("name").isEmpty()) {
            logger.error("Customer name is not valid");
            return false;
        }

        String[] fullName = customerMap.get("name").split(" ");
        if (fullName.length < 2) {
            logger.error("Customer name is not valid");
            return false;
        }

        if (customerMap.get("dateOfBirth") == null || customerMap.get("dateOfBirth").isEmpty()) {
            logger.error("Customer date of birth is not valid");
            return false;
        }

        if (customerMap.get("occupation") == null || customerMap.get("occupation").isEmpty()) {
            logger.error("Customer occupation is not valid");
            return false;
        }

        if (customerMap.get("citizenship") == null || customerMap.get("citizenship").isEmpty()) {
            logger.error("Customer citizenship is not valid");
            return false;
        }

        if (customerMap.get("visa") == null || customerMap.get("visa").isEmpty()) {
            logger.error("Customer visa is not valid");
            return false;
        }

        if (!validateAddress(addressList)) {
            return false;
        }

        if (!validateEmail(emailList)) {
            return false;
        }

        if (!validateEmployer(employerList)) {
            return false;
        }

        if (!validatePhone(phoneList)) {
            return false;
        }

        logger.info("Validated customer");
        return true;
    }

    public boolean validateAddress(List<Map<String, String>> addressList) {
        logger.info("Validating address");

        int primaryCount = 0;
        int mailingCount = 0;

        for (Map<String, String> addressMap : addressList) {

            if (addressMap.get("type") == null || addressMap.get("type").isEmpty()) {
                logger.error("Address type is not valid");
                return false;
            }

            if (addressMap.get("line1") == null || addressMap.get("line1").isEmpty()) {
                logger.error("Address line1 is not valid");
                return false;
            }

            if (addressMap.get("suburb") == null || addressMap.get("suburb").isEmpty()) {
                logger.error("Address suburb is not valid");
                return false;
            }

            if (addressMap.get("city") == null || addressMap.get("city").isEmpty()) {
                logger.error("Address city is not valid");
                return false;
            }

            if (addressMap.get("postCode") == null || addressMap.get("postCode").isEmpty() ||
                    !addressMap.get("postCode").matches("\\d{4}")) {
                // assume post code is exactly 4 digits
                logger.error("Address postCode is not valid");
                return false;
            }

            if (addressMap.get("country") == null || addressMap.get("country").isEmpty()) {
                logger.error("Address country is not valid");
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
            logger.error("Address validation failed: there should be exactly one primary address, found {}", primaryCount);
            return false;
        }

        if (mailingCount == 0) {
            logger.error("Address validation failed: there should be at least one mailing address, found {}", mailingCount);
            return false;
        }

        logger.info("Validated address successfully");
        return true;
    }

    public boolean validateEmail(List<Map<String, String>> emailList) {
        logger.info("Validating email");

        int primaryCount = 0;

        for (Map<String, String> emailMap : emailList) {
            if (emailMap.get("address") == null || emailMap.get("address").isEmpty()) {
                logger.error("Email address is not valid: empty");
                return false;
            }

            String[] email = emailMap.get("address").split("@");
            if (email.length != 2) {
                // email split into prefix and domain
                logger.error("Email address is not valid: multiple @");
                return false;
            }

            String prefix = email[0];
            if (!prefix.matches("^[a-zA-Z0-9._-]+$")) {
                logger.error("Email address is not valid: prefix contains invalid characters");
                return false;
            }

            if (!Character.isLetter(prefix.charAt(0)) || !Character.isLetterOrDigit(prefix.charAt(prefix.length() - 1))) {
                logger.error("Email address is not valid: prefix starts or ends with invalid characters");
                return false;
            }

            if (prefix.contains("..") || prefix.contains("__") || prefix.contains("--")) {
                logger.error("Email address is not valid: prefix has invalid adjacent characters");
                return false;
            }

            String[] domain = email[1].split("\\.");
            if (domain.length <= 1) {
                logger.error("Email address is not valid: domain has no full stop");
                return false;
            }

            for (String domainPart : domain) {
                if (domainPart.isEmpty()) {
                    logger.error("Email address is not valid: domain empty");
                    return false;
                }

                if (!domainPart.matches("^[a-zA-Z0-9-]+$")) {
                    logger.error("Email address is not valid: domain contains invalid characters");
                    return false;
                }

                if (!Character.isLetterOrDigit(domainPart.charAt(0)) || !Character.isLetterOrDigit(domainPart.charAt(domainPart.length() - 1))) {
                    logger.error("Email address is not valid: domain starts or ends with invalid characters");
                    return false;
                }

                if (domainPart.contains("..") || domainPart.contains("--")) {
                    logger.error("Email address is not valid: domain has invalid adjacent characters");
                    return false;
                }
            }

            if (Boolean.parseBoolean(emailMap.get("isPrimary"))) {
                primaryCount++;
            }
        }

        if (primaryCount != 1) {
            logger.error("Email validation failed: there should be exactly one primary email, found {}", primaryCount);
            return false;
        }

        logger.info("Validated email successfully");
        return true;
    }

    public boolean validateEmployer(List<Map<String, String>> employerList) {
        logger.info("Validating employer");

        for (Map<String, String> employerMap : employerList) {
            if (employerMap.get("name") == null || employerMap.get("name").isEmpty()) {
                logger.error("Employer name is not valid: empty");
                return false;
            }

            String[] fullName = employerMap.get("name").split(" ");
            if (fullName.length < 2) {
                logger.error("Employer name is not valid: name needs first and last name");
                return false;
            }

            if (employerMap.get("line1") == null || employerMap.get("line1").isEmpty()) {
                logger.error("Employer line1 is not valid");
                return false;
            }

            if (employerMap.get("suburb") == null || employerMap.get("suburb").isEmpty()) {
                logger.error("Employer suburb is not valid");
                return false;
            }

            if (employerMap.get("city") == null || employerMap.get("city").isEmpty()) {
                logger.error("Employer city is not valid");
                return false;
            }

            if (employerMap.get("postCode") == null || employerMap.get("postCode").isEmpty() ||
                    !employerMap.get("postCode").matches("\\d{4}")) {
                // assume post code is 4 digits
                logger.error("Employer postCode is not valid");
                return false;
            }

            if (employerMap.get("country") == null || employerMap.get("country").isEmpty()) {
                logger.error("Employer country is not valid");
                return false;
            }

            if (employerMap.get("phone") == null || employerMap.get("phone").isEmpty() ||
                    !employerMap.get("phone").matches("\\d+")) {
                // phone only has digits
                logger.error("Employer phone is not valid");
                return false;
            }

            if (employerMap.get("email") == null || employerMap.get("email").isEmpty()) {
                logger.error("Employer email is not valid");
                return false;
            }

            if (employerMap.get("web") == null || employerMap.get("web").isEmpty()) {
                logger.error("Employer web is not valid");
                return false;
            }
        }

        logger.info("Validated employer successfully");
        return true;
    }

    public boolean validatePhone(List<Map<String, String>> phoneList) {
        logger.info("Validating phone");

        int primaryCount = 0;
        int textingCount = 0;

        for (Map<String, String> phoneMap : phoneList) {
            if (phoneMap.get("type") == null || phoneMap.get("type").isEmpty()) {
                logger.error("Phone type is not valid: empty");
                return false;
            }

            if (phoneMap.get("prefix") == null || phoneMap.get("prefix").isEmpty() ||
                    !phoneMap.get("prefix").matches("\\d{3}")) {
                // prefix exactly 3 digits
                logger.error("Phone prefix is not valid");
                return false;
            }

            if (phoneMap.get("number") == null || phoneMap.get("number").isEmpty() ||
                    !phoneMap.get("number").matches("\\d+")) {
                // number only has digits
                logger.error("Phone number is not valid");
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
            logger.error("Phone validation failed: there should be exactly one primary phone, found {}", primaryCount);
            return false;
        }

        if (textingCount == 0) {
            logger.error("Phone validation failed: there should be at least one texting phone, found {}", textingCount);
            return false;
        }

        logger.info("Validated phone successfully");
        return true;
    }
}
