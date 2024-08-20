package uoa.lavs.controllers;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import uoa.lavs.models.Customer.CustomerSummary;

import java.util.ArrayList;
import java.util.List;

public class CustomerResultReader {
    public static List<AnchorPane> processSearch(List<CustomerSummary> searchSummaries) {
        List<AnchorPane> panes = new ArrayList<>();
        for (CustomerSummary summary : searchSummaries) {
            AnchorPane pane = new AnchorPane();
            VBox left = new VBox();
            VBox right = new VBox();
            pane.getChildren().addAll(left, right);
            AnchorPane.setLeftAnchor(left, 0.0);
            AnchorPane.setRightAnchor(right, 0.0);

            Label id = new Label("Customer ID: " + summary.getId());
            Label name = new Label("Customer Name: " + summary.getName());
            Label dob = new Label("Customer Date of Birth: " + summary.getDob());
            Label address = new Label("Customer Address: " + summary.getAddress());
            left.getChildren().add(id);
            left.getChildren().add(name);
            right.getChildren().add(dob);
            right.getChildren().add(address);
            panes.add(pane);
        }
        return panes;
    }
}
