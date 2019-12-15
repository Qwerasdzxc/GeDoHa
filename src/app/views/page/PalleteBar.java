package app.views.page;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import app.actions.graphics.GraphicsActionManager;
import app.state.StateManager;

/**
 * Created by Qwerasdzxc on 14/12/2019.
 */
public class PalleteBar extends JToolBar {

    public PalleteBar(StateManager stateManager) {
        super(JToolBar.VERTICAL);

        setFloatable(false);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(GraphicsActionManager.getInstance().getSelectAction(stateManager));
        addSeparator();
        add(GraphicsActionManager.getInstance().getRectangleAction(stateManager));


//        JToggleButton btn4 = new JToggleButton(AppCore.getInstance().getActionManager().getpCircleAction());
//        group.add(btn4);
//        add(btn4);
//
//
//        JToggleButton btn5 = new JToggleButton(AppCore.getInstance().getActionManager().getpTriangleAction());
//        group.add(btn5);
//        add(btn5);
    }
}
