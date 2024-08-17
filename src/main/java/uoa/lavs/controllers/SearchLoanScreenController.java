package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SearchLoanScreenController {

    @FXML
    private FontAwesomeIconView searchLoanBack;
    @FXML
    private TextField searchLoanBar;
    @FXML
    private TextField loanSearchResult1;
    @FXML
    private TextField loanSearchResult2;
    @FXML
    private TextField loanSearchResult3;
    @FXML
    private TextField loanSearchResult4;

    private String searchString;

    public boolean submitLoanSearch() {
        // TODO:
        return true;
    }

    @FXML
    private void onSearchLoanBackClicked(MouseEvent event) {
        //
    }
}
