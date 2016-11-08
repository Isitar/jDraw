package jdraw.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;
import jdraw.handles.NorthEastHandle;
import jdraw.handles.NorthWestHandle;
import jdraw.handles.SouthEastHandle;
import jdraw.handles.SouthWestHandle;

@SuppressWarnings("serial")
public abstract class ObservableFigure implements Figure {

    private Set<FigureListener> listeners;

    List<FigureHandle> handles;

    public ObservableFigure() {
        listeners = new HashSet<>();

        handles = new LinkedList<>();
        handles.add(new NorthEastHandle(this));
        handles.add(new NorthWestHandle(this));
        handles.add(new SouthWestHandle(this));
        handles.add(new SouthEastHandle(this));
    }

    @Override
    public abstract void draw(Graphics g);

    @Override
    public abstract void move(int dx, int dy);

    @Override
    public abstract boolean contains(int x, int y);

    @Override
    public abstract void setBounds(Point origin, Point corner);

    @Override
    public abstract Rectangle getBounds();

    @Override
    public void addFigureListener(FigureListener listener) {
        listeners.add(listener);

    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        listeners.remove(listener);
    }

    protected void NotifyListeners() {
        // copy needed because concurrent Exception may occur.
        Set<FigureListener> listenersCopy = new HashSet<>(listeners);
        listenersCopy.forEach(l -> l.figureChanged(new FigureEvent(this)));
    }

    @Override
    public abstract Figure clone();

    @Override
    public List<FigureHandle> getHandles() {
        return handles;
    }
}
