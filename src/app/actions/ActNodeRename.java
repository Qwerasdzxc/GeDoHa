package app.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.tree.TreePath;

import app.views.MainFrame;
import app.views.hierarchy.HierarchyTree;


public class ActNodeRename extends GAbstractAction {

	public ActNodeRename() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
		putValue(SMALL_ICON, loadIcon("images/rename_node.png"));
		putValue(NAME, "Preimenuj");
		putValue(SHORT_DESCRIPTION, "Preimenuj izabranu stavku");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		HierarchyTree tree = MainFrame.getInstance().getHierarchyTree();
		TreePath path = tree.getSelectionPath();
		if (path != null) {
			System.out.println(path);
			tree.startEditingAtPath(path);
		}		
	}
}
