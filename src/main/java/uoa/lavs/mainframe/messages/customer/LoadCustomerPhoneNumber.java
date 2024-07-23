package uoa.lavs.mainframe.messages.customer;

import uoa.lavs.mainframe.*;

public class LoadCustomerPhoneNumber implements Message, MessageDescription {
    public static final int REQUEST_CODE = 1103;
    public static final String[] INPUT = {"id", "number"};
    public static final String[] OUTPUT = {"type", "prefix", "phone", "flags", "flags"};

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

    // gets prefix from server
    public String getPrefixFromServer()
     {
        String key = "prefix";
        return response.getValue(key);
     }

    // gets phone number [phone] from server
    public String getPhoneNumberFromServer()
     {
        String key = "phone";
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

    // gets can send txt [flags] from server
    public Boolean getCanSendTxtFromServer()
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
