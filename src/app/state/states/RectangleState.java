package app.state.states;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import app.graphics.elements.PageElement;
import app.graphics.elements.shapes.RectangleElement;
import app.state.State;
import app.views.page.PageView;

/**
 * Created by Qwerasdzxc on 13/12/2019.
 */
public class RectangleState extends State {

    private PageView mediator;

    public RectangleState(PageView mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        Point2D position = (Point2D) e.getPoint().clone();

        if (e.getButton() == MouseEvent.BUTTON1) {
            PageElement element = RectangleElement.createDefault(position);

            mediator.getPage().addSlot(element);
        }
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
    }

    @Override
    public void onMouseReleased(MouseEvent e) {
    }
}
