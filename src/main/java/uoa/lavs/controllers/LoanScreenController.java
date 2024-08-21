package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import uoa.lavs.Main;
import uoa.lavs.SceneManager.Screens;
import uoa.lavs.comms.Customer.SearchCustomer;
import uoa.lavs.logging.Cache;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Loan.Loan;

import java.text.NumberFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class LoanScreenController {

    private static LoanScreenController INSTANCE;

    private Loan activeLoan;

    private HashMap<String, String> changesMap = new HashMap<>();

    @FXML
    private FontAwesomeIconView loanBack;
    @FXML
    private Circle editLoansCircle;
    @FXML
    private Rectangle saveLoansRectangle;
    // general
    @FXML
    private Button btnGeneralLoans;
    @FXML
    private AnchorPane loanGeneralPane;
    @FXML
    private Label lbGeneralLoanId;
    @FXML
    private Label lbGeneralLoanCustomerId;
    @FXML
    private Label lbGeneralLoanCustomerName;
    @FXML
    private Label lbGeneralLoanPrincipal;
    @FXML
    private Label lbGeneralLoanStatus;
    @FXML
    private Label lbGeneralLoanRate;
    @FXML
    private Label lbGeneralLoanStartDate;
    @FXML
    private Label lbGeneralLoanPeriod;
    @FXML
    private Label lbGeneralLoanTerm;
    @FXML
    private Label lbGeneralLoanCompounding;
    @FXML
    private Label lbGeneralLoanFrequency;
    @FXML
    private Label lbGeneralLoanAmount;
    @FXML
    private Label lbGeneralLoanInterestOnly;
    // payments
    @FXML
    private Button btnPaymentsLoans;
    @FXML
    private AnchorPane loanPaymentsPane;
    // TODO: @FXML private Label ...
    @FXML
    private Label lbPaymentsLoansLine1;
    @FXML
    private Label lbPaymentsLoansLine2;
    @FXML
    private Label lbPaymentsLoansSuburb;
    @FXML
    private Label lbPaymentsLoansCity;
    @FXML
    private Label lbPaymentsLoansPostcode;
    @FXML
    private Label lbPaymentsLoansCountry;
    // coborrowers
    @FXML
    private Button btnCoborrowersLoans;
    @FXML
    private AnchorPane loanCoborrowersPane;
    // TODO:
    // summary
    @FXML
    private Button btnSummaryLoans;
    @FXML
    private AnchorPane loanSummaryPane;
    @FXML
    private Label lbSummaryLoansCustomerName;
    @FXML
    private Label lbSummaryLoansCustomerId;
    @FXML
    private Label lbSummaryLoansRate;
    @FXML
    private Label lbSummaryLoansTerm;
    @FXML
    private Label lbSummaryLoansFrequency;
    @FXML
    private Label lbSummaryLoansAmount;
    @FXML
    private Label lbSummaryLoansTotalPaid;
    @FXML
    private Label lbSummaryLoansTotalCost;
    @FXML
    private Label lbSummaryLoansPayoffDate;

    private static LoanScreenController instance;

    @FXML
    public void initialize() {
        instance = this;
    }

    public boolean submitLoanUpdate() {
        // TODO:
        return true;
    }

    public static void updateLoan() {
        // get the current customer in the customer bucket and set it as the active customer
        instance.activeLoan = LoanBucket.getInstance().getLoan();

        // Fill the loan screen with the correct details
        instance.fillFields(instance.activeLoan);
    }

    private void fillFields(Loan loan) {
        lbGeneralLoanId.setText(loan.getId());
        lbGeneralLoanCustomerId.setText(loan.getCustomerId());
        lbGeneralLoanCustomerName.setText(getCustomerName(loan.getCustomerId()));
        lbGeneralLoanPrincipal.setText(formatCurrency(loan.getPrincipal()));
        lbGeneralLoanStatus.setText(loan.getStatusString());
        lbGeneralLoanRate.setText(String.valueOf(loan.getRate()) + " %");
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

    @FXML
    private void onLoanBackClicked(MouseEvent event) {
        Main.setScreen(Screens.SEARCH_LOAN);
    }

    @FXML
    private void onGeneralButtonClicked() {
        loanGeneralPane.setVisible(true);
        loanPaymentsPane.setVisible(false);
        loanSummaryPane.setVisible(false);
        loanCoborrowersPane.setVisible(false);
        btnGeneralLoans.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnPaymentsLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnCoborrowersLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnSummaryLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onPaymentsButtonClicked() {
        loanGeneralPane.setVisible(false);
        loanPaymentsPane.setVisible(true);
        loanSummaryPane.setVisible(false);
        loanCoborrowersPane.setVisible(false);
        btnGeneralLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnPaymentsLoans.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnCoborrowersLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnSummaryLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onCoborrowersButtonClicked() {
        loanGeneralPane.setVisible(false);
        loanPaymentsPane.setVisible(false);
        loanSummaryPane.setVisible(false);
        loanCoborrowersPane.setVisible(true);
        btnGeneralLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnPaymentsLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnCoborrowersLoans.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnSummaryLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onSummaryButtonClicked() {
        loanGeneralPane.setVisible(false);
        loanPaymentsPane.setVisible(false);
        loanSummaryPane.setVisible(true);
        loanCoborrowersPane.setVisible(false);
        btnGeneralLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnPaymentsLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnCoborrowersLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnSummaryLoans.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void logoClicked() {
        Main.setScreen(Screens.HOME);
    }

    @FXML
    private void btnLogOut() {
        Main.setScreen(Screens.LOGIN);
    }


}
