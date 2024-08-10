package uoa.lavs;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Instance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Connection connection = Instance.getConnection();
        updateMainframe(connection);
    }

    private static void updateMainframe(Connection connection) {


    }
}