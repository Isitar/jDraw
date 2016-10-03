package jdraw.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

@SuppressWarnings("serial")
public abstract class ObservableFigure implements Figure {

	private Set<FigureListener> listeners;

	public ObservableFigure() {
		listeners = new HashSet<>();
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
	public abstract List<FigureHandle> getHandles();

	@Override
	public void addFigureListener(FigureListener listener) {
		listeners.add(listener);

	}

	@Override
	public void removeFigureListener(FigureListener listener) {
		listeners.remove(listener);
	}

	protected void NotifyListeners() {
		listeners.forEach(l -> l.figureChanged(new FigureEvent(this)));
	}

	@Override
	public abstract Figure clone();

}
