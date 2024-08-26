package uoa.lavs.controllers;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import uoa.lavs.Main;
import uoa.lavs.SceneManager;
import uoa.lavs.comms.Loan.SearchCoborrower;
import uoa.lavs.comms.Loan.SearchLoan;
import uoa.lavs.comms.Loan.SearchLoanSummary;
import uoa.lavs.comms.Loan.SearchPayments;
import uoa.lavs.logging.Cache;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.models.Loan.*;

import java.util.ArrayList;
import java.util.List;

public class LoanResultReader {
    public static List<AnchorPane> processSearch(List<LoanSummary> searchSummaries) {
        List<AnchorPane> panes = new ArrayList<>();
        for (LoanSummary summary : searchSummaries) {
            AnchorPane pane = new AnchorPane();
            pane.setPrefWidth(650);
            pane.setPrefHeight(50);
            pane.setStyle("-fx-background-color: #F0F0F0; -fx-border-color: #CCCCCC; -fx-border-radius: 5; -fx-background-radius: 5;");

            VBox left = new VBox();
            left.setStyle("-fx-padding: 10;");

            VBox right = new VBox();
            right.setStyle("-fx-padding: 10;");

            pane.getChildren().addAll(left, right);
            AnchorPane.setLeftAnchor(left, 0.0);
            AnchorPane.setRightAnchor(right, 0.0);

            Label id = new Label("Loan ID: " + summary.getLoanID());
            id.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            Label name = new Label("Loan Status: " + summary.getStatusString());
            name.setStyle("-fx-font-size: 12px; ");
            Label address = new Label("Loan Principal: " + summary.getPrincipal());
            address.setStyle("-fx-font-size: 12px; ");
            left.getChildren().add(id);
            left.getChildren().add(name);
            right.getChildren().add(address);

            pane.setCursor(javafx.scene.Cursor.HAND);
            pane.setOnMouseClicked(event -> {
                //get Loan id
                String LoanId = summary.getLoanID();
                //get Loan with id
                SearchLoan searchLoan = new SearchLoan();
                Loan Loan = searchLoan.findById(Instance.getConnection(), LoanId);
                SearchCoborrower searchCoborrower = new SearchCoborrower();
                List<Coborrower> list = searchCoborrower.findAll(Instance.getConnection(), LoanId);
                for (Coborrower e : list) {
                    Loan.addCoborrower(e);
                }

                SearchLoanSummary searchSummary = new SearchLoanSummary();
                LoanDetails details = searchSummary.findById(Instance.getConnection(), LoanId);
                Loan.setSummary(details);

                SearchPayments searchPayments = new SearchPayments();
                List<Payments> paymentList = searchPayments.findAll(Instance.getConnection(), LoanId);
                Loan.setPaymentsList(paymentList);
                // cache Loan
                Cache.cacheLoan(Loan);
                //set active Loan
                LoanBucket.getInstance().setLoan(Loan);
                LoanScreenController.updateLoan();
                //load Loan screen
                Main.setScreen(SceneManager.Screens.LOAN);
            });
            panes.add(pane);
        }
        return panes;
    }
}
