package uoa.lavs.utility;

import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        validCustomerMap.put("dateOfBirth", "1999-06-11");
        validCustomerMap.put("occupation", "Engineer");
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
        employerMap.put("name", "Fake Company");
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
}
