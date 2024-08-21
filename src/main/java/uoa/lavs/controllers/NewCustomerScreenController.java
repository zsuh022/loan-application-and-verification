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
    private TextField tfNewCustomerName1;
    @FXML
    private TextField tfNewCustomerName2;
    @FXML
    private DatePicker dpNewCustomerDob;
    @FXML
    private TextField tfNewCustomerOccupation;
    @FXML
    private TextField tfNewCustomerCitizenship;
    @FXML
    private TextField tfNewCustomerVisa;
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
    @FXML
    private Button btnAddressNewCustomer1;
    @FXML
    private AnchorPane newCustomerAddressPane1;
    @FXML
    private TextField tfNewCustomerType1;
    @FXML
    private TextField tfNewCustomerLine11;
    @FXML
    private TextField tfNewCustomerLine21;
    @FXML
    private TextField tfNewCustomerSuburb1;
    @FXML
    private TextField tfNewCustomerCity1;
    @FXML
    private TextField tfNewCustomerPostcode1;
    @FXML
    private TextField tfNewCustomerCountry1;
    @FXML
    private CheckBox cbNewCustomerIsPrimary1;
    @FXML
    private CheckBox cbNewCustomerIsMailing1;
    @FXML
    private Button btnAddressNewCustomer2;
    @FXML
    private AnchorPane newCustomerAddressPane2;
    @FXML
    private TextField tfNewCustomerType2;
    @FXML
    private TextField tfNewCustomerLine12;
    @FXML
    private TextField tfNewCustomerLine22;
    @FXML
    private TextField tfNewCustomerSuburb2;
    @FXML
    private TextField tfNewCustomerCity2;
    @FXML
    private TextField tfNewCustomerPostcode2;
    @FXML
    private TextField tfNewCustomerCountry2;
    @FXML
    private CheckBox cbNewCustomerIsPrimary2;
    @FXML
    private CheckBox cbNewCustomerIsMailing2;
    @FXML
    private Button btnAddressNewCustomer3;
    @FXML
    private AnchorPane newCustomerAddressPane3;
    @FXML
    private TextField tfNewCustomerType3;
    @FXML
    private TextField tfNewCustomerLine13;
    @FXML
    private TextField tfNewCustomerLine23;
    @FXML
    private TextField tfNewCustomerSuburb3;
    @FXML
    private TextField tfNewCustomerCity3;
    @FXML
    private TextField tfNewCustomerPostcode3;
    @FXML
    private TextField tfNewCustomerCountry3;
    @FXML
    private CheckBox cbNewCustomerIsPrimary3;
    @FXML
    private CheckBox cbNewCustomerIsMailing3;
    //contacts
    @FXML
    private AnchorPane newCustomerContactsPane;
    @FXML
    private Button btnContactsNewCustomer;
    @FXML
    private TextField tfNewCustomerPhType;
    @FXML
    private TextField tfNewCustomerPhPrefix;
    @FXML
    private TextField tfNewCustomerPhNum;
    @FXML
    private CheckBox cbNewCustomerIsPhPrimary;
    @FXML
    private CheckBox cbNewCustomerIsPhSMS;
    @FXML
    private TextField tfNewCustomerEmail;
    @FXML
    private CheckBox cbNewCustomerEmailIsPrimary;
    @FXML
    private TextField tfNewCustomerPhType1;
    @FXML
    private TextField tfNewCustomerPhPrefix1;
    @FXML
    private TextField tfNewCustomerPhNum1;
    @FXML
    private CheckBox cbNewCustomerIsPhPrimary1;
    @FXML
    private CheckBox cbNewCustomerIsPhSMS1;
    @FXML
    private TextField tfNewCustomerEmail1;
    @FXML
    private CheckBox cbNewCustomerEmailIsPrimary1;
    @FXML
    private TextField tfNewCustomerPhType2;
    @FXML
    private TextField tfNewCustomerPhPrefix2;
    @FXML
    private TextField tfNewCustomerPhNum2;
    @FXML
    private CheckBox cbNewCustomerIsPhPrimary2;
    @FXML
    private CheckBox cbNewCustomerIsPhSMS2;
    @FXML
    private TextField tfNewCustomerEmail2;
    @FXML
    private CheckBox cbNewCustomerEmailIsPrimary2;
    @FXML
    private TextField tfNewCustomerPhType3;
    @FXML
    private TextField tfNewCustomerPhPrefix3;
    @FXML
    private TextField tfNewCustomerPhNum3;
    @FXML
    private CheckBox cbNewCustomerIsPhPrimary3;
    @FXML
    private CheckBox cbNewCustomerIsPhSMS3;
    @FXML
    private TextField tfNewCustomerEmail3;
    @FXML
    private CheckBox cbNewCustomerEmailIsPrimary3;
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
    @FXML
    private Button btnEmployerNewCustomer1;
    @FXML
    private AnchorPane newCustomerEmployerPane1;
    @FXML
    private TextField tfNewCustomerEmployerName1;
    @FXML
    private TextField tfNewCustomerEmployerLine11;
    @FXML
    private TextField tfNewCustomerEmployerLine21;
    @FXML
    private TextField tfNewCustomerEmployerSuburb1;
    @FXML
    private TextField tfNewCustomerEmployerCity1;
    @FXML
    private TextField tfNewCustomerEmployerPostcode1;
    @FXML
    private TextField tfNewCustomerEmployerCountry1;
    @FXML
    private TextField tfNewCustomerEmployerPhone1;
    @FXML
    private TextField tfNewCustomerEmployerEmail1;
    @FXML
    private TextField tfNewCustomerEmployerWeb1;
    @FXML
    private CheckBox cbNewCustomerEmployerIsOwner1;
    @FXML
    private Button btnEmployerNewCustomer2;
    @FXML
    private AnchorPane newCustomerEmployerPane2;
    @FXML
    private TextField tfNewCustomerEmployerName2;
    @FXML
    private TextField tfNewCustomerEmployerLine12;
    @FXML
    private TextField tfNewCustomerEmployerLine22;
    @FXML
    private TextField tfNewCustomerEmployerSuburb2;
    @FXML
    private TextField tfNewCustomerEmployerCity2;
    @FXML
    private TextField tfNewCustomerEmployerPostcode2;
    @FXML
    private TextField tfNewCustomerEmployerCountry2;
    @FXML
    private TextField tfNewCustomerEmployerPhone2;
    @FXML
    private TextField tfNewCustomerEmployerEmail2;
    @FXML
    private TextField tfNewCustomerEmployerWeb2;
    @FXML
    private CheckBox cbNewCustomerEmployerIsOwner2;
    @FXML
    private Button btnEmployerNewCustomer3;
    @FXML
    private AnchorPane newCustomerEmployerPane3;
    @FXML
    private TextField tfNewCustomerEmployerName3;
    @FXML
    private TextField tfNewCustomerEmployerLine13;
    @FXML
    private TextField tfNewCustomerEmployerLine23;
    @FXML
    private TextField tfNewCustomerEmployerSuburb3;
    @FXML
    private TextField tfNewCustomerEmployerCity3;
    @FXML
    private TextField tfNewCustomerEmployerPostcode3;
    @FXML
    private TextField tfNewCustomerEmployerCountry3;
    @FXML
    private TextField tfNewCustomerEmployerPhone3;
    @FXML
    private TextField tfNewCustomerEmployerEmail3;
    @FXML
    private TextField tfNewCustomerEmployerWeb3;
    @FXML
    private CheckBox cbNewCustomerEmployerIsOwner3;
    @FXML
    private Button btnEmployerNewCustomer4;
    @FXML
    private AnchorPane newCustomerEmployerPane4;
    @FXML
    private TextField tfNewCustomerEmployerName4;
    @FXML
    private TextField tfNewCustomerEmployerLine14;
    @FXML
    private TextField tfNewCustomerEmployerLine24;
    @FXML
    private TextField tfNewCustomerEmployerSuburb4;
    @FXML
    private TextField tfNewCustomerEmployerCity4;
    @FXML
    private TextField tfNewCustomerEmployerPostcode4;
    @FXML
    private TextField tfNewCustomerEmployerCountry4;
    @FXML
    private TextField tfNewCustomerEmployerPhone4;
    @FXML
    private TextField tfNewCustomerEmployerEmail4;
    @FXML
    private TextField tfNewCustomerEmployerWeb4;
    @FXML
    private CheckBox cbNewCustomerEmployerIsOwner4;
    // notes
    @FXML
    private Button btnNotesNewCustomer;
    @FXML
    private AnchorPane newCustomerNotesPane;
    @FXML
    private TextArea taNewCustomerNotes;
    //tab panes
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

    @FXML
    public void submitNewCustomer(MouseEvent event) {
        fillCustomerValuesMap();
        fillAddressValuesList();
        fillEmailValuesList();
        fillEmployerValuesList();
        fillPhoneValuesList();

        if (customerValidator.validateCustomer(customerValuesMap, addressValuesList, emailValuesList,
                employerValuesList, phoneValuesList)) {
            Customer newCustomer = customerValidator.createCustomer(customerValuesMap, addressValuesList,
                    emailValuesList, employerValuesList, phoneValuesList);
            CustomerBucket customerBucket = CustomerBucket.getInstance();
            customerBucket.setCustomer(newCustomer);
            Main.setScreen(Screens.CUSTOMER);
        }
    }

    private void fillCustomerValuesMap() {
        customerValuesMap.put("title", tfNewCustomerTitle.getText());
        customerValuesMap.put("firstName", tfNewCustomerName.getText());
        customerValuesMap.put("middleName", tfNewCustomerName1.getText());
        customerValuesMap.put("lastName", tfNewCustomerName2.getText());
        customerValuesMap.put("dob", dpNewCustomerDob.getValue().toString());
        customerValuesMap.put("occupation", tfNewCustomerOccupation.getText());
        customerValuesMap.put("citizenship", tfNewCustomerCitizenship.getText());
        customerValuesMap.put("visa", tfNewCustomerVisa.getText());
        customerValuesMap.put("note", taNewCustomerNotes.getText());
    }

    private void fillAddressValuesList() {
        addressValuesList.clear();

        for (int i = 0; i < addressTabPane.getTabs().size(); i++) {
            AnchorPane pane = (AnchorPane) addressTabPane.getTabs().get(i).getContent();
            Map<String, String> addressMap = new HashMap<>();

            if (i == 0) {
                addressMap.put("type", ((TextField) pane.lookup("#tfNewCustomerType")).getText());
                addressMap.put("line1", ((TextField) pane.lookup("#tfNewCustomerLine1")).getText());
                addressMap.put("line2", ((TextField) pane.lookup("#tfNewCustomerLine2")).getText());
                addressMap.put("suburb", ((TextField) pane.lookup("#tfNewCustomerSuburb")).getText());
                addressMap.put("city", ((TextField) pane.lookup("#tfNewCustomerCity")).getText());
                addressMap.put("postCode", ((TextField) pane.lookup("#tfNewCustomerPostcode")).getText());
                addressMap.put("country", ((TextField) pane.lookup("#tfNewCustomerCountry")).getText());
                addressMap.put("isPrimary", String.valueOf(((CheckBox) pane.lookup("#cbNewCustomerIsPrimary")).isSelected()));
                addressMap.put("isMailing", String.valueOf(((CheckBox) pane.lookup("#cbNewCustomerIsMailing")).isSelected()));
            } else {
                addressMap.put("type", ((TextField) pane.lookup("#tfNewCustomerType" + i)).getText());
                addressMap.put("line1", ((TextField) pane.lookup("#tfNewCustomerLine1" + i)).getText());
                addressMap.put("line2", ((TextField) pane.lookup("#tfNewCustomerLine2" + i)).getText());
                addressMap.put("suburb", ((TextField) pane.lookup("#tfNewCustomerSuburb" + i)).getText());
                addressMap.put("city", ((TextField) pane.lookup("#tfNewCustomerCity" + i)).getText());
                addressMap.put("postCode", ((TextField) pane.lookup("#tfNewCustomerPostcode" + i)).getText());
                addressMap.put("country", ((TextField) pane.lookup("#tfNewCustomerCountry" + i)).getText());
                addressMap.put("isPrimary", String.valueOf(((CheckBox) pane.lookup("#cbNewCustomerIsPrimary" + i)).isSelected()));
                addressMap.put("isMailing", String.valueOf(((CheckBox) pane.lookup("#cbNewCustomerIsMailing" + i)).isSelected()));
            }
            addressValuesList.add(addressMap);
        }
    }

    private void fillEmailValuesList() {
        emailValuesList.clear();

        for (int i = 0; i < emailTabPane.getTabs().size(); i++) {
            AnchorPane pane = (AnchorPane) emailTabPane.getTabs().get(i).getContent();
            Map<String, String> emailMap = new HashMap<>();

            if (i == 0) {
                emailMap.put("address", ((TextField) pane.lookup("#tfNewCustomerEmail")).getText());
                emailMap.put("isPrimary", String.valueOf(((CheckBox) pane.lookup("#cbNewCustomerEmailIsPrimary")).isSelected()));
            } else {
                emailMap.put("address", ((TextField) pane.lookup("#tfNewCustomerEmail" + i)).getText());
                emailMap.put("isPrimary", String.valueOf(((CheckBox) pane.lookup("#cbNewCustomerEmailIsPrimary" + i)).isSelected()));
            }
            emailValuesList.add(emailMap);
        }
    }

    private void fillEmployerValuesList() {
        employerValuesList.clear();

        for (int i = 0; i < employerTabPane.getTabs().size(); i++) {
            AnchorPane pane = (AnchorPane) employerTabPane.getTabs().get(i).getContent();
            Map<String, String> employerMap = new HashMap<>();

            if (i == 0) {
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
            } else {
                employerMap.put("name", ((TextField) pane.lookup("#tfNewCustomerEmployerName" + i)).getText());
                employerMap.put("line1", ((TextField) pane.lookup("#tfNewCustomerEmployerLine1" + i)).getText());
                employerMap.put("line2", ((TextField) pane.lookup("#tfNewCustomerEmployerLine2" + i)).getText());
                employerMap.put("suburb", ((TextField) pane.lookup("#tfNewCustomerEmployerSuburb" + i)).getText());
                employerMap.put("city", ((TextField) pane.lookup("#tfNewCustomerEmployerCity" + i)).getText());
                employerMap.put("postCode", ((TextField) pane.lookup("#tfNewCustomerEmployerPostcode" + i)).getText());
                employerMap.put("country", ((TextField) pane.lookup("#tfNewCustomerEmployerCountry" + i)).getText());
                employerMap.put("phone", ((TextField) pane.lookup("#tfNewCustomerEmployerPhone" + i)).getText());
                employerMap.put("email", ((TextField) pane.lookup("#tfNewCustomerEmployerEmail" + i)).getText());
                employerMap.put("web", ((TextField) pane.lookup("#tfNewCustomerEmployerWeb" + i)).getText());
                employerMap.put("isOwner", String.valueOf(((CheckBox) pane.lookup("#cbNewCustomerEmployerIsOwner" + i)).isSelected()));
            }

            employerValuesList.add(employerMap);
        }
    }

    private void fillPhoneValuesList() {
        phoneValuesList.clear();

        for (int i = 0; i < phoneTabPane.getTabs().size(); i++) {
            AnchorPane pane = (AnchorPane) phoneTabPane.getTabs().get(i).getContent();
            Map<String, String> phoneMap = new HashMap<>();

            if (i == 0) {
                phoneMap.put("type", ((TextField) pane.lookup("#tfNewCustomerPhType")).getText());
                phoneMap.put("prefix", ((TextField) pane.lookup("#tfNewCustomerPhPrefix")).getText());
                phoneMap.put("number", ((TextField) pane.lookup("#tfNewCustomerPhNum")).getText());
                phoneMap.put("isPrimary", String.valueOf(((CheckBox) pane.lookup("#cbNewCustomerIsPhPrimary")).isSelected()));
                phoneMap.put("isTexting", String.valueOf(((CheckBox) pane.lookup("#cbNewCustomerIsPhSMS")).isSelected()));
            } else {
                phoneMap.put("type", ((TextField) pane.lookup("#tfNewCustomerPhType" + i)).getText());
                phoneMap.put("prefix", ((TextField) pane.lookup("#tfNewCustomerPhPrefix" + i)).getText());
                phoneMap.put("number", ((TextField) pane.lookup("#tfNewCustomerPhNum" + i)).getText());
                phoneMap.put("isPrimary", String.valueOf(((CheckBox) pane.lookup("#cbNewCustomerIsPhPrimary" + i)).isSelected()));
                phoneMap.put("isTexting", String.valueOf(((CheckBox) pane.lookup("#cbNewCustomerIsPhSMS" + i)).isSelected()));
            }

            phoneValuesList.add(phoneMap);
        }
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

    @FXML
    private void btnLogOut() {
        Main.setScreen(Screens.LOGIN);
    }

}