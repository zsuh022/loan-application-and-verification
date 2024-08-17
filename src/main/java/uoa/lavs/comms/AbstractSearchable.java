package uoa.lavs.comms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Message;
import uoa.lavs.mainframe.Status;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public abstract class AbstractSearchable<T> {

    // Log4J2
    private static final Logger logger = LogManager.getLogger(AbstractSearchable.class);

    public T findById(Connection conn, String customerId) {
        throw new UnsupportedOperationException("findById without an index is not supported for this entity.");
    }

    public T findById(Connection conn, String customerId, int index) {
        throw new UnsupportedOperationException("findById with index is not supported for this entity.");
    }

    public abstract List<T> findAll(Connection conn, String customerId);

    protected <R extends Message, Y> Y processRequest(Connection conn, R request,
                                                      Function<Status, Y> onSuccess, Function<Status, Y> onFailure) {
        try {
            Status status = request.send(conn);

            if (status.getWasSuccessful()) {
                logger.info("Successfully retrieved data using {}, Transaction ID = {}", request.getClass().getName(), status.getTransactionId());
                return onSuccess.apply(status);
            } else {
                logger.error("Failed to retrieve data using {}, Status: {}, Error Code: {} Transaction ID = {}", request.getClass().getName(), status.getErrorMessage(), status.getErrorCode(), status.getTransactionId());
                return onFailure.apply(status);
            }
        } catch (IOException e) {
            logger.error("IOException occurred while sending the request: {}", e.getMessage());
            return null;
        }
    }


}

