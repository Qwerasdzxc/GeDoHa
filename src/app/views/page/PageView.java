package app.views.page;

import app.Utilities;
import app.models.document.DocListener;
import app.models.document.Document;
import app.models.page.Page;
import app.models.page.PageListener;
import app.models.project.ProjListener;
import app.models.project.Project;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class PageView extends JPanel implements ProjListener, DocListener, PageListener {

    private Page page;

    private JLabel pathLabel;
    private TitledBorder pageTitleBorder;
    private JPanel content;

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

        content = new JPanel();
        content.setBackground(Color.WHITE);
        add(content);

        pathLabel = new JLabel();

        setVisible(true);
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
