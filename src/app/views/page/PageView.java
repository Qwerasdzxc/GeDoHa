package app.views.page;

import app.models.page.Page;
import app.models.page.PageListener;

import javax.swing.*;
import java.awt.*;

public class PageView extends JPanel implements PageListener {

    private Page page;

    public PageView(Page page) {
        this.page = page;

        setLayout(new BorderLayout());
        setBackground(Color.gray);
        add(new Label(page.getName()));

        setVisible(true);
    }

    @Override
    public void onPageSelected(Page page) {

    }
}
