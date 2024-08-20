package uoa.lavs.models.Customer;

import java.util.Objects;

public class CustomerAddress {

    private String type;
    private String line1;
    private String line2;
    private String suburb;
    private String city;
    private int postCode;
    private String country;
    private Boolean isPrimary;
    private Boolean isMailing;
    private int index;

    public CustomerAddress() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public Boolean getIsMailing() {
        return isMailing;
    }

    public void setIsMailing(Boolean isMailing) {
        this.isMailing = isMailing;
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
        uoa.lavs.models.Customer.CustomerAddress that = (uoa.lavs.models.Customer.CustomerAddress) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(line1, that.line1) &&
                Objects.equals(line2, that.line2) &&
                Objects.equals(suburb, that.suburb) &&
                Objects.equals(city, that.city) &&
                Objects.equals(postCode, that.postCode) &&
                Objects.equals(country, that.country) &&
                Objects.equals(isPrimary, that.isPrimary) &&
                Objects.equals(isMailing, that.isMailing);
    }
}
