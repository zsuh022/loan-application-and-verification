package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import uoa.lavs.Main;
import uoa.lavs.SceneManager.Screens;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.utility.CustomerValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerScreenController {

    private static  CustomerScreenController instance;

    private Customer activeCustomer;

    private HashMap<String, String> changeMap = new HashMap<>();

    private CustomerValidator customerValidator = new CustomerValidator();

    @FXML
    private Rectangle addLoanRectangle;

    @FXML
    private Button btnAddressCustomer;

    @FXML
    private Button btnContactsCustomer;

    @FXML
    private Button btnEmployerCustomer;

    @FXML
    private Button btnGeneralCustomer;

    @FXML
    private Button btnLoansCustomer;

    @FXML
    private Button btnNotesCustomer;

    @FXML
    private CheckBox cbCustomerIsMailing;

    @FXML
    private CheckBox cbCustomerIsMailing1;

    @FXML
    private CheckBox cbCustomerIsMailing2;

    @FXML
    private CheckBox cbCustomerIsMailing3;

    @FXML
    private CheckBox cbCustomerIsPhPrimary;

    @FXML
    private CheckBox cbCustomerIsPhPrimary1;

    @FXML
    private CheckBox cbCustomerIsPhPrimary2;

    @FXML
    private CheckBox cbCustomerIsPhPrimary3;

    @FXML
    private CheckBox cbCustomerIsPhSMS;

    @FXML
    private CheckBox cbCustomerIsPhSMS1;

    @FXML
    private CheckBox cbCustomerIsPhSMS2;

    @FXML
    private CheckBox cbCustomerIsPhSMS3;

    @FXML
    private CheckBox cbCustomerIsPrimary;

    @FXML
    private CheckBox cbCustomerIsPrimary1;

    @FXML
    private CheckBox cbCustomerIsPrimary2;

    @FXML
    private CheckBox cbCustomerIsPrimary3;

    @FXML
    private CheckBox cbLoanStatus;

    @FXML
    private CheckBox cbLoanStatus1;

    @FXML
    private CheckBox cbLoanStatus2;

    @FXML
    private CheckBox cbLoanStatus3;

    @FXML
    private AnchorPane customerAddressPane;

    @FXML
    private FontAwesomeIconView customerBack;

    @FXML
    private AnchorPane customerContactsPane;

    @FXML
    private TabPane customerEmailTabPane;

    @FXML
    private AnchorPane customerEmployerPane;

    @FXML
    private AnchorPane customerGeneralPane;

    @FXML
    private AnchorPane customerLoansPane;

    @FXML
    private AnchorPane customerNotesPane;

    @FXML
    private Circle editCustomersCircle;

    @FXML
    private AnchorPane emailTab1;

    @FXML
    private AnchorPane emailTab2;

    @FXML
    private AnchorPane emailTab3;

    @FXML
    private Label lbCustomerCitizenship;

    @FXML
    private Label lbCustomerCity;

    @FXML
    private Label lbCustomerCity1;

    @FXML
    private Label lbCustomerCity2;

    @FXML
    private Label lbCustomerCity3;

    @FXML
    private Label lbCustomerCountry;

    @FXML
    private Label lbCustomerCountry1;

    @FXML
    private Label lbCustomerCountry2;

    @FXML
    private Label lbCustomerCountry3;

    @FXML
    private Label lbCustomerDob;

    @FXML
    private Label lbCustomerEmail;

    @FXML
    private Label lbCustomerEmail1;

    @FXML
    private Label lbCustomerEmail2;

    @FXML
    private Label lbCustomerEmail3;

    @FXML
    private Label lbCustomerEmployerCity;

    @FXML
    private Label lbCustomerEmployerCity1;

    @FXML
    private Label lbCustomerEmployerCity2;

    @FXML
    private Label lbCustomerEmployerCity3;

    @FXML
    private Label lbCustomerEmployerCity4;

    @FXML
    private Label lbCustomerEmployerCountry;

    @FXML
    private Label lbCustomerEmployerCountry1;

    @FXML
    private Label lbCustomerEmployerCountry2;

    @FXML
    private Label lbCustomerEmployerCountry3;

    @FXML
    private Label lbCustomerEmployerCountry4;

    @FXML
    private Label lbCustomerEmployerEmail;

    @FXML
    private Label lbCustomerEmployerEmail1;

    @FXML
    private Label lbCustomerEmployerEmail2;

    @FXML
    private Label lbCustomerEmployerEmail3;

    @FXML
    private Label lbCustomerEmployerEmail4;

    @FXML
    private Label lbCustomerEmployerLine1;

    @FXML
    private Label lbCustomerEmployerLine11;

    @FXML
    private Label lbCustomerEmployerLine12;

    @FXML
    private Label lbCustomerEmployerLine13;

    @FXML
    private Label lbCustomerEmployerLine14;

    @FXML
    private Label lbCustomerEmployerLine2;

    @FXML
    private Label lbCustomerEmployerLine21;

    @FXML
    private Label lbCustomerEmployerLine22;

    @FXML
    private Label lbCustomerEmployerLine23;

    @FXML
    private Label lbCustomerEmployerLine24;

    @FXML
    private Label lbCustomerEmployerName;

    @FXML
    private Label lbCustomerEmployerName1;

    @FXML
    private Label lbCustomerEmployerName2;

    @FXML
    private Label lbCustomerEmployerName3;

    @FXML
    private Label lbCustomerEmployerName4;

    @FXML
    private Label lbCustomerEmployerPhone;

    @FXML
    private Label lbCustomerEmployerPhone1;

    @FXML
    private Label lbCustomerEmployerPhone2;

    @FXML
    private Label lbCustomerEmployerPhone3;

    @FXML
    private Label lbCustomerEmployerPhone4;

    @FXML
    private Label lbCustomerEmployerPostcode;

    @FXML
    private Label lbCustomerEmployerPostcode1;

    @FXML
    private Label lbCustomerEmployerPostcode2;

    @FXML
    private Label lbCustomerEmployerPostcode3;

    @FXML
    private Label lbCustomerEmployerPostcode4;

    @FXML
    private Label lbCustomerEmployerSuburb;

    @FXML
    private Label lbCustomerEmployerSuburb1;

    @FXML
    private Label lbCustomerEmployerSuburb2;

    @FXML
    private Label lbCustomerEmployerSuburb3;

    @FXML
    private Label lbCustomerEmployerSuburb4;

    @FXML
    private Label lbCustomerEmployerWeb;

    @FXML
    private Label lbCustomerEmployerWeb1;

    @FXML
    private Label lbCustomerEmployerWeb2;

    @FXML
    private Label lbCustomerEmployerWeb3;

    @FXML
    private Label lbCustomerEmployerWeb4;

    @FXML
    private Label lbCustomerId;

    @FXML
    private CheckBox lbCustomerIsPrimary;

    @FXML
    private CheckBox lbCustomerIsPrimary1;

    @FXML
    private CheckBox lbCustomerIsPrimary2;

    @FXML
    private CheckBox lbCustomerIsPrimary3;

    @FXML
    private Label lbCustomerLine1;

    @FXML
    private Label lbCustomerLine11;

    @FXML
    private Label lbCustomerLine12;

    @FXML
    private Label lbCustomerLine13;

    @FXML
    private Label lbCustomerLine2;

    @FXML
    private Label lbCustomerLine21;

    @FXML
    private Label lbCustomerLine22;

    @FXML
    private Label lbCustomerLine23;

    @FXML
    private Label lbCustomerName;

    @FXML
    private Label lbCustomerOccupation;

    @FXML
    private Label lbCustomerPhNum;

    @FXML
    private Label lbCustomerPhNum1;

    @FXML
    private Label lbCustomerPhNum2;

    @FXML
    private Label lbCustomerPhNum3;

    @FXML
    private Label lbCustomerPhPrefix;

    @FXML
    private Label lbCustomerPhPrefix1;

    @FXML
    private Label lbCustomerPhPrefix2;

    @FXML
    private Label lbCustomerPhPrefix3;

    @FXML
    private Label lbCustomerPhType;

    @FXML
    private Label lbCustomerPhType1;

    @FXML
    private Label lbCustomerPhType2;

    @FXML
    private Label lbCustomerPhType3;

    @FXML
    private Label lbCustomerPostcode;

    @FXML
    private Label lbCustomerPostcode1;

    @FXML
    private Label lbCustomerPostcode2;

    @FXML
    private Label lbCustomerPostcode3;

    @FXML
    private Label lbCustomerStatus;

    @FXML
    private Label lbCustomerSuburb;

    @FXML
    private Label lbCustomerSuburb1;

    @FXML
    private Label lbCustomerSuburb2;

    @FXML
    private Label lbCustomerSuburb3;

    @FXML
    private Label lbCustomerTitle;

    @FXML
    private Label lbCustomerType;

    @FXML
    private Label lbCustomerType1;

    @FXML
    private Label lbCustomerType2;

    @FXML
    private Label lbCustomerType3;

    @FXML
    private Label lbCustomerVisa;

    @FXML
    private Label lbLoanId;

    @FXML
    private Label lbLoanId1;

    @FXML
    private Label lbLoanId2;

    @FXML
    private Label lbLoanId3;

    @FXML
    private Label lbLoanPrincipal;

    @FXML
    private Label lbLoanPrincipal1;

    @FXML
    private Label lbLoanPrincipal2;

    @FXML
    private Label lbLoanPrincipal3;

    @FXML
    private AnchorPane phoneTab1;

    @FXML
    private AnchorPane phoneTab2;

    @FXML
    private AnchorPane phoneTab3;

    @FXML
    private TabPane phoneTabPane;

    @FXML
    private TextArea taCustomerNotes;

    @FXML
    public void initialize() {
        instance = this;
    }

    public void submitCustomerUpdate(HashMap<String, String> customerMap, List<Map<String, String>> addressList,
                                     List<Map<String, String>> emailList, List<Map<String, String>> employerList,
                                     List<Map<String, String>> phoneList) {
        if (customerValidator.validateCustomer(customerMap, addressList, emailList, employerList, phoneList)) {
            // validate and replace active customer with updated customer
            Customer updatedCustomer = customerValidator.createCustomer(customerMap, addressList, emailList, employerList, phoneList);
            updatedCustomer.setCustomerId(activeCustomer.getId());
            activeCustomer = updatedCustomer;
        }
    }

    public void setGeneralPaneInformation() {
        lbCustomerId.setText(activeCustomer.getId());
        lbCustomerTitle.setText(activeCustomer.getTitle());
        lbCustomerName.setText(activeCustomer.getName());
        // TODO: lbCustomerStatus.setText("Active");
        lbCustomerDob.setText(String.valueOf(activeCustomer.getDateOfBirth()));
        lbCustomerOccupation.setText(activeCustomer.getOccupation());
        lbCustomerCitizenship.setText(activeCustomer.getCitizenship());
        lbCustomerVisa.setText(activeCustomer.getVisa());
        // TODO: lbCustomerPhone.setText(activeCustomer.getPhoneList())
        // TODO: lbCustomerEmail.setText(activeCustomer.getEmailList());
    }

    public void setAddressPaneInformation() {
        // TODO:
    }

    public void setLoanPaneInformation() {
        // TODO:
    }

    public void setEmployerPaneInformation() {
        // TODO:
    }

    public void setNotesPaneInformation() {
        taCustomerNotes.setText(String.valueOf(activeCustomer.getNote()));
    }

    @FXML
    private void onCustomerBackClicked(MouseEvent event) {
        Main.setScreen(Screens.SEARCH_CUSTOMER);
    }

    @FXML
    private void onGeneralButtonClicked() {
        customerGeneralPane.setVisible(true);
        customerAddressPane.setVisible(false);
        customerContactsPane.setVisible(false);
        customerLoansPane.setVisible(false);
        customerEmployerPane.setVisible(false);
        customerNotesPane.setVisible(false);
        btnGeneralCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnContactsCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnLoansCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onAddressButtonClicked() {
        customerGeneralPane.setVisible(false);
        customerAddressPane.setVisible(true);
        customerContactsPane.setVisible(false);
        customerLoansPane.setVisible(false);
        customerEmployerPane.setVisible(false);
        customerNotesPane.setVisible(false);
        btnGeneralCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnContactsCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnLoansCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onContactsButtonClicked() {
        customerGeneralPane.setVisible(false);
        customerAddressPane.setVisible(false);
        customerContactsPane.setVisible(true);
        customerLoansPane.setVisible(false);
        customerEmployerPane.setVisible(false);
        customerNotesPane.setVisible(false);
        btnGeneralCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnContactsCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnLoansCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onLoansButtonClicked() {
        customerGeneralPane.setVisible(false);
        customerAddressPane.setVisible(false);
        customerContactsPane.setVisible(false);
        customerLoansPane.setVisible(true);
        customerEmployerPane.setVisible(false);
        customerNotesPane.setVisible(false);
        btnGeneralCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnContactsCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnLoansCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onEmployerButtonClicked() {
        customerGeneralPane.setVisible(false);
        customerAddressPane.setVisible(false);
        customerContactsPane.setVisible(false);
        customerLoansPane.setVisible(false);
        customerEmployerPane.setVisible(true);
        customerNotesPane.setVisible(false);
        btnGeneralCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnContactsCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnLoansCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onNotesButtonClicked() {
        customerGeneralPane.setVisible(false);
        customerAddressPane.setVisible(false);
        customerContactsPane.setVisible(false);
        customerLoansPane.setVisible(false);
        customerEmployerPane.setVisible(false);
        customerNotesPane.setVisible(true);
        btnGeneralCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnContactsCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnLoansCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void logoClicked() {
        Main.setScreen(Screens.HOME);
    }

    @FXML
    private void btnLogOut(){
        Main.setScreen(Screens.LOGIN);
    }

    public static void updateCustomer() {
        // get the current customer in the customer bucket and set it as the active customer
        instance.activeCustomer = CustomerBucket.getInstance().getCustomer();
    }
}
