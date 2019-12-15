package app.state;

import app.state.states.RectangleState;
import app.state.states.ResizeState;
import app.state.states.SelectState;
import app.views.page.PageView;

/**
 * Created by Qwerasdzxc on 14/12/2019.
 */
public class StateManager {

    private State currentState;

    private RectangleState rectangleState;

    private SelectState selectState;
    private ResizeState resizeState;

    public StateManager(PageView mediator) {
        rectangleState = new RectangleState(mediator);
        selectState = new SelectState(mediator);
        resizeState = new ResizeState(mediator);

        this.currentState = selectState;
    }

    public RectangleState getRectangleState() {
        return rectangleState;
    }

    public void startRectangleState() {
        currentState = rectangleState;
    }

    public SelectState getSelectState() {
        return selectState;
    }

    public void startSelectState() {
        currentState = selectState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void startResizeState() {
        currentState = resizeState;
    }

    public ResizeState getResizeState() {
        return resizeState;
    }
}
