package app.views.hierarchy;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import app.models.document.Document;
import app.models.page.Page;
import app.models.project.Project;
import app.models.workspace.Workspace;
import app.views.project.ProjectView;

public class HierarchyTree extends JTree implements TreeSelectionListener {

    public HierarchyTree() {
        addTreeSelectionListener(this);
        setCellEditor(new HierarchyTreeEditor(this,new DefaultTreeCellRenderer()));
        setCellRenderer(new HierarchyTreeCellRendered());
        setEditable(true);
    }

    public void valueChanged(TreeSelectionEvent arg0) {
        TreePath path = arg0.getPath();

        Object selectedComponent = path.getLastPathComponent();

        if (selectedComponent instanceof Workspace) {
            // Ovde idu akcije vezane za rad sa workspace-om
        } else if (selectedComponent instanceof Project) {
            Project project = (Project) selectedComponent;
            project.setSelected();

        } else if (selectedComponent instanceof Document) {
            Document document = (Document) selectedComponent;
            Project parent = (Project) document.getParent();

            parent.setSelected();
            document.setSelected();
        } else if (selectedComponent instanceof Page) {
            Page page = (Page) selectedComponent;
            // Ovde idu akcije vezane za rad sa page-om
        }
    }
}