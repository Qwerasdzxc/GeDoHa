package app.views.project;

import app.models.document.DocListener;
import app.models.document.Document;
import app.models.project.ProjListener;
import app.models.project.Project;
import app.views.MainFrame;
import app.views.document.DocumentView;

import javax.swing.*;
import java.awt.*;

public class ProjectView extends JPanel implements ProjListener, DocListener {

    private JTabbedPane tabbedPane;
    private Project project;

    private JLabel projectNameLabel;

    private JButton closeAllTabsButton;
    private JButton closeActiveTabButton;

    public ProjectView(Project project) {
        super(new BorderLayout());

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        this.setSize(screenSize);

        this.project = project;
        this.project.addObserver(this);

        this.setBackground(Color.WHITE);

        JPanel topPanel = new JPanel();

        this.closeAllTabsButton = new JButton();
        this.closeAllTabsButton.setText("Zatvori sve tabove");
        this.closeActiveTabButton = new JButton();
        this.closeActiveTabButton.setText("Zatvori aktivan tab");

        this.projectNameLabel = new JLabel(project.getName());

        topPanel.add(projectNameLabel);
        topPanel.add(closeActiveTabButton);
        topPanel.add(closeAllTabsButton);

        this.add(topPanel, BorderLayout.NORTH);
        this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        addExistingDocuments();
        this.add(tabbedPane, BorderLayout.CENTER);

        // Inicijalizacija controller-a
        new ProjectViewController(this);
    }

    void closeAllTabs() {
        for (Document doc : project.getDocuments()) {
            DocumentView view = null;

            for (int i = 0; i < tabbedPane.getComponents().length; i ++) {
                if (((DocumentView) tabbedPane.getComponents()[i]).getDocument() == doc)
                    view = (DocumentView) tabbedPane.getComponents()[i];
            }

            tabbedPane.remove(view);
        }
    }

    void closeActiveTab() {
        tabbedPane.remove(tabbedPane.getSelectedComponent());
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

        // Ako tab postoji, postavi ga u fokus
        if (view != null)
            tabbedPane.setSelectedComponent(view);
        // Ako ne postoji, napravi novi tab
        else {
            document.addObserver(this);

            DocumentView documentView = new DocumentView(document);
            tabbedPane.add(document.getName(), documentView);

            revalidate();
            repaint();
        }
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

    public JButton getCloseAllTabsButton() {
        return closeAllTabsButton;
    }

    public JButton getCloseActiveTabButton() {
        return closeActiveTabButton;
    }
}
