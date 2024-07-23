package uoa.lavs.mainframe.messages.customer;

import uoa.lavs.mainframe.*;

public class LoadCustomerEmployer implements Message, MessageDescription {
    public static final int REQUEST_CODE = 1105;
    public static final String[] INPUT = {"id", "number"};
    public static final String[] OUTPUT = {"name", "line1", "line2", "suburb", "city", "postCode", "country", "phone", "email", "web", "flags"};

    private Response response;
    private String customerId;
    private Integer number;

    @Override
    public Status send(Connection connection) {
        Request request = new Request(REQUEST_CODE);
        if (customerId != null) request.setValue("id", customerId.toString());
        if (number != null) request.setValue("number", number.toString());
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

    // sets number [number]
    public void setNumber(Integer value)
     {
        number = value;
     }

    // gets name from server
    public String getNameFromServer()
     {
        String key = "name";
        return response.getValue(key);
     }

    // gets line 1 [line1] from server
    public String getLine1FromServer()
     {
        String key = "line1";
        return response.getValue(key);
     }

    // gets line 2 [line2] from server
    public String getLine2FromServer()
     {
        String key = "line2";
        return response.getValue(key);
     }

    // gets suburb from server
    public String getSuburbFromServer()
     {
        String key = "suburb";
        return response.getValue(key);
     }

    // gets city from server
    public String getCityFromServer()
     {
        String key = "city";
        return response.getValue(key);
     }

    // gets post code [postCode] from server
    public String getPostCodeFromServer()
     {
        String key = "postCode";
        return response.getValue(key);
     }

    // gets country from server
    public String getCountryFromServer()
     {
        String key = "country";
        return response.getValue(key);
     }

    // gets phone number [phone] from server
    public String getPhoneNumberFromServer()
     {
        String key = "phone";
        return response.getValue(key);
     }

    // gets email address [email] from server
    public String getEmailAddressFromServer()
     {
        String key = "email";
        return response.getValue(key);
     }

    // gets website [web] from server
    public String getWebsiteFromServer()
     {
        String key = "web";
        return response.getValue(key);
     }

    // gets is owner [flags] from server
    public Boolean getIsOwnerFromServer()
     {
        String key = "flags";
        String value = response.getValue(key);
        int flags = Integer.parseInt(value);
        return (flags & 1) == 1;
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
