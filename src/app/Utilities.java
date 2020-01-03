package app;

import app.graphics.elements.PageShape;
import app.graphics.elements.shapes.CircleElement;
import app.graphics.elements.shapes.RectangleElement;
import app.graphics.elements.shapes.TriangleElement;

import java.awt.*;
import java.awt.geom.Point2D;

public class Utilities {

    public static final int PAGE_HEIGHT = 600;
    public static final int PAGE_WIDTH = 500;

    public static final int PAGE_THUMBNAIL_HEIGHT = 200;
    public static final int PAGE_THUMBNAIL_WIDTH = 120;

    public enum ShapeType {
        RECTANGLE, CIRCLE, TRIANGLE
    }

    public static PageShape recreateElement(Point2D pos, Dimension dim, int angle, ShapeType type) {
        if (type == ShapeType.RECTANGLE)
            return RectangleElement.createWithData(pos, dim, angle);
        else if (type == ShapeType.CIRCLE)
            return CircleElement.createWithData(pos, dim, angle);
        else if (type == ShapeType.TRIANGLE)
            return TriangleElement.createWithData(pos, dim, angle);

        return null;
    }

    public static ShapeType getShapeType(PageShape shape) {
        if (shape instanceof RectangleElement)
            return ShapeType.RECTANGLE;
        else if (shape instanceof CircleElement)
            return ShapeType.CIRCLE;
        else if (shape instanceof TriangleElement)
            return ShapeType.TRIANGLE;

        return null;
    }
}
