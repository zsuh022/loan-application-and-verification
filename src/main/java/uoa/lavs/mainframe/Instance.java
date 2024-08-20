package uoa.lavs.mainframe;

import uoa.lavs.mainframe.simulator.IntermittentConnection;
import uoa.lavs.mainframe.simulator.NitriteConnection;
import uoa.lavs.mainframe.simulator.SimpleReplayConnection;
import uoa.lavs.mainframe.simulator.failures.NFailsPerMRequestsPolicy;
import uoa.lavs.mainframe.simulator.failures.RandomPolicy;

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
        //        private static final Connection INSTANCE = new IntermittentConnection(new NitriteConnection(dataPath), new NFailsPerMRequestsPolicy(1, 3));
        private static final Connection INSTANCE = new NitriteConnection(dataPath);
//        private static final Connection INSTANCE = new IntermittentConnection(new NitriteConnection(dataPath), new RandomPolicy(10, false));
    }

    // return the underlying connection
    public static Connection getConnection() {
        return SingletonHelper.INSTANCE;
    }
}
