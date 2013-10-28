package display;

import java.awt.Graphics;

/**
 * A line segment that will span a window of given width and height.
 * @author Sam Stokes
 */
public class ExtendedLine {
	private int startX, startY, endX, endY;
	private double ydiff, xdiff;
	private double m, c;		// gradient and intercept of line
	private int width, height;
	
	/**
	 * Create a line through (x1,y1) and (x2,y2) long enough to span
	 * a window of width w and height h.
	 */
	public ExtendedLine(int x1, int y1, int x2, int y2, int w, int h) {
		width = w; height = h;
		adjust(x1, y1, x2, y2);
	}
	
	/**
	 * Create a line long enough to span a window of width w
	 * and height h, satisfying the vector equation<br/>
	 * <tt><b>w</b>.<b>x</b> - <i>d</i> = 0</tt><br/>
	 * where <b>w</b> is the vector (wx, wy) and <i>d</i>
	 * is a scalar representing closest perpendicular distance
	 * from the origin.
	 */
	public ExtendedLine(double wx, double wy, double d, int w, int h) {
		width = w; height = h;
		adjust(wx, wy, d);
	}
	
	/**
	 * Readjust the line to pass through points (x1,y1) and (x2,y2).
	 */
	public void adjust(double x1, double y1, double x2, double y2) {
		ydiff = y1 - y2; xdiff = x1 - x2;
		if (Math.abs(ydiff) == 0 && Math.abs(xdiff) == 0)
			throw new IllegalArgumentException("(x1,y1) = (x2,y2), cannot " +
					"extrapolate line!");
		m = ydiff / xdiff;					// calc gradient
		startX = (int)x1; startY = (int)y1;
		while (startX > 0 && startY > 0) {	// extend start of line
			startX -= width;				// until off edge of window
			startY -= (m * width);
		}
		endX = (int)x2; endY = (int)y2;
		while (endX < width && endY < height	// and end of line too
				|| (int)startX == (int)endX && (int)startY == (int)endY) {
			endX += width;
			endY += (m * width);
		}
		c = -m * (double)startX + (double)startY;	// calc intercept
	}
	
	/**
	 * Readjust the line to satisfy the vector equation<br/>
	 * <tt><b>w</b>.<b>x</b> - <i>d</i> = 0</tt><br/>
	 * where <b>w</b> is the vector (wx, wy) and <i>d</i>
	 * is a scalar representing closest perpendicular distance
	 * from the origin.
	 */
	public void adjust(double wx, double wy, double d) {
		if (Math.abs(wx) == 0 && Math.abs(wy) == 0)
			throw new IllegalArgumentException("Direction vector vanishes!");
		double x1, y1, x2, y2;
		if (Math.abs(wx) == 0) {		// line is horizontal
			x1 = 0;
			y1 = y2 = -d / wy;
			x2 = width;
		} else if (Math.abs(wy) == 0) {	// line is vertical
			x1 = x2 = -d / wx;
			y1 = 0;
			y2 = width;
		} else if (Math.abs(d) == 0) {	// line is thru origin
			x1 = y1 = 0;
			x2 = width;
			y2 = -wx * x2 / wy;
		} else {
			x1 = 0; y2 = 0;
			y1 = -d / wy;
			x2 = -d / wx;
		}
		adjust(x1, y1, x2, y2);
	}
	
	/**
	 * Draws the line on the specified Graphics object.
	 */
	public void draw(Graphics g) {
		g.drawLine(startX, startY, endX, endY);
	}
	double wx, wy, d;
	/** Returns the x-component of the normal vector of the line. */
	public double getwx() {
		return -m;
	}
	/** Returns the y-component of the normal vector of the line. */
	public double getwy() {
		return 1.0;
	}
	/** Returns the closest approach of the line to the origin. */
	public double getd() {
		return c / Math.sqrt(m*m + 1);
	}
	
	/** Returns true iff (x,y) is above the line. */
	public boolean above(int x, int y) {
		return (((double)y) >= (m * (double)x + c));
	}
}
