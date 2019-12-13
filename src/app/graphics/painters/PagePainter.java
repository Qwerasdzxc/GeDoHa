package app.graphics.painters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

import app.graphics.elements.PageElement;
import app.graphics.elements.PageShape;

/**
 * Created by Qwerasdzxc on 13/12/2019.
 */
public class PagePainter extends ElementPainter {

    protected Shape shape;

    public PagePainter(PageElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, PageElement element) {
        g.setPaint(Color.RED);

        g.setStroke(element.getStroke());
        g.draw(getShape());
        g.setPaint(element.getPaint());

        g.fill(getShape());

        if (element instanceof PageShape) {
            g.setPaint(Color.BLACK);
            PageShape device = (PageShape) element;
            g.drawString("Page element", (int) device.getPosition().getX() + 10,
                    (int) device.getPosition().getY() + 10);
        }
    }

    @Override
    public boolean elementAt(PageElement element, Point pos) {
        return getShape().contains(pos);
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
