package app;

import javax.swing.*;
import java.awt.*;

import app.hierarchy.HierarchyModel;
import app.hierarchy.HierarchyTree;

public class MainFrame extends JFrame {

    private static MainFrame instance = null;

    private HierarchyModel hierarchyModel;
    private HierarchyTree hierarchyTree;
    
    private MainFrame() {

        initialiseWorkspaceTree();
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

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("GeRuDok");

        this.setLocationRelativeTo(null);

        MyMenuBar menuBar= new MyMenuBar();
        this.setJMenuBar(menuBar);

        MyToolBar toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);
        
		
        // create a panel
        JPanel p = new JPanel();

        // create text areas
        JTextArea t1 = new JTextArea(10, 10);

        // set text
        t1.setText("this is a text area");

        // add text area to panel
        p.add(t1);

        // create a splitpane
        JScrollPane scroll=new JScrollPane(hierarchyTree);
        scroll.setMinimumSize(new Dimension(200,150));
        JSplitPane sl = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, p);
        sl.setOneTouchExpandable(true);
        sl.setResizeWeight(0.1);

        // add panel
        this.getContentPane().add(sl, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void initialiseWorkspaceTree(){
        this.hierarchyTree = new HierarchyTree();
        this.hierarchyModel = new HierarchyModel();
        this.hierarchyTree.setModel(hierarchyModel);
    }
}
