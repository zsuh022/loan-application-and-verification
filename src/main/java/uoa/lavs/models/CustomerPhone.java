package uoa.lavs.models;

public class CustomerPhone {

    private String type;
    private String prefix;
    private String number;
    private Boolean isPrimary;
    private Boolean isTexting;

    public CustomerPhone() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public Boolean getIsTexting() {
        return isTexting;
    }

    public void setIsTexting(Boolean isTexting) {
        this.isTexting = isTexting;
    }

    public void validatePrefix(String p) {
        // TODO:
    }

    public void validateNumber(String n) {
        // TODO:
    }
}
