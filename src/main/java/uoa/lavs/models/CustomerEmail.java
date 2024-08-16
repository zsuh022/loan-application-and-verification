package uoa.lavs.models;

import java.util.Objects;

public class CustomerEmail {

    private String address;
    private Boolean isPrimaryEmail;

    public CustomerEmail() {}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIsPrimaryEmail() {
        return isPrimaryEmail;
    }

    public void setIsPrimaryEmail(Boolean isPrimaryEmail) {
        this.isPrimaryEmail = isPrimaryEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerEmail that = (CustomerEmail) o;
        return Objects.equals(address, that.address) && Objects.equals(isPrimaryEmail, that.isPrimaryEmail);
    }
}
