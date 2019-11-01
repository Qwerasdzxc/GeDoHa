package app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {

    private static MainFrame instance = null;

    private MainFrame() {
        initialize();
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    private void initialize() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("GeRuDok");

        this.setLocationRelativeTo(null);

        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);

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
            toolbar.add(new JButton("", buttonIcon));

        Container contentPane = this.getContentPane();
        contentPane.add(toolbar, BorderLayout.NORTH);

        //Where the GUI is created:
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("Menu");
        menuBar.add(menu);

        menuItem = new JMenuItem("File");
        menu.add(menuItem);

        menuItem = new JMenuItem("About");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutDialog dialog = new AboutDialog(MainFrame.this);
                dialog.setVisible(true);
            }
        });
        menu.add(menuItem);

        this.setJMenuBar(menuBar);

        // create a panel
        JPanel p1 = new JPanel();
        JPanel p = new JPanel();

        // create text areas
        JTextArea t1 = new JTextArea(10, 10);
        JTextArea t2 = new JTextArea(10, 10);

        // set texts
        t1.setText("this is first text area");
        t2.setText("this is second text area");

        // add text area to panel
        p1.add(t1);
        p.add(t2);

        // create a splitpane
        JSplitPane sl = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, p1, p);
        sl.setOneTouchExpandable(true);
        sl.setResizeWeight(0.5);

        // add panel
        this.getContentPane().add(sl, BorderLayout.CENTER);

        this.setVisible(true);
    }

}
