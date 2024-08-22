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
import uoa.lavs.comms.Loan.InitialSearch;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.models.Customer.*;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.models.Loan.LoanSummary;
import uoa.lavs.utility.CustomerValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerScreenController {

    private static CustomerScreenController instance;

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
    }

    public void setAddressPaneInformation() {
        List<CustomerAddress> addressList = activeCustomer.getAddressList();

        if (!addressList.isEmpty()) {
            lbCustomerType.setText(addressList.get(0).getType());
            lbCustomerLine1.setText(addressList.get(0).getLine1());
            lbCustomerLine2.setText(addressList.get(0).getLine2());
            lbCustomerSuburb.setText(addressList.get(0).getSuburb());
            lbCustomerCity.setText(addressList.get(0).getCity());
            lbCustomerPostcode.setText(addressList.get(0).getPostCode());
            lbCustomerCountry.setText(addressList.get(0).getCountry());
            cbCustomerIsPrimary.setSelected(addressList.get(0).getIsPrimary());
            cbCustomerIsMailing.setSelected(addressList.get(0).getIsMailing());
        }
        if (addressList.size() > 1) {
            lbCustomerType1.setText(addressList.get(1).getType());
            lbCustomerLine11.setText(addressList.get(1).getLine1());
            lbCustomerLine21.setText(addressList.get(1).getLine2());
            lbCustomerSuburb1.setText(addressList.get(1).getSuburb());
            lbCustomerCity1.setText(addressList.get(1).getCity());
            lbCustomerPostcode1.setText(addressList.get(1).getPostCode());
            lbCustomerCountry1.setText(addressList.get(1).getCountry());
            cbCustomerIsPrimary1.setSelected(addressList.get(1).getIsPrimary());
            cbCustomerIsMailing1.setSelected(addressList.get(1).getIsMailing());
        }
        if (addressList.size() > 2) {
            lbCustomerType2.setText(addressList.get(2).getType());
            lbCustomerLine12.setText(addressList.get(2).getLine1());
            lbCustomerLine22.setText(addressList.get(2).getLine2());
            lbCustomerSuburb2.setText(addressList.get(2).getSuburb());
            lbCustomerCity2.setText(addressList.get(2).getCity());
            lbCustomerPostcode2.setText(addressList.get(2).getPostCode());
            lbCustomerCountry2.setText(addressList.get(2).getCountry());
            cbCustomerIsPrimary2.setSelected(addressList.get(2).getIsPrimary());
            cbCustomerIsMailing2.setSelected(addressList.get(2).getIsMailing());
        }
        if (addressList.size() > 3) {
            lbCustomerType3.setText(addressList.get(3).getType());
            lbCustomerLine13.setText(addressList.get(3).getLine1());
            lbCustomerLine23.setText(addressList.get(3).getLine2());
            lbCustomerSuburb3.setText(addressList.get(3).getSuburb());
            lbCustomerCity3.setText(addressList.get(3).getCity());
            lbCustomerPostcode3.setText(addressList.get(3).getPostCode());
            lbCustomerCountry3.setText(addressList.get(3).getCountry());
            cbCustomerIsPrimary3.setSelected(addressList.get(3).getIsPrimary());
            cbCustomerIsMailing3.setSelected(addressList.get(3).getIsMailing());
        }
    }

    public void setContactsPaneInformation() {
        List<CustomerPhone> phoneList = activeCustomer.getPhoneList();
        List<CustomerEmail> emailList = activeCustomer.getEmailList();

        if (!phoneList.isEmpty()) {
            lbCustomerPhType.setText(phoneList.get(0).getType());
            lbCustomerPhPrefix.setText(phoneList.get(0).getPrefix());
            lbCustomerPhNum.setText(phoneList.get(0).getNumber());
            cbCustomerIsPhPrimary.setSelected(phoneList.get(0).getIsPrimary());
            cbCustomerIsPhSMS.setSelected(phoneList.get(0).getIsTexting());
        }
        if (phoneList.size() > 1) {
            lbCustomerPhType1.setText(phoneList.get(1).getType());
            lbCustomerPhPrefix1.setText(phoneList.get(1).getPrefix());
            lbCustomerPhNum1.setText(phoneList.get(1).getNumber());
            cbCustomerIsPhPrimary1.setSelected(phoneList.get(1).getIsPrimary());
            cbCustomerIsPhSMS1.setSelected(phoneList.get(1).getIsTexting());
        }
        if (phoneList.size() > 2) {
            lbCustomerPhType2.setText(phoneList.get(2).getType());
            lbCustomerPhPrefix2.setText(phoneList.get(2).getPrefix());
            lbCustomerPhNum2.setText(phoneList.get(2).getNumber());
            cbCustomerIsPhPrimary2.setSelected(phoneList.get(2).getIsPrimary());
            cbCustomerIsPhSMS2.setSelected(phoneList.get(2).getIsTexting());
        }
        if (phoneList.size() > 3) {
            lbCustomerPhType3.setText(phoneList.get(3).getType());
            lbCustomerPhPrefix3.setText(phoneList.get(3).getPrefix());
            lbCustomerPhNum3.setText(phoneList.get(3).getNumber());
            cbCustomerIsPhPrimary3.setSelected(phoneList.get(3).getIsPrimary());
            cbCustomerIsPhSMS3.setSelected(phoneList.get(3).getIsTexting());
        }

        if (!emailList.isEmpty()) {
            lbCustomerEmail.setText(emailList.get(0).getAddress());
            lbCustomerIsPrimary.setSelected(emailList.get(0).getIsPrimary());
        }
        if (emailList.size() > 1) {
            lbCustomerEmail1.setText(emailList.get(1).getAddress());
            lbCustomerIsPrimary1.setSelected(emailList.get(1).getIsPrimary());
        }
        if (emailList.size() > 2) {
            lbCustomerEmail2.setText(emailList.get(2).getAddress());
            lbCustomerIsPrimary2.setSelected(emailList.get(2).getIsPrimary());
        }
        if (emailList.size() > 3) {
            lbCustomerEmail3.setText(emailList.get(3).getAddress());
            lbCustomerIsPrimary3.setSelected(emailList.get(3).getIsPrimary());
        }
    }

    public void setLoanPaneInformation() {
        InitialSearch loanSearch = new InitialSearch();
        List<LoanSummary> loanList = loanSearch.findAll(Instance.getConnection(), activeCustomer.getId());

        if (loanList.isEmpty()) {
            lbLoanId.setText("");
            lbLoanPrincipal.setText("");
            cbLoanStatus.setSelected(false);
        }
        if (!loanList.isEmpty()) {
            LoanSummary loan1 = loanList.get(0);
            lbLoanId.setText(loan1.getLoanID());
            lbLoanPrincipal.setText(loan1.getPrincipal().toString());
            cbLoanStatus.setSelected("Active".equals(loan1.getStatusString()));
        }
        if (loanList.size() > 1) {
            LoanSummary loan2 = loanList.get(1);
            lbLoanId1.setText(loan2.getLoanID());
            lbLoanPrincipal1.setText(loan2.getPrincipal().toString());
            cbLoanStatus1.setSelected("Active".equals(loan2.getStatusString()));
        }
        if (loanList.size() > 2) {
            LoanSummary loan3 = loanList.get(2);
            lbLoanId2.setText(loan3.getLoanID());
            lbLoanPrincipal2.setText(loan3.getPrincipal().toString());
            cbLoanStatus2.setSelected("Active".equals(loan3.getStatusString()));
        }
        if (loanList.size() > 3) {
            LoanSummary loan4 = loanList.get(3);
            lbLoanId3.setText(loan4.getLoanID());
            lbLoanPrincipal3.setText(loan4.getPrincipal().toString());
            cbLoanStatus3.setSelected("Active".equals(loan4.getStatusString()));
        }
    }

    public void setEmployerPaneInformation() {
        List<CustomerEmployer> employerList = activeCustomer.getEmployerList();

        if (!employerList.isEmpty()) {
            lbCustomerEmployerName.setText(employerList.get(0).getName());
            lbCustomerEmployerLine1.setText(employerList.get(0).getLine1());
            lbCustomerEmployerLine2.setText(employerList.get(0).getLine2());
            lbCustomerEmployerSuburb.setText(employerList.get(0).getSuburb());
            lbCustomerEmployerCity.setText(employerList.get(0).getCity());
            lbCustomerEmployerPostcode.setText(employerList.get(0).getPostCode());
            lbCustomerEmployerCountry.setText(employerList.get(0).getCountry());
            lbCustomerEmployerPhone.setText(employerList.get(0).getPhone());
            lbCustomerEmployerEmail.setText(employerList.get(0).getEmail());
            lbCustomerEmployerWeb.setText(employerList.get(0).getWeb());
        }
        if (employerList.size() > 1) {
            lbCustomerEmployerName1.setText(employerList.get(1).getName());
            lbCustomerEmployerLine11.setText(employerList.get(1).getLine1());
            lbCustomerEmployerLine21.setText(employerList.get(1).getLine2());
            lbCustomerEmployerSuburb1.setText(employerList.get(1).getSuburb());
            lbCustomerEmployerCity1.setText(employerList.get(1).getCity());
            lbCustomerEmployerPostcode1.setText(employerList.get(1).getPostCode());
            lbCustomerEmployerCountry1.setText(employerList.get(1).getCountry());
            lbCustomerEmployerPhone1.setText(employerList.get(1).getPhone());
            lbCustomerEmployerEmail1.setText(employerList.get(1).getEmail());
            lbCustomerEmployerWeb1.setText(employerList.get(1).getWeb());
        }
        if (employerList.size() > 2) {
            lbCustomerEmployerName2.setText(employerList.get(2).getName());
            lbCustomerEmployerLine12.setText(employerList.get(2).getLine1());
            lbCustomerEmployerLine22.setText(employerList.get(2).getLine2());
            lbCustomerEmployerSuburb2.setText(employerList.get(2).getSuburb());
            lbCustomerEmployerCity2.setText(employerList.get(2).getCity());
            lbCustomerEmployerPostcode2.setText(employerList.get(2).getPostCode());
            lbCustomerEmployerCountry2.setText(employerList.get(2).getCountry());
            lbCustomerEmployerPhone2.setText(employerList.get(2).getPhone());
            lbCustomerEmployerEmail2.setText(employerList.get(2).getEmail());
            lbCustomerEmployerWeb2.setText(employerList.get(2).getWeb());
        }
        if (employerList.size() > 3) {
            lbCustomerEmployerName3.setText(employerList.get(3).getName());
            lbCustomerEmployerLine13.setText(employerList.get(3).getLine1());
            lbCustomerEmployerLine23.setText(employerList.get(3).getLine2());
            lbCustomerEmployerSuburb3.setText(employerList.get(3).getSuburb());
            lbCustomerEmployerCity3.setText(employerList.get(3).getCity());
            lbCustomerEmployerPostcode3.setText(employerList.get(3).getPostCode());
            lbCustomerEmployerCountry3.setText(employerList.get(3).getCountry());
            lbCustomerEmployerPhone3.setText(employerList.get(3).getPhone());
            lbCustomerEmployerEmail3.setText(employerList.get(3).getEmail());
            lbCustomerEmployerWeb3.setText(employerList.get(3).getWeb());
        }
        if (employerList.size() > 4) {
            lbCustomerEmployerName4.setText(employerList.get(4).getName());
            lbCustomerEmployerLine14.setText(employerList.get(4).getLine1());
            lbCustomerEmployerLine24.setText(employerList.get(4).getLine2());
            lbCustomerEmployerSuburb4.setText(employerList.get(4).getSuburb());
            lbCustomerEmployerCity4.setText(employerList.get(4).getCity());
            lbCustomerEmployerPostcode4.setText(employerList.get(4).getPostCode());
            lbCustomerEmployerCountry4.setText(employerList.get(4).getCountry());
            lbCustomerEmployerPhone4.setText(employerList.get(4).getPhone());
            lbCustomerEmployerEmail4.setText(employerList.get(4).getEmail());
            lbCustomerEmployerWeb4.setText(employerList.get(4).getWeb());
        }
    }

    public void setNotesPaneInformation() {
        if (activeCustomer.getNote() != null) {
            taCustomerNotes.setText(activeCustomer.getNote().getNote());
        } else {
            taCustomerNotes.clear();
        }
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
    private void btnLogOut() {
        Main.setScreen(Screens.LOGIN);
    }

    @FXML
    private void customerAddLoan() {
        CustomerBucket.getInstance().setCustomer(activeCustomer);
        NewLoanScreenController.updateCustomerField();
        Main.setScreen(Screens.NEW_LOAN);
    }

    @FXML
    private void editCustomer() {
        EditCustomerScreenController.editCustomer();
        Main.setScreen(Screens.EDIT_CUSTOMER);
    }

    public static void updateCustomer() {
        // get the current customer in the customer bucket and set it as the active customer
        instance.activeCustomer = CustomerBucket.getInstance().getCustomer();
        instance.setGeneralPaneInformation();
        instance.setAddressPaneInformation();
        instance.setContactsPaneInformation();
        instance.setEmployerPaneInformation();
        instance.setLoanPaneInformation();
        instance.setNotesPaneInformation();
    }
}
