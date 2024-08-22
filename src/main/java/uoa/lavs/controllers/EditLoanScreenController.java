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
import uoa.lavs.models.Loan.LoanDetails;
import uoa.lavs.models.Loan.Payments;
import uoa.lavs.utility.LoanValidator;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EditLoanScreenController {

    private static EditLoanScreenController INSTANCE;

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
    private CheckBox cbNewLoanIsFloating;
    @FXML
    private CheckBox cbNewLoanIsFixed;
    @FXML
    private Label lbGeneralLoanStartDate;
    @FXML
    private TextField tfNewLoanTerm;
    @FXML
    private TextField tfNewLoanPeriod;
    @FXML
    private CheckBox cbNewLoanCompoundingWeekly;
    @FXML
    private CheckBox cbNewLoanCompoundingMonthly;
    @FXML
    private CheckBox cbNewLoanCompoundingAnnually;
    @FXML
    private CheckBox cbNewLoanFrequencyWeekly;
    @FXML
    private CheckBox cbNewLoanFrequencyFortnightly;
    @FXML
    private CheckBox cbNewLoanFrequencyMonthly;
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
    private TextField tfNewLoanCoborrowerId;
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

    private static EditLoanScreenController instance;

    @FXML
    public void initialize() {
        instance = this;
    }


    public static void editLoan() {
        instance.activeLoan = LoanBucket.getInstance().getLoan();
        instance.mapFields(instance.activeLoan);

    }

    private void mapFields(Loan loan) {
        lbGeneralLoanCustomerId.setText(loan.getCustomerId());
        lbGeneralLoanCustomerName.setText(getCustomerName(loan.getCustomerId()));
        lbGeneralLoanPrincipal.setText(formatCurrency(loan.getPrincipal()));
        lbGeneralLoanStatus.setText(loan.getStatusString());
        lbGeneralLoanRate.setText(loan.getRate() + " %");
        lbGeneralLoanStartDate.setText(formatDate(loan.getStartDate()));
        lbGeneralLoanPeriod.setText(formatMonthsToYears(loan.getPeriod()));
        lbGeneralLoanTerm.setText(formatMonthsToYears(loan.getTerm()));
        lbGeneralLoanCompounding.setText(loan.getCompoundingString());
        lbGeneralLoanFrequency.setText(loan.getPaymentFrequencyString());
        lbGeneralLoanAmount.setText(formatCurrency(loan.getPaymentAmount()));
        if (loan.getInterestOnly()) {
            lbGeneralLoanInterestOnly.setText("\u2611");
        } else {
            lbGeneralLoanInterestOnly.setText("\u2610");
        }

        List<Coborrower> list = loan.getCoborrowerList();
        displayCoborrowers(list);


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

    private String formatCurrency(double amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        return currencyFormatter.format(amount);
    }

    private String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }

    private String formatMonthsToYears(int totalMonths) {
        int years = totalMonths / 12;
        int months = totalMonths % 12;

        if (years > 0 && months > 0) {
            return String.format("%d months (%d years)", totalMonths, years);
        } else if (years > 0) {
            return String.format("%d years", years);
        } else {
            return String.format("%d months", months);
        }
    }

    }

    private void fillLoanValuesMap() {
        loanValuesMap.clear();

        loanValuesMap.put("customerId", lbGeneralLoanCustomerId.getText());
        loanValuesMap.put("principal", lbGeneralLoanPrincipal.getText());
        loanValuesMap.put("rate", tfNewLoanRate.getText());
        loanValuesMap.put("isFloating", String.valueOf(cbNewLoanIsFloating.isSelected()));
        loanValuesMap.put("isFixed", String.valueOf(cbNewLoanIsFixed.isSelected()));
        loanValuesMap.put("startDate", lbGeneralLoanStartDate.getText());
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
