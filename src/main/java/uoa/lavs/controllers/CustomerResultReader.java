package uoa.lavs.controllers;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import uoa.lavs.Main;
import uoa.lavs.SceneManager;
import uoa.lavs.comms.Customer.*;
import uoa.lavs.logging.Cache;
import uoa.lavs.comms.Loan.SearchCoborrower;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.models.Customer.*;
import uoa.lavs.models.Loan.Coborrower;

import java.util.ArrayList;
import java.util.List;

public class CustomerResultReader {
    public static List<AnchorPane> processSearch(List<CustomerSummary> searchSummaries) {
        List<AnchorPane> panes = new ArrayList<>();
        for (CustomerSummary summary : searchSummaries) {
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

            Label id = new Label("Customer ID: " + summary.getId());
            id.setStyle("-fx-font-size: 12px; ");

            Label name = new Label("Customer Name: " + summary.getName());
            name.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            Label address = new Label("Customer Email: " + summary.getEmail());
            address.setStyle("-fx-font-size: 12px; ");

            left.getChildren().add(name);
            left.getChildren().add(id);
            right.getChildren().add(address);
            pane.setCursor(javafx.scene.Cursor.HAND);
            pane.setOnMouseClicked(event -> {
                //get customer id
                String customerId = summary.getId();
                //get customer with id
                SearchCustomer searchCustomer = new SearchCustomer();
                Customer customer = searchCustomer.findById(Instance.getConnection(), customerId);
                //check for missing fields
                if(customer.getAddressList().isEmpty()){
                    SearchAddress searchAddress = new SearchAddress();
                    for(CustomerAddress customerAddress : searchAddress.findAll(Instance.getConnection(), customerId)) {
                        customer.addAddress(customerAddress);
                    }
                }
                if(customer.getEmailList().isEmpty()){
                    SearchEmail searchEmail = new SearchEmail();
                    for(CustomerEmail email : searchEmail.findAll(Instance.getConnection(), customerId)) {
                        customer.addEmail(email);
                    }
                }
                if(customer.getEmployerList().isEmpty()){
                    SearchEmployer searchEmployer = new SearchEmployer();
                    for(CustomerEmployer employer : searchEmployer.findAll(Instance.getConnection(), customerId)) {
                        customer.addEmployer(employer);
                    }
                }
                if(customer.getPhoneList().isEmpty()){
                    SearchPhone searchPhone = new SearchPhone();
                    for(CustomerPhone phone : searchPhone.findAll(Instance.getConnection(), customerId)) {
                        customer.addPhone(phone);
                    }
                }
                if(customer.getNote().getNote() == null){
                    SearchNote searchNote = new SearchNote();
                    customer.setNote(searchNote.findById(Instance.getConnection(), customerId));
                }
                // cache customer
                Cache.cacheCustomer(customer);
                //set active customer
                CustomerBucket.getInstance().setCustomer(customer);
                CustomerScreenController.updateCustomer();
                //load customer screen
                Main.setScreen(SceneManager.Screens.CUSTOMER);
            });
            panes.add(pane);
        }
        return panes;
    }
}
