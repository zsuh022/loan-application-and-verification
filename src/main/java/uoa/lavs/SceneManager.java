package uoa.lavs;

import java.util.HashMap;
import javafx.scene.Parent;

public class SceneManager {
    public enum Screens {
        CUSTOMER,
        HOME,
        LOAN,
        LOGIN,
        NEW_CUSTOMER,
        NEW_LOAN,
        SCREEN,
        SEARCH_CUSTOMER,
        SEARCH_LOAN
    }

    private static HashMap<Screens, Parent> screenMap = new HashMap<>();

    //Adds a screen and its UI element to the SceneManager
    public static void addScreenUi(Screens screen, Parent ui){
        screenMap.put(screen, ui);
    }

    //removes a screen ui from the manager
    public static void removeScreen(Screens screen){
        screenMap.remove(screen);
    }

    //Gets the ui element related to the screen
    public static Parent getScreen(Screens screen){
        return screenMap.get(screen);
    }


}
