package app.models.project;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import app.models.AbstractNode;
import app.models.document.Document;

/**
 * Created by Qwerasdzxc on 11/12/2019.
 */
public class Project extends AbstractNode implements ProjObserver, Serializable {

    private File file;

    private transient List<ProjListener> listeners;

    public Project(String name) {
        super(name);
        this.file = null;
    }

    @Override
    public void addNewChild() {
        Document document = new Document("Document " + (getChildCount() + 1));
        this.addChild(document);

        notifyDocumentCreated(document);
    }

    public File getFile() {
        return file;
    }

    @Override
    public void setName(String name) {
        super.setName(name);

        notifyProjectChangedName(name);
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setSelected() {
        notifyProjectSelected();
    }

    public void deleteDocument(Document document) {
        children.remove(document);

        notifyDocumentDeleted(document);
    }

    @Override
    public void addObserver(ProjListener listener) {
        if (listener == null)
            return;
        if (this.listeners == null)
            this.listeners = new ArrayList<>();
        if (this.listeners.contains(listener))
            return;

        this.listeners.add(listener);
    }

    @Override
    public void removeObserver(ProjListener listener) {
        if (listener == null || this.listeners == null)
            return;

        this.listeners.remove(listener);
    }

    @Override
    public void notifyDocumentCreated(Document document) {
        if (this.listeners == null)
            return;

        for (int i = 0; i < listeners.size(); i++) {
            ProjListener listener = listeners.get(i);
            listener.onDocumentCreated(document);
        }
    }

    @Override
    public void notifyDocumentDeleted(Document document) {
        if (this.listeners == null)
            return;

        for (ProjListener listener : listeners) {
            listener.onDocumentDeleted(document);
        }
    }

    @Override
    public void notifyProjectSelected() {
        if (this.listeners == null)
            return;

        for (int i = 0; i < listeners.size(); i++) {
            ProjListener listener = listeners.get(i);
            listener.onProjectSelected(this);
        }
    }

    @Override
    public void notifyProjectChangedName(String name) {
        if (this.listeners == null)
            return;

        for (ProjListener listener : listeners)
            listener.onProjectChangedName(name);
    }
}