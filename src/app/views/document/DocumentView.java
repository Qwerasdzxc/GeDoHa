package app.views.document;

import app.models.AbstractNode;
import app.models.document.DocListener;
import app.models.document.Document;
import app.models.page.Page;
import app.models.page.PageListener;
import app.models.project.ProjListener;
import app.models.project.Project;
import app.views.MainFrame;
import app.views.page.PageView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

public class DocumentView extends JPanel implements ProjListener, DocListener, PageListener {

    public Document document;

    private JPanel body;
    private JLabel pathLabel;

    private ArrayList<PageView> pageViews = new ArrayList<>();

    public DocumentView(Document document) {
        super();

        this.document = document;
        this.document.addObserver(this);
        Project parent = (Project) this.document.getParent();
        parent.addObserver(this);

        body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.PAGE_AXIS));
        body.setBackground(Color.LIGHT_GRAY);
        body.setBorder(new EmptyBorder(10, 0, 0, 10));

        this.setLayout(new BorderLayout());
        JScrollPane scrollBar = new JScrollPane(body, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBar.getVerticalScrollBar().setUnitIncrement(15);
        this.add(scrollBar);

        addExistingPages();

        pathLabel = new JLabel();

        setVisible(true);
    }

    private void addExistingPages() {
        for (AbstractNode node : document.getChildren()) {
            Page page = (Page) node;
            page.addObserver(this);

            PageView pageView = new PageView(page);
            pageViews.add(pageView);
            body.add(pageView);
            body.scrollRectToVisible(pageView.getBounds());
            repaint();
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

        body.add(pageView);
        body.scrollRectToVisible(pageView.getBounds());

        revalidate();

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getHierarchyTree());
    }

    @Override
    public void onPageDeleted(Page page, int index) {
        page.removeObserver(this);

        PageView pageView = pageViews.get(index);
        body.remove(pageView);
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
