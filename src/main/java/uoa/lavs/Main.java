package uoa.lavs;

import uoa.lavs.SceneManager.Screens;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.customer.LoadCustomer;
import uoa.lavs.mainframe.simulator.RecorderConnection;
import uoa.lavs.mainframe.simulator.SimpleReplayConnection;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static Stage stage;
    private static Scene scene;
    
    public static void main(String[] args) {
        launch();
        // the following shows two ways of using the mainframe interface
        // approach #1: use the singleton instance - this way is recommended as it provides a single configuration
        // location (and is easy for the testers to change when needed).
        Connection connection = Instance.getConnection();
        executeTestMessage(connection);

        // approach #2: dynamically initialize the interface based on some parameters - this way allows the connection
        // to change when needed (e.g., based on a command-line argument.) But it means that the connection must be
        // passed around in the application.
        String dataPath = args.length > 1 ? args[1] : "lavs-data.txt";
        if (args.length > 0 && args[0].equals("record"))
        {
            connection = new RecorderConnection(dataPath);
        }
        else
        {
            connection = new SimpleReplayConnection(dataPath);
        }
        executeTestMessage(connection);

        // you can use another approach if desired, but make sure you document how the markers can change the
        // connection implementation.
        
    }

    private static void executeTestMessage(Connection connection) {
        LoadCustomer testMessage = new LoadCustomer();
        testMessage.setCustomerId("123456-789");
        Status status = testMessage.send(connection);
        try {
            connection.close();
        } catch (IOException e) {
            System.out.println("Something went wrong - could not clos connection! The message is " + e.getMessage());
            return;
        }

        if (status.getWasSuccessful()) {
            System.out.println("The send was successful: the customer name is " + testMessage.getNameFromServer());
        } else {
            System.out.println("Something went wrong - the send failed! The code is " + status.getErrorCode());
        }
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
        scene = new Scene(loadFxml("homeScreen"), 960, 540);
        stage.setScene(scene);
        stage.show();

    }

}