package uoa.lavs.models;

public class CustomerPhoneDTO {

    private String type;
    private String prefix;
    private String number;
    private Boolean isPrimary;
    private Boolean isTexting;
    private int index;

    public CustomerPhoneDTO() {
    }

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

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public Boolean getTexting() {
        return isTexting;
    }

    public void setTexting(Boolean texting) {
        isTexting = texting;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
