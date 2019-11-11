package app.models.page;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.TreeNode;

import app.models.document.Document;
import app.observer.IListener;
import app.observer.IObserver;

public class Page implements TreeNode, PageObserver {

    // Parent node
    private Document parent;

    private String name;
    
	List<PageListener> listeners;

    public Page(Document parent, String name) {
    	this.name = name;
        this.parent = parent;
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return null;
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    @Override
    public TreeNode getParent() {
        return this.parent;
    }

    @Override
    public int getIndex(TreeNode node) {
        return 0;
    }

    @Override
    public boolean getAllowsChildren() {
        return false;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

        notifyPageChangedName(name);
    }

    @Override
    public String toString() {
        return getName();
    }

    // Observer metode

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
    public void notifyPageSelected(Page page) {
        // TODO
    }

    @Override
    public void notifyPageChangedName(String name) {
        if (this.listeners == null)
            return;

        for (int i = 0; i < listeners.size(); i++)
            listeners.get(i).onPageChangedName(name);
    }
}
