package uoa.lavs.comms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.logging.LocalLogManager;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Message;
import uoa.lavs.mainframe.Status;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class AbstractWriter<T> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(AbstractWriter.class);

    public static final String TEMPORARY_CUSTOMER_ID_PREFIX = "TEMP_CUSTOMER_";

    public String add(Connection conn, T entity) {
        throw new UnsupportedOperationException("add with No customerID is not supported for this entity.");
    }

    protected String add(Connection conn, T entity, String customerID) {
        throw new UnsupportedOperationException("add with customerID is not supported for this entity.");
    }

    protected abstract Map<String, String> extractLogProperties(T entity, String customerID);

    protected <R extends Message, Y> Y processRequest(Connection conn, R request, T entity,
                                                      Function<Status, Y> onSuccess, Function<Status, Y> onFailure,
                                                      int logType, String entityName, String ID) {
        try {
            Status status = request.send(conn);

            if (status.getWasSuccessful()) {
                logger.info("Successfully wrote data using {}, Transaction ID = {}", request.getClass().getName(), status.getTransactionId());
                return onSuccess.apply(status);
            } else {
                writeToLog(logType, entity, entityName, status.getTransactionId(), ID);
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

        Map<String, String> properties = extractLogProperties(entity, customerID);
        LocalLogManager.writeToLog(type, (HashMap<String, String>) properties);
    }

    protected void mainframeError(int code, String message, String customerID, T entity) {
        logger.error(
                "Failed to save {} to mainframe. Customer ID = {}, Error Code: {}, Error Message: {}.", entity.getClass().getSimpleName(), customerID, code, message);
    }

}
