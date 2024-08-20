package uoa.lavs.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import uoa.lavs.Main;
import uoa.lavs.SceneManager.Screens;

public class LoginScreenController {

    @FXML
    private Button btnCloseApp;

    @FXML
    private Button btnOpenApp;

    @FXML
    void closeApp(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void launchApp(MouseEvent event) {
        Main.setScreen(Screens.HOME);
    }

}
