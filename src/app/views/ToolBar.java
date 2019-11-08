package app.views;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import app.actions.ActionManager;

public class ToolBar extends JToolBar{
	
	public ToolBar() {
		super(SwingConstants.VERTICAL);
		
		setFloatable(false);
		
        ImageIcon buttonIcon = null;
		try {
            buttonIcon = new ImageIcon(this.getClass().getResource("/images/document.png"));
            Image image = buttonIcon.getImage(); // transform it
            Image newimg = image.getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            buttonIcon = new ImageIcon(newimg);
        } catch (Exception e) {
            e.printStackTrace();
        }

		 
        if (buttonIcon != null)
            this.add(new JButton("", buttonIcon));
		
	}
}
