package uoa.lavs.mainframe.messages.loan;

import uoa.lavs.mainframe.*;

public class UpdateLoanCoborrower implements Message, MessageDescription {
    public static final int REQUEST_CODE = 2206;
    public static final String[] INPUT = {"id", "coborrowerId"};
    public static final String[] OUTPUT = {"customerId", "customerName", "coborrowerId", "coborrowerName"};

    private Response response;
    private String loanId;
    private String coborrowerId;

    @Override
    public Status send(Connection connection) {
        Request request = new Request(REQUEST_CODE);
        if (loanId != null) request.setValue("id", loanId.toString());
        if (coborrowerId != null) request.setValue("coborrowerId", coborrowerId.toString());
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

    // sets coborrower id [coborrowerId]
    public void setCoborrowerId(String value)
        throws IllegalArgumentException
     {
        if (value.length() > 10) {
            throw new IllegalArgumentException("coborrowerId is too long - max length is 10");
        }
        coborrowerId = value;
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

    // gets coborrower id [coborrowerId] from server
    public String getCoborrowerIdFromServer()
     {
        String key = "coborrowerId";
        return response.getValue(key);
     }

    // gets coborrower name [coborrowerName] from server
    public String getCoborrowerNameFromServer()
     {
        String key = "coborrowerName";
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
