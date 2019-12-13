package app.views.workspace;

import app.models.AbstractNode;
import app.models.document.Document;
import app.models.project.ProjListener;
import app.models.project.Project;
import app.models.workspace.WSListener;
import app.models.workspace.Workspace;
import app.views.MainFrame;
import app.views.document.DocumentView;
import app.views.project.ProjectView;

import javax.swing.*;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;

public class WorkspaceView extends JPanel implements WSListener, ProjListener {

    private Workspace workspace;
    private Project activeProject;

    public WorkspaceView(Workspace workspace) {
        super(new BorderLayout());

        this.workspace = workspace;

        workspace.addObserver(this);
        attachProjectObservers();

        setBackground(Color.WHITE);
        setVisible(true);
    }

    private void attachProjectObservers() {
        for (AbstractNode node : workspace.getChildren()) {
            Project project = (Project) node;
            project.addObserver(this);
        }
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
        repaint();

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getHierarchyTree());
    }

    @Override
    public void onProjectDeleted(Project project) {
        project.removeObserver(this);
        activeProject = null;

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
    }
}
