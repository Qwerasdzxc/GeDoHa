package app.views.workspace;

import app.models.document.Document;
import app.models.project.ProjListener;
import app.models.project.Project;
import app.models.workspace.WSListener;
import app.models.workspace.Workspace;
import app.views.MainFrame;
import app.views.project.ProjectView;

import javax.swing.*;
import java.awt.*;

public class WorkspaceView extends JPanel implements WSListener, ProjListener {

    ProjectView activeProjectView;

    public WorkspaceView() {
        super(new BorderLayout());


    }

    @Override
    public void onProjectCreated(Project project) {
        project.addObserver(this);
        activeProjectView = new ProjectView(project);
        add(activeProjectView, BorderLayout.CENTER);
        setBackground(Color.BLUE);

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getHierarchyTree());
    }

    @Override
    public void onDocumentCreated(Document document) {}

    @Override
    public void onProjectSelected(Project project) {
        System.out.println("sel");
        removeAll();

        onProjectCreated(project);
    }
}
