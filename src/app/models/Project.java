package app.models;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class Project implements MutableTreeNode {

    private String name;

    public Project(String name) {
        this.name = name;
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
}
