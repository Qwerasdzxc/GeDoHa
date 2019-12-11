package app.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.tree.TreePath;

import app.models.AbstractNode;
import app.views.MainFrame;

/**
 * Created by Qwerasdzxc on 11/12/2019.
 */
public class ActNewNode extends GAbstractAction {

    public ActNewNode() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/new_project.png"));
        putValue(NAME, "Novo");
        putValue(SHORT_DESCRIPTION, "Napravi");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTree hierarchy = MainFrame.getInstance().getHierarchyTree();
        TreePath path = hierarchy.getSelectionPath();
        hierarchy.expandPath(path);

        Object selectedComponent = hierarchy.getLastSelectedPathComponent();

        AbstractNode selectedNode = (AbstractNode) selectedComponent;
        selectedNode.addNewChild();
    }
}
