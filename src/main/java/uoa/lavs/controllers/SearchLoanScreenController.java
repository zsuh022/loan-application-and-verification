package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SearchLoanScreenController {

    @FXML private TextField searchLoanBar;
    @FXML private FontAwesomeIconView searchLoanBack;

    private String searchString;

    public boolean submitLoanSearch() {
        // TODO:
        return true;
    }
}
