package app.models.node_factory;

import app.models.AbstractNode;
import app.models.project.Project;

/**
 * Created by Qwerasdzxc on 16/12/2019.
 */
public class ProjectFactory extends NodeFactory {

    @Override
    public AbstractNode createNode(int number) {
        return new Project(number);
    }
}
