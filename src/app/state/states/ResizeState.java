package app.state.states;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import app.graphics.elements.PageElement;
import app.graphics.elements.PageShape;
import app.graphics.elements.shapes.RectangleElement;
import app.state.State;
import app.views.page.PageView;

/**
 * Created by Qwerasdzxc on 15/12/2019.
 */
public class ResizeState extends State {

    private PageView mediator;

    private PageShape shape;

    private boolean dragging = true;
    private final int PROX_DIST = 3;

    public ResizeState(PageView mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        if (mediator.getCursor() != Cursor.getDefaultCursor()) {
            // If cursor is set for resizing, allow dragging.
            dragging = true;
        }
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        if (shape == null)
            return;

        if (dragging) {
            Point p = e.getPoint();
            int type = mediator.getCursor().getType();
            int dx = p.x - (int) shape.getPosition().getX();
            int dy = p.y - (int) shape.getPosition().getY();

            PageShape newElement = null;

            switch (type) {
                case Cursor.N_RESIZE_CURSOR:
                    int height = (int) shape.getSize().getHeight() - dy;
                    newElement = RectangleElement.createWithData(
                            new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY()),
                            new Dimension((int) shape.getSize().getWidth(), height)
                    );
                    break;
                case Cursor.NW_RESIZE_CURSOR:
                    int width = (int) shape.getSize().getWidth() - dx;
                    height = (int) shape.getSize().getHeight() - dy;
                    newElement = RectangleElement.createWithData(
                            new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY()),
                            new Dimension(width, height)
                    );
                    break;
//                case Cursor.W_RESIZE_CURSOR:
//                    width = r.width - dx;
//                    r.setRect(r.x+dx, r.y, width, r.height);
//                    break;
//                case Cursor.SW_RESIZE_CURSOR:
//                    width = r.width - dx;
//                    height = dy;
//                    r.setRect(r.x+dx, r.y, width, height);
//                    break;
//                case Cursor.S_RESIZE_CURSOR:
//                    height = dy;
//                    r.setRect(r.x, r.y, r.width, height);
//                    break;
//                case Cursor.SE_RESIZE_CURSOR:
//                    width = dx;
//                    height = dy;
//                    r.setRect(r.x, r.y, width, height);
//                    break;
//                case Cursor.E_RESIZE_CURSOR:
//                    width = dx;
//                    r.setRect(r.x, r.y, width, r.height);
//                    break;
//                case Cursor.NE_RESIZE_CURSOR:
//                    width = dx;
//                    height = r.height - dy;
//                    r.setRect(r.x, r.y+dy, width, height);
//                    break;
                default:
                    System.out.println("unexpected type: " + type);
            }

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
        shape = null;
    }

    @Override
    public void onMouseMoved(MouseEvent e) {
        Point p = e.getPoint();

        shape = mediator.getOverlappedElement(p);

        if (shape == null) {
            if (mediator.getCursor() != Cursor.getDefaultCursor()) {
                // If cursor is not over rect reset it to the default.
                mediator.replaceCursor(Cursor.getDefaultCursor());
            }
            return;
        }

        Rectangle r = new Rectangle(
                (int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                (int) shape.getSize().getWidth(), (int) shape.getSize().getHeight()
        );

        // Locate cursor relative to center of rect.
        int outcode = getOutcode(p, r);

        switch (outcode) {
            case Rectangle.OUT_TOP:
                if (Math.abs(p.y + r.height - r.y) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.N_RESIZE_CURSOR));
                }
                break;
            case Rectangle.OUT_TOP + Rectangle.OUT_LEFT:
                if (Math.abs(p.y + r.height - r.y) < PROX_DIST &&
                        Math.abs(p.x - r.x) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.NW_RESIZE_CURSOR));
                }
                break;
            case Rectangle.OUT_LEFT:
                if (Math.abs(p.x - r.x) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.W_RESIZE_CURSOR));
                }
                break;
            case Rectangle.OUT_LEFT + Rectangle.OUT_BOTTOM:
                if (Math.abs(p.x - r.x) < PROX_DIST &&
                        Math.abs(p.y - (r.y + r.height)) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.SW_RESIZE_CURSOR));
                }
                break;
            case Rectangle.OUT_BOTTOM:
                if (Math.abs(p.y - (r.y + r.height)) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.S_RESIZE_CURSOR));
                }
                break;
            case Rectangle.OUT_BOTTOM + Rectangle.OUT_RIGHT:
                if (Math.abs(p.x - (r.x + r.width)) < PROX_DIST &&
                        Math.abs(p.y - (r.y + r.height)) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.SE_RESIZE_CURSOR));
                }
                break;
            case Rectangle.OUT_RIGHT:
                if (Math.abs(p.x - (r.x + r.width)) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.E_RESIZE_CURSOR));
                }
                break;
            case Rectangle.OUT_RIGHT + Rectangle.OUT_TOP:
                if (Math.abs(p.x - (r.x + r.width)) < PROX_DIST &&
                        Math.abs(p.y - r.y) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.NE_RESIZE_CURSOR));
                }
                break;
            default:    // center
                mediator.replaceCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * Make a smaller Rectangle and use it to locate the
     * cursor relative to the Rectangle center.
     */
    private int getOutcode(Point p, Rectangle r) {
        r.grow(-PROX_DIST, -PROX_DIST);
        return r.outcode(p.x, p.y);
    }
}
