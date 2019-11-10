package app.actions;

import app.models.document.Document;
import app.models.project.Project;
import app.models.workspace.Workspace;
import app.views.MainFrame;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

public class ActNewDocument extends GAbstractAction {

    public ActNewDocument() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/new_document.png"));
        putValue(NAME, "Novi dokument");
        putValue(SHORT_DESCRIPTION, "Napravi novi dokument za projekat");
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        JTree hierarchy = MainFrame.getInstance().getHierarchyTree();
        Object selectedComponent = hierarchy.getLastSelectedPathComponent();
        TreePath path = hierarchy.getSelectionPath();

        if (selectedComponent instanceof Project) {
            hierarchy.expandPath(path);

            Project project = (Project) selectedComponent;
            Document document = new Document(project,"Novi dokument - " + new Random().nextInt(100));
            project.addDocument(document);
        }
    }
}