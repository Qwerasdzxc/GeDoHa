package app.models;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class Document implements MutableTreeNode {

    private String name;

    public Document(String name) {
        this.name = name;

        ArrayList<Page> pages = new ArrayList<>();
        pages.add(new Page("Page 1"));
        pages.add(new Page("Page 2"));

        this.pages = pages;
    }

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

    public Page getPage(int index) {
        return (Page) this.pages.get(index);
    }

    public int getPageCount() {
        return this.pages.size();
    }

    public int getPageIndex(Page child) {
        return this.pages.indexOf(child);
    }
}
