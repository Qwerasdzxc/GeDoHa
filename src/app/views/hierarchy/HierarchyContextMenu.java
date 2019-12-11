package app.views.hierarchy;

import app.actions.ActionManager;

import javax.swing.*;

public class HierarchyContextMenu extends JPopupMenu {

    private JMenuItem create;
    private JMenuItem rename;
    private JMenuItem delete;

    public HierarchyContextMenu() {
        this.create = new JMenuItem("Napravi");
        this.create.addActionListener(ActionManager.getInstance().getNewNode());

        this.rename = new JMenuItem("Preimenuj");
        this.rename.addActionListener(ActionManager.getInstance().getRenameNode());

        this.delete = new JMenuItem("Obriši");
        this.delete.addActionListener(ActionManager.getInstance().getDeleteNode());

        add(create);
        addSeparator();
        add(rename);
        add(delete);
    }

    public void enableForWorkspace() {
        create.setEnabled(true);
        rename.setEnabled(false);
        delete.setEnabled(false);
    }

    public void enableForProject() {
        create.setEnabled(true);
        rename.setEnabled(true);
        delete.setEnabled(true);
    }

    public void enableForDocument() {
        create.setEnabled(true);
        rename.setEnabled(true);
        delete.setEnabled(true);
    }

    public void enableForPage() {
        create.setEnabled(false);
        rename.setEnabled(true);
        delete.setEnabled(true);
    }
}
