package uoa.lavs.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Customer {

    private String customerId;
    private String title;
    private String name;
    private LocalDate dateOfBirth;
    private String occupation;
    private String citizenship;
    private String visa;
    private List<CustomerAddress> addressList = new ArrayList<>();
    private List<CustomerEmail> emailList = new ArrayList<>();
    private CustomerNote note = new CustomerNote();
    private List<CustomerPhone> phoneList = new ArrayList<>();
    private List<CustomerEmployer> employerList = new ArrayList<>();

    public Customer() {
    }

    public String getId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa;
    }

    public List<CustomerAddress> getAddressList() {
        return addressList;
    }

    public void addAddress(CustomerAddress address) {
        this.addressList.add(address);
    }

    public List<CustomerEmail> getEmailList() {
        return emailList;
    }

    public void addEmail(CustomerEmail email) {
        this.emailList.add(email);
    }

    public List<CustomerEmployer> getEmployerList() {
        return employerList;
    }

    public void addEmployer(CustomerEmployer employer) {
        this.employerList.add(employer);
    }

    public CustomerNote getNote() {
        return note;
    }

    public void setNote(CustomerNote note) {
        this.note = note;
    }

    public List<CustomerPhone> getPhoneList() {
        return phoneList;
    }

    public void addPhone(CustomerPhone phone) {
        this.phoneList.add(phone);
    }

    public void writeCustomer(HashMap<String, String> map) {
        map.put("customerId", customerId);
        map.put("title", title);
        map.put("name", name);
        map.put("dateOfBirth", dateOfBirth.toString());
        map.put("occupation", occupation);
        map.put("citizenship", citizenship);
        map.put("visa", visa);

        writeAddress(map);
        writeEmail(map);
        writeEmployer(map);
        writeNote(map);
        writePhone(map);
    }

    public void writeAddress(HashMap<String, String> map) {
        for (int i = 0; i < addressList.size(); i++) {
            CustomerAddress address = addressList.get(i);
            map.put("address type " + i, address.getType());
            map.put("address line1 " + i, address.getLine1());
            map.put("address line2 " + i, address.getLine2());
            map.put("address suburb " + i, address.getSuburb());
            map.put("address city " + i, address.getCity());
            map.put("address postCode " + i, String.valueOf(address.getPostCode()));
            map.put("address country " + i, address.getCountry());
            map.put("address isPrimary " + i, address.getIsPrimary().toString());
            map.put("address isMailing " + i, address.getIsMailing().toString());
        }
    }

    public void writeEmail(HashMap<String, String> map) {
        for (int i = 0; i < emailList.size(); i++) {
            CustomerEmail email = emailList.get(i);
            map.put("email address " + i, email.getAddress());
            map.put("email isPrimary " + i, email.getIsPrimary().toString());
        }
    }

    public void writeEmployer(HashMap<String, String> map) {
        for (int i = 0; i < noteList.size(); i++) {
            CustomerEmployer employer = employerList.get(i);
            map.put("employer name " + i, employer.getName());
            map.put("employer line1 " + i, employer.getLine1());
            map.put("employer line2 " + i, employer.getLine2());
            map.put("employer suburb " + i, employer.getSuburb());
            map.put("employer city " + i, employer.getCity());
            map.put("employer postCode " + i, String.valueOf(employer.getPostCode()));
            map.put("employer country " + i, employer.getCountry());
            map.put("employer phone " + i, employer.getPhone());
            map.put("employer email " + i, employer.getEmail());
            map.put("employer web " + i, employer.getWeb());
            map.put("employer isOwner " + i, employer.getIsOwner().toString());
        }
    }

    public void writeNote(HashMap<String, String> map) {
        for (int i = 0; i < noteList.size(); i++) {
            CustomerNote note = noteList.get(i);
            map.put("note " + i, note.getNote());
        }
    }

    public void writePhone(HashMap<String, String> map) {
        for (int i = 0; i < phoneList.size(); i++) {
            CustomerPhone phone = phoneList.get(i);
            map.put("phone type " + i, phone.getType());
            map.put("phone prefix " + i, phone.getPrefix());
            map.put("phone number " + i, phone.getNumber());
            map.put("phone isPrimary " + i, phone.getIsPrimary().toString());
            map.put("phone isTexting " + i, phone.getIsTexting().toString());
        }
    }
}
