package app.models.workspace;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import app.models.project.Project;


public class Workspace implements MutableTreeNode, WSObserver {

    private String name;

	List<WSListener> listeners;

    public Workspace() {
        this.name = "Workspace";
        this.projects = new ArrayList<>();
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

        notifyProjectCreated(project);
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

  	@Override
  	public void addObserver(WSListener listener) {
  		if(listener == null)
  			return;
  		if(this.listeners ==null)
  			this.listeners = new ArrayList<>();
  		if(this.listeners.contains(listener))
  			return;
  		this.listeners.add(listener);		
  	}

  	@Override
  	public void removeObserver(WSListener listener) {
  		if(listener == null)
  			return;

  		this.listeners.remove(listener);		
  	}

  	@Override
  	public void notifyProjectCreated(Project project) {
  		if(this.listeners == null)
  			return;

  		for(WSListener listener : listeners){
  			listener.onProjectCreated(project);
  		}		
  	}
}
