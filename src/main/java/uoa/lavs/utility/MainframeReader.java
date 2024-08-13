package uoa.lavs.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainframeReader {
    // Log4J2
    private static final Logger logger = LogManager.getLogger(MainframeReader.class);
    private MainframeReader(){}

    private static class MainframeSingleton {
        private static final MainframeReader instance = new MainframeReader();
    }

    public static MainframeReader getInstance() {
        return MainframeSingleton.instance;
    }
}
