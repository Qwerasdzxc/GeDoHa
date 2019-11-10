package app.views;

import javax.swing.*;
import java.awt.*;

import app.models.workspace.Workspace;
import app.views.hierarchy.HierarchyModel;
import app.views.hierarchy.HierarchyTree;
import app.actions.ActionManager;
import app.observer.IListener;
import app.views.miscellaneous.MenuBar;
import app.views.miscellaneous.ToolBar;
import app.views.workspace.WorkspaceView;

public class MainFrame extends JFrame {

    private static MainFrame instance = null;
    private ActionManager actionManager;

    private WorkspaceView workspaceView;

    private HierarchyModel hierarchyModel;
    private HierarchyTree hierarchyTree;
    
    private JDesktopPane desktop;

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

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("GeRuDok");

        this.setLocationRelativeTo(null);

        MenuBar menuBar= new MenuBar();
        this.setJMenuBar(menuBar);

        ToolBar toolBar = new ToolBar();
        this.add(toolBar, BorderLayout.NORTH);

        initialiseWorkspaceTree();

        JScrollPane scroll = new JScrollPane(hierarchyTree);
        scroll.setMinimumSize(new Dimension(200,150));
        JSplitPane sl = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, workspaceView);
        sl.setOneTouchExpandable(true);
        sl.setResizeWeight(0.1);

        this.getContentPane().add(sl, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public HierarchyTree getHierarchyTree() {
		return hierarchyTree;
	}
    
    private void initialiseWorkspaceTree() {
        this.workspaceView = new WorkspaceView();

        Workspace workspace = new Workspace();
        workspace.addObserver(workspaceView);

        this.hierarchyTree = new HierarchyTree();
        this.hierarchyModel = new HierarchyModel(workspace);
        this.hierarchyTree.setModel(hierarchyModel);
    }
}
