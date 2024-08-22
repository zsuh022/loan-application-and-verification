package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import uoa.lavs.Main;
import uoa.lavs.SceneManager.Screens;
import uoa.lavs.comms.Customer.SearchCustomer;
import uoa.lavs.comms.Loan.SearchLoanSummary;
import uoa.lavs.comms.Loan.SearchPayments;
import uoa.lavs.logging.Cache;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Loan.Coborrower;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.models.Loan.LoanDetails;
import uoa.lavs.models.Loan.Payments;

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
    @FXML
    private ScrollPane loanPaymentScrollPane;
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
    @FXML
    private ScrollPane loanCoborrowersPaneInner;
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

        LoanDetails details = loan.getSummary();
        if (details == null) {
            SearchLoanSummary searchSummary = new SearchLoanSummary();
            details = searchSummary.findById(Instance.getConnection(), loan.getId());
        }

        lbSummaryLoansCustomerName.setText(details.getCustomerName());
        lbSummaryLoansCustomerId.setText(details.getCustomerID());
        lbSummaryLoansRate.setText(details.getRate() + " %");
        lbSummaryLoansTerm.setText(formatMonthsToYears(details.getTerm()));
        lbSummaryLoansFrequency.setText(details.getFrequencyString());
        lbSummaryLoansAmount.setText(formatCurrency(details.getPayment()));
        lbSummaryLoansTotalPaid.setText(formatCurrency(details.getInterest()));
        lbSummaryLoansTotalCost.setText(formatCurrency(details.getTotal()));
        lbSummaryLoansPayoffDate.setText(formatDate(details.getPayoffDate()));

        List<Coborrower> list = loan.getCoborrowerList();
        displayCoborrowers(list);

        List<Payments> paymentList = loan.getPaymentsList();
        displayPaymentList(paymentList);

    }

    private void displayPaymentList(List<Payments> paymentList) {
        VBox vbox = createPaymentsVBox(paymentList);
        loanPaymentScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        loanPaymentScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        loanPaymentScrollPane.setContent(vbox);
    }

    private VBox createPaymentsVBox(List<Payments> paymentList) {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setStyle("-fx-padding: 10;");

        for (Payments payment : paymentList) {
            System.out.println(":ADWADAWDA" + payment.getCustomerName());

            AnchorPane pane = new AnchorPane();
            pane.setPrefWidth(630);
            pane.setPrefHeight(100);
            pane.setStyle("-fx-background-color: #F0F0F0; -fx-border-color: #CCCCCC; -fx-border-radius: 5; -fx-background-radius: 5;");

            VBox left = new VBox();
            left.setStyle("-fx-padding: 10;");
            VBox right = new VBox();
            right.setStyle("-fx-padding: 10;");

            pane.getChildren().addAll(left, right);
            AnchorPane.setLeftAnchor(left, 0.0);
            AnchorPane.setRightAnchor(right, 0.0);

            Label idLabel = new Label("Customer ID: " + payment.getCustomerId());
            idLabel.setStyle("-fx-font-size: 12px;");

            Label paymentDateLabel = new Label("Payment Date: " + formatDate(payment.getPaymentDate()));
            paymentDateLabel.setStyle("-fx-font-size: 12px; ");

            Label paymentNumberLabel = new Label("Payment Number: " + payment.getPaymentNumber());
            paymentNumberLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            Label paymentInterestLabel = new Label("Interest Paid: " + String.format("%.2f", payment.getPaymentInterest()));
            paymentInterestLabel.setStyle("-fx-font-size: 12px;");

            Label paymentPrincipalLabel = new Label("Principal Paid: " + String.format("%.2f", payment.getPaymentPrincipal()));
            paymentPrincipalLabel.setStyle("-fx-font-size: 12px;");

            Label paymentRemainingLabel = new Label("Remaining Balance: " + String.format("%.2f", payment.getPaymentRemaining()));
            paymentRemainingLabel.setStyle("-fx-font-size: 12px;");

            left.getChildren().addAll(paymentNumberLabel, idLabel, paymentDateLabel);
            right.getChildren().addAll(paymentInterestLabel, paymentPrincipalLabel, paymentRemainingLabel);

            vbox.getChildren().add(pane);
        }

        return vbox;
    }


    private void displayCoborrowers(List<Coborrower> coborrowers) {
        VBox vbox = createCoborrowersVBox(coborrowers);

        loanCoborrowersPaneInner.setContent(vbox);
        loanCoborrowersPaneInner.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        loanCoborrowersPaneInner.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    private VBox createCoborrowersVBox(List<Coborrower> coborrowers) {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setStyle("-fx-padding: 10;");

        for (Coborrower coborrower : coborrowers) {

            AnchorPane pane = new AnchorPane();
            pane.setPrefWidth(630);
            pane.setPrefHeight(70);
            pane.setStyle("-fx-background-color: #F0F0F0; -fx-border-color: #CCCCCC; -fx-border-radius: 5; -fx-background-radius: 5;");

            VBox left = new VBox();
            left.setStyle("-fx-padding: 10;");

            pane.getChildren().addAll(left);
            AnchorPane.setLeftAnchor(left, 0.0);

            Label nameLabel = new Label("Name: " + coborrower.getName());
            nameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            Label idLabel = new Label("Coborrower ID: " + coborrower.getId());
            idLabel.setStyle("-fx-font-size: 12px;");


            left.getChildren().addAll(nameLabel, idLabel);

            vbox.getChildren().add(pane);
        }

        return vbox;
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
