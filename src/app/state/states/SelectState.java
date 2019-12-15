package app.state.states;

import java.awt.event.MouseEvent;

import app.state.State;
import app.views.page.PageView;

/**
 * Created by Qwerasdzxc on 14/12/2019.
 */
public class SelectState extends State {

    private PageView mediator;

    public SelectState(PageView mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onMousePressed(MouseEvent e) {}

    @Override
    public void onMouseDragged(MouseEvent e) {}

    @Override
    public void onMouseReleased(MouseEvent e) {}
}
