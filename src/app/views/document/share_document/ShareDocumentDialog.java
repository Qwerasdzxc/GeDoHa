package app.views.document.share_document;

import app.models.AbstractNode;
import app.models.document.Document;
import app.models.project.Project;
import app.views.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

public class ShareDocumentDialog extends JDialog {

    private Document shared;
    private JButton btnOk;
    private JList<Project> projects;

    public ShareDocumentDialog(Document shared) {
        super(MainFrame.getInstance(), "Izaberite projekat", ModalityType.APPLICATION_MODAL,
                MainFrame.getInstance().getGraphicsConfiguration());

        this.shared = shared;

        this.setLayout(new BorderLayout());

        JLabel lblWelcome = new JLabel("Izaberite projekat u koji će biti ubačen podeljeni dokument:");
        lblWelcome.setBorder(new EmptyBorder(10, 10, 10, 10));
        lblWelcome.setAlignmentY(CENTER_ALIGNMENT);
        this.add(lblWelcome, BorderLayout.NORTH);

        DefaultListModel<Project> listModel = new DefaultListModel<>();
        projects = new JList<Project>(listModel);
        this.add(projects, BorderLayout.CENTER);

        for (AbstractNode node : MainFrame.getInstance().getWorkspace().getChildren()) {
            Project project = (Project) node;

            if (!shared.getParents().contains(project))
                listModel.addElement(project);
        }

        btnOk = new JButton("Podeli sa izabranim projektom");
        btnOk.setEnabled(false);
        this.add(btnOk, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(500, 500));
        pack();
        setLocationRelativeTo(null);

        ShareDocumentDialogController controller = new ShareDocumentDialogController(this);

        this.setVisible(true);
    }

    public void enableBtnOk() {
        this.btnOk.setEnabled(projects.getSelectedValuesList().size() != 0);
    }

    public List<Project> getSelected() {
        return this.projects.getSelectedValuesList();
    }

    public Document getShared() {
        return this.shared;
    }

    public void addSelectionChangedListener(ListSelectionListener listener) {
        this.projects.addListSelectionListener(listener);
    }

    public void addBtnOkListener(ActionListener listener) {
        this.btnOk.addActionListener(listener);
    }

    public void addDoubleClickListener(MouseListener listener) {
        this.projects.addMouseListener(listener);
    }
}