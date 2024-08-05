package uoa.lavs.logging;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import uoa.lavs.mainframe.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class LogManager {
    // singleton instance to read log only at startup
    private final static LogManager INSTANCE = new LogManager();
    private JSONArray log;
    private int logCount;

    private LogManager(){
        // read log from file
        JSONParser parser = new JSONParser();
        try{
            log = new JSONArray(parser.parse(new FileReader("log.json")));
            logCount = log.length();
        } catch (Exception e) {
            // if file does not exist, create new log
            log = new JSONArray();
            logCount = 0;
        }
    }

    public static void writeToLog(int type, HashMap<String, String> data) {
        // write log entry to log
        data.put("type", Integer.toString(type));
        JSONObject logEntry = new JSONObject(data);
        INSTANCE.log.put(logEntry);
        INSTANCE.logCount++;

        // write log to file
        try {
            FileWriter file = new FileWriter("log.json");
            file.write(INSTANCE.log.toString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void flushLog() {

        boolean succeeded = true;
        // use singleton instance to send all log entries to mainframe
        Connection connection = Instance.getConnection();

        for(int i = 0; i < INSTANCE.logCount; i++) {
            // for each log entry, create a request with recorded type and send it to mainframe
            JSONObject logEntry = (JSONObject) INSTANCE.log.get(i);
            Request request = INSTANCE.parseLogEntry(logEntry);
            Response reponse = connection.send(request);
            Status status = reponse.getStatus();
            // NO CLUE HOW STATUS WORKS YET
//            if (status != Status.OK) {
//                succeeded = false;
//            }
        }

        // clear log
        if(succeeded) {
            INSTANCE.clearLog();
        }
    }

    private void clearLog() {
        log.clear();
        logCount = 0;
        // clear log file
        try {
            FileWriter file = new FileWriter("log.json");
            file.write(log.toString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Request parseLogEntry(JSONObject logEntry) {
        // parse log entry into request
        Request request = new Request((int) (logEntry.get("type")));
        for (String key : logEntry.keySet()) {
            if (!key.equals("type") && !key.equals("order")) {
                request.setValue(key, (String) logEntry.get(key));
            }
        }
        return request;
    }
}
