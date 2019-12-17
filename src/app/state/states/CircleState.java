package app.state.states;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import app.graphics.elements.PageElement;
import app.graphics.elements.shapes.CircleElement;
import app.graphics.elements.shapes.RectangleElement;
import app.state.State;
import app.views.page.PageView;

public class CircleState extends State {

    private PageView mediator;

    public CircleState(PageView mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        Point2D position = (Point2D) e.getPoint().clone();

        if (e.getButton() == MouseEvent.BUTTON1) {
            PageElement element = CircleElement.createDefault(position);

            mediator.getPage().addSlot(element);
        }
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
    }

    @Override
    public void onMouseReleased(MouseEvent e) {
    }
}
