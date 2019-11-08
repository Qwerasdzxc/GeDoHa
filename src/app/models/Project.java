package app.models;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import app.observer.IListener;
import app.observer.IObserver;


public class Project implements MutableTreeNode, IObserver {

    private String name;
	private static int count=0;

	List<IListener> listeners;

    public Project(String name) {
        this.name = name;

        ArrayList<Document> documents = new ArrayList<>();
        documents.add(new Document("Document 1"));
        documents.add(new Document("Document 2"));

        this.documents = documents;
    }

    // Children nodes
    private ArrayList<Document> documents = new ArrayList<>();

    // Parent node object
    private Workspace parent;

    public Document getDocument(int index) {
        return this.documents.get(index);
    }

    public int getDocumentCount() {
        return this.documents.size();
    }

    public void addDocument(Document document) {
        this.documents.add(document);
    }

    public int getValue() {

		return count;
	}
    
	public void deleteDocument(Document document) {
		documents.remove(document);
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
    public void setUserObject(Object object) {}

    @Override
    public void removeFromParent() {}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
    
    // Observer metode

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
