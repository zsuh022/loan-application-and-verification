package uoa.lavs.mainframe;

import java.util.HashMap;

// a request to send to the mainframe
public final class Request {
    private HashMap<String, String> rawData = new HashMap<>();
    private int requestType;

    // initialize a new Request instance
    public Request(int requestType) {
        this.requestType = requestType;
        this.rawData = rawData;
    }

    // gets a data value
    public String getValue(String key) {
        return rawData.get(key);
    }

    // sets a data value
    public String setValue(String key, String value) {
        return rawData.put(key, value);
    }

    // gets the request type.
    public Integer getRequestType() {
        return requestType;
    }

}
