package app.state;

import java.awt.event.MouseEvent;

/**
 * Created by Qwerasdzxc on 13/12/2019.
 */
public abstract class State {

    public abstract void onMousePressed(MouseEvent e);

    public abstract void onMouseDragged(MouseEvent e);

    public abstract void onMouseReleased(MouseEvent e);

    public void onMouseMoved(MouseEvent e) {}
}