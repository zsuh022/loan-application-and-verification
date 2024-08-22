package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import uoa.lavs.Main;
import uoa.lavs.SceneManager;
import uoa.lavs.SceneManager.Screens;
import uoa.lavs.comms.Customer.SearchCustomer;
import uoa.lavs.comms.Loan.*;
import uoa.lavs.logging.Cache;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Loan.Coborrower;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.utility.LoanValidator;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EditLoanScreenController {

    private static EditLoanScreenController instance;

    private Loan activeLoan;

    private HashMap<String, String> loanValuesMap = new HashMap<>();

    private LoanValidator loanValidator = new LoanValidator();

    @FXML
    private FontAwesomeIconView newLoanBack;
    @FXML
    private Rectangle saveNewLoanRectangle;
    // general
    @FXML
    private Button btnGeneralNewLoan;
    @FXML
    private AnchorPane newLoanGeneralPane;
    @FXML
    private Label lbGeneralLoanCustomerId;
    @FXML
    private Label lbGeneralLoanCustomerName;
    @FXML
    private Label lbGeneralLoanPrincipal;
    @FXML
    private TextField tfNewLoanRate;
    @FXML
    private RadioButton cbNewLoanIsFloating;
    @FXML
    private RadioButton cbNewLoanIsFixed;
    @FXML
    private RadioButton rbNewLoanStatusNew;
    @FXML
    private RadioButton rbNewLoanStatusPending;
    @FXML
    private RadioButton rbNewLoanStatusActive;
    @FXML
    private RadioButton rbNewLoanStatusCancelled;
    @FXML
    private Label lbGeneralLoanStartDate;
    @FXML
    private TextField tfNewLoanTerm;
    @FXML
    private TextField tfNewLoanPeriod;
    @FXML
    private RadioButton cbNewLoanCompoundingWeekly;
    @FXML
    private RadioButton cbNewLoanCompoundingMonthly;
    @FXML
    private RadioButton cbNewLoanCompoundingAnnually;
    @FXML
    private RadioButton cbNewLoanFrequencyWeekly;
    @FXML
    private RadioButton cbNewLoanFrequencyFortnightly;
    @FXML
    private RadioButton cbNewLoanFrequencyMonthly;
    @FXML
    private TextField tfNewLoanAmount;
    @FXML
    private CheckBox cbNewLoanIsInterestOnly;
    // coborrowers
    @FXML
    private Button btnCoborrowersNewLoan;
    @FXML
    private AnchorPane newLoanCoborrowersPane;
    @FXML
    private TextField tfNewLoanCoborrowerId0;
    @FXML
    private TextField tfNewLoanCoborrowerId1;
    @FXML
    private TextField tfNewLoanCoborrowerId2;
    @FXML
    private TextField tfNewLoanCoborrowerId3;
    @FXML
    private TextField tfNewLoanCoborrowerId4;
    @FXML
    private TextField tfNewLoanCoborrowerId5;
    @FXML
    private TextField tfNewLoanCoborrowerId6;
    @FXML
    private TextField tfNewLoanCoborrowerId7;
    @FXML
    private TextField tfNewLoanCoborrowerId8;
    @FXML
    private TextField tfNewLoanCoborrowerId9;
    @FXML
    private TextField tfNewLoanCoborrowerId10;
    @FXML
    private TextField tfNewLoanCoborrowerId11;
    @FXML
    private TextField tfNewLoanCoborrowerId12;
    @FXML
    private TextField tfNewLoanCoborrowerId13;
    @FXML
    private TextField tfNewLoanCoborrowerId14;
    @FXML
    private TextField tfNewLoanCoborrowerId15;
    @FXML
    private TextField tfNewLoanCoborrowerId16;
    @FXML
    private TextField tfNewLoanCoborrowerId17;

    private List<TextField> coborrowerTextFields;

    @FXML
    public void initialize() {

        instance = this;
        coborrowerTextFields = Arrays.asList(
                tfNewLoanCoborrowerId0,
                tfNewLoanCoborrowerId1,
                tfNewLoanCoborrowerId3,
                tfNewLoanCoborrowerId4,
                tfNewLoanCoborrowerId5,
                tfNewLoanCoborrowerId6,
                tfNewLoanCoborrowerId7,
                tfNewLoanCoborrowerId8,
                tfNewLoanCoborrowerId10,
                tfNewLoanCoborrowerId11,
                tfNewLoanCoborrowerId12,
                tfNewLoanCoborrowerId13,
                tfNewLoanCoborrowerId14,
                tfNewLoanCoborrowerId15,
                tfNewLoanCoborrowerId16,
                tfNewLoanCoborrowerId17);
    }


    public static void editLoanUpdate() {
        instance.activeLoan = LoanBucket.getInstance().getLoan();
        instance.mapFields(instance.activeLoan);

    }

    private void mapFields(Loan loan) {
        lbGeneralLoanCustomerId.setText(loan.getCustomerId());
        lbGeneralLoanCustomerName.setText(getCustomerName(loan.getCustomerId()));
        lbGeneralLoanPrincipal.setText(String.valueOf(loan.getPrincipal()));
        LoanStatus status = loan.getStatus();
        switch (status) {
            case New:
                rbNewLoanStatusNew.setSelected(true);
            case Pending:
                rbNewLoanStatusPending.setSelected(true);
            case Cancelled:
                rbNewLoanStatusCancelled.setSelected(true);
            default:
                rbNewLoanStatusActive.setSelected(true);
        }
        switch (loan.getRateType()) {
            case Fixed:
                cbNewLoanIsFixed.setSelected(true);
            case Floating:
                cbNewLoanIsFloating.setSelected(true);
        }
        tfNewLoanRate.setText(String.valueOf(loan.getRate()));
        lbGeneralLoanStartDate.setText(formatDate(loan.getStartDate()));
        tfNewLoanTerm.setText(String.valueOf(loan.getTerm()));
        tfNewLoanPeriod.setText(String.valueOf(loan.getPeriod()));
        switch (loan.getCompoundingFrequency()) {
            case Weekly:
                cbNewLoanCompoundingWeekly.setSelected(true);
            case Monthly:
                cbNewLoanCompoundingMonthly.setSelected(true);
            default:
                cbNewLoanCompoundingAnnually.setSelected(true);
        }
        switch (loan.getPaymentFrequency()) {
            case Weekly:
                cbNewLoanFrequencyWeekly.setSelected(true);
            case Fortnightly:
                cbNewLoanFrequencyFortnightly.setSelected(true);
            default:
                cbNewLoanFrequencyMonthly.setSelected(true);
        }
        tfNewLoanAmount.setText(String.valueOf(loan.getPaymentAmount()));
        cbNewLoanIsInterestOnly.setSelected(loan.getInterestOnly());
        List<Coborrower> list = loan.getCoborrowerList();
        displayCoborrowers(list);
    }

    public void submitNewLoan() {
        fillLoanValuesMap();

        if (loanValidator.validateLoan(loanValuesMap)) {
            loanValidator.updateLoan(loanValuesMap);

            // Connection
            Connection conn = Instance.getConnection();

            // Add Instances
            AddLoan addLoan = new AddLoan();
            AddCoborrower addCoborrower = new AddCoborrower();

            // Attempt to update
            String loanID = addLoan.add(conn, activeLoan);

            loanValuesMap.put("isFloating", String.valueOf(cbNewLoanIsFloating.isSelected()));
            loanValuesMap.put("isFixed", String.valueOf(cbNewLoanIsFixed.isSelected()));
            LoanStatus status = null;
            if (rbNewLoanStatusActive.isSelected()) {
                activeLoan.setStatus(LoanStatus.Active);
                status = LoanStatus.Active;
            } else if (rbNewLoanStatusCancelled.isSelected()) {
                activeLoan.setStatus(LoanStatus.Cancelled);
                status = LoanStatus.Cancelled;
            } else if (rbNewLoanStatusPending.isSelected()) {
                activeLoan.setStatus(LoanStatus.Pending);
                status = LoanStatus.Pending;
            }
            UpdateStatus update = new UpdateStatus();

            update.add(conn, status, loanID);

            for (Coborrower coborrower : activeLoan.getCoborrowerList()) {
                addCoborrower.add(conn, coborrower, loanID);
            }

            LoanScreenController.updateLoan();

            Main.setScreen(Screens.LOAN);

        }
    }

    private void displayCoborrowers(List<Coborrower> coborrowers) {
        int size = Math.min(coborrowers.size(), coborrowerTextFields.size());
        for (int i = 0; i < size; i++) {
            TextField field = coborrowerTextFields.get(i);
            field.setText(coborrowers.get(i).getId());
        }
    }

    private String getCustomerName(String customerID) {
        List<Customer> list = Cache.searchCustomerCacheId(customerID);
        if (list.size() == 1) {
            // Customer exists in cache
            return list.get(0).getName();
        } else {

            // Customer not in cache so cache customer
            SearchCustomer customerSearch = new SearchCustomer();
            Customer cus = customerSearch.findById(Instance.getConnection(), customerID);
            if (cus != null) {
                Cache.cacheCustomer(cus);
                // Recursive call as customer is cached now
                return getCustomerName(cus.getId());
            }
            return "";
        }
    }


    private String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }


    private void fillLoanValuesMap() {
        loanValuesMap.clear();

        loanValuesMap.put("customerId", lbGeneralLoanCustomerId.getText());
        loanValuesMap.put("principal", lbGeneralLoanPrincipal.getText());
        loanValuesMap.put("rate", tfNewLoanRate.getText());
        loanValuesMap.put("isFloating", String.valueOf(cbNewLoanIsFloating.isSelected()));
        loanValuesMap.put("isFixed", String.valueOf(cbNewLoanIsFixed.isSelected()));
        loanValuesMap.put("startDate", String.valueOf(activeLoan.getStartDate()));
        loanValuesMap.put("period", tfNewLoanPeriod.getText());
        loanValuesMap.put("term", tfNewLoanTerm.getText());
        loanValuesMap.put("compoundingWeekly", String.valueOf(cbNewLoanCompoundingWeekly.isSelected()));
        loanValuesMap.put("compoundingMonthly", String.valueOf(cbNewLoanCompoundingMonthly.isSelected()));
        loanValuesMap.put("compoundingAnnually", String.valueOf(cbNewLoanCompoundingAnnually.isSelected()));
        loanValuesMap.put("frequencyWeekly", String.valueOf(cbNewLoanFrequencyWeekly.isSelected()));
        loanValuesMap.put("frequencyFortnightly", String.valueOf(cbNewLoanFrequencyFortnightly.isSelected()));
        loanValuesMap.put("frequencyMonthly", String.valueOf(cbNewLoanFrequencyMonthly.isSelected()));
        loanValuesMap.put("amount", tfNewLoanAmount.getText());
        loanValuesMap.put("isInterestOnly", String.valueOf(cbNewLoanIsInterestOnly.isSelected()));

        for (int i = 0; i < 18; i++) {
            TextField coborrowerId;
            if (i == 0) {
                coborrowerId = (TextField) newLoanCoborrowersPane.lookup("#tfNewLoanCoborrowerId");
            } else {
                coborrowerId = (TextField) newLoanCoborrowersPane.lookup("#tfNewLoanCoborrowerId" + i);
            }

            if (coborrowerId != null && !coborrowerId.getText().isEmpty()) {
                loanValuesMap.put("coborrowerId" + i, coborrowerId.getText());
            }
        }
    }

    @FXML
    private void onNewLoanBackClicked(MouseEvent event) {
        Main.setScreen(Screens.HOME);
    }

    @FXML
    private void onGeneralButtonClicked() {
        newLoanGeneralPane.setVisible(true);
        newLoanCoborrowersPane.setVisible(false);
        btnCoborrowersNewLoan.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnGeneralNewLoan.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onCoborrowersButtonClicked() {
        newLoanGeneralPane.setVisible(false);
        newLoanCoborrowersPane.setVisible(true);
        btnGeneralNewLoan.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnCoborrowersNewLoan.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
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
    private void onEditLoanBackClicked() {
        Main.setScreen(Screens.LOAN);
    }
}
