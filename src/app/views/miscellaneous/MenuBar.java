package app.views.miscellaneous;

import app.actions.ActionManager;
import app.views.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar{
	
	public MenuBar() {
		JMenu file = new JMenu("File");

		file.add(ActionManager.getInstance().getSaveProject());
		file.add(ActionManager.getInstance().getSaveProjectAs());
		file.add(ActionManager.getInstance().getOpenProject());
		file.addSeparator();
		file.add(ActionManager.getInstance().getNewNode());
		file.addSeparator();
		file.add(ActionManager.getInstance().getRenameNode());
		file.add(ActionManager.getInstance().getDeleteNode());

		JMenu help = new JMenu("Help");
		JMenu about = new JMenu("About");
		
		JMenuItem aboutTim= new JMenuItem("Members");

		about.add(aboutTim);
		
		aboutTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutDialog dialog = new AboutDialog(MainFrame.getInstance());
                dialog.setVisible(true);
            }
        });
		
		this.add(file);
		this.add(help);
		this.add(about);

	}
}
