package jdraw.handles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public class SouthWestHandle extends AbstractHandle {

    public SouthWestHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = owner.getBounds();
        return new Point(bounds.getLocation().x, (int) (bounds.getLocation().y + bounds.getHeight()));
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        int newHeight = (int) (y - oldNW.y);
        int newWidth = (int) (owner.getBounds().getWidth() + oldNW.x - x);
        owner.setBounds(new Point(x, y - newHeight), new Point(x + newWidth, y));
        oldNW = owner.getBounds().getLocation();
    }
}
