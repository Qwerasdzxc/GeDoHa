package app.state;

import app.state.states.CircleState;
import app.state.states.RectangleState;
import app.state.states.ResizeState;
import app.state.states.RotateState;
import app.state.states.SelectState;
import app.state.states.TriangleState;
import app.views.page.PageView;

public class StateManager {

    private State currentState;

    private RectangleState rectangleState;
    private CircleState circleState;
    private TriangleState triangleState;

    private SelectState selectState;
    private ResizeState resizeState;
    private RotateState rotateState;

    public StateManager(PageView mediator) {
        rectangleState = new RectangleState(mediator);
        circleState = new CircleState(mediator);
        triangleState = new TriangleState(mediator);
        selectState = new SelectState(mediator);
        resizeState = new ResizeState(mediator);
        rotateState = new RotateState(mediator);

        this.currentState = selectState;
    }

    public RectangleState getRectangleState() {
        return rectangleState;
    }

    public void startRectangleState() {
        currentState = rectangleState;
    }

    public CircleState getCircleState() {
        return circleState;
    }

    public void startCircleState() {
        currentState = circleState;
    }

    public TriangleState getTriangleState() {
        return triangleState;
    }

    public void startTriangleState() {
        currentState = triangleState;
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

    public void startRotateState() {
        currentState = rotateState;
    }

    public RotateState getRotateState() {
        return rotateState;
    }
}
