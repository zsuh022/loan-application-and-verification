package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SearchCustomerScreenController {

    @FXML
    private FontAwesomeIconView searchCustomerBack;
    @FXML
    private TextField searchCustomerBar;
    @FXML
    private TextField customerSearchResult1;
    @FXML
    private TextField customerSearchResult2;
    @FXML
    private TextField customerSearchResult3;
    @FXML
    private TextField customerSearchResult4;

    private String searchString;

    public boolean submitCustomerSearch() {
        // TODO:
        return true;
    }

    @FXML
    private void onSearchCustomerBackClicked() {
        //
    }
}
