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
import uoa.lavs.mainframe.Instance;
import uoa.lavs.models.Loan.*;

import java.util.ArrayList;
import java.util.List;

public class LoanResultReader {
    public static List<AnchorPane> processSearch(List<LoanSummary> searchSummaries) {
        List<AnchorPane> panes = new ArrayList<>();
        for (LoanSummary summary : searchSummaries) {
            AnchorPane pane = new AnchorPane();
            pane.setPrefWidth(678);
            pane.setPrefHeight(70);
            VBox left = new VBox();
            VBox right = new VBox();
            pane.getChildren().addAll(left, right);
            AnchorPane.setLeftAnchor(left, 0.0);
            AnchorPane.setRightAnchor(right, 0.0);

            Label id = new Label("Loan ID: " + summary.getLoanID());
            Label name = new Label("Loan Status: " + summary.getStatusString());
            Label address = new Label("Loan Principal: " + summary.getPrincipal());
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
