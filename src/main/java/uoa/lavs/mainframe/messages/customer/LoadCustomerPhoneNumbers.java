package uoa.lavs.mainframe.messages.customer;

import uoa.lavs.mainframe.*;

public class LoadCustomerPhoneNumbers implements Message, MessageDescription {
    public static final int REQUEST_CODE = 1003;
    private Response response;
    private String customerId;

    public class Fields {
        public static final String[] INPUT = {"id"};
        public static final String[] OUTPUT = {"[01].flags", "[01].number", "[01].phone", "[01].prefix", "[01].type", "[02].flags", "[02].number", "[02].phone", "[02].prefix", "[02].type", "[03].flags", "[03].number", "[03].phone", "[03].prefix", "[03].type", "[04].flags", "[04].number", "[04].phone", "[04].prefix", "[04].type", "[05].flags", "[05].number", "[05].phone", "[05].prefix", "[05].type", "count"};

        public static final String CUSTOMER_ID = "id";
        public static final String COUNT = "count";
        public static final String NUMBER = "[%02d].number";
        public static final String TYPE = "[%02d].type";
        public static final String PREFIX = "[%02d].prefix";
        public static final String PHONE_NUMBER = "[%02d].phone";
        public static final String IS_PRIMARY = "[%02d].flags";
        public static final String CAN_SEND_TXT = "[%02d].flags";
    }

    @Override
    public Status send(Connection connection) {
        Request request = new Request(REQUEST_CODE);
        if (customerId != null) request.setValue(Fields.CUSTOMER_ID, customerId.toString());
        response = connection.send(request);
        return response.getStatus();
    }

    // sets customer id [customerId]
    public void setCustomerId(String value)
        throws IllegalArgumentException
     {
        if (value != null && value.length() > 10) {
            throw new IllegalArgumentException("customerId is too long - max length is 10");
        }
        customerId = value;
     }

    // gets count from server
    public Integer getCountFromServer()
     {
        String key = Fields.COUNT;
        String value = response.getValue(key);
        if (value == null) return null;
        return Integer.parseInt(value);
     }

    // gets number from server
    public Integer getNumberFromServer(Integer row)
     {
        String key = String.format(Fields.NUMBER, row);
        String value = response.getValue(key);
        if (value == null) return null;
        return Integer.parseInt(value);
     }

    // gets type from server
    public String getTypeFromServer(Integer row)
     {
        String key = String.format(Fields.TYPE, row);
        return response.getValue(key);
     }

    // gets prefix from server
    public String getPrefixFromServer(Integer row)
     {
        String key = String.format(Fields.PREFIX, row);
        return response.getValue(key);
     }

    // gets phone number [PhoneNumber] from server
    public String getPhoneNumberFromServer(Integer row)
     {
        String key = String.format(Fields.PHONE_NUMBER, row);
        return response.getValue(key);
     }

    // gets is primary [flags] from server
    public Boolean getIsPrimaryFromServer(Integer row)
     {
        String key = String.format(Fields.IS_PRIMARY, row);
        String value = response.getValue(key);
        int flags = Integer.parseInt(value);
        return (flags & 1) == 1;
     }

    // gets can send txt [flags] from server
    public Boolean getCanSendTxtFromServer(Integer row)
     {
        String key = String.format(Fields.CAN_SEND_TXT, row);
        String value = response.getValue(key);
        int flags = Integer.parseInt(value);
        return (flags & 2) == 2;
     }

    @Override
    public String[] getInputFields()
    {
        return Fields.INPUT;
    }

    @Override
    public String[] getOutputFields()
    {
        return Fields.OUTPUT;
    }
}