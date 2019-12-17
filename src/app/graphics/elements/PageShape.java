package app.graphics.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Point2D;

public abstract class PageShape extends PageElement {

    private Dimension size;
    private Point2D position;
    private int angle;

    public PageShape(Point2D position, Dimension size, Stroke stroke, Paint paint, Color color, int angle) {
        super(stroke, paint, color);

        this.position = position;
        this.size = size;
        this.angle = angle;
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

    public int getAngle() { return angle; }

    public void setAngle(int angle) {
        if (angle > 360)
            angle = angle - 360;
        if (angle < 0)
            angle = 360 - angle;

        this.angle = angle;
    }
}
