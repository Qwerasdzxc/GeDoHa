package app.actions;

import app.models.project.Project;
import app.models.workspace.Workspace;
import app.views.MainFrame;
import app.views.hierarchy.HierarchyModel;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

public class ActNewProject extends GAbstractAction {

    public ActNewProject() {
        putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/new_project.png"));
        putValue(NAME, "Novi projekat");
        putValue(SHORT_DESCRIPTION, "Napravi novi projekat za workspace");

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
		JTree hierarchy = MainFrame.getInstance().getHierarchyTree();
		TreePath path = hierarchy.getSelectionPath();
        hierarchy.expandPath(path);

        Workspace workspace = (Workspace) ((HierarchyModel) hierarchy.getModel()).getRoot();
		Project project = new Project(workspace, "Projekat " + workspace.getProjectsCount());
		workspace.addProject(project);
    }
}
