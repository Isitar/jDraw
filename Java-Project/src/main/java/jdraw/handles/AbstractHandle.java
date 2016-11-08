package jdraw.handles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

public abstract class AbstractHandle implements FigureHandle {
    protected Figure owner;
    private final int RECTSIZE = 3;

    public AbstractHandle(Figure f) {
        owner = f;
    }

    @Override
    public Figure getOwner() {
        return owner;
    }

    @Override
    public void draw(Graphics g) {
        Point loc = getLocation();
        g.setColor(Color.WHITE);
        g.fillRect(loc.x - RECTSIZE, loc.y - RECTSIZE, 2 * RECTSIZE, 2 * RECTSIZE);
        g.setColor(Color.BLACK);
        g.drawRect(loc.x - RECTSIZE, loc.y - RECTSIZE, RECTSIZE * 2, RECTSIZE * 2);
    }

    @Override
    public boolean contains(int x, int y) {
        Point loc = getLocation();
        return (Math.abs(x - loc.x) < RECTSIZE) && (Math.abs(y - loc.y) < RECTSIZE);
        // return new Rectangle(loc.x - RECTSIZE, loc.y - RECTSIZE, RECTSIZE *
        // 2, RECTSIZE * 2).contains(x, y);
    }

    protected Point oldNW;

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle bounds = owner.getBounds();
        oldNW = bounds.getLocation();
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
    }
}
