package uoa.lavs.controllers;

import java.util.List;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import uoa.lavs.Main;
import uoa.lavs.SceneManager.Screens;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.comms.Loan.InitialSearch;
import uoa.lavs.models.Loan.LoanSummary;

public class SearchLoanScreenController {

    @FXML
    private FontAwesomeIconView searchLoanBack;
    @FXML
    private FontAwesomeIconView searchLoanEnter;
    @FXML
    private TextField searchLoanBar;
    @FXML
    private TextField loanSearchResult1;
    @FXML
    private TextField loanSearchResult2;
    @FXML
    private TextField loanSearchResult3;
    @FXML
    private TextField loanSearchResult4;
    @FXML
    private ScrollPane resultBox;

    private String searchString;

    @FXML
    public void initialize() {
        searchLoanEnter.requestFocus();
    }

    public boolean submitLoanSearch() {
        InitialSearch search = new InitialSearch();
        List<LoanSummary> searchResults = search.findAll(Instance.getConnection(), searchLoanBar.getText());
        if (searchResults.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Results Found");
            alert.setHeaderText("No results found for search term: " + searchLoanBar.getText());
            alert.showAndWait();
            return false;
        } else {
            VBox content = new VBox();
            content.getChildren().addAll(LoanResultReader.processSearch(searchResults));
            resultBox.setContent(content);
        }
        return true;
    }

    @FXML
    private void onSearchLoanBackClicked(MouseEvent event) {
        // go to home screen
        Main.setScreen(Screens.HOME);
    }

    @FXML
    private void onSearchLoanEnterClicked(MouseEvent event) {
        submitLoanSearch();
    }

    @FXML
    private void onSearchLoanEnterKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            submitLoanSearch();
        }
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
