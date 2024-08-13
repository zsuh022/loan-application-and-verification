package uoa.lavs.models;

public class CustomerEmail {

    private String address;
    private Boolean isPrimary;

    public CustomerEmail(String address, Boolean isPrimary) {
        this.address = address;
        this.isPrimary = isPrimary;
    }
}
