package app.state.states;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import app.graphics.elements.PageShape;
import app.graphics.elements.shapes.CircleElement;
import app.graphics.elements.shapes.RectangleElement;
import app.graphics.elements.shapes.TriangleElement;
import app.state.State;
import app.views.page.PageView;

/**
 * Created by Qwerasdzxc on 27/12/2019.
 */
public class MoveState extends State {

    private PageView mediator;

    private PageShape shape;

    private boolean dragging = true;
    private int dy = 0;
    private int dx = 0;
    private Point2D oldPoint;

    public MoveState(PageView mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        dragging = true;
        oldPoint = (Point2D) e.getPoint().clone();
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        if (dragging && shape != null) {
            Point p = e.getPoint();

            dx = (int) p.getX() - (int) oldPoint.getX();
            dy = (int) p.getY() - (int) oldPoint.getY();

            oldPoint = e.getPoint();

            PageShape newElement = recreateElement(
                    new Point2D.Double(shape.getPosition().getX() + dx, shape.getPosition().getY() + dy),
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

        if (mediator.getCursor() != Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))
            mediator.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private PageShape recreateElement(Point2D pos, Dimension dim, int angle) {
        if (shape instanceof RectangleElement)
            return RectangleElement.createWithData(pos, dim, angle);
        else if (shape instanceof CircleElement)
            return CircleElement.createWithData(pos, dim, angle);
        else if (shape instanceof TriangleElement)
            return TriangleElement.createWithData(pos, dim, angle);

        return null;
    }
}
