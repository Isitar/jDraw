/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import jdraw.framework.DrawContext;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @see jdraw.framework.Figure
 *
 * @author Pascal Lüscher
 */
public class RectTool extends AbstractDrawTool<Rect> {

	public RectTool(DrawContext context) {
		super(context);

	}

	@Override
	protected Rect CreateFigure(int x, int y) {
		return new Rect(x, y, 0, 0);
	}

	@Override
	public String getName() {

		return "Rectangle";
	}
}
