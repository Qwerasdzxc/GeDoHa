package app.graphics.elements;

import java.awt.Paint;
import java.awt.Stroke;

import app.graphics.painters.ElementPainter;
import app.models.slot.Slot;

/**
 * Created by Qwerasdzxc on 13/12/2019.
 */
public abstract class PageElement extends Slot {

    protected Paint paint;
    protected Stroke stroke;

    protected ElementPainter elementPainter;

    public PageElement(Stroke stroke, Paint paint){
        this.stroke = stroke;
        this.paint = paint;
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
        this.stroke = stroke;
    }
}
