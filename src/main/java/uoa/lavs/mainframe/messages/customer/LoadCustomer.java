package uoa.lavs.mainframe.messages.customer;

import uoa.lavs.mainframe.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LoadCustomer implements Message, MessageDescription {
    public static final int REQUEST_CODE = 1101;
    public static final String[] INPUT = {"id"};
    public static final String[] OUTPUT = {"title", "name", "dob", "occupation", "citizenship", "visa"};

    private Response response;
    private String customerId;

    @Override
    public Status send(Connection connection) {
        Request request = new Request(REQUEST_CODE);
        if (customerId != null) request.setValue("id", customerId.toString());
        response = connection.send(request);
        return response.getStatus();
    }

    // sets customer id [customerId]
    public void setCustomerId(String value)
        throws IllegalArgumentException
     {
        if (value.length() > 10) {
            throw new IllegalArgumentException("customerId is too long - max length is 10");
        }
        customerId = value;
     }

    // gets title from server
    public String getTitleFromServer()
     {
        String key = "title";
        return response.getValue(key);
     }

    // gets name from server
    public String getNameFromServer()
     {
        String key = "name";
        return response.getValue(key);
     }

    // gets date of birth [dob] from server
    public LocalDate getDateofBirthFromServer()
     {
        String key = "dob";
        String value = response.getValue(key);
        if (value == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(value, formatter);
     }

    // gets occupation from server
    public String getOccupationFromServer()
     {
        String key = "occupation";
        return response.getValue(key);
     }

    // gets citizenship from server
    public String getCitizenshipFromServer()
     {
        String key = "citizenship";
        return response.getValue(key);
     }

    // gets visa from server
    public String getVisaFromServer()
     {
        String key = "visa";
        return response.getValue(key);
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
