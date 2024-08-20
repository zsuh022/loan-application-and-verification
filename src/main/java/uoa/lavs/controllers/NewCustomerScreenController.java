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
import uoa.lavs.models.Customer;
import uoa.lavs.utility.CustomerValidator;

import java.util.HashMap;
import java.util.Map;

public class NewCustomerScreenController {

    private CustomerValidator customerValidator = new CustomerValidator();
    private Map<String, String> customerValuesMap = new HashMap<>();

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
        fillCustomerValuesMap();

        if (customerValidator.validateCustomer(customerValuesMap)) {
            Customer newCustomer = customerValidator.createCustomer(customerValuesMap);
            return true;
        }

        return false;
    }

    private void fillCustomerValuesMap() {
        // general
        customerValuesMap.put("title", tfNewCustomerTitle.getText());
        customerValuesMap.put("name", tfNewCustomerName.getText());
        customerValuesMap.put("dob", tfNewCustomerDob.getText());
        customerValuesMap.put("occupation", tfNewCustomerOccupation.getText());
        customerValuesMap.put("citizenship", tfNewCustomerCitizenship.getText());
        customerValuesMap.put("visa", tfNewCustomerVisa.getText());

        // address
        customerValuesMap.put("address type 0", tfNewCustomerType.getText());
        customerValuesMap.put("address line1 0", tfNewCustomerLine1.getText());
        customerValuesMap.put("address line2 0", tfNewCustomerLine2.getText());
        customerValuesMap.put("address suburb 0", tfNewCustomerSuburb.getText());
        customerValuesMap.put("address city 0", tfNewCustomerCity.getText());
        customerValuesMap.put("address postCode 0", tfNewCustomerPostcode.getText());
        customerValuesMap.put("address country 0", tfNewCustomerCountry.getText());
        customerValuesMap.put("address isPrimary 0", String.valueOf(cbNewCustomerIsPrimary.isSelected()));
        customerValuesMap.put("address isMailing 0", String.valueOf(cbNewCustomerIsMailing.isSelected()));

        // email
        customerValuesMap.put("email address 0", tfNewCustomerEmail.getText());
        // TODO: customerValuesMap.put("email isPrimary 0", String.valueOf(cbNewCustomerEmailIsPrimary.isSelected()));

        // employer
        customerValuesMap.put("employer name 0", tfNewCustomerEmployerName.getText());
        customerValuesMap.put("employer line1 0", tfNewCustomerEmployerLine1.getText());
        customerValuesMap.put("employer line2 0", tfNewCustomerEmployerLine2.getText());
        customerValuesMap.put("employer suburb 0", tfNewCustomerEmployerSuburb.getText());
        customerValuesMap.put("employer city 0", tfNewCustomerEmployerCity.getText());
        customerValuesMap.put("employer postCode 0", tfNewCustomerEmployerPostcode.getText());
        customerValuesMap.put("employer country 0", tfNewCustomerEmployerCountry.getText());
        customerValuesMap.put("employer phone 0", tfNewCustomerEmployerPhone.getText());
        customerValuesMap.put("employer email 0", tfNewCustomerEmployerEmail.getText());
        customerValuesMap.put("employer web 0", tfNewCustomerEmployerWeb.getText());
        customerValuesMap.put("employer isOwner 0", String.valueOf(cbNewCustomerEmployerIsOwner.isSelected()));

        // phone
        // TODO: customerValuesMap.put("phone type 0", tfNewCustomerPhoneType.getText());
        customerValuesMap.put("phone number 0", tfNewCustomerPhone.getText());
        // TODO: customerValuesMap.put("phone isPrimary 0", String.valueOf(cbNewCustomerPhoneIsPrimary.isSelected()));
        // TODO: customerValuesMap.put("phone isTexting 0", String.valueOf(cbNewCustomerPhoneIsTexting.isSelected()));

        // note
        customerValuesMap.put("note", taNewCustomerNotes.getText());
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

    @FXML
    private void logoClicked(){
        Main.setScreen(Screens.HOME);
    }
}
