package app.views.document;

import app.models.document.Document;

import javax.swing.*;
import java.awt.*;

public class DocumentView extends JPanel {

    private Document document;

    public DocumentView(Document document) {
        super();

        this.document = document;
        setLayout(new BorderLayout());
        setVisible(true);
    }
}
