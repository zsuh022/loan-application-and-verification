package uoa.lavs.models.Customer;

import java.util.Objects;

public class CustomerPhone {

    private String type;
    private String prefix;
    private String number;
    private Boolean isPrimary;
    private Boolean isTexting;
    private Integer index;

    public CustomerPhone() {
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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
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
        uoa.lavs.models.Customer.CustomerPhone that = (uoa.lavs.models.Customer.CustomerPhone) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(prefix, that.prefix) &&
                Objects.equals(number, that.number) &&
                Objects.equals(isPrimary, that.isPrimary) &&
                Objects.equals(isTexting, that.isTexting);
    }
}
