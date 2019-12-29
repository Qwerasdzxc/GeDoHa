package app.models.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import app.graphics.elements.PageElement;
import app.models.AbstractNode;
import app.models.element_selection.ElementSelectionModel;

public class Page extends AbstractNode implements PageObserver, Serializable {

    private transient List<PageListener> listeners;

    private ArrayList<PageElement> slots;

    private ElementSelectionModel selectionModel;

    public Page(int number) {
        super("Stranica " + number);

        slots = new ArrayList<>();
    }

    public void setSelected() {
        notifyPageSelected(this);
    }

    @Override
    public void setName(String name) {
        super.setName(name);

        notifyPageChangedName(name);
    }

    public void addSlot(PageElement slot) {
        this.slots.add(slot);

        notifySlotChanged();
    }

    public void removeSlot(PageElement slot) {
        this.slots.remove(slot);

        notifySlotChanged();
    }

    public Iterator<PageElement> getSlotsIterator() {
        return slots.iterator();
    }

    public ArrayList<PageElement> getSlots() {
        return slots;
    }

    @Override
    public void addObserver(PageListener listener) {
        if(listener == null)
            return;
        if(this.listeners ==null)
            this.listeners = new ArrayList<>();
        if(this.listeners.contains(listener))
            return;
        this.listeners.add(listener);
    }

    @Override
    public void removeObserver(PageListener listener) {
        if(listener == null || this.listeners == null)
            return;
        this.listeners.remove(listener);
    }

    @Override
    public void notifyPageChangedName(String name) {
        if (this.listeners == null)
            return;

        for (int i = 0; i < listeners.size(); i++)
            listeners.get(i).onPageChangedName(name);
    }

    @Override
    public void notifyPageSelected(Page page) {
        if (this.listeners == null)
            return;

        for (int i = 0; i < listeners.size(); i++)
            listeners.get(i).onPageSelected(page);
    }

    @Override
    public void notifySlotChanged() {
        if (this.listeners == null)
            return;

        for (int i = 0; i < listeners.size(); i++)
            listeners.get(i).onSlotChanged();
    }

    public ElementSelectionModel getSelectionModel() {
        if(selectionModel == null)
            selectionModel = new ElementSelectionModel();
        return selectionModel;
    }
}
