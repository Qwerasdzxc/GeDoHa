package app.graphics.elements;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.Serializable;

import app.graphics.SerializableStroke;
import app.graphics.painters.ElementPainter;
import app.models.slot.Slot;

/**
 * Created by Qwerasdzxc on 13/12/2019.
 */
public abstract class PageElement implements Serializable {

    protected Paint paint;
    protected Color color;
    protected SerializableStroke stroke;

    protected ElementPainter elementPainter;

    public PageElement(Stroke stroke, Paint paint, Color color){
        this.stroke = new SerializableStroke(stroke);
        this.paint = paint;
        this.color = color;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = new SerializableStroke(stroke);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ElementPainter getElementPainter() {
        return elementPainter;
    }
}
