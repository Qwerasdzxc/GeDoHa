package app.models.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.models.AbstractNode;
import app.models.page.Page;

/**
 * Created by Qwerasdzxc on 11/12/2019.
 */
public class Document extends AbstractNode implements DocObserver, Serializable {

    public Document(String name) {
        super(name);
    }

    private transient List<DocListener> listeners;

    @Override
    public void addNewChild() {
        Page page = new Page("Page " + (getChildCount() + 1));
        this.addChild(page);

        notifyPageCreated(page);
    }

    public void setSelected() {
        notifyDocumentSelected();
    }

    public void deletePage(Page page) {
        int index = getIndex(page);
        children.remove(page);

        notifyPageDeleted(page, index);
    }

    @Override
    public void setName(String name) {
        super.setName(name);

        notifyDocumentChangedName();
    }

    @Override
    public void addObserver(DocListener listener) {
        if(listener == null)
            return;
        if(this.listeners ==null)
            this.listeners = new ArrayList<>();
        if(this.listeners.contains(listener))
            return;
        this.listeners.add(listener);
    }

    @Override
    public void removeObserver(DocListener listener) {
        if(listener == null || this.listeners == null)
            return;

        this.listeners.remove(listener);
    }

    @Override
    public void notifyPageCreated(Page page) {
        if (this.listeners == null)
            return;

        for (int i = 0; i < listeners.size(); i++) {
            DocListener listener = listeners.get(i);
            listener.onPageCreated(page);
        }
    }

    @Override
    public void notifyPageDeleted(Page page, int index) {
        if (this.listeners == null)
            return;

        for (DocListener listener : listeners) {
            listener.onPageDeleted(page, index);
        }
    }

    @Override
    public void notifyDocumentSelected() {
        if (this.listeners == null)
            return;

        for (int i = 0; i < listeners.size(); i++)
            listeners.get(i).onDocumentSelected(this);
    }

    @Override
    public void notifyDocumentChangedName() {
        if (this.listeners == null)
            return;

        for (int i = 0; i < listeners.size(); i++)
            listeners.get(i).onDocumentChangedName(this);
    }
}
