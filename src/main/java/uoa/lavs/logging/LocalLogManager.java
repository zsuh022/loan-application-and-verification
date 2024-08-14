package uoa.lavs.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import uoa.lavs.mainframe.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

public class LocalLogManager {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(LocalLogManager.class);

    // singleton instance to read log only at startup
    private final static LocalLogManager INSTANCE = new LocalLogManager();
    private JSONArray log;
    private int logCount;

    private LocalLogManager(){
        // read log from file
        try{
            File logFile = new File("log.json");
            logFile.createNewFile();
            log = new JSONArray(log = new JSONArray(Files.readString(logFile.toPath())));
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
        INSTANCE.writeLog();
    }

    public static boolean flushLog() {

        boolean succeeded = true;
        // use singleton instance to send all log entries to mainframe
        Connection connection = Instance.getConnection();

        ArrayList<Response> responses = new ArrayList<>();

        for(int i = 0; i < INSTANCE.logCount; i++) {
            // for each log entry, create a request with recorded type and send it to mainframe
            JSONObject logEntry = (JSONObject) INSTANCE.log.get(i);
            Request request = parseLogEntry(logEntry);
            Response response = connection.send(request);
            responses.add(response);
            if(response.getStatus().getWasSuccessful()) {
                // if successful, remove log entry
                INSTANCE.log.remove(i);
                INSTANCE.logCount--;
                i--;
            } else {
                // if not successful break loop
                succeeded = false;
                INSTANCE.writeLog();
                break;
            }
        }
        // if all responses were successful, clear log
        if(succeeded) {
            INSTANCE.clearLog();
        }
        return succeeded;
    }

    private void clearLog() {
        log.clear();
        logCount = 0;
        // clear log file
        writeLog();
    }

    private void writeLog() {
        // write log to file
        try {
            File logFile = new File("log.json");
            logFile.createNewFile();
            FileWriter file = new FileWriter("log.json");
            file.write(log.toString());
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Request parseLogEntry(JSONObject logEntry) {
        // parse log entry into request
        Request request = new Request(logEntry.getInt("type"));
        for(String key : logEntry.keySet()) {
            if(key.equals("type")) continue;
            request.setValue(key, logEntry.getString(key));
        }
        return request;
    }
}
