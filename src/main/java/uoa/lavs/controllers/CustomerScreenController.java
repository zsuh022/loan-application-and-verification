package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import uoa.lavs.models.Customer;

import java.util.HashMap;

public class CustomerScreenController {

    @FXML private FontAwesomeIconView CustomerBack;

    private Customer activeCustomer;
    private HashMap<String, String> changeMap = new HashMap<>();

    public boolean submitCustomerUpdate(HashMap<String, String> map) {
        // TODO:
        return true;
    }

    public boolean submitAddressUpdate(HashMap<String, String> map) {
        // TODO:
        return true;
    }

    public boolean submitEmailUpdate(HashMap<String, String> map) {
        // TODO:
        return true;
    }

    public boolean submitNoteUpdate(HashMap<String, String> map) {
        // TODO:
        return true;
    }

    public boolean submitPhoneUpdate(HashMap<String, String> map) {
        // TODO:
        return true;
    }
}
