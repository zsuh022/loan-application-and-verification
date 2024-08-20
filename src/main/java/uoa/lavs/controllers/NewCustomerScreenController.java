package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import uoa.lavs.Main;
import uoa.lavs.SceneManager.Screens;
import uoa.lavs.utility.CustomerValidator;
import uoa.lavs.models.Customer.Customer;

import java.util.HashMap;

public class NewCustomerScreenController {

    private HashMap<String, String> customerValuesMap = new HashMap<>();

    private CustomerValidator customerValidator = new CustomerValidator();

    @FXML
    private FontAwesomeIconView newCustomerBack;
    @FXML
    private Rectangle saveNewCustomerGeneral;
    @FXML
    private Circle btnNewAddress;
    @FXML
    private Circle btnNewEmail;
    @FXML
    private Circle btnNewPhone;
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
    private DatePicker dpNewCustomerDob;
    @FXML
    private TextField tfNewCustomerOccupation;
    @FXML
    private TextField tfNewCustomerCitizenship;
    @FXML
    private TextField tfNewCustomerVisa;
    @FXML
    private TextField tfNewCustomerPhone111;
    @FXML
    private TextField tfNewCustomerEmail1;
    @FXML
    private TextField tfNewCustomerEmail11;
    @FXML
    private TextField tfNewCustomerEmail12;
    @FXML
    private TextField tfNewCustomerEmail13;
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
    //contacts
    @FXML
    private AnchorPane newCustomerContactsPane;
    @FXML
    private Button btnContactsNewCustomer;
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
    @FXML
    private TabPane addressTabPane;
    @FXML
    private TabPane phoneTabPane;
    @FXML
    private TabPane emailTabPane;
    @FXML
    private AnchorPane addressTab1;
    @FXML
    private AnchorPane addressTab2;
    @FXML
    private AnchorPane addressTab3;
    @FXML
    private AnchorPane phoneTab1;
    @FXML
    private AnchorPane phoneTab2;
    @FXML
    private AnchorPane phoneTab3;
    @FXML
    private AnchorPane emailTab1;
    @FXML
    private AnchorPane emailTab2;
    @FXML
    private AnchorPane emailTab3;
    @FXML
    private AnchorPane employerTab1;
    @FXML
    private AnchorPane employerTab2;
    @FXML
    private AnchorPane employerTab3;
    @FXML
    private AnchorPane employerTab4;
    @FXML
    private TabPane employerTabPane;


    @FXML
    public void initialize() {
        Tab firstTab = addressTabPane.getTabs().get(0);
        firstTab.setClosable(false);
        Tab firstPhoneTab = phoneTabPane.getTabs().get(0);
        firstPhoneTab.setClosable(false);
        Tab firstEmailTab = emailTabPane.getTabs().get(0);
        firstEmailTab.setClosable(false);
    }

    @FXML
    private void onNewCustomerBackClicked(MouseEvent event) {
        // go to ...
        Main.setScreen(Screens.HOME);
    }

    @FXML
    public void submitNewCustomer(MouseEvent event) {
        fillCustomerValuesMap();

        if (customerValidator.validateCustomer(customerValuesMap)) {
            Customer newCustomer = customerValidator.createCustomer(customerValuesMap);
            CustomerBucket customerBucket = CustomerBucket.getInstance();
            customerBucket.setCustomer(newCustomer);

            Main.setScreen(Screens.CUSTOMER);
        }
    }

    private void fillCustomerValuesMap() {
        // general
        customerValuesMap.put("title", tfNewCustomerTitle.getText());
        customerValuesMap.put("name", tfNewCustomerName.getText());
        customerValuesMap.put("dob", dpNewCustomerDob.getValue().toString());
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
        customerValuesMap.put("email address 0", tfNewCustomerEmail1.getText());
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
        customerValuesMap.put("phone number 0", tfNewCustomerPhone111.getText());
        // TODO: customerValuesMap.put("phone isPrimary 0", String.valueOf(cbNewCustomerPhoneIsPrimary.isSelected()));
        // TODO: customerValuesMap.put("phone isTexting 0", String.valueOf(cbNewCustomerPhoneIsTexting.isSelected()));

        // note
        customerValuesMap.put("note", taNewCustomerNotes.getText());
    }


    @FXML
    private void onGeneralButtonClicked() {
        newCustomerGeneralPane.setVisible(true);
        newCustomerAddressPane.setVisible(false);
        newCustomerContactsPane.setVisible(false);
        newCustomerEmployerPane.setVisible(false);
        newCustomerNotesPane.setVisible(false);
        btnGeneralNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnContactsNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onAddressButtonClicked() {
        newCustomerGeneralPane.setVisible(false);
        newCustomerAddressPane.setVisible(true);
        newCustomerContactsPane.setVisible(false);
        newCustomerEmployerPane.setVisible(false);
        newCustomerNotesPane.setVisible(false);
        btnGeneralNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnContactsNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onContactsButtonClicked() {
        newCustomerGeneralPane.setVisible(false);
        newCustomerAddressPane.setVisible(false);
        newCustomerContactsPane.setVisible(true);
        newCustomerEmployerPane.setVisible(false);
        newCustomerNotesPane.setVisible(false);
        btnGeneralNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnContactsNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onEmployerButtonClicked() {
        newCustomerGeneralPane.setVisible(false);
        newCustomerAddressPane.setVisible(false);
        newCustomerContactsPane.setVisible(false);
        newCustomerEmployerPane.setVisible(true);
        newCustomerNotesPane.setVisible(false);
        btnGeneralNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnContactsNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onNotesButtonClicked() {
        newCustomerGeneralPane.setVisible(false);
        newCustomerAddressPane.setVisible(false);
        newCustomerContactsPane.setVisible(false);
        newCustomerEmployerPane.setVisible(false);
        newCustomerNotesPane.setVisible(true);
        btnGeneralNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnContactsNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesNewCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void logoClicked() {
        Main.setScreen(Screens.HOME);
    }

    @FXML
    private void addNewAddress() {
        if (addressTabPane.getTabs().size() >= 4) {
            return;
        }
        int numTabs = addressTabPane.getTabs().size();
        Tab tab = new Tab("Address " + (numTabs + 1));
        AnchorPane newContent;
        switch (numTabs) {
            case 1:
                newContent = addressTab1;
                newContent.setVisible(true);
                break;
            case 2:
                newContent = addressTab2;
                newContent.setVisible(true);
                break;
            case 3:
                newContent = addressTab3;
                newContent.setVisible(true);
                break;
            default:
                newContent = new AnchorPane();
                break;
        }
        tab.setContent(newContent);
        tab.setClosable(true);
        addressTabPane.getTabs().add(tab);
        if (addressTabPane.getTabs().size() == 4) {
            Tab firstTab = addressTabPane.getTabs().get(0);
            firstTab.setClosable(false);
        }
        addressTabPane.getSelectionModel().selectLast();
    }

    @FXML
    private void addNewEmail() {
        if (emailTabPane.getTabs().size() >= 4) {
            return;
        }
        int numTabs = emailTabPane.getTabs().size();
        Tab tab = new Tab("Email " + (numTabs + 1));
        AnchorPane newContent;
        switch (numTabs) {
            case 1:
                newContent = emailTab1;
                newContent.setVisible(true);
                break;
            case 2:
                newContent = emailTab2;
                newContent.setVisible(true);
                break;
            case 3:
                newContent = emailTab3;
                newContent.setVisible(true);
                break;
            default:
                newContent = new AnchorPane();
                break;
        }
        tab.setContent(newContent);
        tab.setClosable(true);
        emailTabPane.getTabs().add(tab);
        if (emailTabPane.getTabs().size() == 4) {
            Tab firstTab = emailTabPane.getTabs().get(0);
            firstTab.setClosable(false);
        }
        emailTabPane.getSelectionModel().selectLast();
    }

    @FXML
    private void addNewPhone() {
        if (phoneTabPane.getTabs().size() >= 4) {
            return;
        }
        int numTabs = phoneTabPane.getTabs().size();
        Tab tab = new Tab("Phone " + (numTabs + 1));
        AnchorPane newContent;
        switch (numTabs) {
            case 1:
                newContent = phoneTab1;
                newContent.setVisible(true);
                break;
            case 2:
                newContent = phoneTab2;
                newContent.setVisible(true);
                break;
            case 3:
                newContent = phoneTab3;
                newContent.setVisible(true);
                break;
            default:
                newContent = new AnchorPane();
                break;
        }
        tab.setContent(newContent);
        tab.setClosable(true);
        phoneTabPane.getTabs().add(tab);
        phoneTabPane.getSelectionModel().selectLast();
    }

    @FXML
    private void addNewEmployer() {
        if (employerTabPane.getTabs().size() >= 4) {
            return;
        }
        int numTabs = employerTabPane.getTabs().size();
        Tab tab = new Tab("Employer " + (numTabs + 1));
        AnchorPane newContent;
        switch (numTabs) {
            case 1:
                newContent = employerTab1;
                newContent.setVisible(true);
                break;
            case 2:
                newContent = employerTab2;
                newContent.setVisible(true);
                break;
            case 3:
                newContent = employerTab3;
                newContent.setVisible(true);
                break;
            default:
                newContent = new AnchorPane();
                break;
        }
        tab.setContent(newContent);
        tab.setClosable(true);
        employerTabPane.getTabs().add(tab);
        employerTabPane.getSelectionModel().selectLast();
    }

}