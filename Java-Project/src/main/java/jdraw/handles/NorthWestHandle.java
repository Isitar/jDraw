package jdraw.handles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public class NorthWestHandle extends AbstractHandle {

    public NorthWestHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        return owner.getBounds().getLocation();
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        int newHeight = (int) (owner.getBounds().getHeight() + (oldNW.y - y));
        int newWidth = (int) (owner.getBounds().getWidth() + oldNW.x - x);
        owner.setBounds(new Point(x, y), new Point(x + newWidth, y + newHeight));
        oldNW = owner.getBounds().getLocation();
    }
}
