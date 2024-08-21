package uoa.lavs.controllers;

import uoa.lavs.models.Customer.Customer;

public class CustomerBucket {

    private Customer customer;

    private CustomerBucket() {
    }

    private static class SingletonHelper {
        private static final CustomerBucket INSTANCE = new CustomerBucket();
    }

    public static CustomerBucket getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public void setCustomer(Customer c) {
        this.customer = c;
    }

    public Customer getCustomer() {
        return customer;
    }
}
