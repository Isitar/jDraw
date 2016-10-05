/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import java.awt.Point;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @see jdraw.framework.Figure
 *
 * @author Christoph Denzler
 */
public class PVLogoTool extends AbstractDrawTool<PVLogo> {

	/**
	 * Create a new rectangle tool for the given context.
	 * 
	 * @param context
	 *            a context to use this tool in.
	 */
	public PVLogoTool(DrawContext context) {
		this.context = context;
		this.view = context.getView();
	}

	/**
	 * Deactivates the current mode by resetting the cursor and clearing the
	 * status bar.
	 * 
	 * @see jdraw.framework.DrawTool#deactivate()
	 */
	@Override
	public void deactivate() {
		this.context.showStatusText("");
	}

	/**
	 * Activates the Rectangle Mode. There will be a specific menu added to the
	 * menu bar that provides settings for Rectangle attributes
	 */
	@Override
	public void activate() {
		this.context.showStatusText("Rectangle Mode");
	}

	@Override
	protected PVLogo CreateFigure(int x, int y) {

		return new PVLogo(x, y, 0, 0);
	}

	@Override
	public String getName() {

		return "PVLogo";
	}

}
