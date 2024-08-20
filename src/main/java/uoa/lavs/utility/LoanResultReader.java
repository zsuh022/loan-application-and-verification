package uoa.lavs.utility;

import uoa.lavs.models.Loan;

import java.util.ArrayList;

public class LoanResultReader {

    public boolean processSearch(ArrayList<Loan> search) {
        if (search == null || search.isEmpty()) {
            return false;
        }

        for (Loan loan : search) {
            // do something
        }

        return true;
    }
}
