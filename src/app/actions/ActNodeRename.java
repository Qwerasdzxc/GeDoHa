package app.actions;

import java.awt.event.ActionEvent;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import app.views.MainFrame;


public class ActNodeRename extends GAbstractAction{

	@Override
	public void actionPerformed(ActionEvent e) {
		JTree tree = MainFrame.getInstance().getTree();
		TreePath path = tree.getSelectionPath();
		if (path != null) {
			tree.startEditingAtPath(path);
		}		
	}

}
