/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

/**
 * Represents lines in JDraw.
 * 
 * @author Christoph Denzler, Pascal Lüscher
 *
 */
@SuppressWarnings("serial")
public class Line extends ObservableFigure implements Figure {

    /**
     * Use the java.awt.Line in order to save/reuse code.
     */
    private Point startingPoint;
    private Point endingPoint;

    /**
     * Create a new rectangle of the given dimension.
     * 
     * @param x the x-coordinate of the upper left corner of the rectangle
     * @param y the y-coordinate of the upper left corner of the rectangle
     * @param w the rectangle's width
     * @param h the rectangle's height
     */
    public Line(Point startingPoint, Point endingPoint) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
    }

    /**
     * Draw the rectangle to the given graphics context.
     * 
     * @param g the graphics context to use for drawing.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(startingPoint.x, startingPoint.y, endingPoint.x, endingPoint.y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        startingPoint = origin;
        endingPoint = corner;
        NotifyListeners();
    }

    @Override
    public void move(int dx, int dy) {
        if ((dx == 0) && (dy == 0))
            return;
        startingPoint.x += dx;
        startingPoint.y += dy;
        endingPoint.x += dx;
        endingPoint.y += dy;
        NotifyListeners();

    }

    @Override
    public boolean contains(int x, int y) {
        return false;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(startingPoint.x, startingPoint.y, endingPoint.x - startingPoint.x,
                endingPoint.y - startingPoint.y);
    }

    @Override
    public List<FigureHandle> getHandles() {
        return super.getHandles();
    }

    @Override
    public Figure clone() {
        return null;
    }

}
