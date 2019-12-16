package app.actions;

import app.models.AbstractNode;
import app.models.workspace.Workspace;
import app.views.MainFrame;
import app.views.workspace.WorkspaceView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ActSwitchWorkspace extends GAbstractAction {

    public ActSwitchWorkspace() {
        putValue(SMALL_ICON, loadIcon("images/switch_workspace.png"));
        putValue(NAME, "Zameni radno okruženje");
        putValue(SHORT_DESCRIPTION, "Zameni trenutno otvoreno radno okruženje");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser(new File(System.getProperty("user.dir")));

        if (jfc.showOpenDialog(MainFrame.getInstance()) != JFileChooser.APPROVE_OPTION)
            return;

        File selected = jfc.getSelectedFile();

        try {
            ObjectInputStream os = new ObjectInputStream(new FileInputStream(selected));
            Workspace ws = null;

            try {
                ws = (Workspace) os.readObject();
                ws.setFile(selected);
            } catch (ClassNotFoundException ee) {
                JOptionPane.showMessageDialog(MainFrame.getInstance(), "Greška u otvaranju izabranog file-a.");
            }

            for (AbstractNode proj : ws.getChildren()) {
                proj.setParent(ws);

                for (AbstractNode doc : proj.getChildren()) {
                    doc.setParent(proj);

                    for (AbstractNode page : doc.getChildren()) {
                        page.setParent(doc);
                    }
                }
            }

            WorkspaceView wsView = new WorkspaceView(ws);

            MainFrame.getInstance().getHierarchyTree().switchWorkspace(ws);
            MainFrame.getInstance().setWorkspaceView(wsView);

            os.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
