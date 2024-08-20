package uoa.lavs.models.Customer;

import java.util.Objects;

public class CustomerEmail {

    private String address;
    private Boolean isPrimary;
    private int index;

    public CustomerEmail() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        uoa.lavs.models.Customer.CustomerEmail that = (uoa.lavs.models.Customer.CustomerEmail) o;
        return Objects.equals(address, that.address) && Objects.equals(isPrimary, that.isPrimary);
    }
}
