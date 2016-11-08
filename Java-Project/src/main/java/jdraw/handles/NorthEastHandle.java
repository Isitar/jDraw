package jdraw.handles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public class NorthEastHandle extends AbstractHandle {
    public NorthEastHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = owner.getBounds();
        return new Point((int) (bounds.getLocation().x + bounds.getWidth()), bounds.getLocation().y);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        int newHeight = (int) (owner.getBounds().getHeight() + (oldNW.y - y));
        owner.setBounds(new Point(oldNW.x, y), new Point(x, y + newHeight));
        oldNW = owner.getBounds().getLocation();
    }
}
