package app.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.tree.TreePath;

import app.models.document.Document;
import app.models.page.Page;
import app.views.MainFrame;


public class ActNewPage extends GAbstractAction {

	public ActNewPage() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
				KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/new_page.png"));
		putValue(NAME, "Nova strana");
		putValue(SHORT_DESCRIPTION, "Napravi novu stranicu za dokument");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTree tree = MainFrame.getInstance().getHierarchyTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();
		TreePath path = tree.getSelectionPath();

		if (selectedComponent instanceof Document) {
			tree.expandPath(path);
			Document document = (Document) selectedComponent;
			Page page = new Page(document, "Page " + document.getPageCount());
			document.addPage(page);
		}
	}

}
