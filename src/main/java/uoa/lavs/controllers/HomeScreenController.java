package uoa.lavs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import uoa.lavs.Main;
import uoa.lavs.SceneManager.Screens;
import uoa.lavs.logging.LocalLogManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomeScreenController
 {

    @FXML
    private MenuItem btnAddCustomer;
    @FXML
    private MenuItem btnAddLoan;
    @FXML
    private Button btnDrafts;
    @FXML
    private Button syncButton;

    @FXML
    private MenuItem btnSearchCustomer;
    @FXML
    private MenuItem btnSearchLoan;
    @FXML
    private Label syncTimeLabel;

    @FXML
    public void initialize() {
        syncButton.disableProperty().bind(LocalLogManager.getEmptyProperty());
        syncTimeLabel.textProperty().bind(LocalLogManager.getSyncTimeProperty());
    }


    @FXML
    void searchLoanClicked(ActionEvent event){
        Main.setScreen(Screens.SEARCH_LOAN);
    }

    @FXML
    void searchCustomerClicked(ActionEvent event){
        Main.setScreen(Screens.SEARCH_CUSTOMER);
    }

    @FXML
    void addLoanClicked(ActionEvent event){
        Main.setScreen(Screens.NEW_LOAN);
    }

    @FXML
    void addCustomerClicked(ActionEvent event){
        Main.setScreen(Screens.NEW_CUSTOMER);
    }

    @FXML
    void onSyncButtonClicked(ActionEvent event){
        if(!LocalLogManager.flushLog()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sync Error");
            alert.setHeaderText("An error occurred while syncing the logs.");
            alert.setContentText("Please try again later.");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnLogOut(){
        Main.setScreen(Screens.LOGIN);
    }
}
