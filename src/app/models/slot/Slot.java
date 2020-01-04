package app.models.slot;

import java.awt.Dimension;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import app.graphics.elements.PageElement;
import app.graphics.painters.ElementPainter;
import app.models.page.Page;

public class Slot implements Serializable, Cloneable {

    private PageElement element;

    public Slot(PageElement element) {
        this.element = element;
    }

    public PageElement getElement() {
        return element;
    }

    public void setElement(PageElement element) {
        this.element = element;
    }

    @Override
    public Object clone() {
        Slot slot;
        try {
            slot = (Slot) super.clone();
        } catch (CloneNotSupportedException e) {
            slot = new Slot(this.getElement());
            this.getElement().setParent(slot);
        }
        PageElement el = (PageElement) this.element.clone();
        slot.setElement(el);
        el.setParent(slot);

        return slot;
    }
}
