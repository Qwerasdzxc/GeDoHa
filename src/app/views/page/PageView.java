package app.views.page;

import app.models.document.DocListener;
import app.models.document.Document;
import app.models.page.Page;
import app.models.page.PageListener;
import app.models.project.ProjListener;
import app.models.project.Project;

import javax.swing.*;
import java.awt.*;

public class PageView extends JPanel implements ProjListener, DocListener, PageListener {

    private Page page;

    private JLabel pathLabel;

    public PageView(Page page) {
        this.page = page;
        this.page.addObserver(this);
        ((Document) this.page.getParent()).addObserver(this);
        ((Project) this.page.getParent().getParent()).addObserver(this);

        setLayout(new BorderLayout());
        setBackground(Color.gray);

        Document doc = (Document) page.getParent();
        Project project = (Project) doc.getParent();
        pathLabel = new JLabel(project.getName() + " -> " + doc.getName() + " -> " + page.getName());
        add(pathLabel, BorderLayout.CENTER);

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
}
