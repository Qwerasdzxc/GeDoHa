package app.models.node_factory;

import app.models.AbstractNode;
import app.models.document.Document;

/**
 * Created by Qwerasdzxc on 16/12/2019.
 */
public class DocumentFactory extends NodeFactory {

    @Override
    public AbstractNode createNode(int number) {
        return new Document(number);
    }
}
