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
            pane.setPrefWidth(678);
            pane.setPrefHeight(70);
            VBox left = new VBox();
            VBox right = new VBox();
            pane.getChildren().addAll(left, right);
            AnchorPane.setLeftAnchor(left, 0.0);
            AnchorPane.setRightAnchor(right, 0.0);

            Label id = new Label("Customer ID: " + summary.getId());
            Label name = new Label("Customer Name: " + summary.getName());
            Label address = new Label("Customer Email: " + summary.getEmail());
            left.getChildren().add(id);
            left.getChildren().add(name);
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
