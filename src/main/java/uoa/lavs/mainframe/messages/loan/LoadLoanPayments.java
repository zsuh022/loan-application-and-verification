package uoa.lavs.mainframe.messages.loan;

import uoa.lavs.mainframe.*;

public class LoadLoanPayments implements Message, MessageDescription {
    public static final int REQUEST_CODE = 2102;
    public static final String[] INPUT = {"id", "number"};
    public static final String[] OUTPUT = {"customerId", "customerName", "pages", "payments", "paymentInterest", "paymentPrincipal", "paymentRemaining", "paymentMonth"};

    private Response response;
    private String loanId;
    private Integer number;

    @Override
    public Status send(Connection connection) {
        Request request = new Request(REQUEST_CODE);
        if (loanId != null) request.setValue("id", loanId.toString());
        if (number != null) request.setValue("number", number.toString());
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

    // sets number [number]
    public void setNumber(Integer value)
     {
        number = value;
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

    // gets page count [pages] from server
    public Integer getPageCountFromServer()
     {
        String key = "pages";
        String value = response.getValue(key);
        if (value == null) return null;
        return Integer.parseInt(value);
     }

    // gets payment count [payments] from server
    public Integer getPaymentCountFromServer()
     {
        String key = "payments";
        String value = response.getValue(key);
        if (value == null) return null;
        return Integer.parseInt(value);
     }

    // gets payment interest [paymentInterest] from server
    public String getPaymentInterestFromServer(Integer payment)
     {
        String key = String.format("[%02d].interest", payment);
        return response.getValue(key);
     }

    // gets payment principal [paymentPrincipal] from server
    public String getPaymentPrincipalFromServer(Integer payment)
     {
        String key = String.format("[%02d].principal", payment);
        return response.getValue(key);
     }

    // gets payment remaining [paymentRemaining] from server
    public String getPaymentRemainingFromServer(Integer payment)
     {
        String key = String.format("[%02d].remaining", payment);
        return response.getValue(key);
     }

    // gets payment month [paymentMonth] from server
    public String getPaymentMonthFromServer(Integer payment)
     {
        String key = String.format("[%02d].month", payment);
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
