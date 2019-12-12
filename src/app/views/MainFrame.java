package app.views;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import app.models.workspace.Workspace;
import app.views.hierarchy.HierarchyTree;
import app.actions.ActionManager;
import app.views.miscellaneous.MenuBar;
import app.views.miscellaneous.ToolBar;
import app.views.workspace.WorkspaceView;

public class MainFrame extends JFrame {

    private static MainFrame instance = null;
    private ActionManager actionManager;

    private WorkspaceView workspaceView;

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
        setSize((int) (screenWidth / 1.5), (int) (screenHeight / 1.5));

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

        addWindowListener(new WindowListener() {
            @Override
            public void windowClosed(WindowEvent e) {}
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {
                MainFrame frame = (MainFrame) e.getComponent();
                int result = JOptionPane.showConfirmDialog(frame, "Da li ste sigurni da želite da ugasite program?",
                        "Gašenje programa", JOptionPane.YES_NO_OPTION);
                if (result != JOptionPane.YES_OPTION) {
                    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                } else {
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });

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
        this.hierarchyTree.setModel(new DefaultTreeModel(workspace));
    }
}
