package app.views.hierarchy;

import app.actions.ActionManager;

import javax.swing.*;

public class HierarchyContextMenu extends JPopupMenu {

    private JMenuItem save;
    private JMenuItem saveAs;
    private JMenuItem create;
    private JMenuItem rename;
    private JMenuItem delete;

    public HierarchyContextMenu() {
        this.save = new JMenuItem("Sačuvaj projekat");
        this.save.addActionListener(ActionManager.getInstance().getSaveProject());

        this.saveAs = new JMenuItem("Sačuvaj projekat kao");
        this.saveAs.addActionListener(ActionManager.getInstance().getSaveProjectAs());

        this.create = new JMenuItem("Napravi");
        this.create.addActionListener(ActionManager.getInstance().getNewNode());

        this.rename = new JMenuItem("Preimenuj");
        this.rename.addActionListener(ActionManager.getInstance().getRenameNode());

        this.delete = new JMenuItem("Obriši");
        this.delete.addActionListener(ActionManager.getInstance().getDeleteNode());

        add(save);
        add(saveAs);
        addSeparator();
        add(create);
        addSeparator();
        add(rename);
        add(delete);
    }

    public void enableForWorkspace() {
        save.setEnabled(false);
        saveAs.setEnabled(false);
        create.setEnabled(true);
        rename.setEnabled(false);
        delete.setEnabled(false);
    }

    public void enableForProject() {
        save.setEnabled(true);
        saveAs.setEnabled(true);
        create.setEnabled(true);
        rename.setEnabled(true);
        delete.setEnabled(true);
    }

    public void enableForDocument() {
        save.setEnabled(false);
        saveAs.setEnabled(false);
        create.setEnabled(true);
        rename.setEnabled(true);
        delete.setEnabled(true);
    }

    public void enableForPage() {
        save.setEnabled(false);
        saveAs.setEnabled(false);
        create.setEnabled(false);
        rename.setEnabled(true);
        delete.setEnabled(true);
    }
}
