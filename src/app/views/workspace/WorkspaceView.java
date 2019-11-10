package app.views.workspace;

import app.models.document.Document;
import app.models.project.ProjListener;
import app.models.project.Project;
import app.models.workspace.WSListener;
import app.models.workspace.Workspace;
import app.views.MainFrame;
import app.views.hierarchy.HierarchyModel;
import app.views.project.ProjectView;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;

public class WorkspaceView extends JPanel implements WSListener, ProjListener {

    Project activeProject;

    public WorkspaceView() {
        super(new BorderLayout());

        setBackground(Color.WHITE);
        setVisible(true);
    }

    @Override
    public void onProjectCreated(Project project) {
        activeProject = project;
        ProjectView activeProjectView = new ProjectView(project);

        removeAll();
        add(activeProjectView, BorderLayout.CENTER);

        activeProjectView.setVisible(true);
        project.addObserver(this);

        revalidate();

//        setTreePath();
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getHierarchyTree());
    }

    @Override
    public void onProjectDeleted(Project project) {
        project.removeObserver(this);
        activeProject = null;
        System.out.println("Call");
        removeAll();
        revalidate();
        repaint();
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getHierarchyTree());
    }

    @Override
    public void onProjectSelected(Project project) {
        if (activeProject == project)
            return;

        activeProject = project;
        ProjectView activeProjectView = new ProjectView(project);

        removeAll();
        add(activeProjectView, BorderLayout.CENTER);

        activeProjectView.setVisible(true);
        project.addObserver(this);

        revalidate();
//        setTreePath();
    }

    private void setTreePath() {

        HierarchyModel m = (HierarchyModel) MainFrame.getInstance().getHierarchyTree().getModel();
        TreeNode[] n = m.getPathToRoot(activeProject);
        System.out.println(n);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame.getInstance().getHierarchyTree().scrollPathToVisible(new TreePath(n));
                MainFrame.getInstance().getHierarchyTree().setSelectionPath(new TreePath(n));
//                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getHierarchyTree());
            }
        });
    }
}
