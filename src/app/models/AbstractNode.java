package app.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * Created by Qwerasdzxc on 11/12/2019.
 */
public abstract class AbstractNode implements MutableTreeNode, Serializable {

    private String name;

    protected transient AbstractNode parent;

    protected ArrayList<AbstractNode> children;

    public AbstractNode(String name) {
        this.setName(name);
        this.children = new ArrayList<>();
    }

    public void addChild(AbstractNode childNode) {
        childNode.parent = this;
        this.children.add(childNode);

        onChildAdded(childNode);
    }

    protected void onChildAdded(AbstractNode childNode) {}

    public List<AbstractNode> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void insert(MutableTreeNode child, int index) {
        this.children.add(index, (AbstractNode) child);
    }

    @Override
    public void remove(int index) {
        this.children.remove(index);
    }

    @Override
    public void remove(MutableTreeNode node) {
        this.children.remove(node);
    }

    @Override
    public void setUserObject(Object object) {}

    @Override
    public void removeFromParent() {
        this.parent.remove(this);
    }

    @Override
    public void setParent(MutableTreeNode newParent) {
        this.parent = (AbstractNode) newParent;
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return this.children.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return this.children.size();
    }

    @Override
    public TreeNode getParent() {
        return this.parent;
    }

    @Override
    public int getIndex(TreeNode node) {
        return this.children.indexOf(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return this.children.isEmpty();
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
