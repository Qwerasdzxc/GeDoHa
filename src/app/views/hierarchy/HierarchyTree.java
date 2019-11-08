package app.views.hierarchy;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import app.models.Document;
import app.models.Page;
import app.models.Project;
import app.models.Workspace;

public class HierarchyTree extends JTree implements TreeSelectionListener {

    public HierarchyTree() {
        addTreeSelectionListener(this);
        setCellEditor(new HierarchyTreeEditor(this,new DefaultTreeCellRenderer()));
        setCellRenderer(new HierarchyTreeCellRendered());
        setEditable(true);
    }

    public void addProject(Project project) {
        ((HierarchyModel) getModel()).addProject(project);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void valueChanged(TreeSelectionEvent arg0) {
        TreePath path = arg0.getPath();

        Object selectedComponent = path.getLastPathComponent();

        if (selectedComponent instanceof Workspace) {
            // Ovde idu akcije vezane za rad sa workspace-om
        } else if (selectedComponent instanceof Project) {
            Project project = (Project) selectedComponent;
            // Ovde idu akcije vezane za rad sa project-om
        } else if (selectedComponent instanceof Document) {
            Document document = (Document) selectedComponent;
            // Ovde idu akcije vezane za rad sa document-om
        } else if (selectedComponent instanceof Page) {
            Page page = (Page) selectedComponent;
            // Ovde idu akcije vezane za rad sa page-om
        }
    }
}