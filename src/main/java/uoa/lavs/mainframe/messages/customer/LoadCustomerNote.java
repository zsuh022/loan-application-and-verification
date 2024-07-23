package uoa.lavs.mainframe.messages.customer;

import uoa.lavs.mainframe.*;

public class LoadCustomerNote implements Message, MessageDescription {
    public static final int REQUEST_CODE = 1106;
    public static final String[] INPUT = {"id", "number"};
    public static final String[] OUTPUT = {"pages", "pages", "line"};

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

    // gets page count [pages] from server
    public Integer getPageCountFromServer()
     {
        String key = "pages";
        String value = response.getValue(key);
        if (value == null) return null;
        return Integer.parseInt(value);
     }

    // gets line count [pages] from server
    public Integer getLineCountFromServer()
     {
        String key = "pages";
        String value = response.getValue(key);
        if (value == null) return null;
        return Integer.parseInt(value);
     }

    // gets line from server
    public String getLineFromServer(Integer row)
     {
        String key = String.format("[%02d].line", row);
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
