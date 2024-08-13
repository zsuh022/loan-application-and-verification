package uoa.lavs.models;

import java.time.LocalDate;

public class Customer {

    private String customerId;
    private String title;
    private String name;
    private LocalDate dateOfBirth;
    private String occupation;
    private String citizenship;
    private String visa;

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
}
