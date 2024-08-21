package uoa.lavs.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import uoa.lavs.Main;
import uoa.lavs.SceneManager.Screens;

public class HomeScreenController
 {

    @FXML
    private MenuItem btnAddCustomer;

    @FXML
    private MenuItem btnAddLoan;

    @FXML
    private Button btnDrafts;

    @FXML
    private MenuItem btnSearchCustomer;

    @FXML
    private MenuItem btnSearchLoan;

    @FXML
    void onDraftsButtonClicked(ActionEvent event) {
        Main.setScreen(Screens.DRAFTS);
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
    private void btnLogOut(){
        Main.setScreen(Screens.LOGIN);
    }
}
