package app.views.miscellaneous;

import app.actions.ActionManager;

import java.awt.Image;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class ToolBar extends JToolBar {
	
	public ToolBar() {
		super(SwingConstants.HORIZONTAL);
		
		setFloatable(false);

		add(ActionManager.getInstance().getSaveProject());
		add(ActionManager.getInstance().getOpenProject());

		addSeparator();
		
        add(ActionManager.getInstance().getNewNode());

		addSeparator();

		add(ActionManager.getInstance().getRenameNode());
		add(ActionManager.getInstance().getDeleteNode());
	}
}
