package app.actions;

import app.models.project.Project;
import app.models.workspace.Workspace;
import app.views.MainFrame;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ActNewProject extends GAbstractAction {

    public ActNewProject() {
        putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/new_project.png"));
        putValue(NAME, "Novi projekat");
        putValue(SHORT_DESCRIPTION, "Napravi novi projekat za workspace");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
		JTree hierarchy = MainFrame.getInstance().getHierarchyTree();
		TreePath path = hierarchy.getSelectionPath();
        hierarchy.expandPath(path);
		Project project = new Project("Novi projekat");
		((Workspace) hierarchy.getModel().getRoot()).addProject(project);
    }
}
