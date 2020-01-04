package app.actions;

import app.graphics.elements.PageElement;
import app.models.page.Page;
import app.models.slot.Slot;
import app.models.slot.SlotSelection;
import app.views.MainFrame;
import app.views.document.DocumentView;
import app.views.project.ProjectView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ActCopy extends GAbstractAction {

    public ActCopy() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_C);
        putValue(SMALL_ICON, loadIcon("images/copy.png"));
        putValue(NAME, "Kopiraj");
        putValue(SHORT_DESCRIPTION, "Kopiraj izabranu stavku");
    }

    public void actionPerformed(ActionEvent e) {
        ProjectView activeProjectView = MainFrame.getInstance().getWorkspaceView().getActiveProjectView();
        if (activeProjectView == null)
            return;

        DocumentView activeDocumentView = activeProjectView.getActiveDocumentView();
        if (activeDocumentView == null)
            return;

        Page activePage = activeDocumentView.getActivePage();
        if (activePage == null)
            return;

        if (!activePage.getSelectionModel().getSelectionList().isEmpty()) {
            ArrayList<Slot> selectedSlots = new ArrayList<>();
            for (PageElement el : activePage.getSelectionModel().getSelectionList())
                selectedSlots.add(el.getParent());

            SlotSelection contents = new SlotSelection(selectedSlots);
            MainFrame.getInstance().getClipboard().setContents(contents, MainFrame.getInstance());
        }
    }
}
