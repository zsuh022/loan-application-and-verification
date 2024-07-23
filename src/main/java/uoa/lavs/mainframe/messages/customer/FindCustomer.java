package uoa.lavs.mainframe.messages.customer;

import uoa.lavs.mainframe.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FindCustomer implements Message, MessageDescription {
    public static final int REQUEST_CODE = 1001;
    public static final String[] INPUT = {"id"};
    public static final String[] OUTPUT = {"count", "name", "dob", "id"};

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

    // gets customer count [count] from server
    public Integer getCustomerCountFromServer()
     {
        String key = "count";
        String value = response.getValue(key);
        if (value == null) return null;
        return Integer.parseInt(value);
     }

    // gets name from server
    public String getNameFromServer(Integer row)
     {
        String key = String.format("[%02d].name", row);
        return response.getValue(key);
     }

    // gets date of birth [dob] from server
    public LocalDate getDateofBirthFromServer(Integer row)
     {
        String key = String.format("[%02d].dob", row);
        String value = response.getValue(key);
        if (value == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(value, formatter);
     }

    // gets id from server
    public String getIdFromServer(Integer row)
     {
        String key = String.format("[%02d].id", row);
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
