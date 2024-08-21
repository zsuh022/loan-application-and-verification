package uoa.lavs.models.Customer;

import java.time.LocalDate;

public class CustomerSummary {

    private String id;
    private String name;
    private LocalDate dob;
    private String email;

    public CustomerSummary() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

}
