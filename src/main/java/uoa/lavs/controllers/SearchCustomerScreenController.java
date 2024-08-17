package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class SearchCustomerScreenController {

    @FXML
    private FontAwesomeIconView searchCustomerBack;
    @FXML
    private FontAwesomeIconView searchCustomerEnter;
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
        // go to home screen
    }

    @FXML
    private void onSearchCustomerEnterClicked(MouseEvent event) {

    }

    @FXML
    private void onSearchCustomerEnterKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {

        }
    }
}
