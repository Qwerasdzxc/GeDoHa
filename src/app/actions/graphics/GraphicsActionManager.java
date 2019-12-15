package app.actions.graphics;

import app.actions.ActionManager;
import app.state.StateManager;

/**
 * Created by Qwerasdzxc on 14/12/2019.
 */
public class GraphicsActionManager {

    public GActRectangleAction getRectangleAction(StateManager stateManager) {
        return new GActRectangleAction(stateManager);
    }

    public GActSelectAction getSelectAction(StateManager stateManager) {
        return new GActSelectAction(stateManager);
    }

    private GraphicsActionManager() {}

    public static GraphicsActionManager getInstance() {
        if (instance == null)
            instance = new GraphicsActionManager();

        return instance;
    }

    private static GraphicsActionManager instance;
}
