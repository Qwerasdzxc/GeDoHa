package app.state.states;

import java.awt.Cursor;
import java.awt.event.MouseEvent;

import app.state.State;
import app.views.page.PageView;

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

    @Override
    public void onMouseMoved(MouseEvent e) {
        if (mediator.getCursor() != Cursor.getDefaultCursor())
            mediator.setCursor(Cursor.getDefaultCursor());
    }
}
