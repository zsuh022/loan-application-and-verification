package uoa.lavs.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import uoa.lavs.mainframe.*;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.*;

public class LocalLogManager {

    // TEMPORARY ID PREFIX
    public static final String TEMPORARY_ID_PREFIX = "TEMP_";

    // LOCAL LOG FILE PATH
    public static final String LOCAL_LOG_FILE_PATH = "log.json";

    // Log4J2
    private static final Logger logger = LogManager.getLogger(LocalLogManager.class);

    // singleton instance to read log only at startup
    private final static LocalLogManager INSTANCE = new LocalLogManager();
    private final HashMap<String, String> temporaryIds = new HashMap<>();
    private JSONArray log;
    private int logCount;

    private LocalLogManager(){
        // read log from file
        try{
            File logFile = new File(LOCAL_LOG_FILE_PATH );
            logFile.createNewFile();
            log = new JSONArray(log = new JSONArray(Files.readString(logFile.toPath())));
            logCount = log.length();
        } catch (Exception e) {
            // if file does not exist, create new log
            logger.error("Error reading log file: " + e.getMessage());
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

        for(int i = 0; i < INSTANCE.logCount; i++) {
            // for each log entry, create a request with recorded type and send it to mainframe
            JSONObject logEntry = INSTANCE.log.getJSONObject(i);
            // if request is for a new customer, remove temporary id and store it for mapping

            String temporaryId = null;
            if(logEntry.getInt("type") == 1201) {
                try{
                    temporaryId = logEntry.getString("id");
                } catch (Exception e) {
                    logger.info("No temporary Id");
                }

            }

            Request request = parseLogEntry(logEntry);

            Response response = connection.send(request);

            System.out.println("Sending with id: " + request.getValue("id"));
            if(response.getStatus().getWasSuccessful()) {
                // if successful, remove log entry
                INSTANCE.log.remove(i);
                INSTANCE.logCount--;
                i--;
                System.out.println("Successfully sent log entry: " + response.getValue("name") + response.getValue("id"));

                // if request was for a new customer, map temporary id to id
                if(request.getRequestType() == 1201) {
                    mapTemporaryIdtoId(temporaryId, response.getValue("id"));
                }
            } else {
                logger.error("Failed to send log entry: " + logEntry);
                System.out.println(response.getStatus().getErrorCode());
                System.out.println(response.getStatus().getErrorMessage());

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
        logger.info("Clearing log");
        log.clear();
        logCount = 0;
        // clear log file
        writeLog();
    }

    private void writeLog() {
        // write log to file
        try {
            File logFile = new File(LOCAL_LOG_FILE_PATH );
            logFile.createNewFile();
            FileWriter file = new FileWriter(LOCAL_LOG_FILE_PATH );
            file.write(log.toString());
            file.close();
        } catch (Exception e) {
            logger.error("Error writing log file: " + e.getMessage());
        }
    }

    private static Request parseLogEntry(JSONObject logEntry) {
        // parse log entry into request
        Request request = new Request(logEntry.getInt("type"));
        for(String key : logEntry.keySet()) {
            if(key.equals("type")) continue;
            if(key.equals("id")) {
                // if id is temporary, replace with mapped id
                String id = logEntry.getString(key);
                if(id.startsWith(TEMPORARY_ID_PREFIX)) {
                    id = INSTANCE.temporaryIds.get(id);
                    if(id == null) {
                        logger.error("Failed to map temporary id: " + logEntry);
                        continue;
                    } else {
                        System.out.println("Found " + logEntry.getString(key) + " to " + id);
                    }
                }
                request.setValue(key, id);
                continue;
            } else {
                request.setValue(key, logEntry.getString(key));
            }
        }
        return request;
    }

    private static void mapTemporaryIdtoId(String temporaryId, String id) {
        // check if temporary id is already mapped
        if(!INSTANCE.temporaryIds.containsKey(temporaryId)) {
            INSTANCE.temporaryIds.put(temporaryId, id);
            System.out.println("Mapped " + temporaryId + " to " + id);
        }
    }
}
