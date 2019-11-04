package app;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class MyToolBar extends JToolBar{
	
	public MyToolBar() {
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
		
		/*JButton btnDoc = new JButton();
		btnDoc.setToolTipText("Document");
		btnDoc.setIcon(new ImageIcon("/images/open_22x22.jpg"));
		this.add(btnDoc);*/
	}
}
