package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class DraftsScreenController {

    @FXML
    private FontAwesomeIconView draftsBack;
    @FXML
    private TextField tfDraft1;
    @FXML
    private TextField tfDraft2;
    @FXML
    private TextField tfDraft3;
    @FXML
    private TextField tfDraft4;

    @FXML
    private void onDraftsBackClicked(MouseEvent event) {
        // go somewhere
    }
}
