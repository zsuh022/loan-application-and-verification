package uoa.lavs.mainframe.messages.loan;

import uoa.lavs.mainframe.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LoadLoanSummary implements Message, MessageDescription {
    public static final int REQUEST_CODE = 2103;
    public static final String[] INPUT = {"id"};
    public static final String[] OUTPUT = {"customerId", "customerName", "principal", "rate.value", "date", "term", "interest", "cost", "payment.amount", "payment.freq"};

    private Response response;
    private String loanId;

    @Override
    public Status send(Connection connection) {
        Request request = new Request(REQUEST_CODE);
        if (loanId != null) request.setValue("id", loanId.toString());
        response = connection.send(request);
        return response.getStatus();
    }

    // sets loan id [loanId]
    public void setLoanId(String value)
        throws IllegalArgumentException
     {
        if (value.length() > 14) {
            throw new IllegalArgumentException("loanId is too long - max length is 14");
        }
        loanId = value;
     }

    // gets customer id [customerId] from server
    public String getCustomerIdFromServer()
     {
        String key = "customerId";
        return response.getValue(key);
     }

    // gets customer name [customerName] from server
    public String getCustomerNameFromServer()
     {
        String key = "customerName";
        return response.getValue(key);
     }

    // gets principal from server
    public Double getPrincipalFromServer()
     {
        String key = "principal";
        String value = response.getValue(key);
        if (value == null) return 0.0;
        return Double.parseDouble(value);
     }

    // gets rate value [rate.value] from server
    public Double getRateValueFromServer()
     {
        String key = "rate.value";
        String value = response.getValue(key);
        if (value == null) return 0.0;
        return Double.parseDouble(value);
     }

    // gets payoff date [date] from server
    public LocalDate getPayoffDateFromServer()
     {
        String key = "date";
        String value = response.getValue(key);
        if (value == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(value, formatter);
     }

    // gets term from server
    public Integer getTermFromServer()
     {
        String key = "term";
        String value = response.getValue(key);
        if (value == null) return null;
        return Integer.parseInt(value);
     }

    // gets total interest [interest] from server
    public Double getTotalInterestFromServer()
     {
        String key = "interest";
        String value = response.getValue(key);
        if (value == null) return 0.0;
        return Double.parseDouble(value);
     }

    // gets total loan cost [cost] from server
    public Double getTotalLoanCostFromServer()
     {
        String key = "cost";
        String value = response.getValue(key);
        if (value == null) return 0.0;
        return Double.parseDouble(value);
     }

    // gets payment amount [payment.amount] from server
    public Double getPaymentAmountFromServer()
     {
        String key = "payment.amount";
        String value = response.getValue(key);
        if (value == null) return 0.0;
        return Double.parseDouble(value);
     }

    // gets payment frequency [payment.freq] from server
    public Frequency getPaymentFrequencyFromServer()
     {
        String key = "payment.freq";
        String value = response.getValue(key);
        switch (value)
        {
            case "2":
                return Frequency.Weekly;
            case "3":
                return Frequency.Fortnightly;
            case "4":
                return Frequency.Monthly;
        }
        return Frequency.Unknown;
     }

    @Override
    public String[] getInputFields()
    {
        return INPUT;
    }

    @Override
    public String[] getOutputFields()
    {
        return OUTPUT;
    }
}
