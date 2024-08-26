package uoa.lavs;

import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import uoa.lavs.SceneManager.Screens;
import uoa.lavs.comms.Customer.AddCustomer;
import uoa.lavs.comms.Loan.AddCoborrower;
import uoa.lavs.comms.Loan.AddLoan;
import uoa.lavs.comms.Loan.SearchLoanSummary;
import uoa.lavs.logging.Cache;
import uoa.lavs.logging.LocalLogManager;
import uoa.lavs.mainframe.*;
import uoa.lavs.mainframe.messages.customer.LoadCustomer;
import uoa.lavs.mainframe.messages.loan.UpdateLoanStatus;
import uoa.lavs.mainframe.simulator.NitriteConnection;
import uoa.lavs.mainframe.simulator.RecorderConnection;
import uoa.lavs.mainframe.simulator.SimpleReplayConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uoa.lavs.models.Customer.Customer;
import uoa.lavs.models.Loan.Coborrower;
import uoa.lavs.models.Loan.Loan;
import uoa.lavs.models.Loan.LoanDetails;
import uoa.lavs.utility.LoanFactory;
import uoa.lavs.utility.LoanType;
import uoa.lavs.utility.PaymentFrequency;
import uoa.lavs.comms.Loan.UpdateStatus;

public class Main extends Application {

    public static Stage stage;
    private static Scene scene;
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        // flush log immediately to avoid inconsistencies with mainframe
        LocalLogManager.flushLog();
        launch();
    }

    //loads a FXML file
    private static Parent loadFxml(final String fxml) throws IOException {
        return new FXMLLoader(Main.class.getResource("/fxml/" + fxml + ".fxml")).load();
    }

    //Set the active screen
    public static void setScreen(Screens screen) {
        scene.setRoot(SceneManager.getScreen(screen));
        scaleContent();
    }

    @Override
    public void start(final Stage stage) throws IOException {
        SceneManager.addScreenUi(Screens.CUSTOMER, loadFxml("customerScreen"));
        SceneManager.addScreenUi(Screens.EDIT_CUSTOMER, loadFxml("editCustomerScreen"));
        SceneManager.addScreenUi(Screens.EDIT_LOAN, loadFxml("editLoanScreen"));
        SceneManager.addScreenUi(Screens.HOME, loadFxml("homeScreen"));
        SceneManager.addScreenUi(Screens.LOAN, loadFxml("loanScreen"));
        SceneManager.addScreenUi(Screens.LOGIN, loadFxml("loginScreen"));
        SceneManager.addScreenUi(Screens.NEW_CUSTOMER, loadFxml("newCustomerScreen"));
        SceneManager.addScreenUi(Screens.NEW_LOAN, loadFxml("newLoanScreen"));
        SceneManager.addScreenUi(Screens.SEARCH_CUSTOMER, loadFxml("searchCustomerScreen"));
        SceneManager.addScreenUi(Screens.SEARCH_LOAN, loadFxml("searchLoanScreen"));

        scene = new Scene(SceneManager.getScreen(Screens.LOGIN), 960, 540);
        stage.setScene(scene);
        stage.show();
        // scale the content to fit the resized window
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> scaleContent();
        scene.widthProperty().addListener(stageSizeListener);
        scene.heightProperty().addListener(stageSizeListener);

    }

    private static void scaleContent() {
        double width = scene.getWidth();
        double height = scene.getHeight();
        // check if height is the limiting factor
        if (height / 9 > width / 16) {
            // if so, keep the width and calculate the height based on 16:9
            height = width / 16 * 9;
        } else {
            // otherwise, keep the height to 16:9 and calculate the width
            width = height / 9 * 16;
        }
        double scale = width / 960;
        try {
            // try to get the root of the scene and cast it to a BorderPane
            BorderPane border = (BorderPane) scene.getRoot();
            // get the content of the BorderPane
            // !!! ONLY ALLOWED TO HAVE ONE CHILD !!!
            Node content = border.getChildren().get(0);
            // transform entirety of content
            content.setScaleX(scale);
            content.setScaleY(scale);
            // calculate and set the margins
            double verticalMargin = (height - 1080) / 2 + (scene.getHeight() - height) / 2;
            double horizontalMargin = (width - 1920) / 2 + (scene.getWidth() - width) / 2;
            BorderPane.setMargin(
                    content,
                    new javafx.geometry.Insets(
                            verticalMargin, horizontalMargin, verticalMargin, horizontalMargin));
        } catch (Exception e) {
            // do nothing if the cast fails
        }
    }
}