package app.commands;

import app.graphics.elements.PageElement;
import app.models.page.Page;
import app.models.slot.Slot;

import javax.swing.*;

public class AddSlotCommand extends AbstractCommand {

    private Page page;
    private PageElement element;

    public AddSlotCommand(Page page, PageElement element){
        this.page = page;
        this.element = element;
    }
    @Override
    public void doCommand() {
        page.addSlot(element);
    }

    @Override
    public void undoCommand() {
        page.removeSlot(element);
        page.getSelectionModel().removeFromSelectionList(element);
    }
}
