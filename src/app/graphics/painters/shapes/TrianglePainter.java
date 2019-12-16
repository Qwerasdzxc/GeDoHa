package app.graphics.painters.shapes;

import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import app.graphics.elements.PageElement;
import app.graphics.elements.shapes.RectangleElement;
import app.graphics.elements.shapes.TriangleElement;
import app.graphics.painters.PagePainter;

/**
 * Created by Qwerasdzxc on 17/12/2019.
 */
public class TrianglePainter extends PagePainter {

    public TrianglePainter(PageElement element) {
        super(element);

        TriangleElement triangle = (TriangleElement) element;

        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(triangle.getPosition().getX(), triangle.getPosition().getY());

        ((GeneralPath) shape).lineTo(triangle.getPosition().getX() + triangle.getSize().getWidth(), triangle.getPosition().getY());

        ((GeneralPath) shape).lineTo((triangle.getPosition().getX() + triangle.getSize().getWidth() / 2), triangle.getPosition().getY() - triangle.getSize().getHeight());

        ((GeneralPath) shape).closePath();
    }
}
