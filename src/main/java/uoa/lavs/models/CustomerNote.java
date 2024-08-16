package uoa.lavs.models;

public class CustomerNote {

    private String note;

    public CustomerNote() {
    }

    public String getNote() {
        return note;
    }

    public void addLine(String note) {
        this.note += note;
    }


}
