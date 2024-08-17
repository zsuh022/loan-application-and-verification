package uoa.lavs.models;

public class CustomerAddressDTO {


    private int index;
    private String type;
    private String line1;
    private String line2;
    private String suburb;
    private String city;
    private Integer postCode;
    private String country;
    private Boolean isPrimary;
    private Boolean isMailing;

    CustomerAddressDTO() {
    }

    public int getNumber() {
        return index;
    }

    public void setNumber(int index) {
        this.index = index;
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

    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public Boolean getMailing() {
        return isMailing;
    }

    public void setMailing(Boolean mailing) {
        isMailing = mailing;
    }

}
