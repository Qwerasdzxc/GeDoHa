package app.actions;

public class ActionManager {

    private static ActionManager instance = null;

    private ActionManager() {}

    public static ActionManager getInstance() {
        if (instance == null)
            instance = new ActionManager();

        return instance;
    }
}
