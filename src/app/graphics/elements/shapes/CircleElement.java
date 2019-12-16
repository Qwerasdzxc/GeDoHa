package app.graphics.elements.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import app.graphics.elements.PageShape;
import app.graphics.painters.shapes.CirclePainter;

/**
 * Created by Qwerasdzxc on 16/12/2019.
 */
public class CircleElement extends PageShape {

    public CircleElement(Point2D position, Dimension size, Stroke stroke, Paint paint, Color color, int angle) {
        super(position, size, stroke, paint, color, angle);

        elementPainter = new CirclePainter(this);
    }

    public static CircleElement createDefault(Point2D pos) {
        Point2D position = (Point2D) pos.clone();

        return new CircleElement(position,
                new Dimension(80, 80),
                new BasicStroke((float) (2), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL),
                Color.WHITE,
                Color.BLACK,
                0);
    }

    public static CircleElement createWithData(Point2D pos, Dimension dim, int angle) {
        Point2D position = (Point2D) pos.clone();

        return new CircleElement(
                position,
                dim,
                new BasicStroke((float) (2), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL),
                Color.WHITE,
                Color.BLACK,
                angle);
    }
}
