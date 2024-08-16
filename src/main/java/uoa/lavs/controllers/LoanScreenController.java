package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import uoa.lavs.models.Loan;

import java.util.HashMap;

public class LoanScreenController {

    @FXML private FontAwesomeIconView loanBack;

    private Loan activeLoan;
    private HashMap<String, String> changesMap = new HashMap<>();

    public boolean submitLoanUpdate() {
        // TODO:
        return true;
    }
}
