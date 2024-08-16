package uoa.lavs.models;

import java.util.Objects;

public class CustomerNote {

    private String note;

    public CustomerNote() {}

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
