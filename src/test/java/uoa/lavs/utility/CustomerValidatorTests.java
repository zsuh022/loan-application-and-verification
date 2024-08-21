package uoa.lavs.utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.models.Customer.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerValidatorTests {

    private CustomerValidator validator;
    private Map<String, String> validCustomerMap;
    private List<Map<String, String>> validAddressList;
    private List<Map<String, String>> validEmailList;
    private List<Map<String, String>> validEmployerList;
    private List<Map<String, String>> validPhoneList;

    @BeforeEach
    void setUp() {
        validator = new CustomerValidator();
        validCustomerMap = new HashMap<>();
        validCustomerMap.put("title", "Mr.");
        validCustomerMap.put("firstName", "John");
        validCustomerMap.put("middleName", "A.");
        validCustomerMap.put("lastName", "Doe");
        validCustomerMap.put("dob", "1999-06-11");
        validCustomerMap.put("occupation", "Software Engineer");
        validCustomerMap.put("citizenship", "New Zealand");
        validCustomerMap.put("visa", "Visa123");

        validAddressList = new ArrayList<>();
        Map<String, String> addressMap = new HashMap<>();
        addressMap.put("type", "Residential");
        addressMap.put("line1", "123 Fake Street");
        addressMap.put("suburb", "Fake Town");
        addressMap.put("city", "Auckland");
        addressMap.put("postCode", "1234");
        addressMap.put("country", "New Zealand");
        addressMap.put("isPrimary", "true");
        addressMap.put("isMailing", "true");
        validAddressList.add(addressMap);

        validEmailList = new ArrayList<>();
        Map<String, String> emailMap = new HashMap<>();
        emailMap.put("address", "john.doe@example.com");
        emailMap.put("isPrimary", "true");
        validEmailList.add(emailMap);

        validEmployerList = new ArrayList<>();
        Map<String, String> employerMap = new HashMap<>();
        employerMap.put("name", "John Two");
        employerMap.put("line1", "123 Fake Road");
        employerMap.put("suburb", "Fake Town");
        employerMap.put("city", "Auckland");
        employerMap.put("postCode", "5678");
        employerMap.put("country", "New Zealand");
        employerMap.put("phone", "0987654321");
        employerMap.put("email", "contact@fakecompany.com");
        employerMap.put("web", "www.fakecompany.com");
        employerMap.put("isOwner", "true");
        validEmployerList.add(employerMap);

        validPhoneList = new ArrayList<>();
        Map<String, String> phoneMap = new HashMap<>();
        phoneMap.put("type", "Mobile");
        phoneMap.put("prefix", "021");
        phoneMap.put("number", "1234567");
        phoneMap.put("isPrimary", "true");
        phoneMap.put("isTexting", "true");
        validPhoneList.add(phoneMap);
    }

    @Test
    void testGenerateTemporaryCustomerId() {
        String tempId = CustomerValidator.generateTemporaryCustomerId();
        assertNotNull(tempId);
        assertTrue(tempId.startsWith(CustomerValidator.TEMPORARY_CUSTOMER_ID_PREFIX));
    }

    @Test
    void testCreateCustomer() {
        Customer customer = validator.createCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList);
        assertNotNull(customer);
        assertNotNull(customer.getId());
        assertEquals("Mr.", customer.getTitle());
        assertEquals("John A. Doe", customer.getName());
        assertEquals(LocalDate.of(1999, 6, 11), customer.getDob());
        assertEquals("Software Engineer", customer.getOccupation());
        assertEquals("New Zealand", customer.getCitizenship());
        assertEquals("Visa123", customer.getVisa());

        // Validate address
        assertNotNull(customer.getAddressList());
        assertEquals(1, customer.getAddressList().size());
        CustomerAddress address = customer.getAddressList().get(0);
        assertEquals("Residential", address.getType());
        assertEquals("123 Fake Street", address.getLine1());
        assertEquals("Fake Town", address.getSuburb());
        assertEquals("Auckland", address.getCity());
        assertEquals("1234", address.getPostCode());
        assertEquals("New Zealand", address.getCountry());
        assertTrue(address.getIsPrimary());
        assertTrue(address.getIsMailing());

        // Validate email
        assertNotNull(customer.getEmailList());
        assertEquals(1, customer.getEmailList().size());
        CustomerEmail email = customer.getEmailList().get(0);
        assertEquals("john.doe@example.com", email.getAddress());
        assertTrue(email.getIsPrimary());

        // Validate employer
        assertNotNull(customer.getEmployerList());
        assertEquals(1, customer.getEmployerList().size());
        CustomerEmployer employer = customer.getEmployerList().get(0);
        assertEquals("John Two", employer.getName());
        assertEquals("123 Fake Road", employer.getLine1());
        assertEquals("Fake Town", employer.getSuburb());
        assertEquals("Auckland", employer.getCity());
        assertEquals("5678", employer.getPostCode());
        assertEquals("New Zealand", employer.getCountry());
        assertEquals("0987654321", employer.getPhone());
        assertEquals("contact@fakecompany.com", employer.getEmail());
        assertEquals("www.fakecompany.com", employer.getWeb());
        assertTrue(employer.getIsOwner());

        // Validate phone
        assertNotNull(customer.getPhoneList());
        assertEquals(1, customer.getPhoneList().size());
        CustomerPhone phone = customer.getPhoneList().get(0);
        assertEquals("Mobile", phone.getType());
        assertEquals("021", phone.getPrefix());
        assertEquals("1234567", phone.getNumber());
        assertTrue(phone.getIsPrimary());
        assertTrue(phone.getIsTexting());
    }

    @Test
    void testValidateCustomer_valid() {
        assertTrue(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_nullFirstName() {
        validCustomerMap.put("firstName", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_emptyFirstName() {
        validCustomerMap.put("firstName", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_nullLastName() {
        validCustomerMap.put("lastName", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_emptyLastName() {
        validCustomerMap.put("lastName", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_nullDateOfBirth() {
        validCustomerMap.put("dateOfBirth", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_emptyDateOfBirth() {
        validCustomerMap.put("dateOfBirth", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_nullOccupation() {
        validCustomerMap.put("occupation", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_emptyOccupation() {
        validCustomerMap.put("occupation", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_nullCitizenship() {
        validCustomerMap.put("citizenship", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_emptyCitizenship() {
        validCustomerMap.put("citizenship", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_nullVisa() {
        validCustomerMap.put("visa", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_emptyVisa() {
        validCustomerMap.put("visa", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateAddress_nullType() {
        validAddressList.get(0).put("type", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateAddress_emptyType() {
        validAddressList.get(0).put("type", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateAddress_nullLine1() {
        validAddressList.get(0).put("line1", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateAddress_emptyLine1() {
        validAddressList.get(0).put("line1", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateAddress_nullSuburb() {
        validAddressList.get(0).put("suburb", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateAddress_emptySuburb() {
        validAddressList.get(0).put("suburb", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateAddress_nullCity() {
        validAddressList.get(0).put("city", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateAddress_emptyCity() {
        validAddressList.get(0).put("city", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateAddress_nullPostCode() {
        validAddressList.get(0).put("postCode", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateAddress_emptyPostCode() {
        validAddressList.get(0).put("postCode", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateAddress_postCode1() {
        validAddressList.get(0).put("postCode", "abc1");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateAddress_postCode2() {
        validAddressList.get(0).put("postCode", "123");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateAddress_postCode3() {
        validAddressList.get(0).put("postCode", "12345");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateAddress_nullCountry() {
        validAddressList.get(0).put("country", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateAddress_emptyCountry() {
        validAddressList.get(0).put("country", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateAddress_multiplePrimaryAddresses() {
        Map<String, String> additionalAddress = new HashMap<>(validAddressList.get(0));
        additionalAddress.put("isPrimary", "true");
        validAddressList.add(additionalAddress);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateAddress_noPrimaryAddress() {
        validAddressList.get(0).put("isPrimary", "false");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateAddress_multipleMailingAddresses() {
        Map<String, String> additionalAddress = new HashMap<>(validAddressList.get(0));
        additionalAddress.put("isPrimary", "false");
        additionalAddress.put("isMailing", "true");
        validAddressList.add(additionalAddress);
        assertTrue(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateAddress_noMailingAddress() {
        validAddressList.get(0).put("isMailing", "false");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_nullAddress() {
        validEmailList.get(0).put("address", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_emptyAddress() {
        validEmailList.get(0).put("address", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_emailContainsNoAt() {
        validEmailList.get(0).put("address", "abc");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_emailContainsMultipleAt() {
        validEmailList.get(0).put("address", "abc@gmail@com");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_emailPrefixInvalidChars1() {
        validEmailList.get(0).put("address", "a(bc@gmail.com");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_emailPrefixInvalidChars2() {
        validEmailList.get(0).put("address", "ab]c@gmail.com");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_emailPrefixInvalidChars3() {
        validEmailList.get(0).put("address", "a,bc@gmail.com");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_emailPrefixStartNotLetter() {
        validEmailList.get(0).put("address", "0abc@gmail.com");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_emailPrefixEndNotLetterOrDigit() {
        validEmailList.get(0).put("address", "abc.@gmail.com");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_emailPrefixContainsInvalidAdjacentChars1() {
        validEmailList.get(0).put("address", "a..bc@gmail.com");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_emailPrefixContainsInvalidAdjacentChars2() {
        validEmailList.get(0).put("address", "a__bc@gmail.com");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_emailPrefixContainsInvalidAdjacentChars3() {
        validEmailList.get(0).put("address", "a--bc@gmail.com");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_emailDomainContainsNoFullStop() {
        validEmailList.get(0).put("address", "abc@gmailcom");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_emailDomainPartEmpty() {
        validEmailList.get(0).put("address", "abc@gmail..com");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_emailDomainPartInvalidChars() {
        validEmailList.get(0).put("address", "abc@gm()ail.com");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_emailDomainPartStartNotLetterOrDigit() {
        validEmailList.get(0).put("address", "abc@gmail.-com");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_emailDomainPartEndNotLetterOrDigit() {
        validEmailList.get(0).put("address", "abc@gmail-.com");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_emailDomainPartContainsInvalidAdjacentChars1() {
        validEmailList.get(0).put("address", "abc@gm--ail.com");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_multiplePrimaryEmails() {
        Map<String, String> additionalEmail = new HashMap<>(validEmailList.get(0));
        additionalEmail.put("isPrimary", "true");
        validEmailList.add(additionalEmail);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmail_noPrimaryEmail() {
        validEmailList.get(0).put("isPrimary", "false");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_nullName() {
        validEmployerList.get(0).put("name", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_emptyName() {
        validEmployerList.get(0).put("name", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_nullLine1() {
        validEmployerList.get(0).put("line1", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_emptyLine1() {
        validEmployerList.get(0).put("line1", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_nullSuburb() {
        validEmployerList.get(0).put("suburb", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_emptySuburb() {
        validEmployerList.get(0).put("suburb", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_nullCity() {
        validEmployerList.get(0).put("city", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_emptyCity() {
        validEmployerList.get(0).put("city", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_nullPostCode() {
        validEmployerList.get(0).put("postCode", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_emptyPostCode() {
        validEmployerList.get(0).put("postCode", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_postCode1() {
        validEmployerList.get(0).put("postCode", "1abc");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_postCode2() {
        validEmployerList.get(0).put("postCode", "987");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_postCode3() {
        validEmployerList.get(0).put("postCode", "98765");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_nullCountry() {
        validEmployerList.get(0).put("country", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_emptyCountry() {
        validEmployerList.get(0).put("country", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_nullPhone() {
        validEmployerList.get(0).put("phone", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_emptyPhone() {
        validEmployerList.get(0).put("phone", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_phoneContainsCharsOtherThanDigits() {
        validEmployerList.get(0).put("phone", "12345a678");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_nullEmail() {
        validEmployerList.get(0).put("email", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validateEmployer_emptyEmail() {
        validEmployerList.get(0).put("email", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validatePhone_nullType() {
        validPhoneList.get(0).put("type", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validatePhone_emptyType() {
        validPhoneList.get(0).put("type", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validatePhone_nullPrefix() {
        validPhoneList.get(0).put("prefix", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validatePhone_emptyPrefix() {
        validPhoneList.get(0).put("prefix", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validatePhone_prefixNotThreeDigits1() {
        validPhoneList.get(0).put("prefix", "12");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validatePhone_prefixNotThreeDigits2() {
        validPhoneList.get(0).put("prefix", "1234");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validatePhone_nullNumber() {
        validPhoneList.get(0).put("number", null);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validatePhone_emptyNumber() {
        validPhoneList.get(0).put("number", "");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validatePhone_numberContainsCharsOtherThanDigits() {
        validEmployerList.get(0).put("phone", "12345a678");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validatePhone_multiplePrimaryPhones() {
        Map<String, String> additionalPhone = new HashMap<>(validPhoneList.get(0));
        additionalPhone.put("isPrimary", "true");
        validAddressList.add(additionalPhone);
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validatePhone_noPrimaryPhone() {
        validPhoneList.get(0).put("isPrimary", "false");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validatePhone_multipleTextingPhone() {
        Map<String, String> additionalPhone = new HashMap<>(validPhoneList.get(0));
        additionalPhone.put("isPrimary", "false");
        additionalPhone.put("isTexting", "true");
        validPhoneList.add(additionalPhone);
        assertTrue(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_validatePhone_noTextingPhone() {
        validPhoneList.get(0).put("isTexting", "false");
        assertFalse(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }

    @Test
    void testValidateCustomer_UnemployedNoEmployer() {
        validCustomerMap.put("occupation", "unemployed");
        assertTrue(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, new ArrayList<>(), validPhoneList));
    }

    @Test
    void testValidateCustomerWithNote() {
        validCustomerMap.put("note", "This is a customer note.");
        assertTrue(validator.validateCustomer(validCustomerMap, validAddressList, validEmailList, validEmployerList, validPhoneList));
    }
}
