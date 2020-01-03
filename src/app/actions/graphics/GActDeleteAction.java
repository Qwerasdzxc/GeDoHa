package app.actions.graphics;

import app.actions.GAbstractAction;
import app.graphics.elements.PageElement;
import app.models.element_selection.ElementSelectionModel;
import app.state.StateManager;
import app.views.page.PageView;

import java.awt.event.ActionEvent;

public class GActDeleteAction extends GAbstractAction {

    private PageView pageView;

    public GActDeleteAction(PageView pageView) {
        this.pageView = pageView;

        putValue(SMALL_ICON, loadIcon("images/delete.png"));
        putValue(NAME, "Obriši");
        putValue(SHORT_DESCRIPTION, "Obriši izabrane elemente");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int elementsToDelete = pageView.getPage().getSelectionModel().getSelectionListSize();

        for (int i = 0; i < elementsToDelete; i++) {
            PageElement toBeRemoved = pageView.getPage().getSelectionModel().getElementFromSelectionListAt(i);

            pageView.getPage().removeSlot(toBeRemoved);
        }

        pageView.getPage().getSelectionModel().removeAllFromSelectionList();
    }
}
