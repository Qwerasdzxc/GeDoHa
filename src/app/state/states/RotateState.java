package app.state.states;

import app.graphics.elements.PageShape;
import app.graphics.elements.shapes.RectangleElement;
import app.state.State;
import app.views.page.PageView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class RotateState extends State {

    private PageView mediator;

    private PageShape shape;

    private boolean dragging = true;
    private int dy = 0;
    private int dx = 0;
    private Point2D oldPoint;

    public RotateState(PageView mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        if (mediator.getCursor() == Cursor.getDefaultCursor()) {
            // If cursor is set for resizing, allow dragging.
            dragging = true;
            oldPoint = (Point2D) e.getPoint().clone();
        }
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        if (dragging) {
            Point p = e.getPoint();


            dx = (int) p.getX() - (int) oldPoint.getX();
            dy = (int) p.getY() - (int) oldPoint.getY();

            oldPoint = e.getPoint();

            PageShape newElement = null;

            shape.setAngle(shape.getAngle() - dx); //setter limits the angle to be between 0 and 360
//            System.out.println(shape.getAngle());

            newElement = RectangleElement.createWithData(
                    new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY()),
                    new Dimension((int) shape.getSize().getWidth(), (int) shape.getSize().getHeight()), shape.getAngle()
            );


            if (newElement == null)
                return;

            mediator.getPage().removeSlot(shape);
            mediator.getPage().addSlot(newElement);

            shape = newElement;

        }
    }

    @Override
    public void onMouseReleased(MouseEvent e) {
        dragging = false;
        dy = 0;
        dx = 0;

    }

    @Override
    public void onMouseMoved(MouseEvent e) {
        Point p = e.getPoint();

        shape = mediator.getOverlappedElement(p);

    }
}
