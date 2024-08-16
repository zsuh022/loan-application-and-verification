package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SearchCustomerScreenController {

    @FXML private TextField searchCustomerBar;
    @FXML private FontAwesomeIconView searchCustomerBack;

    private String searchString;

    public boolean submitCustomerSearch() {
        // TODO:
        return true;
    }
}
