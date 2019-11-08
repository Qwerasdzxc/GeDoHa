package app.models;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import app.observer.IListener;
import app.observer.IObserver;


public class Workspace implements MutableTreeNode, IObserver {

    private String name;

	List<IListener> listeners;

    public Workspace(String name) {
        this.name = name;

        ArrayList<Project> projects = new ArrayList<>();
        projects.add(new Project("Project 1"));
        projects.add(new Project("Project 2"));

        this.projects = projects;
    }

    // Children nodes
    private ArrayList<Project> projects = new ArrayList<>();

    public Project getProject(int index) {
        return this.projects.get(index);
    }

    public int getProjectsCount() {
        return this.projects.size();
    }

    public int getProjectIndex(Project child) {
        return this.projects.indexOf(child);
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return projects.get(childIndex);
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    @Override
    public int getChildCount() {
        return projects.size();
    }

    @Override
    public TreeNode getParent() {
        return null;
    }

    @Override
    public int getIndex(TreeNode node) {
        return projects.indexOf(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return (Enumeration<Project>) projects;
    }

    @Override
    public void insert(MutableTreeNode child, int index) {
        projects.add(index, (Project) child);
    }

    @Override
    public void remove(int index) {
        projects.remove(projects.get(index));
    }

    @Override
    public void remove(MutableTreeNode node) {
        projects.remove(node);
    }

    @Override
    public void setUserObject(Object object) {}

    @Override
    public void removeFromParent() {}

    @Override
    public void setParent(MutableTreeNode newParent) {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
    
    public void deleteProject(Project project) {
		projects.remove(project);

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
