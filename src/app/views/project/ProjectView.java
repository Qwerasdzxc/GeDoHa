package app.views.project;

import app.models.document.DocListener;
import app.models.document.Document;
import app.models.project.ProjListener;
import app.models.project.Project;
import app.views.MainFrame;
import app.views.document.DocumentView;
import app.views.hierarchy.HierarchyModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;

public class ProjectView extends JPanel implements ProjListener, DocListener {

    private JTabbedPane tabbedPane;
    private Project project;

    private JLabel projectNameLabel;

    public ProjectView(Project project) {
        super(new BorderLayout());

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        this.setSize(screenSize);

        this.project = project;
        this.project.addObserver(this);

        this.setBackground(Color.WHITE);

        projectNameLabel = new JLabel(project.getName());
        this.add(projectNameLabel, BorderLayout.NORTH);

        this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        addExistingDocuments();
        this.add(tabbedPane, BorderLayout.CENTER);
    }

    private void addExistingDocuments() {
        for (Document doc : project.getDocuments()) {
            doc.addObserver(this);

            DocumentView documentView = new DocumentView(doc);
            tabbedPane.add(doc.getName(), documentView);
        }
    }

    @Override
    public void onDocumentCreated(Document document) {
       document.addObserver(this);

       DocumentView documentView = new DocumentView(document);
       tabbedPane.add(document.getName(), documentView);

       SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getHierarchyTree());
    }

    @Override
    public void onDocumentDeleted(Document document) {
        document.removeObserver(this);

        DocumentView view = null;

        for (int i = 0; i < tabbedPane.getComponents().length; i ++) {
            if (((DocumentView) tabbedPane.getComponents()[i]).getDocument() == document)
                view = (DocumentView) tabbedPane.getComponents()[i];
        }

        tabbedPane.remove(view);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getHierarchyTree());
    }

    @Override
    public void onDocumentSelected(Document document) {
        DocumentView view = null;
        for (int i = 0; i < tabbedPane.getComponents().length; i ++) {
            if (((DocumentView) tabbedPane.getComponents()[i]).getDocument() == document)
                view = (DocumentView) tabbedPane.getComponents()[i];
        }

        tabbedPane.setSelectedComponent(view);
    }

    @Override
    public void onProjectChangedName(String name) {
        projectNameLabel.setText(name);
    }

    @Override
    public void onDocumentChangedName(Document document) {
        DocumentView view = null;
        for (int i = 0; i < tabbedPane.getComponents().length; i ++) {
            if (((DocumentView) tabbedPane.getComponents()[i]).getDocument() == document)
                view = (DocumentView) tabbedPane.getComponents()[i];
        }

        tabbedPane.setTitleAt(tabbedPane.indexOfComponent(view), document.getName());
    }
}
