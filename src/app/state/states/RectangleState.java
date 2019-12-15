package app.state.states;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;

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
        Point position = e.getPoint();

        if (e.getButton() == MouseEvent.BUTTON1) {
            PageElement element = new RectangleElement(
                    position,
                    new Dimension(100, 50),
                    new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL),
                    new Color(0,0,0,0),
                    Color.RED
            );
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
