package uoa.lavs;

import uoa.lavs.SceneManager.Screens;
import uoa.lavs.logging.LocalLogManager;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.customer.LoadCustomer;
import uoa.lavs.mainframe.simulator.RecorderConnection;
import uoa.lavs.mainframe.simulator.SimpleReplayConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static Stage stage;
    private static Scene scene;
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        // flush log immediately to avoid inconsistencies with mainframe
        LocalLogManager.flushLog();
        launch();
        // the following shows two ways of using the mainframe interface
        // approach #1: use the singleton instance - this way is recommended as it provides a single configuration
        // location (and is easy for the testers to change when needed).
        Connection connection = Instance.getConnection();
        updateMainframe(connection);
    }

    private static void updateMainframe(Connection connection) {

    }

    //loads a FXML file
    private static Parent loadFxml(final String fxml) throws IOException{
        return new FXMLLoader(Main.class.getResource("/fxml/"+fxml+".fxml")).load();
    }

    //Set the active screen
    public static void setScreen(Screens screen){
        scene.setRoot(SceneManager.getScreen(screen));
    }

    @Override
    public void start(final Stage stage) throws IOException{
        SceneManager.addScreenUi(Screens.CUSTOMER, loadFxml("customerScreen"));
        SceneManager.addScreenUi(Screens.DRAFTS, loadFxml("draftsScreen"));
        SceneManager.addScreenUi(Screens.HOME, loadFxml("homeScreen"));
        SceneManager.addScreenUi(Screens.LOAN, loadFxml("loanScreen"));
        SceneManager.addScreenUi(Screens.NEW_CUSTOMER, loadFxml("newCustomerScreen"));
        SceneManager.addScreenUi(Screens.NEW_LOAN, loadFxml("newLoanScreen"));
        SceneManager.addScreenUi(Screens.SEARCH_CUSTOMER, loadFxml("searchCustomerScreen"));
        SceneManager.addScreenUi(Screens.SEARCH_LOAN, loadFxml("searchLoanScreen"));
        
        scene = new Scene(SceneManager.getScreen(Screens.HOME), 960, 540);
        stage.setScene(scene);
        stage.show();

    }

}