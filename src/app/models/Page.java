package app.models;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import app.observer.IListener;
import app.observer.IObserver;

public class Page implements TreeNode,IObserver {

    // Parent node
    private Document parent;

    private String name;
    
	List<IListener> listeners;


    public Page(String name) {
        this.name = name;
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
    }

    @Override
    public String toString() {
        return getName();
    }

  //Observer metode

  	@Override
  	public void addObserver(IListener listener) {
  		if(listener == null)
  			return;
  		if(this.listeners ==null)
  			this.listeners = new ArrayList<>();
  		if(this.listeners.contains(listener))
  			return;
  		this.listeners.add(listener);		
  	}

  	@Override
  	public void removeObserver(IListener listener) {
  		if(listener == null || this.listeners == null || !this.listeners.contains(listener))
  			return;
  		this.listeners.remove(listener);		
  	}

  	@Override
  	public void notifyObservers(Object event) {
  		if(event == null || this.listeners == null || this.listeners.isEmpty())
  			return;

  		for(IListener listener : listeners){
  			listener.update(event);
  		}		
  	}
}
