package uoa.lavs.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import uoa.lavs.models.Loan;

import java.util.HashMap;

public class LoanScreenController {

    @FXML private FontAwesomeIconView loanBack;
    @FXML private Circle editLoansCircle;
    @FXML private Rectangle saveLoansRectangle;

    private Loan activeLoan;
    private HashMap<String, String> changesMap = new HashMap<>();

    public boolean submitLoanUpdate() {
        // TODO:
        return true;
    }

    @FXML
    private void onLoanBackClicked(MouseEvent event) {
        // go to drafts?
    }
}
