package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyMenuBar extends JMenuBar{
	
	public MyMenuBar() {
		JMenu file= new JMenu("File");
		JMenu help= new JMenu("Help");
		JMenu about= new JMenu("About");
		
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
