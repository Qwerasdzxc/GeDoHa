package app.actions;

import java.awt.event.ActionEvent;

import javax.swing.JTree;

import app.models.Document;
import app.models.Page;
import app.models.Project;
import app.models.Workspace;
import app.views.MainFrame;



public class ActNodeDelete extends GAbstractAction{

	@Override
	public void actionPerformed(ActionEvent e) {
		JTree tree = MainFrame.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();
		Object[] path = tree.getSelectionPath().getPath();

		if (((selectedComponent instanceof Workspace)) || (selectedComponent == null)) {
			return;

		} else if (selectedComponent instanceof Project) {

			Project project = (Project) selectedComponent;
			Workspace parent = (Workspace) project.getParent();
			parent.deleteProject(project);

		} else if (selectedComponent instanceof Document) {

			Document document = (Document) selectedComponent;
			Project project = (Project) path[1];
			project.deleteDocument(document);

		} else if (selectedComponent instanceof Page) {

			Page page = (Page) selectedComponent;
			Document parent = (Document) page.getParent();
			parent.deletePage(page);

		}	
	}

}
