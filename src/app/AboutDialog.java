package app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutDialog extends JDialog {

    public AboutDialog(JFrame parent) {
        super(parent, "About Dialog", true);

        Box b = Box.createVerticalBox();
        b.add(Box.createGlue());
        b.add(new JLabel("Članovi tima Grupa 202:"));
        b.add(new JLabel("Luka Petrović - RN 33/2018"));
        b.add(new JLabel("Matija Pleskonjić - RN 59/2018"));
        b.add(Box.createGlue());
        getContentPane().add(b, "Center");

        JPanel p2 = new JPanel();
        JButton ok = new JButton("Ok");
        p2.add(ok);
        getContentPane().add(p2, "South");

        this.setLocationRelativeTo(null);

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setVisible(false);
            }
        });

        setSize(250, 150);
    }
}
