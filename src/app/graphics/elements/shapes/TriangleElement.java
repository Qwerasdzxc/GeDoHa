package app.graphics.elements.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import app.graphics.elements.PageElement;
import app.graphics.elements.PageShape;
import app.graphics.painters.shapes.RectanglePainter;
import app.graphics.painters.shapes.TrianglePainter;

public class TriangleElement extends PageShape {

    public TriangleElement(Point2D position, Dimension size, Stroke stroke, Paint paint, Color color, int angle) {
        super(position, size, stroke, paint, color, angle);

        elementPainter = new TrianglePainter(this);
    }

    public TriangleElement(TriangleElement triangle) {
        super(triangle);
        elementPainter = new TrianglePainter(this);
    }

    public static TriangleElement createDefault(Point2D pos) {
        Point2D position = (Point2D) pos.clone();

        return new TriangleElement(position,
                new Dimension(90, 90),
                new BasicStroke((float) (2), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL),
                Color.WHITE,
                Color.BLACK,
                0);
    }

    public static TriangleElement createWithData(Point2D pos, Dimension dim, int angle) {
        Point2D position = (Point2D) pos.clone();

        return new TriangleElement(
                position,
                dim,
                new BasicStroke((float) (2), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL),
                Color.WHITE,
                Color.BLACK,
                angle);
    }

    @Override
    public PageElement clone() {
        return new TriangleElement(this);
    }
}
