package app.views.hierarchy;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import app.actions.ActionManager;
import app.models.AbstractNode;
import app.models.document.Document;
import app.models.page.Page;
import app.models.project.Project;
import app.models.workspace.Workspace;

public class HierarchyTree extends JTree implements TreeSelectionListener {

    private HierarchyContextMenu contextMenu;

    public HierarchyTree() {
        addTreeSelectionListener(this);
        setCellEditor(new HierarchyTreeEditor(this, new DefaultTreeCellRenderer()));
        setCellRenderer(new HierarchyTreeCellRendered());
        setEditable(true);
        setToggleClickCount(2);

        this.contextMenu = new HierarchyContextMenu();
        setComponentPopupMenu(contextMenu);
    }

    @Override
    public void valueChanged(TreeSelectionEvent arg0) {
        TreePath path = arg0.getPath();

        AbstractNode selectedComponent = (AbstractNode) path.getLastPathComponent();

        if (selectedComponent instanceof Workspace) {
            enableForWorkspace();
        } else if (selectedComponent instanceof Project) {
            enableForProject();

            Project project = (Project) selectedComponent;
            project.setSelected();

        } else if (selectedComponent instanceof Document) {
            enableForDocument();

            Document document = (Document) selectedComponent;
            Project parent = (Project) document.getParent();

            parent.setSelected();
            document.setSelected();
        } else if (selectedComponent instanceof Page) {
            enableForPage();

            Page page = (Page) selectedComponent;
            // Ovde idu akcije vezane za rad sa page-om
        }
    }

    public HierarchyContextMenu getContextMenu() {
        return contextMenu;
    }

    public Project getCurrentProject() {
        TreePath path = getSelectionPath();
        for (int i = 0; i < path.getPathCount(); i++) {
            if (path.getPathComponent(i) instanceof Project) {
                return (Project) path.getPathComponent(i);
            }
        }
        return null;
    }

    private void enableForWorkspace() {
        ActionManager.getInstance().getNewNode().setEnabled(true);
        ActionManager.getInstance().getRenameNode().setEnabled(false);
        ActionManager.getInstance().getDeleteNode().setEnabled(false);

        ActionManager.getInstance().getSaveProject().setEnabled(false);
        ActionManager.getInstance().getSaveProjectAs().setEnabled(false);

        getContextMenu().enableForWorkspace();
    }

    private void enableForProject() {
        ActionManager.getInstance().getNewNode().setEnabled(true);
        ActionManager.getInstance().getRenameNode().setEnabled(true);
        ActionManager.getInstance().getDeleteNode().setEnabled(true);

        ActionManager.getInstance().getSaveProject().setEnabled(true);
        ActionManager.getInstance().getSaveProjectAs().setEnabled(true);

        getContextMenu().enableForProject();
    }

    private void enableForDocument() {
        ActionManager.getInstance().getNewNode().setEnabled(true);
        ActionManager.getInstance().getRenameNode().setEnabled(true);
        ActionManager.getInstance().getDeleteNode().setEnabled(true);

        ActionManager.getInstance().getSaveProject().setEnabled(false);
        ActionManager.getInstance().getSaveProjectAs().setEnabled(false);

        getContextMenu().enableForDocument();
    }

    private void enableForPage() {
        ActionManager.getInstance().getNewNode().setEnabled(false);
        ActionManager.getInstance().getRenameNode().setEnabled(true);
        ActionManager.getInstance().getDeleteNode().setEnabled(true);

        ActionManager.getInstance().getSaveProject().setEnabled(false);
        ActionManager.getInstance().getSaveProjectAs().setEnabled(false);

        getContextMenu().enableForPage();
    }
}