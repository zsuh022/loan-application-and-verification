package uoa.lavs.mainframe.messages.loan;

import uoa.lavs.mainframe.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LoadLoan implements Message, MessageDescription {
    public static final int REQUEST_CODE = 2101;
    public static final String[] INPUT = {"id"};
    public static final String[] OUTPUT = {"customerId", "customerName", "status", "principal", "rate.value", "rate.type", "date", "period", "term", "payment.amount", "payment.freq", "compounding"};

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

    // gets status from server
    public String getStatusFromServer()
     {
        String key = "status";
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

    // gets rate type [rate.type] from server
    public RateType getRateTypeFromServer()
     {
        String key = "rate.type";
        String value = response.getValue(key);
        switch (value)
        {
            case "1":
                return RateType.Floating;
            case "2":
                return RateType.Fixed;
            case "3":
                return RateType.InterestOnly;
        }
        return RateType.Unknown;
     }

    // gets start date [date] from server
    public LocalDate getStartDateFromServer()
     {
        String key = "date";
        String value = response.getValue(key);
        if (value == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(value, formatter);
     }

    // gets period from server
    public Integer getPeriodFromServer()
     {
        String key = "period";
        String value = response.getValue(key);
        if (value == null) return null;
        return Integer.parseInt(value);
     }

    // gets term from server
    public Integer getTermFromServer()
     {
        String key = "term";
        String value = response.getValue(key);
        if (value == null) return null;
        return Integer.parseInt(value);
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

    // gets compounding from server
    public Frequency getCompoundingFromServer()
     {
        String key = "compounding";
        String value = response.getValue(key);
        switch (value)
        {
            case "2":
                return Frequency.Weekly;
            case "4":
                return Frequency.Monthly;
            case "7":
                return Frequency.Yearly;
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
