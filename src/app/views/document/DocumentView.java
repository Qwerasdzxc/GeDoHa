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
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DocumentView extends JPanel implements ProjListener, DocListener, PageListener {

    public Document document;

    private Page activePage;

    private JPanel pageStripPanel;
    private JPanel body;
    private JLabel pathLabel;

    private ArrayList<PageThumbnail> thumbnails = new ArrayList<>();

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

        JScrollPane bodyScroll = new JScrollPane(body);

        pageStripPanel = new JPanel();
        pageStripPanel.setLayout(new BoxLayout(pageStripPanel, BoxLayout.Y_AXIS));
        pageStripPanel.setBackground(Color.LIGHT_GRAY);

        JScrollPane scroll = new JScrollPane(pageStripPanel);
        scroll.setMinimumSize(new Dimension(200, 150));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, bodyScroll);
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(0.1);

        setLayout(new BorderLayout());
        add(splitPane);

        addExistingPages();

        pathLabel = new JLabel();

        setVisible(true);
    }

    private void addExistingPages() {
        for (AbstractNode node : document.getChildren()) {
            Page page = (Page) node;
            page.addObserver(this);

            PageThumbnail thumbnail = new PageThumbnail(page);
            thumbnail.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    page.setSelected();
                }
            });
            thumbnails.add(thumbnail);
            pageStripPanel.add(thumbnail);
            repaint();
        }
    }

    public Document getDocument() {
        return document;
    }

    @Override
    public void onPageCreated(Page page) {
        activePage = page;
        page.addObserver(this);

        PageThumbnail thumbnail = new PageThumbnail(page);
        thumbnail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                page.setSelected();
            }
        });
        thumbnails.add(thumbnail);
        pageStripPanel.add(thumbnail);

        body.removeAll();
        body.add(new PageView(page));

        revalidate();

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getHierarchyTree());
    }

    @Override
    public void onPageDeleted(Page page, int index) {
        page.removeObserver(this);

        PageThumbnail thumbnail = thumbnails.get(index);
        pageStripPanel.remove(thumbnail);
        thumbnails.remove(thumbnail);

        if (activePage == page) {
            body.removeAll();
            activePage = null;
        }

        revalidate();
        repaint();

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getHierarchyTree());
    }

    @Override
    public void onPageSelected(Page page) {
        activePage = page;

        body.removeAll();
        body.add(new PageView(page));

        revalidate();
        repaint();
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
