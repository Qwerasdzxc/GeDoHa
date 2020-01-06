package app.models.slot;

import java.awt.Dimension;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import app.graphics.elements.PageElement;
import app.graphics.painters.ElementPainter;
import app.models.page.Page;
import app.models.slot.content.SlotContent;

public class Slot implements Serializable, Cloneable {

    private PageElement element;
    private SlotContent content;

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
        SlotContent content = (SlotContent) this.content.clone();
        slot.setElement(el);
        el.setParent(slot);
        slot.setContent(content);

        return slot;
    }

    public SlotContent getContent() {
        return content;
    }

    public void setContent(SlotContent content) {
        this.content = content;
    }
}
