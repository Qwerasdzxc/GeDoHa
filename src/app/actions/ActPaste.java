package app.actions;

import app.actions.GAbstractAction;
import app.models.page.Page;
import app.models.slot.SlotSelection;
import app.views.MainFrame;
import app.views.document.DocumentView;
import app.views.page.PageView;
import app.views.project.ProjectView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ActPaste extends GAbstractAction {

    public ActPaste() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_V);
        putValue(SMALL_ICON, loadIcon("images/paste.png"));
        putValue(NAME, "Nalepi");
        putValue(SHORT_DESCRIPTION, "Nalepi kopiranu stavku");
    }

    public void actionPerformed(ActionEvent e) {
        ProjectView activeProjectView = MainFrame.getInstance().getWorkspaceView().getActiveProjectView();
        if (activeProjectView == null)
            return;

        DocumentView activeDocumentView = activeProjectView.getActiveDocumentView();
        if (activeDocumentView == null)
            return;

        PageView activePage = activeDocumentView.getActivePageView();
        if (activePage == null)
            return;

        activePage.paste();
    }
}

