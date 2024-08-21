package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import uoa.lavs.Main;
import uoa.lavs.SceneManager.Screens;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    private DatePicker dpNewLoanStartDate;
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


    public void submitNewLoan() {
        fillLoanValuesMap();

        if (validateLoan(loanValuesMap)) {
            // create loan
            // do something
        }
    }

    private void fillLoanValuesMap() {
        loanValuesMap.clear();

        loanValuesMap.put("customerId", tfNewLoanCustomerId.getText());
        loanValuesMap.put("principal", tfNewLoanPrincipal.getText());
        loanValuesMap.put("rate", tfNewLoanRate.getText());
        loanValuesMap.put("isFloating", String.valueOf(cbNewLoanIsFloating.isSelected()));
        loanValuesMap.put("isFixed", String.valueOf(cbNewLoanIsFixed.isSelected()));
        loanValuesMap.put("startDate", dpNewLoanStartDate.getValue().toString());
        loanValuesMap.put("period", tfNewLoanPeriod.getText());
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

    private boolean validateLoan(Map<String, String> loanValuesMap) {

        if (loanValuesMap.get("customerId") == null || loanValuesMap.get("customerId").isEmpty()) {
            return false;
        }

        // check customer id valid

        if (loanValuesMap.get("principal") == null || loanValuesMap.get("principal").isEmpty()) {
            return false;
        }

        if (loanValuesMap.get("rate") == null || loanValuesMap.get("rate").isEmpty()) {
            return false;
        }

        if (loanValuesMap.get("isFloating") == null || loanValuesMap.get("isFloating").isEmpty()) {
            return false;
        }

        if (loanValuesMap.get("isFixed") == null || loanValuesMap.get("isFixed").isEmpty()) {
            return false;
        }

        if (loanValuesMap.get("startDate") == null || loanValuesMap.get("startDate").isEmpty()) {
            return false;
        }

        if (loanValuesMap.get("period") == null || loanValuesMap.get("period").isEmpty()) {
            return false;
        }

        int compoundingCount = 0;
        if (loanValuesMap.get("compoundingWeekly").equals("true")) {
            compoundingCount++;
        }
        if (loanValuesMap.get("compoundingMonthly").equals("true")) {
            compoundingCount++;
        }
        if (loanValuesMap.get("compoundingAnnually").equals("true")) {
            compoundingCount++;
        }

        if (compoundingCount != 1) {
            return false;
        }

        int frequencyCount = 0;
        if (loanValuesMap.get("frequencyWeekly").equals("true")) {
            frequencyCount++;
        }
        if (loanValuesMap.get("frequencyFortnightly").equals("true")) {
            frequencyCount++;
        }
        if (loanValuesMap.get("frequencyMonthly").equals("true")) {
            frequencyCount++;
        }

        if (frequencyCount != 1) {
            return false;
        }

        if (loanValuesMap.get("amount") == null || loanValuesMap.get("amount").isEmpty()) {
            return false;
        }

        if (loanValuesMap.get("isInterestOnly") == null || loanValuesMap.get("isInterestOnly").isEmpty()) {
            return false;
        }

        // check coborrower id valid

        return true;
    }

    @FXML
    private void onNewLoanBackClicked(MouseEvent event) {
        // go to previous screen
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
    private void logoClicked(){
        Main.setScreen(Screens.HOME);
    }
}
