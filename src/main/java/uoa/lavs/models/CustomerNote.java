package uoa.lavs.models;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerNote that = (CustomerNote) o;
        return Objects.equals(note, that.note);
    }
}
