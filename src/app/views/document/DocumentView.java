package app.views.document;

import app.models.document.DocListener;
import app.models.document.Document;
import app.models.page.Page;
import app.models.page.PageListener;
import app.models.project.ProjListener;
import app.models.project.Project;
import app.views.MainFrame;
import app.views.page.PageView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DocumentView extends JPanel implements ProjListener, DocListener, PageListener {

    public Document document;

    private JLabel pathLabel;

    private ArrayList<PageView> pageViews = new ArrayList<>();

    public DocumentView(Document document) {
        super();

        this.document = document;
        this.document.addObserver(this);
        ((Project) this.document.getParent()).addObserver(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Project parent = (Project) document.getParent();
        pathLabel = new JLabel(parent.getName() + " -> " + document.getName());
        this.add(pathLabel);

        addExistingPages();

        setVisible(true);
    }

    private void addExistingPages() {
        for (Page page : document.getPages()) {
            page.addObserver(this);

            PageView pageView = new PageView(page);
            pageViews.add(pageView);
            add(pageView);
        }
    }

    public Document getDocument() {
        return document;
    }

    @Override
    public void onPageCreated(Page page) {
        page.addObserver(this);

        PageView pageView = new PageView(page);
        pageViews.add(pageView);
        add(pageView);

        revalidate();

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getHierarchyTree());
    }

    @Override
    public void onPageDeleted(Page page, int index) {
        page.removeObserver(this);

        PageView pageView = pageViews.get(index);
        remove(pageView);
        pageViews.remove(pageView);

        revalidate();
        repaint();

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getHierarchyTree());
    }

    @Override
    public void onDocumentChangedName(Document document) {
        Project parent = (Project) document.getParent();

        pathLabel.setText(parent.getName() + " -> " + document.getName());
    }

    @Override
    public void onProjectChangedName(String name) {
        pathLabel.setText(name + " -> " + document.getName());
    }
}
