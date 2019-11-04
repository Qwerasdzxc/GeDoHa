package app.hierarchy;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import app.models.Document;
import app.models.Project;

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
        for (int i = 0; i < path.getPathCount(); i++) {
            if (path.getPathComponent(i) instanceof Document) {
                // Pronaci i postaviti kao aktivan JInternalFrame koji sadrži selektovani dokument
                Document d = (Document) path.getPathComponent(i);
                break;
            }
        }
    }
}