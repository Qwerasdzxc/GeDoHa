package app.views.page;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import app.actions.graphics.GraphicsActionManager;
import app.state.StateManager;

public class PalleteBar extends JToolBar {

    public PalleteBar(StateManager stateManager) {
        super(JToolBar.VERTICAL);

        setFloatable(false);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(GraphicsActionManager.getInstance().getSelectAction(stateManager));
        addSeparator();
        add(GraphicsActionManager.getInstance().getRectangleAction(stateManager));
        add(GraphicsActionManager.getInstance().getCircleAction(stateManager));
        add(GraphicsActionManager.getInstance().getTriangleAction(stateManager));
        addSeparator();
        add(GraphicsActionManager.getInstance().getResizeAction(stateManager));
        add(GraphicsActionManager.getInstance().getRotateAction(stateManager));
    }
}
