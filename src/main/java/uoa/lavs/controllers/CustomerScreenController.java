package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    private Customer activeCustomer;

    private HashMap<String, String> changeMap = new HashMap<>();

    private CustomerValidator customerValidator = new CustomerValidator();

    @FXML
    private FontAwesomeIconView CustomerBack;
    @FXML
    private Rectangle addLoanRectangle;
    @FXML
    private Circle editCustomersCircle;
    // general
    @FXML
    private Button btnGeneralCustomer;
    @FXML
    private AnchorPane customerGeneralPane;
    @FXML
    private Label lbCustomerId;
    @FXML
    private Label lbCustomerTitle;
    @FXML
    private Label lbCustomerName;
    @FXML
    private Label lbCustomerStatus;
    @FXML
    private Label lbCustomerDob;
    @FXML
    private Label lbCustomerOccupation;
    @FXML
    private Label lbCustomerCitizenship;
    @FXML
    private Label lbCustomerVisa;
    @FXML
    private Label lbCustomerPhone;
    @FXML
    private Label lbCustomerEmail;
    // address
    @FXML
    private Button btnAddressCustomer;
    @FXML
    private AnchorPane customerAddressPane;
    @FXML
    private Label lbCustomerType;
    @FXML
    private Label lbCustomerLine1;
    @FXML
    private Label lbCustomerLine2;
    @FXML
    private Label lbCustomerSuburb;
    @FXML
    private Label lbCustomerCity;
    @FXML
    private Label lbCustomerPostcode;
    @FXML
    private Label lbCustomerCountry;
    // loans
    @FXML
    private Button btnLoansCustomer;
    // TODO:
    @FXML
    private AnchorPane customerLoansPane;
    // employer
    @FXML
    private Button btnEmployerCustomer;
    @FXML
    private AnchorPane customerEmployerPane;
    @FXML
    private Label lbCustomerEmployerName;
    @FXML
    private Label lbCustomerEmployerLine1;
    @FXML
    private Label lbCustomerEmployerLine2;
    @FXML
    private Label lbCustomerEmployerSuburb;
    @FXML
    private Label lbCustomerEmployerCity;
    @FXML
    private Label lbCustomerEmployerPostcode;
    @FXML
    private Label lbCustomerEmployerCountry;
    @FXML
    private Label lbCustomerEmployerPhone;
    @FXML
    private Label lbCustomerEmployerEmail;
    @FXML
    private Label lbCustomerEmployerWeb;
    // notes
    @FXML
    private Button btnNotesCustomer;
    @FXML
    private AnchorPane customerNotesPane;
    @FXML
    private TextArea taCustomerNotes;

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
        lbCustomerDob.setText(String.valueOf(activeCustomer.getDob()));
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
        // go to
    }

    @FXML
    private void onGeneralButtonClicked() {
        customerGeneralPane.setVisible(true);
        customerAddressPane.setVisible(false);
        customerLoansPane.setVisible(false);
        customerEmployerPane.setVisible(false);
        customerNotesPane.setVisible(false);
        btnGeneralCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnLoansCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onAddressButtonClicked() {
        customerGeneralPane.setVisible(false);
        customerAddressPane.setVisible(true);
        customerLoansPane.setVisible(false);
        customerEmployerPane.setVisible(false);
        customerNotesPane.setVisible(false);
        btnGeneralCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnLoansCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onLoansButtonClicked() {
        customerGeneralPane.setVisible(false);
        customerAddressPane.setVisible(false);
        customerLoansPane.setVisible(true);
        customerEmployerPane.setVisible(false);
        customerNotesPane.setVisible(false);
        btnGeneralCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnLoansCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onEmployerButtonClicked() {
        customerGeneralPane.setVisible(false);
        customerAddressPane.setVisible(false);
        customerLoansPane.setVisible(false);
        customerEmployerPane.setVisible(true);
        customerNotesPane.setVisible(false);
        btnGeneralCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnLoansCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onNotesButtonClicked() {
        customerGeneralPane.setVisible(false);
        customerAddressPane.setVisible(false);
        customerLoansPane.setVisible(false);
        customerEmployerPane.setVisible(false);
        customerNotesPane.setVisible(true);
        btnGeneralCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnAddressCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnLoansCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnEmployerCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnNotesCustomer.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void logoClicked() {
        Main.setScreen(Screens.HOME);
    }
}
