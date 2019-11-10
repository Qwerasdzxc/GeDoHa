package app.models.document;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import app.models.page.Page;
import app.models.project.ProjListener;
import app.models.project.Project;
import app.observer.IListener;
import app.observer.IObserver;

public class Document implements MutableTreeNode, DocObserver {

    private String name;

	List<DocListener> listeners;

    public Document(Project parent, String name) {
        this.parent = parent;
        this.name = name;
        this.pages = new ArrayList<>();
    }

    // Children nodes
    private ArrayList<Page> pages = new ArrayList<>();

    // Parent node object 
    private Project parent;
    
    public void deletePage(Page page) {
        int index = getPageIndex(page);
		pages.remove(page);

		notifyPageDeleted(page, index);
	}

    public TreeNode getChildAt(int childIndex) {
        return this.pages.get(childIndex);
    }

    public int getChildCount() {
        return this.pages.size();
    }

    public TreeNode getParent() {
        return this.parent;
    }

    public int getIndex(TreeNode node) {
        return this.pages.indexOf(node);
    }

    public boolean getAllowsChildren() {
        return true;
    }

    public boolean isLeaf() {
        return this.pages.isEmpty();
    }

    public Enumeration<? extends TreeNode> children() {
        return (Enumeration<Page>) this.pages;
    }

    @Override
    public void setParent(MutableTreeNode newParent) {
        this.parent = (Project) newParent;
    }

    @Override
    public void insert(MutableTreeNode child, int index) {}

    @Override
    public void remove(int index) {}

    @Override
    public void remove(MutableTreeNode node) {}

    @Override
    public void setUserObject(Object object) {}

    @Override
    public void removeFromParent() {}

    public void setName(String name) {
        this.name = name;

        notifyDocumentChangedName();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
    
    public void addPage(Page page) {
		pages.add(page);

        notifyPageCreated(page);
	}

    public Page getPage(int index) {
        return (Page) this.pages.get(index);
    }

    public int getPageCount() {
        return this.pages.size();
    }

    public int getPageIndex(Page child) {
        return this.pages.indexOf(child);
    }

    public void setSelected() {
        notifyDocumentSelected();
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

    public ArrayList<Page> getPages() {
        return this.pages;
    }
}
