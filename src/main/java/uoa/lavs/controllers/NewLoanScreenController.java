package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;

public class NewLoanScreenController {

    private HashMap<String, String> loanValuesMap = new HashMap<>();

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
    private CheckBox cbNewLoanIsFloating;
    @FXML
    private CheckBox cbNewLoanIsFixed;
    @FXML
    private TextField tfNewLoanStartDate;
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

    public boolean submitNewLoan() {
        // TODO:
        return true;
    }
}
