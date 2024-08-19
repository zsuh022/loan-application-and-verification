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
    public static final String TEMPORARY_CUSTOMER_ID_PREFIX = "TEMP_CUSTOMER_";

    public static final String TEMPORARY_LOAN_ID_PREFIX = "TEMP_LOAN_";

    // LOCAL LOG FILE PATH
    private static final String LOCAL_LOG_FILE_PATH = "log.json";

    // Log4J2
    private static final Logger logger = LogManager.getLogger(LocalLogManager.class);

    // singleton instance to read log only at startup
    private final static LocalLogManager INSTANCE = new LocalLogManager();
    private final HashMap<String, String> temporaryCustomerIds = new HashMap<>();
    private final HashMap<String, String> temporaryLoanIds = new HashMap<>();
    private JSONArray log = new JSONArray();
    private int logCount = 0;

    private LocalLogManager(){
        // read log from file
        try{
            File logFile = new File(LOCAL_LOG_FILE_PATH );
            logFile.createNewFile();
            log = new JSONArray(log = new JSONArray(Files.readString(logFile.toPath())));
            logCount = log.length();
        } catch (Exception e) {
            // if file does not exist, create new log
            logger.error("Error reading log file: {}", e.getMessage());
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

    public static boolean flushLog(Connection connection) {

        boolean succeeded = true;

        for(int i = 0; i < INSTANCE.logCount; i++) {
            // for each log entry, create a request with recorded type and send it to mainframe
            JSONObject logEntry = INSTANCE.log.getJSONObject(i);
            // if request is for a new customer, remove temporary id and store it for mapping

            String temporaryId = null;
            if(logEntry.getInt("type") == 1201) {
                try{
                    temporaryId = logEntry.getString("id");
                } catch (Exception e) {
                    logger.info("No temporary Customer Id");
                }

            }

            if(logEntry.getInt("type") == 2201) {
                try{
                    temporaryId = logEntry.getString("id");
                } catch (Exception e) {
                    logger.info("No temporary loan Id");
                }

            }

            Request request = parseLogEntryToRequest(logEntry);

            Response response = connection.send(request);
            if(response.getStatus().getWasSuccessful()) {
                // if successful, remove log entry
                INSTANCE.log.remove(i);
                INSTANCE.logCount--;
                i--;

                // if request was for a new customer, map temporary id to id
                if(request.getRequestType() == 1201) {
                    mapTemporaryCustomerId(temporaryId, response.getValue("id"));
                }

                // if request was for a new loan, map temporary id to id
                if(request.getRequestType() == 2201) {
                    mapTemporaryLoanId(temporaryId, response.getValue("id"));
                }
            } else {
                logger.error("Failed to send log entry: {} with error: {}: {}", logEntry, response.getStatus().getErrorCode(), response.getStatus().getErrorMessage());
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

    // ONLY CALL OUTSIDE FOR TESTS!!!

    public static void clearLog() {
        logger.info("Clearing log");
        INSTANCE.log.clear();
        INSTANCE.logCount = 0;
        // clear log file
        INSTANCE.writeLog();
        INSTANCE.temporaryCustomerIds.clear();
        INSTANCE.temporaryLoanIds.clear();
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
            logger.error("Error writing log file: {}", e.getMessage());
        }
    }

    private static Request parseLogEntryToRequest(JSONObject logEntry) {
        // parse log entry into request
        Request request = new Request(logEntry.getInt("type"));
        for(String key : logEntry.keySet()) {
            if(key.equals("type")) continue;
            if(key.equals("id")  || key.equals("customerId") || key.equals("loanId")) {
                // if id is temporary, replace with mapped id
                String id = logEntry.getString(key);
                if(id.startsWith(TEMPORARY_CUSTOMER_ID_PREFIX)) {
                    id = INSTANCE.temporaryCustomerIds.get(id);
                } else if(id.startsWith(TEMPORARY_LOAN_ID_PREFIX)) {
                    id = INSTANCE.temporaryLoanIds.get(id);
                }
                System.out.println("ID: " + id);
                request.setValue(key, id);
            } else {
                request.setValue(key, logEntry.getString(key));
            }
        }
        return request;
    }

    private static void mapTemporaryCustomerId(String temporaryId, String id) {
        // check if temporary id is already mapped
        if(!INSTANCE.temporaryCustomerIds.containsKey(temporaryId)) {
            INSTANCE.temporaryCustomerIds.put(temporaryId, id);
            logger.info("Mapped customer {} to {}", temporaryId, id);
        }
    }

    private static void mapTemporaryLoanId(String temporaryId, String id) {
        // check if temporary id is already mapped
        if(!INSTANCE.temporaryLoanIds.containsKey(temporaryId)) {
            INSTANCE.temporaryLoanIds.put(temporaryId, id);
            logger.info("Mapped loan {} to {}", temporaryId, id);
        }
    }
}
