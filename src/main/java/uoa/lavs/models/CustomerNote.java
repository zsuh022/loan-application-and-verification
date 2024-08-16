package uoa.lavs.models;

public class CustomerNote {

    private String note;
    private Integer number;

    public CustomerNote() {
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer n) {
        this.number = n;
    }

    public void addNote(String note) {
        this.note += note;
    }


}
