package uoa.lavs.controllers;

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
import uoa.lavs.comms.Customer.InitialSearch;
import uoa.lavs.comms.Customer.SearchCustomer;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.models.Customer.CustomerSummary;

import java.util.List;

public class SearchCustomerScreenController {

    @FXML
    private FontAwesomeIconView searchCustomerBack;
    @FXML
    private FontAwesomeIconView searchCustomerEnter;
    @FXML
    private TextField searchCustomerBar;
    @FXML
    private TextField customerSearchResult1;
    @FXML
    private TextField customerSearchResult2;
    @FXML
    private TextField customerSearchResult3;
    @FXML
    private TextField customerSearchResult4;
    @FXML
    private ScrollPane resultBox;

    public boolean submitCustomerSearch() {
        String searchText = searchCustomerBar.getText().trim();
        InitialSearch search;

        if (isInteger(searchText)) {
            search = new InitialSearch(0);
        } else {
            search = new InitialSearch(1);
        }
        List<CustomerSummary> searchResults = search.findAll(Instance.getConnection(), searchCustomerBar.getText());
        if (searchResults.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Results Found");
            alert.setHeaderText("No results found for the search term: " + searchCustomerBar.getText());
            alert.showAndWait();
            return false;
        } else {
            VBox content = new VBox();

            content.setSpacing(10);
            content.getChildren().addAll(CustomerResultReader.processSearch(searchResults));
            resultBox.setContent(content);
        }
        return true;
    }

    @FXML
    private void onSearchCustomerBackClicked() {
        // go to home screen
        Main.setScreen(Screens.HOME);
    }

    @FXML
    private void onSearchCustomerEnterClicked(MouseEvent event) {
        submitCustomerSearch();
    }

    @FXML
    private void onSearchCustomerEnterKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            submitCustomerSearch();
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

    private boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
