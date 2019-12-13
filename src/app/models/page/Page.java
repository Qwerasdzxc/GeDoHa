package app.models.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.models.AbstractNode;
import app.models.slot.Slot;

/**
 * Created by Qwerasdzxc on 11/12/2019.
 */
public class Page extends AbstractNode implements PageObserver, Serializable {

    private transient List<PageListener> listeners;

    private List<Slot> slots;

    public Page(String name) {
        super(name);

        slots = new ArrayList<>();
    }

    @Override
    public AbstractNode addNewChild() {
        return null;
    }

    @Override
    public void setName(String name) {
        super.setName(name);

        notifyPageChangedName(name);
    }

    public void addSlot(Slot slot) {
        this.slots.add(slot);
    }

    @Override
    public void notifyPageSelected(Page page) {
        // TODO
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
}
