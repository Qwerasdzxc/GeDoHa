package app.models.project;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import app.models.workspace.Workspace;
import app.models.document.Document;
import app.observer.IListener;
import app.observer.IObserver;
import app.views.MainFrame;


public class Project implements MutableTreeNode, ProjObserver, Serializable,UpdateListener {

    private String name;
    private static int count = 0;
    private transient boolean changed;
	private File projectFile=null;


    List<ProjListener> listeners;

    public Project(Workspace parent, String name) {
        this.parent = parent;
        this.name = name;
        this.documents = new ArrayList<>();
    }

    // Children nodes
    private ArrayList<Document> documents = new ArrayList<>();

    // Parent node object
    private Workspace parent;

    public ArrayList<Document> getDocuments() {
        return this.documents;
    }

    public Document getDocument(int index) {
        return this.documents.get(index);
    }

    public int getDocumentCount() {
        return this.documents.size();
    }

    public void addDocument(Document document) {
        this.documents.add(document);

        notifyDocumentCreated(document);
    }

    public void deleteDocument(Document document) {
        documents.remove(document);

        notifyDocumentDeleted(document);
    }

    public static void setCount(int count) {
        Project.count = count;
    }

    public int getDocumentIndex(Document child) {
        return this.documents.indexOf(child);
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return this.documents.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return this.documents.size();
    }

    @Override
    public TreeNode getParent() {
        return this.parent;
    }

    @Override
    public int getIndex(TreeNode node) {
        return this.documents.indexOf(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return this.documents.isEmpty();
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return (Enumeration<Document>) this.documents;
    }

    @Override
    public void insert(MutableTreeNode child, int index) {
        this.documents.add(index, (Document) child);
    }

    @Override
    public void remove(int index) {
        this.documents.remove(index);
    }

    @Override
    public void remove(MutableTreeNode node) {
        this.documents.remove(node);
    }

    @Override
    public void setParent(MutableTreeNode newParent) {
        this.parent = (Workspace) newParent;
    }

    @Override
    public void setUserObject(Object object) {
    }

    @Override
    public void removeFromParent() {
    }

    public void setName(String name) {
        this.name = name;

        notifyProjectChangedName(name);
    }

    public String getName() {
        return name;
    }

    public void setSelected() {
        notifyProjectSelected();
    }
    public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getHierarchyTree());
	}
	
	public File getProjectFile() {
		return projectFile;
	}
	
	public void setProjectFile(File projectFile) {
		this.projectFile=projectFile;
	}

    @Override
    public String toString() {
        return this.getName();
    }
    @Override
	public void updatePerformed(UpdateEvent e) {
    	setChanged(true);
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
