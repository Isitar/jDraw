package jdraw.figures;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

@SuppressWarnings("serial")
public class PVLogo extends ObservableFigure implements Figure {

	private Image img = null;
	private Rectangle bounds;

	/**
	 * the image resource path.
	 */
	private static final String IMAGES = "/images/";

	public PVLogo(int x, int y, int w, int h) {
		bounds = new Rectangle(x, y, w, h);
		try {
			img = ImageIO.read(getClass().getResourceAsStream(IMAGES + "PVLogo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, bounds.x, bounds.y, bounds.width, bounds.height, null);

	}

	@Override
	public void move(int dx, int dy) {
		if ((dx == 0) && (dy == 0))
			return;

		bounds.setLocation(bounds.x + dx, bounds.y + dy);
		NotifyListeners();

	}

	@Override
	public boolean contains(int x, int y) {
		return bounds.contains(x, y);

	}

	@Override
	public void setBounds(Point origin, Point corner) {
		bounds.setFrameFromDiagonal(origin, corner);
		NotifyListeners();
	}

	@Override
	public Rectangle getBounds() {

		return bounds;
	}

	@Override
	public List<FigureHandle> getHandles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Figure clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
