package app.models.node_factory;

import app.models.AbstractNode;

public abstract class NodeFactory {

    public AbstractNode deliverNode(AbstractNode parent) {
        AbstractNode node = createNode(parent.getChildCount() + 1);
        parent.addChild(node);

        return node;
    }

    public abstract AbstractNode createNode(int number);
}
