package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import uoa.lavs.Main;
import uoa.lavs.SceneManager;
import uoa.lavs.SceneManager.Screens;
import uoa.lavs.comms.Loan.*;
import uoa.lavs.logging.Cache;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Loan.Coborrower;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.models.Loan.LoanDetails;
import uoa.lavs.utility.LoanValidator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NewLoanScreenController {

    private static NewLoanScreenController instance;

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
    private TextField tfNewLoanCustomerId;
    @FXML
    private TextField tfNewLoanPrincipal;
    @FXML
    private TextField tfNewLoanRate;
    @FXML
    private RadioButton rbRateTypeFloating;
    @FXML
    private RadioButton rbRateTypeFixed;
    @FXML
    private DatePicker dpNewLoanStartDate;
    @FXML
    private TextField tfNewLoanTerm;
    @FXML
    private TextField tfNewLoanPeriod;
    @FXML
    private RadioButton rbCompoundingWeekly;
    @FXML
    private RadioButton rbCompoundingMonthly;
    @FXML
    private RadioButton rbCompoundingAnnually;
    @FXML
    private RadioButton rbPaymentWeekly;
    @FXML
    private RadioButton rbPaymentFortnightly;
    @FXML
    private RadioButton rbPaymentMonthly;
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

    @FXML
    private void initialize() {
        instance = this;
    }

    public void submitNewLoan() throws IOException {
        fillLoanValuesMap();

        if (loanValidator.validateLoan(loanValuesMap)) {
            Loan newLoan = loanValidator.createLoan(loanValuesMap);

            // Connection
            Connection conn = Instance.getConnection();

            // Add Instances
            AddLoan addLoan = new AddLoan();
            AddCoborrower addCoborrower = new AddCoborrower();

            // Attempt to create new loan in the mainframe
            String loanID = addLoan.add(conn, newLoan);

            newLoan.setLoanId(loanID);
            newLoan.setStatus(LoanStatus.New);
            UpdateStatus update = new UpdateStatus();

            // Will log if loan was not created in mainframe
            update.add(conn, LoanStatus.New, loanID);
            
            for (Coborrower coborrower : newLoan.getCoborrowerList()) {
                addCoborrower.add(conn, coborrower, loanID);
            }

            // Add Loan to Cache
            Cache.cacheLoan(newLoan);

            // Set active Loan
            LoanBucket.getInstance().setLoan(newLoan);
            LoanScreenController.updateLoan();

            Main.setScreen(Screens.LOAN);
            Main.refreshLoan();

        }
    }

    private void fillLoanValuesMap() {
        loanValuesMap.clear();
        LocalDate startDate = dpNewLoanStartDate.getValue();
        String startDateString = "";
        if (startDate != null) {
            startDateString = startDate.toString();
        }

        loanValuesMap.put("customerId", tfNewLoanCustomerId.getText());
        loanValuesMap.put("principal", tfNewLoanPrincipal.getText());
        loanValuesMap.put("rate", tfNewLoanRate.getText());
        loanValuesMap.put("isFloating", String.valueOf(rbRateTypeFloating.isSelected()));
        loanValuesMap.put("isFixed", String.valueOf(rbRateTypeFixed.isSelected()));
        loanValuesMap.put("startDate", startDateString);
        loanValuesMap.put("period", tfNewLoanPeriod.getText());
        loanValuesMap.put("term", tfNewLoanTerm.getText());
        loanValuesMap.put("compoundingWeekly", String.valueOf(rbCompoundingWeekly.isSelected()));
        loanValuesMap.put("compoundingMonthly", String.valueOf(rbCompoundingMonthly.isSelected()));
        loanValuesMap.put("compoundingAnnually", String.valueOf(rbCompoundingAnnually.isSelected()));
        loanValuesMap.put("frequencyWeekly", String.valueOf(rbPaymentWeekly.isSelected()));
        loanValuesMap.put("frequencyFortnightly", String.valueOf(rbPaymentFortnightly.isSelected()));
        loanValuesMap.put("frequencyMonthly", String.valueOf(rbPaymentMonthly.isSelected()));
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
    private void onNewLoanBackClicked(MouseEvent event) throws IOException {
        Main.setScreen(Screens.HOME);
        Main.refreshLoan();
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
    private void logoClicked() throws IOException {
        Main.setScreen(Screens.HOME);
        Main.refreshLoan();
    }

    @FXML
    private void btnLogOut() throws IOException {
        Main.setScreen(Screens.LOGIN);
        Main.refreshLoan();
    }

    public static void updateCustomerField() {
        Customer customer = CustomerBucket.getInstance().getCustomer();
        if (customer != null) {
            instance.tfNewLoanCustomerId.setText(customer.getId());
        }
    }
}
