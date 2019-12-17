package app.actions.graphics;

import java.awt.event.ActionEvent;

import app.actions.GAbstractAction;
import app.state.StateManager;

class GActCircleAction extends GAbstractAction {

    private StateManager stateManager;

    public GActCircleAction(StateManager stateManager) {
        this.stateManager = stateManager;

        putValue(SMALL_ICON, loadIcon("images/circle.png"));
        putValue(NAME, "Krug");
        putValue(SHORT_DESCRIPTION, "Napravi novi krug");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        stateManager.startCircleState();
    }
}
