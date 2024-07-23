package uoa.lavs.mainframe.messages.customer;

import uoa.lavs.mainframe.*;

public class LoadCustomerAddress implements Message, MessageDescription {
    public static final int REQUEST_CODE = 1102;
    public static final String[] INPUT = {"id", "number"};
    public static final String[] OUTPUT = {"type", "line1", "line2", "suburb", "city", "postCode", "country", "flags", "flags"};

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

    // gets type from server
    public String getTypeFromServer()
     {
        String key = "type";
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

    // gets is primary [flags] from server
    public Boolean getIsPrimaryFromServer()
     {
        String key = "flags";
        String value = response.getValue(key);
        int flags = Integer.parseInt(value);
        return (flags & 1) == 1;
     }

    // gets is mailing [flags] from server
    public Boolean getIsMailingFromServer()
     {
        String key = "flags";
        String value = response.getValue(key);
        int flags = Integer.parseInt(value);
        return (flags & 2) == 2;
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
