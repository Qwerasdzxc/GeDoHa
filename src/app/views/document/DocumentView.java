package app.views.document;

import app.models.document.Document;
import app.models.project.Project;

import javax.swing.*;
import java.awt.*;

public class DocumentView extends JPanel {

    public Document document;

    public DocumentView(Document document) {
        super();

        this.document = document;
        setLayout(new BorderLayout());

        Project parent = (Project) document.getParent();
        Label label = new Label(parent.getName() + " -> " + document.getName());
        this.add(label, BorderLayout.NORTH);

        setVisible(true);
    }

    public Document getDocument() {
        return document;
    }
}
