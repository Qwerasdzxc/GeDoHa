package app.views.page;

import app.Utilities;
import app.graphics.elements.PageElement;
import app.graphics.painters.ElementPainter;
import app.models.document.DocListener;
import app.models.document.Document;
import app.models.page.Page;
import app.models.page.PageListener;
import app.models.project.ProjListener;
import app.models.project.Project;
import app.state.StateManager;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class PageView extends JPanel implements ProjListener, DocListener, PageListener {

    private Page page;

    private JLabel pathLabel;
    private TitledBorder pageTitleBorder;
    private JPanel content;

    private PalleteBar palleteBar;

    private StateManager stateManager;

    public PageView(Page page) {
        this.page = page;
        this.page.addObserver(this);
        ((Document) this.page.getParent()).addObserver(this);
        ((Project) this.page.getParent().getParent()).addObserver(this);

        Dimension pageDimensions = new Dimension(Utilities.PAGE_WIDTH, Utilities.PAGE_HEIGHT);

        setMaximumSize(pageDimensions);
        setPreferredSize(pageDimensions);
        setMinimumSize(pageDimensions);
        setBackground(Color.LIGHT_GRAY);
        setAlignmentY(CENTER_ALIGNMENT);

        EmptyBorder paddingBorder = new EmptyBorder(15, 0, 15, 0);
        pageTitleBorder = BorderFactory.createTitledBorder(new EmptyBorder(0, 0, 0, 0), page.getName());
        pageTitleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);
        pageTitleBorder.setTitleJustification(TitledBorder.CENTER);
        CompoundBorder border = new CompoundBorder(paddingBorder, pageTitleBorder);

        setLayout(new BorderLayout());
        setBorder(border);

        PageController controller = new PageController(this);

        content = new JPanel();
        content.setBackground(Color.WHITE);
        content.addMouseListener(controller);
        content.addMouseMotionListener(controller);
        add(content);

        stateManager = new StateManager(this);
        palleteBar = new PalleteBar(stateManager);
        add(palleteBar, BorderLayout.NORTH);

        pathLabel = new JLabel();

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Iterator<PageElement> it = page.getSlotsIterator();
        while(it.hasNext()){
            PageElement element = it.next();
            ElementPainter painter = element.getElementPainter();
            painter.paint(g2, element);
        }
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    @Override
    public void onSlotAdded() {
        repaint();
    }

    @Override
    public void onPageSelected(Page page) {

    }

    @Override
    public void onProjectChangedName(String name) {
        Document doc = (Document) page.getParent();
        pathLabel.setText(name + " -> " + doc.getName() + " -> " + page.getName());
    }

    @Override
    public void onDocumentChangedName(Document document) {
        Project project = (Project) page.getParent().getParent();
        pathLabel.setText(project.getName() + " -> " + document.getName() + " -> " + page.getName());
    }

    @Override
    public void onPageChangedName(String name) {
        Project project = (Project) page.getParent().getParent();
        Document doc = (Document) page.getParent();

        pathLabel.setText(project.getName() + " -> " + doc.getName() + " -> " + name);
        pageTitleBorder.setTitle(name);
    }

    public Page getPage() {
        return page;
    }
}
