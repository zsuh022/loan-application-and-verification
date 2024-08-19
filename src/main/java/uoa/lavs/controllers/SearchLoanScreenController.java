package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import uoa.lavs.Main;
import uoa.lavs.SceneManager.Screens;

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

    private String searchString;

    public boolean submitLoanSearch() {
        // TODO:
        return true;
    }

    @FXML
    private void onSearchLoanBackClicked(MouseEvent event) {
        // go to home screen
        Main.setScreen(Screens.HOME);
    }

    @FXML
    private void onSearchLoanEnterClicked(MouseEvent event) {

    }

    @FXML
    private void onSearchLoanEnterKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {

        }
    }

    @FXML
    private void logoClicked(){
        Main.setScreen(Screens.HOME);
    }
}
