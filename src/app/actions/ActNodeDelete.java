package app.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

import app.models.document.Document;
import app.models.page.Page;
import app.models.project.Project;
import app.models.workspace.Workspace;
import app.views.MainFrame;



public class ActNodeDelete extends GAbstractAction {

	public ActNodeDelete() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		putValue(SMALL_ICON, loadIcon("images/delete_node.png"));
		putValue(NAME, "Obriši");
		putValue(SHORT_DESCRIPTION, "Obriši izabranu stavku");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTree tree = MainFrame.getInstance().getHierarchyTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();
		Object[] treePath = tree.getSelectionPath().getPath();

		if (((selectedComponent instanceof Workspace)) || (selectedComponent == null)) {
			return;

		} else if (selectedComponent instanceof Project) {

			Project project = (Project) selectedComponent;
			Workspace parent = (Workspace) project.getParent();
			parent.deleteProject(project);

		} else if (selectedComponent instanceof Document) {

			Document document = (Document) selectedComponent;
			Project project = (Project) treePath[1];
			project.deleteDocument(document);

		} else if (selectedComponent instanceof Page) {

			Page page = (Page) selectedComponent;
			Document parent = (Document) page.getParent();
			parent.deletePage(page);
		}
	}

}
