package app.graphics.elements;

import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Point2D;

/**
 * Created by Qwerasdzxc on 13/12/2019.
 */
public abstract class PageShape extends PageElement {

    private Dimension size;
    private Point2D position;

    public PageShape(Point2D position, Dimension size, Stroke stroke, Paint paint) {
        super(stroke, paint);

        this.position = position;
        this.size = size;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }
}
