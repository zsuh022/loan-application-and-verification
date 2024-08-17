package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import uoa.lavs.Main;
import uoa.lavs.SceneManager.Screens;

import java.util.HashMap;

public class NewCustomerScreenController {

    private HashMap<String, String> customerValuesMap = new HashMap<>();

    @FXML
    private FontAwesomeIconView newCustomerBack;
    @FXML
    private Rectangle saveNewCustomerRectangle;
    // general
    @FXML
    private Button btnGeneralNewCustomer;
    @FXML
    private AnchorPane newCustomerGeneralPane;
    @FXML
    private TextField tfNewCustomerTitle;
    @FXML
    private TextField tfNewCustomerName;
    @FXML
    private TextField tfNewCustomerDob;
    @FXML
    private TextField tfNewCustomerOccupation;
    @FXML
    private TextField tfNewCustomerCitizenship;
    @FXML
    private TextField tfNewCustomerVisa;
    @FXML
    private TextField tfNewCustomerPhone;
    @FXML
    private TextField tfNewCustomerEmail;
    // address
    @FXML
    private Button btnAddressNewCustomer;
    @FXML
    private AnchorPane newCustomerAddressPane;
    @FXML
    private TextField tfNewCustomerType;
    @FXML
    private TextField tfNewCustomerLine1;
    @FXML
    private TextField tfNewCustomerLine2;
    @FXML
    private TextField tfNewCustomerSuburb;
    @FXML
    private TextField tfNewCustomerCity;
    @FXML
    private TextField tfNewCustomerPostcode;
    @FXML
    private TextField tfNewCustomerCountry;
    @FXML
    private CheckBox cbNewCustomerIsPrimary;
    @FXML
    private CheckBox cbNewCustomerIsMailing;
    // employer
    @FXML
    private Button btnEmployerNewCustomer;
    @FXML
    private AnchorPane newCustomerEmployerPane;
    @FXML
    private TextField tfNewCustomerEmployerName;
    @FXML
    private TextField tfNewCustomerEmployerLine1;
    @FXML
    private TextField tfNewCustomerEmployerLine2;
    @FXML
    private TextField tfNewCustomerEmployerSuburb;
    @FXML
    private TextField tfNewCustomerEmployerCity;
    @FXML
    private TextField tfNewCustomerEmployerPostcode;
    @FXML
    private TextField tfNewCustomerEmployerCountry;
    @FXML
    private TextField tfNewCustomerEmployerPhone;
    @FXML
    private TextField tfNewCustomerEmployerEmail;
    @FXML
    private TextField tfNewCustomerEmployerWeb;
    @FXML
    private CheckBox cbNewCustomerEmployerIsOwner;
    // notes
    @FXML
    private Button btnNotesNewCustomer;
    @FXML
    private AnchorPane newCustomerNotesPane;
    @FXML
    private TextArea taNewCustomerNotes;



    public boolean submitNewCustomer() {
        // TODO:
        return false;
    }

    @FXML
    private void onNewCustomerBackClicked(MouseEvent event) {
        // go to ...
        Main.setScreen(Screens.HOME);
    }
    

    @FXML
    private void onGeneralButtonClicked() {
        newCustomerGeneralPane.setVisible(true);
        newCustomerAddressPane.setVisible(false);
        newCustomerEmployerPane.setVisible(false);
        newCustomerNotesPane.setVisible(false);
        btnGeneralNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onAddressButtonClicked() {
        newCustomerGeneralPane.setVisible(false);
        newCustomerAddressPane.setVisible(true);
        newCustomerEmployerPane.setVisible(false);
        newCustomerNotesPane.setVisible(false);
        btnGeneralNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onEmployerButtonClicked() {
        newCustomerGeneralPane.setVisible(false);
        newCustomerAddressPane.setVisible(false);
        newCustomerEmployerPane.setVisible(true);
        newCustomerNotesPane.setVisible(false);
        btnGeneralNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onNotesButtonClicked() {
        newCustomerGeneralPane.setVisible(false);
        newCustomerAddressPane.setVisible(false);
        newCustomerEmployerPane.setVisible(false);
        newCustomerNotesPane.setVisible(true);
        btnGeneralNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }
}
