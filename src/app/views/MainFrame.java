package app.views;

import javax.swing.*;
import java.awt.*;

import app.views.hierarchy.HierarchyModel;
import app.views.hierarchy.HierarchyTree;
import app.actions.ActionManager;
import app.observer.IListener;

public class MainFrame extends JFrame implements IListener {

    private static MainFrame instance = null;
    private ActionManager actionManager;

    private HierarchyModel hierarchyModel;
    private HierarchyTree hierarchyTree;
    
    private JDesktopPane desktop;

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

        MenuBar menuBar= new MenuBar();
        this.setJMenuBar(menuBar);

        ToolBar toolBar = new ToolBar();
        add(toolBar, BorderLayout.NORTH);
        
		
        // create a panel
        //JPanel p = new JPanel();

		desktop=new JDesktopPane();

        // create text areas
        JTextArea t1 = new JTextArea(10, 10);

        // set text
        //t1.setText("this is a text area");

        // add text area to panel
        //p.add(t1);

        // create a splitpane
        JScrollPane scroll=new JScrollPane(hierarchyTree);
        scroll.setMinimumSize(new Dimension(200,150));
        JSplitPane sl = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, desktop);
        sl.setOneTouchExpandable(true);
        sl.setResizeWeight(0.1);

        // add panel
        this.getContentPane().add(sl, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public HierarchyTree getHierarchyTree() {
		return hierarchyTree;
	}
    
    private void initialiseWorkspaceTree(){
        this.hierarchyTree = new HierarchyTree();
        this.hierarchyModel = new HierarchyModel();
        this.hierarchyTree.setModel(hierarchyModel);
    }

    @Override
    public void update(Object event) {

    }
}
