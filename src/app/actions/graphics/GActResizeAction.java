package app.actions.graphics;

import java.awt.event.ActionEvent;

import app.actions.GAbstractAction;
import app.state.StateManager;

/**
 * Created by Qwerasdzxc on 15/12/2019.
 */
public class GActResizeAction extends GAbstractAction {

    private StateManager stateManager;

    public GActResizeAction(StateManager stateManager) {
        this.stateManager = stateManager;

        putValue(SMALL_ICON, loadIcon("images/resize.png"));
        putValue(NAME, "Transformiši");
        putValue(SHORT_DESCRIPTION, "Transformiši element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stateManager.startResizeState();
    }
}
