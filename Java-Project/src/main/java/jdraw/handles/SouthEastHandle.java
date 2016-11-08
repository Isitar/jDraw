package jdraw.handles;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

public class SouthEastHandle extends AbstractHandle {

    public SouthEastHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Rectangle bounds = owner.getBounds();
        return new Point((int) (bounds.getLocation().x + bounds.getWidth()),
                (int) (bounds.getLocation().y + bounds.getHeight()));
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        owner.setBounds(oldNW, new Point(x, y));
        oldNW = owner.getBounds().getLocation();
    }
}
