package uoa.lavs.mainframe;

import uoa.lavs.mainframe.simulator.*;
import uoa.lavs.mainframe.simulator.failures.NFailsPerMRequestsPolicy;
import uoa.lavs.mainframe.simulator.failures.RandomPolicy;

import java.util.HashMap;

// implements the singleton pattern for a mainframe connection
public class Instance {
    // private constructor so that this class can only be initialized internally
    private Instance() {
    }

    // the path to the data file
    private static final String dataPath = "lavs-data.db";

    // internal class to initialize the singleton, this enables lazy-loading
    // for the singleton
    private static class SingletonHelper {

        //always works
        private static final Connection INSTANCE = new NitriteConnection(dataPath);
        // always fail
        //private static final Connection INSTANCE = new IntermittentConnection(new NitriteConnection(dataPath), new NFailsPerMRequestsPolicy(1, 1));
//
    }

    // return the underlying connection
    public static Connection getConnection() {
        return SingletonHelper.INSTANCE;
    }
}
