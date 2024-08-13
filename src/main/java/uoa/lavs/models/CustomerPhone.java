package uoa.lavs.models;

public class CustomerPhone {

    private String type;
    private String prefix;
    private String number;
    private Boolean isPrimary;
    private Boolean isTexting;

    public CustomerPhone(String type, String prefix, String number, Boolean isPrimary, Boolean isTexting) {
        this.type = type;
        this.prefix = prefix;
        this.number = number;
        this.isPrimary = isPrimary;
        this.isTexting = isTexting;
    }

    public void validatePrefix(String p) {
        // TODO:
    }

    public void validateNumber(String n) {
        // TODO:
    }
}
