package app.actions;

import java.awt.event.ActionEvent;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import app.models.Document;
import app.models.Page;
import app.views.MainFrame;


public class ActNewPage extends GAbstractAction{

	@Override
	public void actionPerformed(ActionEvent e) {
		// Dodavanje nove stranice u selektovani dokument
				JTree tree = MainFrame.getInstance().getTree();
				Object selectedComponent = tree.getLastSelectedPathComponent();
				TreePath path = tree.getSelectionPath();
				if (selectedComponent instanceof Document) {
					tree.expandPath(path);
					Document document = (Document) selectedComponent;
					Page page = new Page(document);
					document.addPage(page);
				}		
	}

}
