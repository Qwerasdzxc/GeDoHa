package app.actions;

import app.models.Document;
import app.models.Project;
import app.views.MainFrame;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

public class ActNewProject extends GAbstractAction {

    public ActNewProject() {
        putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/filenew.png"));
        putValue(NAME, "Novi projekat");
        putValue(SHORT_DESCRIPTION, "Napravi novi projekat za workspace");
    }

    public void actionPerformed(ActionEvent arg0) {

        // TODO: Implementirati akciju pravljenja novog projekta

    }
}
