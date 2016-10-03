/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.std;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import jdraw.framework.DrawCommandHandler;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawModelEvent;
import jdraw.framework.DrawModelListener;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;

/**
 * Provide a standard behavior for the drawing model. This class initially does
 * not implement the methods in a proper way. It is part of the course
 * assignments to do so.
 * 
 * @author TODO Pascal Lüscher
 *
 */
public class StdDrawModel implements DrawModel, FigureListener {
	private ArrayList<Figure> figures;
	private Set<DrawModelListener> listeners;

	public StdDrawModel() {
		figures = new ArrayList<>();
		listeners = new HashSet<>();
	}

	@Override
	public void addFigure(Figure f) {
		figures.add(f);
		f.addFigureListener(this);

		notifyListeners(new DrawModelEvent(this, f, DrawModelEvent.Type.FIGURE_ADDED));
	}

	@Override
	public Iterable<Figure> getFigures() {
		return figures;
	}

	@Override
	public void removeFigure(Figure f) {
		figures.remove(f);
		f.removeFigureListener(this);
		notifyListeners(new DrawModelEvent(this, f, DrawModelEvent.Type.FIGURE_REMOVED));
	}

	@Override
	public void addModelChangeListener(DrawModelListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeModelChangeListener(DrawModelListener listener) {
		listeners.remove(listener);
	}

	/**
	 * The draw command handler. Initialized here with a dummy implementation.
	 */
	// TODO initialize with your implementation of the undo/redo-assignment.
	private DrawCommandHandler handler = new EmptyDrawCommandHandler();

	/**
	 * Retrieve the draw command handler in use.
	 * 
	 * @return the draw command handler.
	 */
	@Override
	public DrawCommandHandler getDrawCommandHandler() {

		return handler;
	}

	@Override
	public void setFigureIndex(Figure f, int index) {
		figures.remove(f);
		figures.add(index, f);
	}

	@Override
	public void removeAllFigures() {
		figures.forEach(f -> f.removeFigureListener(this));
		figures.clear();
	}

	@Override
	public void figureChanged(FigureEvent e) {
		DrawModelEvent dme = new DrawModelEvent(this, e.getFigure(), DrawModelEvent.Type.FIGURE_CHANGED);
		notifyListeners(dme);
	}

	protected void notifyListeners(DrawModelEvent dme) {
		listeners.forEach(l -> l.modelChanged(dme));
	}

}
