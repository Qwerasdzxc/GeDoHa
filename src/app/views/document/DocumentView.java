package app.views.document;

import app.models.document.DocListener;
import app.models.document.Document;
import app.models.page.Page;
import app.models.page.PageListener;
import app.models.project.Project;
import app.views.MainFrame;
import app.views.page.PageView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DocumentView extends JPanel implements DocListener, PageListener {

    public Document document;

    private ArrayList<PageView> pageViews = new ArrayList<>();

    public DocumentView(Document document) {
        super();

        this.document = document;
        this.document.addObserver(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Project parent = (Project) document.getParent();
        Label label = new Label(parent.getName() + " -> " + document.getName());
        this.add(label);

        addExistingPages();

        setVisible(true);
    }

    private void addExistingPages() {
        for (Page page : document.getPages()) {
            page.addObserver(this);

            PageView pageView = new PageView(page);
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
        add(pageView);

        revalidate();

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getHierarchyTree());
    }

    @Override
    public void onPageDeleted(Page page) {
        page.removeObserver(this);

        remove(pageViews.get(((Document) page.getParent()).getPageIndex(page)));
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getHierarchyTree());
    }
}
