package uoa.lavs.comms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.logging.LocalLogManager;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.mainframe.Message;
import uoa.lavs.mainframe.Status;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class AbstractWriter<T> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(AbstractWriter.class);

    protected String add(Connection conn, T entity) {
        throw new UnsupportedOperationException("add with No customerID is not supported for this entity.");
    }

    protected String add(Connection conn, T entity, String customerID) {
        throw new UnsupportedOperationException("add with customerID is not supported for this entity.");
    }

    protected abstract Map<String, String> extractLogProperties(T entity, String customerID);

    protected <R extends Message, Y> Y processRequest(Connection conn, R request, T entity,
                                                      Function<Status, Y> onSuccess, Function<Status, Y> onFailure,
                                                      int logType, String entityName, String customerID) {
        try {
            Status status = request.send(conn);

            if (status.getWasSuccessful()) {
                logger.info("Successfully wrote data using {}, Transaction ID = {}", request.getClass().getName(), status.getTransactionId());
                return onSuccess.apply(status);
            } else {
                writeToLog(logType, entity, entityName, status.getTransactionId(), customerID);
                return onFailure.apply(status);
            }
        } catch (IOException e) {
            logger.error("IOException occurred while sending the request: {}", e.getMessage());
            return null;
        }
    }

    protected void writeToLog(int type, T entity, String name, Long tranID, String customerID) {
        logger.info(
                "\n********** Failure to update Mainframe ************\n Attempting to log {} {} with code: {}, Transaction ID: {}",
                entity.getClass().getSimpleName(),
                name,
                type,
                tranID);
        try {
            Map<String, String> properties = extractLogProperties(entity, customerID);
            LocalLogManager.writeToLog(type, (HashMap<String, String>) properties);
        } catch (Exception e) {
            logger.fatal("Failed to write to log!! Error: {}", e.getMessage());
        }
    }

    protected void mainframeError(int code, String message) {
        logger.error(
                "Failed to save data to mainframe. Error Code: {}, Error Message: {}.", code, message);
    }

}
