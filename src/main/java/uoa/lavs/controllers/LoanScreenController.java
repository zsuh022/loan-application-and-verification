package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import uoa.lavs.Main;
import uoa.lavs.SceneManager.Screens;
import uoa.lavs.models.Loan.Loan;

import java.util.HashMap;

public class LoanScreenController {

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
    // TODO: @FXML private CheckBox cbGeneralLoanCompounding
    // TODO: @FXML private CheckBox cbGeneralLoanFrequency
    @FXML
    private Label lbGeneralLoanAmount;
    // TODO: @FXML private CheckBox cbGeneralLoanInterestOnly
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

    public boolean submitLoanUpdate() {
        // TODO:
        return true;
    }

    @FXML
    private void onLoanBackClicked(MouseEvent event) {
        // go to drafts?
    }

    @FXML
    private void onGeneralButtonClicked() {
        btnGeneralLoans.setVisible(true);
        btnPaymentsLoans.setVisible(false);
        btnCoborrowersLoans.setVisible(false);
        btnSummaryLoans.setVisible(false);
        btnGeneralLoans.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnPaymentsLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnCoborrowersLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnSummaryLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onPaymentsButtonClicked() {
        btnGeneralLoans.setVisible(false);
        btnPaymentsLoans.setVisible(true);
        btnCoborrowersLoans.setVisible(false);
        btnSummaryLoans.setVisible(false);
        btnGeneralLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnPaymentsLoans.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnCoborrowersLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnSummaryLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onCoborrowersButtonClicked() {
        btnGeneralLoans.setVisible(false);
        btnPaymentsLoans.setVisible(false);
        btnCoborrowersLoans.setVisible(true);
        btnSummaryLoans.setVisible(false);
        btnGeneralLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnPaymentsLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnCoborrowersLoans.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-border-color: #ffffff; -fx-border-width: 1px");
        btnSummaryLoans.setStyle("-fx-background-color: rgba(255,255,255,0.3); -fx-border-color: #ffffff; -fx-border-width: 1px");
    }

    @FXML
    private void onSummaryButtonClicked() {
        btnGeneralLoans.setVisible(false);
        btnPaymentsLoans.setVisible(false);
        btnCoborrowersLoans.setVisible(false);
        btnSummaryLoans.setVisible(true);
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
    private void btnLogOut(){
        Main.setScreen(Screens.LOGIN);
    }
}
