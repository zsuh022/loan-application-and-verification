package uoa.lavs.controllers;

import javafx.scene.control.Alert;
import uoa.lavs.utility.CustomerValidator;

public class AlertManager {
    private static boolean testing = false;

    public static void errorPopUp(String header, String body) {
        if (!testing) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Validating Customer");
            alert.setHeaderText(header);
            alert.setContentText(body);
            alert.showAndWait();
        }
    }

    public static void setTesting(boolean testing) {
        AlertManager.testing = testing;
    }
}
