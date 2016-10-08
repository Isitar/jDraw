package jdraw.figures;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;

public abstract class AbstractDrawTool<T extends ObservableFigure> implements DrawTool {

	/**
	 * the image resource path.
	 */
	private static final String IMAGES = "/images/";
	/**
	 * The context we use for drawing.
	 */
	protected DrawContext context;
	/**
	 * The context's view. This variable can be used as a shortcut, i.e. instead
	 * of calling context.getView().
	 */
	protected DrawView view;
	/**
	 * Temporary variable. During rectangle creation (during a mouse down -
	 * mouse drag - mouse up cycle) this variable refers to the new rectangle
	 * that is inserted.
	 */
	private T newFigure = null;

	private Point anchor = null;

	public AbstractDrawTool(DrawContext context) {
		super();
		this.context = context;
		this.view = context.getView();
	}

	/**
	 * Initializes a new Rectangle object by setting an anchor point where the
	 * mouse was pressed. A new Rectangle is then added to the model.
	 * 
	 * @param x
	 *            x-coordinate of mouse
	 * @param y
	 *            y-coordinate of mouse
	 * @param e
	 *            event containing additional information about which keys were
	 *            pressed.
	 * 
	 * @see jdraw.framework.DrawTool#mouseDown(int, int, MouseEvent)
	 */
	@Override
	public void mouseDown(int x, int y, MouseEvent e) {
		if (newFigure != null) {
			throw new IllegalStateException();
		}
		anchor = new Point(x, y);
		newFigure = CreateFigure(x, y);
		view.getModel().addFigure(newFigure);
	}

	protected abstract T CreateFigure(int x, int y);

	/**
	 * During a mouse drag, the Rectangle will be resized according to the mouse
	 * position. The status bar shows the current size.
	 * 
	 * @param x
	 *            x-coordinate of mouse
	 * @param y
	 *            y-coordinate of mouse
	 * @param e
	 *            event containing additional information about which keys were
	 *            pressed.
	 * 
	 * @see jdraw.framework.DrawTool#mouseDrag(int, int, MouseEvent)
	 */
	@Override
	public void mouseDrag(int x, int y, MouseEvent e) {
		newFigure.setBounds(anchor, new Point(x, y));
		java.awt.Rectangle r = newFigure.getBounds();
		this.context.showStatusText("w: " + r.width + ", h: " + r.height);
	}

	/**
	 * When the user releases the mouse, the Rectangle object is updated
	 * according to the color and fill status settings.
	 * 
	 * @param x
	 *            x-coordinate of mouse
	 * @param y
	 *            y-coordinate of mouse
	 * @param e
	 *            event containing additional information about which keys were
	 *            pressed.
	 * 
	 * @see jdraw.framework.DrawTool#mouseUp(int, int, MouseEvent)
	 */
	@Override
	public void mouseUp(int x, int y, MouseEvent e) {
		newFigure = null;
		anchor = null;
		this.context.showStatusText(getName() + " Mode");
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	}

	@Override
	public Icon getIcon() {

		Image img = null;
		try {
			img = ImageIO.read(getClass().getResourceAsStream(IMAGES + getName() + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ImageIcon(img.getScaledInstance(25, 25, 0));

	}

	@Override
	public abstract String getName();

	@Override
	public void activate() {
		this.context.showStatusText(getName() + " Mode");

	}

	@Override
	public void deactivate() {
		this.context.showStatusText("");
	}
}