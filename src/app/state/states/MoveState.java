package app.state.states;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import app.commands.CommandManager;
import app.commands.MoveSlotsCommand;
import app.graphics.elements.PageElement;
import app.graphics.elements.PageShape;
import app.state.State;
import app.views.page.PageView;

import static app.Utilities.getShapeType;
import static app.Utilities.recreateElement;

public class MoveState extends State {

    private PageView mediator;

    private PageShape shape;

    private boolean dragging = true;

    private int originalX;
    private int originalY;
    private int dx = 0;
    private int dy = 0;

    private Point2D oldPoint;

    private boolean singleElement;

    public MoveState(PageView mediator) {
        this.mediator = mediator;
    }

    @Override
    public void onMousePressed(MouseEvent e) {
        dragging = true;
        oldPoint = (Point2D) e.getPoint().clone();
    }

    @Override
    public void onMouseDragged(MouseEvent e) {
        if (dragging && shape != null) {

            // Support for single element transformation.
            // That element doesn't need to be selected using the Lasso select.
            if (!singleElement) {
                if (mediator.getPage().getSelectionModel().getSelectionList().isEmpty()) {
                    mediator.getPage().getSelectionModel().addToSelectionList(shape);
                    singleElement = true;
                }
            }

            // TODO: Save first element's coordinates for dx and xy
//            originalX = (int) ((Point2D) shape.getPosition().clone()).getX();
//            originalY = (int) ((Point2D) shape.getPosition().clone()).getY();

            Point p = e.getPoint();

            dx = (int) p.getX() - (int) oldPoint.getX();
            dy = (int) p.getY() - (int) oldPoint.getY();

            oldPoint = (Point2D) e.getPoint().clone();

            ArrayList<PageElement> selectedElements = mediator.getPage().getSelectionModel().getSelectionList();
            ArrayList<PageShape> newElements = new ArrayList<>();

            for (PageElement element : selectedElements) {

                PageShape shape = (PageShape) element;

                PageShape newElement = recreateElement(shape.getId(),
                        new Point2D.Double(shape.getPosition().getX() + dx, shape.getPosition().getY() + dy),
                        new Dimension((int) shape.getSize().getWidth(), (int) shape.getSize().getHeight()), shape.getAngle(), getShapeType(shape)
                );

                newElements.add(newElement);
            }

            for (int i = 0; i < selectedElements.size(); i++) {
                mediator.getPage().removeSlot(selectedElements.get(i));
                mediator.getPage().addSlot(newElements.get(i));
            }

            mediator.getPage().getSelectionModel().removeAllFromSelectionList();

            // Add the overlapped element to the selected elements list:
            mediator.getPage().getSelectionModel().addShapesToSelectionList(newElements);
        }
    }

    @Override
    public void onMouseReleased(MouseEvent e) {

        CommandManager.getInstance().addCommand(
                new MoveSlotsCommand(mediator.getPage(),
                        (int) shape.getPosition().getX() - originalX,
                        (int) shape.getPosition().getY() - originalY)
        );

        if (singleElement) {
            // Remove the transformed element from the selected elements list:
            mediator.getPage().getSelectionModel().removeAllFromSelectionList();
            singleElement = false;
        }

        dragging = false;
        shape = null;
        dy = 0;
        dx = 0;
        originalX = 0;
        originalY = 0;
    }

    @Override
    public void onMouseMoved(MouseEvent e) {
        Point p = e.getPoint();

        shape = mediator.getOverlappedElement(p);

        if (mediator.getCursor() != Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR))
            mediator.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }
}
