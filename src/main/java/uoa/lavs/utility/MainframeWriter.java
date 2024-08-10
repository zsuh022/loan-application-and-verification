package uoa.lavs.utility;

// Java Imports

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Custom imports
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.customer.UpdateCustomer;
import uoa.lavs.models.Customer;
import uoa.lavs.logging.LocalLogManager;

public class MainframeWriter {

  // Log4J2
  private static final Logger logger = LogManager.getLogger(MainframeWriter.class);

  private MainframeWriter() {}

  private static class MainframeSingleton {
    private static final MainframeWriter instance = new MainframeWriter();
  }

  public static MainframeWriter getInstance() {
    return MainframeSingleton.instance;
  }

  // Returns ID for new customer
  public String newCustomer(Connection conn, Customer customer) {
    UpdateCustomer newCustomer = new UpdateCustomer();
    // null to indicate new customer
    newCustomer.setCustomerId(null);
    newCustomer.setTitle(customer.getTitle());
    newCustomer.setName(customer.getName());
    newCustomer.setDateofBirth(customer.getDateOfBirth());
    newCustomer.setOccupation(customer.getOccupation());
    newCustomer.setCitizenship(customer.getCitizenship());
    newCustomer.setVisa(customer.getVisa());
    Status status = newCustomer.send(conn);

    try {
      conn.close();
    } catch (IOException e) {
      connectionError(e);
      writeToLog(1201, customer, customer.getName(), status.getTransactionId());
      // Null to indicate error
      return null;
    }
    if (status.getWasSuccessful()) {
      logger.info(
          "\n**********SUCCESS************\n New customer created: Name = {}, ID = {}, Transaction ID = {}",
          newCustomer.getNameFromServer(),
          newCustomer.getCustomerIdFromServer(),
          status.getTransactionId());
      // Return new customer ID
      return newCustomer.getCustomerIdFromServer();
    } else {
      mainframeError(status.getErrorCode(), status.getErrorMessage());
      writeToLog(1201, customer, customer.getName(), status.getTransactionId());
      // Null to indicate error
      return null;
    }
  }

  //    // Returns ID for new loan
  //    public String newLoan(Connection conn, Loan loan) {
  //        UpdateLoan newLoan = new UpdateLoan();
  //        // null to indicate new loan
  //        newLoan.setLoanId(null);
  //        newLoan.setCustomerId();
  //        newLoan.setPrincipal();
  //        newLoan.setRateType();
  //        newLoan.setRateType();
  //        newLoan.setStartDate();
  //        newLoan.setPeriod();
  //        newLoan.setTerm();
  //        newLoan.setPaymentAmount();
  //        newLoan.setPaymentFrequency();
  //        newLoan.setCompounding();
  //
  //        Status status = newLoan.send(conn);
  //        try {
  //            conn.close();
  //        } catch (IOException e) {
  //            connectionError(e);
  //            // Null to indicate error
  //            return null;
  //        }
  //
  //        if (status.getWasSuccessful()){
  //            logger.info("New Loan created: ID = {}", newLoan.getLoanIdFromServer());
  //            // Return new customer ID
  //            return newLoan.getCustomerIdFromServer();
  //
  //        }
  //        else {
  //            mainframeError(status.getErrorCode(), status.getErrorMessage());
  //            writeToLog(2210, loan);
  //            // Null to indicate error
  //            return null;
  //        }
  //    }

  private <T> void writeToLog(int type, T data, String name, Long tranID) {
    logger.info(
        "\n**********Logging************\n Attempting to log {} {} with code: {}, Transaction ID: {}",
        data.getClass().getSimpleName(),
        name,
        type,
        tranID);
    try {
      if (data instanceof Customer customer) {
        Map<String, String> properties = getCustomerLog(customer);
        LocalLogManager.writeToLog(type, (HashMap<String, String>) properties);
      }
      //            if (data instanceof Loan loan){
      //                Map<String, String> properties = getLoanLog(loan);
      //            }
    } catch (Exception e) {
      logger.fatal("Failed to write to log!!. Error {}", e.getMessage());
    }
  }

  private static Map<String, String> getCustomerLog(Customer customer) {
    Map<String, String> properties = new HashMap<>();
    properties.put("customerId", customer.getId());
    properties.put("title", customer.getTitle());
    properties.put("name", customer.getName());
    properties.put("dateOfBirth", customer.getDateOfBirth().toString());
    properties.put("occupation", customer.getOccupation());
    properties.put("citizenship", customer.getCitizenship());
    properties.put("visa", customer.getVisa());
    return properties;
  }

  //    private static Map<String, String> getLoanLog(Loan loan) {
  //        Map<String, String> properties = new HashMap<>();
  //        properties.put("id", );
  //        properties.put("customerId", );
  //        properties.put("customerName", );
  //        properties.put("status", );
  //        properties.put("principal", );
  //        properties.put("rate.value", );
  //        properties.put("rate.type", );
  //        properties.put("date", );
  //        properties.put("period", );
  //        properties.put("term", );
  //        properties.put("payment.amount", );
  //        properties.put("payment.freq", );
  //        properties.put("compounding", );
  //        return properties;
  //    }

  private void connectionError(IOException e) {
    logger.error("\nCould not close connection! The message is: {}", e.getMessage());
  }

  private void mainframeError(int code, String message) {
    logger.error(
        "\nFailed to save data to mainframe.\n Error Code: {}, Error Message: {}.", code, message);
  }
}
