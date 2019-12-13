package app.graphics.painters;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;

import app.graphics.elements.PageElement;

/**
 * Created by Qwerasdzxc on 13/12/2019.
 */
public abstract class ElementPainter implements Serializable {

    public ElementPainter(PageElement element) {	}

    public abstract void paint(Graphics2D g, PageElement element);

    public abstract boolean elementAt(PageElement element, Point pos);

}
