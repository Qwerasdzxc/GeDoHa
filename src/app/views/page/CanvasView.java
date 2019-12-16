package app.views.page;

import app.graphics.elements.PageElement;
import app.graphics.elements.PageShape;
import app.graphics.painters.ElementPainter;
import app.models.page.Page;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class CanvasView extends JPanel {

    private Page page;

    public CanvasView(Page page) {
        super();

        this.page = page;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Iterator<PageElement> it = page.getSlotsIterator();
        while(it.hasNext()){
            g2 = (Graphics2D) g.create();
            PageElement element = it.next();
            PageShape shape = (PageShape) element;
            g2.rotate(Math.toRadians(-shape.getAngle()), shape.getPosition().getX() + shape.getSize().width / 2.0, shape.getPosition().getY() + shape.getSize().height / 2.0);
            ElementPainter painter = element.getElementPainter();
            painter.paint(g2, element);
            g2.dispose();
        }
    }
}
