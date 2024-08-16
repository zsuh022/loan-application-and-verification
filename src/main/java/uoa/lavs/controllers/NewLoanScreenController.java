package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;

import java.util.HashMap;

public class NewLoanScreenController {

    @FXML private FontAwesomeIconView newLoanBack;

    private HashMap<String, String> loanValuesMap = new HashMap<>();

    public boolean submitNewLoan() {
        // TODO:
        return true;
    }
}
