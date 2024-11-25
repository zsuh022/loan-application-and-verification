package uoa.lavs.models.Customer;

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
    
}
