package app.views.hierarchy;

import app.actions.ActionManager;

import javax.swing.*;

public class HierarchyContextMenu extends JPopupMenu {

    private JMenuItem createProject;
    private JMenuItem createDocument;
    private JMenuItem createPage;
    private JMenuItem rename;
    private JMenuItem delete;

    public HierarchyContextMenu() {
        this.createProject = new JMenuItem("Napravi projekat");
        this.createProject.addActionListener(ActionManager.getInstance().getNewProject());

        this.createDocument = new JMenuItem("Napravi dokument");
        this.createDocument.addActionListener(ActionManager.getInstance().getNewDocument());

        this.createPage = new JMenuItem("Napravi stranu");
        this.createPage.addActionListener(ActionManager.getInstance().getNewPage());

        this.rename = new JMenuItem("Preimenuj");
        this.rename.addActionListener(ActionManager.getInstance().getRenameNode());

        this.delete = new JMenuItem("Obriši");
        this.delete.addActionListener(ActionManager.getInstance().getDeleteNode());

        add(createProject);
        add(createDocument);
        add(createPage);
        addSeparator();
        add(rename);
        add(delete);
    }

    public void enableForWorkspace() {
        createProject.setEnabled(true);
        createDocument.setEnabled(false);
        createPage.setEnabled(false);
        rename.setEnabled(false);
        delete.setEnabled(false);
    }

    public void enableForProject() {
        createProject.setEnabled(false);
        createDocument.setEnabled(true);
        createPage.setEnabled(false);
        rename.setEnabled(true);
        delete.setEnabled(true);
    }

    public void enableForDocument() {
        createProject.setEnabled(false);
        createDocument.setEnabled(false);
        createPage.setEnabled(true);
        rename.setEnabled(true);
        delete.setEnabled(true);
    }

    public void enableForPage() {
        createProject.setEnabled(false);
        createDocument.setEnabled(false);
        createPage.setEnabled(false);
        rename.setEnabled(true);
        delete.setEnabled(true);
    }
}
