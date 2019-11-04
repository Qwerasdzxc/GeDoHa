package app.models;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class Document implements MutableTreeNode {

    private String name;

    // Children nodes
    private ArrayList<Page> pages = new ArrayList<>();

    // Parent node object
    private Project parent;

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
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
