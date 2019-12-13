package app.models.slot;

import java.awt.Dimension;
import java.awt.Point;

import app.graphics.painters.ElementPainter;
import app.models.page.Page;

/**
 * Created by Qwerasdzxc on 13/12/2019.
 */
public class Slot {

    private String name;

    public Slot() {}

    public Slot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
