package app.actions;

import javax.swing.JOptionPane;

import app.views.MainFrame;

public class ErrorHandler {

    public static void showFileError() {
        JOptionPane.showMessageDialog(MainFrame.getInstance(), "Greška u otvaranju izabranog file-a.");
    }

    public static void showRenameError() {
        JOptionPane.showMessageDialog(null, "Naziv ne može biti prazan!",
                "Greška", JOptionPane.ERROR_MESSAGE);
    }
}
