package uoa.lavs.utility;

import uoa.lavs.models.Loan;

import java.util.ArrayList;

public class LoanSearch {

    private ArrayList<Loan> loanArrayList = new ArrayList<>();

    public LoanSearch(ArrayList<Loan> loanArrayList) {
        this.loanArrayList = loanArrayList;
    }

    public ArrayList<Loan> getSearchResults(String search) {
        ArrayList<Loan> searchResult = new ArrayList<>();

        for (Loan loan : loanArrayList) {
            if (loan.getId().toLowerCase().contains(search.toLowerCase())) {
                searchResult.add(loan);
            }
        }

        return searchResult;
    }
}
