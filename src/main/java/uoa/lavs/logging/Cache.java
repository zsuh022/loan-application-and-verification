package uoa.lavs.logging;

import uoa.lavs.models.Customer;
import uoa.lavs.models.Loan;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Cache {

    private static final Set<Customer> customerCache = new HashSet<>();
    private static final Set<Loan> loanCache = new HashSet<>();

    public static void cacheCustomer(Customer customer) {
        customerCache.add(customer);
    }

    public static void cacheLoan(Loan loan) {
        loanCache.add(loan);
    }


    public static ArrayList<Customer> searchCustomerCacheId(String id) {
        ArrayList<Customer> results = new ArrayList<>();
        // search the cache for a customer with the given id
        for(Customer customer : customerCache) {
            if(customer.getId().equals(id)) {
                results.add(customer);
            }
        }
        return results;
    }
    
    public static ArrayList<Customer> searchCustomerName(String name) {
        ArrayList<Customer> results = new ArrayList<>();
        // search the cache for a customer with the given name
        for(Customer customer : customerCache) {
            if(customer.getName().equals(name)) {
                results.add(customer);
            }
        }
        return results;
    }

    public static ArrayList<Loan> searchLoanCache(String id) {
        ArrayList<Loan> results = new ArrayList<>();
        // search the cache for a loan with the given id
        for(Loan loan : loanCache) {
            if(loan.getId().equals(id)) {
                results.add(loan);
            }
        }
        return results;
    }

    // ONLY USE FOR TESTING!!!

    public static void clearCache() {
        customerCache.clear();
        loanCache.clear();
    }
}
