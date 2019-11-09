package app.views.project;

import app.models.document.Document;
import app.models.project.ProjListener;
import app.models.project.Project;
import app.views.MainFrame;
import app.views.document.DocumentView;

import javax.swing.*;
import java.awt.*;

public class ProjectView extends JPanel implements ProjListener {

    private JTabbedPane tabbedPane;
    private Project project;

    public ProjectView(Project project) {
        super(new BorderLayout());

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        this.setSize(screenSize);

        this.project = project;
        this.project.addObserver(this);

        this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        this.setBackground(Color.WHITE);
        Label label = new Label("Adawdawdaawda adaw");
        this.add(label, BorderLayout.NORTH);
        this.add(tabbedPane, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void onDocumentCreated(Document document) {
       DocumentView documentView = new DocumentView(document);
       tabbedPane.add(document.getName(), documentView);

       SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getHierarchyTree());
    }

    @Override
    public void onProjectSelected(Project project) {
        this.setVisible(true);
    }
}
