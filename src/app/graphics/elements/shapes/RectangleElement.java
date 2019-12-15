package app.graphics.elements.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import app.graphics.elements.PageShape;
import app.graphics.painters.shapes.RectanglePainter;

/**
 * Created by Qwerasdzxc on 13/12/2019.
 */
public class RectangleElement extends PageShape {

    public RectangleElement(Point2D position, Dimension size, Stroke stroke, Paint paint, Color color, int angle) {
        super(position, size, stroke, paint, color, angle);

        elementPainter = new RectanglePainter(this);
    }

    public static RectangleElement createDefault(Point2D pos) {
        Point2D position = (Point2D) pos.clone();

        return new RectangleElement(position,
                new Dimension(80, 200),
                new BasicStroke((float) (2), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL),
                Color.WHITE,
                Color.BLACK,
                0);
    }

    public static RectangleElement createWithData(Point2D pos, Dimension dim, int angle) {
        Point2D position = (Point2D) pos.clone();

        return new RectangleElement(
                position,
                dim,
                new BasicStroke((float) (2), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL),
                Color.WHITE,
                Color.BLACK,
                angle);
    }
}
