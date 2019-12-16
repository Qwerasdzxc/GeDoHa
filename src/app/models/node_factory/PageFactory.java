package app.models.node_factory;

import app.models.AbstractNode;
import app.models.page.Page;

/**
 * Created by Qwerasdzxc on 16/12/2019.
 */
public class PageFactory extends NodeFactory {

    @Override
    public AbstractNode createNode(int number) {
        return new Page(number);
    }
}