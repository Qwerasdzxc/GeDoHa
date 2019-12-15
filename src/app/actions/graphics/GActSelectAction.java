package app.actions.graphics;

import java.awt.event.ActionEvent;

import app.actions.GAbstractAction;
import app.state.StateManager;

/**
 * Created by Qwerasdzxc on 14/12/2019.
 */
public class GActSelectAction extends GAbstractAction {

    private StateManager stateManager;

    public GActSelectAction(StateManager stateManager) {
        this.stateManager = stateManager;

        putValue(SMALL_ICON, loadIcon("images/cursor.png"));
        putValue(NAME, "Izaberi");
        putValue(SHORT_DESCRIPTION, "Izaberi element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stateManager.startSelectState();
    }
}
