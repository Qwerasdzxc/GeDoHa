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
import app.graphics.elements.shapes.CircleElement;
import app.graphics.elements.shapes.RectangleElement;
import app.graphics.elements.shapes.TriangleElement;
import app.state.State;
import app.views.page.PageView;

/**
 * Created by Qwerasdzxc on 15/12/2019.
 */
public class ResizeState extends State {

    private PageView mediator;

    private PageShape shape;
    private ShapeType type;

    private boolean dragging = true;
    private final int PROX_DIST = 5;
    private double dy = 0;
    private double dx = 0;

    private Point2D oldPoint;

    public ResizeState(PageView mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        if (mediator.getCursor() != Cursor.getDefaultCursor()) {
            // If cursor is set for resizing, allow dragging.
            dragging = true;
            oldPoint = (Point2D) e.getPoint().clone();
        }
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        if (shape == null) {
            dy = 0;
            dx = 0;
            return;
        }

        if (dragging) {
            Point p = e.getPoint();


            dx = p.getX() - oldPoint.getX();
            dy = p.getY() - oldPoint.getY();

            oldPoint = (Point2D) e.getPoint().clone();
            int type = mediator.getCursor().getType();

            PageShape newElement;

            switch (type) {
                case Cursor.N_RESIZE_CURSOR:

                    double height = (int) (shape.getSize().getHeight() - dy);

                    if (height < 10)
                        return;

                    double centerX = shape.getPosition().getX() + shape.getSize().getWidth() / 2.0;
                    double centerY = shape.getPosition().getY() + shape.getSize().getHeight() / 2.0;

                    double centerX2 = shape.getPosition().getX() + shape.getSize().getWidth() / 2.0;
                    double centerY2 = shape.getPosition().getY() + height / 2.0;

                    newElement = recreateElement(
                            new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY() + dy),
                            new Dimension((int) shape.getSize().getWidth(), (int) height),
                            shape.getAngle()
                    );

                    break;
                case Cursor.NW_RESIZE_CURSOR:
                    double width = (int) shape.getSize().getWidth() - dx;
                    height = (int) shape.getSize().getHeight() - dy;

                    if (width < 10 || height < 10)
                        return;

                    newElement = recreateElement(
                            new Point2D.Double(shape.getPosition().getX() + dx, shape.getPosition().getY() + dy),
                            new Dimension((int) width, (int) height), shape.getAngle()
                    );

                    break;
                case Cursor.W_RESIZE_CURSOR:
                    width = (int) shape.getSize().getWidth() - dx;

                    if (width < 10)
                        return;

                    newElement = recreateElement(
                            new Point2D.Double(shape.getPosition().getX() + dx, shape.getPosition().getY()),
                            new Dimension((int) width, (int) shape.getSize().getHeight()), shape.getAngle()
                    );
                    break;
                case Cursor.SW_RESIZE_CURSOR:
                    width = (int) shape.getSize().getWidth() - dx;
                    height = (int) shape.getSize().getHeight() + dy;

                    if (width < 10 || height < 10)
                        return;

                    newElement = recreateElement(
                            new Point2D.Double(shape.getPosition().getX() + dx, shape.getPosition().getY()),
                            new Dimension((int) width, (int) height), shape.getAngle()
                    );
                    break;
                case Cursor.S_RESIZE_CURSOR:
                    height = (int) shape.getSize().getHeight() + dy;

                    if (height < 10)
                        return;

                    newElement = recreateElement(
                            new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY()),
                            new Dimension((int) shape.getSize().getWidth(), (int)height), shape.getAngle()
                    );
                    break;
                case Cursor.SE_RESIZE_CURSOR:
                    width = (int) shape.getSize().getWidth() + dx;
                    height = (int) shape.getSize().getHeight() + dy;

                    if (width < 10 || height < 10)
                        return;

                    newElement = recreateElement(
                            new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY()),
                            new Dimension((int) width, (int) height), shape.getAngle()
                    );
                    break;
                case Cursor.E_RESIZE_CURSOR:
                    width = (int) shape.getSize().getWidth() + dx;

                    if (width < 10)
                        return;

                    newElement = recreateElement(
                            new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY()),
                            new Dimension((int) width, (int) shape.getSize().getHeight()), shape.getAngle()
                    );
                    break;
                case Cursor.NE_RESIZE_CURSOR:
                    width = (int) shape.getSize().getWidth() + dx;
                    height = (int) shape.getSize().getHeight() - dy;

                    if (width < 10 || height < 10)
                        return;

                    newElement = recreateElement(
                            new Point2D.Double(shape.getPosition().getX(), shape.getPosition().getY() + dy),
                            new Dimension((int) width, (int) height), shape.getAngle()
                    );
                    break;
                default:
                    return;
            }

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
        type = getShapeType(shape);

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

        p = (Point) mediator.rotatePoint(r.getCenterX(), r.getCenterY(), shape.getAngle(), (Point2D) p.clone());


        // Locate cursor relative to center of rect.
        int outcode = getOutcode(p, r);

        switch (outcode) {
            case Rectangle.OUT_TOP:
                if (Math.abs(p.y - r.y) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.N_RESIZE_CURSOR));
                }
                break;
            case Rectangle.OUT_TOP + Rectangle.OUT_LEFT:
                // Ignore handle for these types
                if (type == ShapeType.CIRCLE || type == ShapeType.TRIANGLE)
                    break;

                if (Math.abs(p.y - r.y) < PROX_DIST &&
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
                // Ignore handle for these types
                if (type == ShapeType.CIRCLE || type == ShapeType.TRIANGLE)
                    break;

                if (Math.abs(p.x - r.x) < PROX_DIST &&
                        Math.abs(p.y - r.y) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.SW_RESIZE_CURSOR));
                }
                break;
            case Rectangle.OUT_BOTTOM:
                if (Math.abs(p.y - r.y - r.height) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.S_RESIZE_CURSOR));
                }
                break;
            case Rectangle.OUT_BOTTOM + Rectangle.OUT_RIGHT:
                // Ignore handle for these types
                if (type == ShapeType.CIRCLE || type == ShapeType.TRIANGLE)
                    break;

                if (Math.abs(p.x - (r.x + r.width)) < PROX_DIST &&
                        Math.abs(p.y - r.y - r.height) < PROX_DIST) {
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
                // Ignore handle for these types
                if (type == ShapeType.CIRCLE || type == ShapeType.TRIANGLE)
                    break;

                if (Math.abs(p.x - (r.x + r.width)) < PROX_DIST &&
                        Math.abs(p.y - r.y) < PROX_DIST) {
                    mediator.replaceCursor(Cursor.getPredefinedCursor(
                            Cursor.NE_RESIZE_CURSOR));
                }
                break;
            default:
                mediator.replaceCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * Make a smaller Rectangle and use it to locate the
     * cursor relative to the Rectangle center.
     */
    private int getOutcode(Point p, Rectangle r) {
        r.grow(-2, -2);
        return r.outcode(p.getX(), p.getY());
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

    private enum ShapeType {
        RECTANGLE, CIRCLE, TRIANGLE
    }

    private ShapeType getShapeType(PageShape shape) {
        if (shape instanceof RectangleElement)
            return ShapeType.RECTANGLE;
        else if (shape instanceof CircleElement)
            return ShapeType.CIRCLE;
        else if (shape instanceof TriangleElement)
            return ShapeType.TRIANGLE;

        return null;
    }
}