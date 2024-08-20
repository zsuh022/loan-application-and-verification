package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewCustomerScreenController {

    private Map<String, String> customerValuesMap = new HashMap<>();

    private ArrayList<Map<String, String>> addressValuesList = new ArrayList<>();
    private ArrayList<Map<String, String>> emailValuesList = new ArrayList<>();
    private ArrayList<Map<String, String>> employerValuesList = new ArrayList<>();
    private ArrayList<Map<String, String>> phoneValuesList = new ArrayList<>();

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
        Main.setScreen(Screens.HOME);
    }

    public boolean submitNewCustomer() {
        fillCustomerValuesMap();

        if (customerValidator.validateCustomer(customerValuesMap, addressValuesList, emailValuesList,
                employerValuesList, phoneValuesList)) {
            Customer newCustomer = customerValidator.createCustomer(customerValuesMap, addressValuesList,
                    emailValuesList, employerValuesList, phoneValuesList);
            // TODO: send customer somewhere
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
        for (Tab tab : addressTabPane.getTabs()) {
            AnchorPane pane = (AnchorPane) tab.getContent();
            Map<String, String> addressMap = new HashMap<>();
            addressMap.put("type", ((TextField) pane.lookup("#tfNewCustomerType")).getText());
            addressMap.put("line1", ((TextField) pane.lookup("#tfNewCustomerLine1")).getText());
            addressMap.put("line2", ((TextField) pane.lookup("#tfNewCustomerLine2")).getText());
            addressMap.put("suburb", ((TextField) pane.lookup("#tfNewCustomerSuburb")).getText());
            addressMap.put("city", ((TextField) pane.lookup("#tfNewCustomerCity")).getText());
            addressMap.put("postCode", ((TextField) pane.lookup("#tfNewCustomerPostcode")).getText());
            addressMap.put("country", ((TextField) pane.lookup("#tfNewCustomerCountry")).getText());
            addressMap.put("isPrimary", String.valueOf(((CheckBox) pane.lookup("#cbNewCustomerIsPrimary")).isSelected()));
            addressMap.put("isMailing", String.valueOf(((CheckBox) pane.lookup("#cbNewCustomerIsMailing")).isSelected()));
            addressValuesList.add(addressMap);
        }

        // email
        for (Tab tab : emailTabPane.getTabs()) {
            AnchorPane pane = (AnchorPane) tab.getContent();
            Map<String, String> emailMap = new HashMap<>();
            emailMap.put("address", ((TextField) pane.lookup("#tfNewCustomerEmail")).getText());
            emailMap.put("isPrimary", String.valueOf(((CheckBox) pane.lookup("#cbNewCustomerEmailIsPrimary")).isSelected()));
            emailValuesList.add(emailMap);
        }

        // employer
        for (Tab tab : employerTabPane.getTabs()) {
            AnchorPane pane = (AnchorPane) tab.getContent();
            Map<String, String> employerMap = new HashMap<>();
            employerMap.put("name", ((TextField) pane.lookup("#tfNewCustomerEmployerName")).getText());
            employerMap.put("line1", ((TextField) pane.lookup("#tfNewCustomerEmployerLine1")).getText());
            employerMap.put("line2", ((TextField) pane.lookup("#tfNewCustomerEmployerLine2")).getText());
            employerMap.put("suburb", ((TextField) pane.lookup("#tfNewCustomerEmployerSuburb")).getText());
            employerMap.put("city", ((TextField) pane.lookup("#tfNewCustomerEmployerCity")).getText());
            employerMap.put("postCode", ((TextField) pane.lookup("#tfNewCustomerEmployerPostcode")).getText());
            employerMap.put("country", ((TextField) pane.lookup("#tfNewCustomerEmployerCountry")).getText());
            employerMap.put("phone", ((TextField) pane.lookup("#tfNewCustomerEmployerPhone")).getText());
            employerMap.put("email", ((TextField) pane.lookup("#tfNewCustomerEmployerEmail")).getText());
            employerMap.put("web", ((TextField) pane.lookup("#tfNewCustomerEmployerWeb")).getText());
            employerMap.put("isOwner", String.valueOf(((CheckBox) pane.lookup("#cbNewCustomerEmployerIsOwner")).isSelected()));
            employerValuesList.add(employerMap);
        }

        // phone
        for (Tab tab : phoneTabPane.getTabs()) {
            AnchorPane pane = (AnchorPane) tab.getContent();
            Map<String, String> phoneMap = new HashMap<>();
            phoneMap.put("type", ((TextField) pane.lookup("#tfNewCustomerPhoneType")).getText());
            phoneMap.put("prefix", ((TextField) pane.lookup("#tfNewCustomerPhonePrefix")).getText());
            phoneMap.put("number", ((TextField) pane.lookup("#tfNewCustomerPhoneNumber")).getText());
            phoneMap.put("isPrimary", String.valueOf(((CheckBox) pane.lookup("#cbNewCustomerPhoneIsPrimary")).isSelected()));
            phoneMap.put("isTexting", String.valueOf(((CheckBox) pane.lookup("#cbNewCustomerPhoneIsTexting")).isSelected()));
            phoneValuesList.add(phoneMap);
        }

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