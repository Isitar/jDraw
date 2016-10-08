/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import java.awt.Point;
import jdraw.framework.DrawContext;

/**
 * This tool defines a mode for drawing lines.
 *
 * @see jdraw.framework.Figure
 *
 * @author Pascal Lüscher
 */
public class LineTool extends AbstractDrawTool<Line> {

	public LineTool(DrawContext context) {
		super(context);

	}

	@Override
	protected Line CreateFigure(int x, int y) {

		return new Line(new Point(x, y), new Point(x, y));
	}

	@Override
	public String getName() {
		return "Line";
	}
}
