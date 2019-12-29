package app.models.element_selection;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultSingleSelectionModel;

import app.graphics.elements.PageElement;
import app.graphics.elements.PageShape;
import app.graphics.elements.shapes.CircleElement;
import app.graphics.elements.shapes.RectangleElement;
import app.graphics.elements.shapes.TriangleElement;
import app.models.page.PageListener;

/**
 * Created by Qwerasdzxc on 29/12/2019.
 */
public class ElementSelectionModel extends DefaultSingleSelectionModel implements ElementSelectionObserver {

    private transient List<ElementSelectionListener> listeners;

    private ArrayList<PageElement> selectionList = new ArrayList<>();

    public void addToSelectionList(PageElement element) {
        // Setting the "selected element" color:
        element.setColor(Color.ORANGE);
        selectionList.add(element);

        notifySelectionChanged();
    }

    public void addToSelectionList(ArrayList<PageShape> elements) {
        // Setting the "selected element" color:
        for (PageElement el : elements)
            el.setColor(Color.ORANGE);

        selectionList.addAll(elements);

        notifySelectionChanged();
    }

    public int getSelectionListSize() {
        return selectionList.size();
    }

    public PageElement getElementFromSelectionListAt(int index) {
        return selectionList.get(index);
    }

    public int getIndexByObject(PageElement element) {
        return selectionList.indexOf(element);
    }

    public void removeFromSelectionList(PageElement element) {
        // Returning the default color:
        element.setColor(Color.BLACK);
        selectionList.remove(element);

        notifySelectionChanged();
    }

    public void removeAllFromSelectionList() {
        // Returning the default color:
        for (PageElement el : selectionList)
            el.setColor(Color.BLACK);

        selectionList.clear();

        notifySelectionChanged();
    }

    public ArrayList<PageElement> getSelectionList() {
        return selectionList;
    }

    public Iterator<PageElement> getSelectionListIterator() {
        return selectionList.iterator();
    }

    public boolean isElementSelected(PageElement element) {
        return selectionList.contains(element);
    }

    public void selectElements(Rectangle2D rec, ArrayList<PageElement> elements) {
        for (PageElement element : elements) {
            PageShape shape = (PageShape) element;
            if (rec.intersects(shape.getPosition().getX(), shape.getPosition().getY(),
                    shape.getSize().getWidth(), shape.getSize().getHeight())) {
                if (!isElementSelected(shape))
                    addToSelectionList(shape);
            }
//            if (element instanceof RectangleElement) {
//                RectangleElement el = (RectangleElement) element;
//                if (rec.intersects(el.getPosition().getX(), el.getPosition().getY(),
//                        el.getSize().getWidth(), el.getSize().getHeight())) {
//                    if (!isElementSelected(el))
//                        selectionList.add(el);
//                }
//            } else if (element instanceof CircleElement) {
//                CircleElement el = (CircleElement) element;
//                if (rec.intersects(el.getPosition().getX(), el.getPosition().getY(),
//                        el.getSize().getWidth(), link.getSize().getHeight())) {
//                    if (!isElementSelected(el))
//                        selectionList.add(el);
//                }
//            } else {
//                TriangleElement link = (TriangleElement) element;
//                if (rec.intersects(link.getPosition().getX(), link.getPosition().getY(),
//                        link.getSize().getWidth(), link.getSize().getHeight())) {
//                    if (!isElementSelected(link))
//                        selectionList.add(link);
//                }
//            }
        }
    }

    @Override
    public void addObserver(ElementSelectionListener listener) {
        if(listener == null)
            return;
        if(this.listeners ==null)
            this.listeners = new ArrayList<>();
        if(this.listeners.contains(listener))
            return;
        this.listeners.add(listener);
    }

    @Override
    public void removeObserver(ElementSelectionListener listener) {
        if(listener == null || this.listeners == null)
            return;
        this.listeners.remove(listener);
    }

    @Override
    public void notifySelectionChanged() {
        if (this.listeners == null)
            return;

        for (int i = 0; i < listeners.size(); i++)
            listeners.get(i).onSelectionChanged();
    }
}
