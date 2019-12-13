package app.graphics.elements.shapes;

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

    public RectangleElement(Point2D position, Dimension size, Stroke stroke, Paint paint) {
        super(position, size, stroke, paint);

        elementPainter = new RectanglePainter(this);
        setName("Pravougaonik");
    }
}
