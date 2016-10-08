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
public class PVLogoTool extends AbstractDrawTool<PVLogo> {

	public PVLogoTool(DrawContext context) {
		super(context);
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
