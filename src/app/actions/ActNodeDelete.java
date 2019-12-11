package app.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

import app.models.AbstractNode;
import app.models.document.Document;
import app.models.page.Page;
import app.models.project.Project;
import app.models.workspace.Workspace;
import app.views.MainFrame;


public class ActNodeDelete extends GAbstractAction {

    public ActNodeDelete() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        putValue(SMALL_ICON, loadIcon("images/delete_node.png"));
        putValue(NAME, "Obriši");
        putValue(SHORT_DESCRIPTION, "Obriši izabranu stavku");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTree tree = MainFrame.getInstance().getHierarchyTree();
        AbstractNode selectedNode = (AbstractNode) tree.getLastSelectedPathComponent();

        if (((selectedNode instanceof Workspace)) || (selectedNode == null))
            return;

        if (selectedNode instanceof Project) {

            Project project = (Project) selectedNode;
            Workspace parent = (Workspace) project.getParent();
            parent.deleteProject(project);

        } else if (selectedNode instanceof Document) {

            Document document = (Document) selectedNode;
            Project project = (Project) document.getParent();
            project.deleteDocument(document);

        } else if (selectedNode instanceof Page) {

            Page page = (Page) selectedNode;
            Document parent = (Document) page.getParent();
            parent.deletePage(page);
        }
    }

}
