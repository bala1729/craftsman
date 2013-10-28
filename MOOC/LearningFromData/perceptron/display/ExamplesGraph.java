package display;

import java.awt.*;
import uk.co.ss496.util.Console;
import javax.swing.JPanel;

/**
 * Component to display the set of training examples.
 * @author Sam Stokes
 */
public class ExamplesGraph extends JPanel {
	private final static int POINT_R = 2;	// radius of the crosses and boxes
	protected int width, height;			// width, height of graph area
	protected int [] xs;
	protected int [] ys;
	protected boolean [] cs;				// labels
	
	/**
	 * Create a new graph.
	 * @param w  width of the graph area.
	 * @param h  height of the graph area.
	 * @param xs x-coords of the points to plot.
	 * @param ys y-coords of the points to plot.
	 * @param cs labels of the points to plot.
	 */
	public ExamplesGraph(int w, int h, int [] xs, int [] ys, boolean [] cs) {
		super();
		width = w; height = h;
		Dimension d = new Dimension(w,h);
		setPreferredSize(d);
		init(xs, ys, cs);
	}
	
	/**
	 * Initialise the graph.
	 * @param xs x-coords of the points to plot.
	 * @param ys y-coords of the points to plot.
	 * @param cs labels of the points to plot.
	 */
	public void init(int [] xs, int [] ys, boolean [] cs) {
		if (!(xs.length == ys.length && ys.length == cs.length))
			throw new IllegalArgumentException("Arrays are of different lengths!");
		this.xs = xs; this.ys = ys; this.cs = cs;
	}

	/* Plot point (x,y) as a cross. */
	private void drawX(Graphics g, int x, int y) {
		g.drawLine(x - POINT_R, y - POINT_R, x + POINT_R, y + POINT_R);
		g.drawLine(x - POINT_R, y + POINT_R, x + POINT_R, y - POINT_R);
	}
	/* Plot point (x,y) as a box. */
	private void drawO(Graphics g, int x, int y) {
		g.drawLine(x - POINT_R, y - POINT_R, x + POINT_R, y - POINT_R);
		g.drawLine(x + POINT_R, y - POINT_R, x + POINT_R, y + POINT_R);
		g.drawLine(x + POINT_R, y + POINT_R, x - POINT_R, y + POINT_R);
		g.drawLine(x - POINT_R, y + POINT_R, x - POINT_R, y - POINT_R);
		g.drawRect(x, y, 0, 0);
	}
	
	public void paint(Graphics g) {
		for (int i=0; i<cs.length; i++) {
			if (cs[i]) {
				g.setColor(Color.GRAY);
				drawX(g, xs[i], ys[i]);
			} else {
				g.setColor(Color.DARK_GRAY);
				drawO(g, xs[i], ys[i]);
			}
		}
	}
	
	/**	Generates a random test training set - for main(). */
	protected static void genTestData(int w, int h, int[] xs, int[] ys, boolean[] cs) {
		if (xs.length != ys.length || ys.length != cs.length)
			throw new IllegalArgumentException("Arrays are of different lengths!");
		int n = xs.length;
		uk.co.ss496.util.test.IntGenerator ig = new uk.co.ss496.util.test.RandIntGenerator(w);
		int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
		while (x1 == x2 && y1 == y2) {
			x1 = ig.next();
			y1 = ig.next();
			x2 = ig.next();
			y2 = ig.next();
		}
		ExtendedLine l = new ExtendedLine(x1, y1, x2, y2, w, h);
		for (int i=0; i<n; i++) {
			xs[i] = ig.next();
			ys[i] = ig.next();
			cs[i] = l.above(xs[i], ys[i]);
		}
	}
	
	/** Test code - plots some random points. */
	public static void main(String[] args) {
		int NUM = 100, WIDTH = 400, HEIGHT = 400;
		int [] xs = new int[NUM];
		int [] ys = new int[NUM];
		boolean [] cs = new boolean[NUM];
		
		genTestData(WIDTH, HEIGHT, xs, ys, cs);

		ExamplesGraph eg = new ExamplesGraph(WIDTH, HEIGHT, xs, ys, cs);
		Console.run(eg, WIDTH, HEIGHT);
//		eg.getGraphics().drawLine(x1,y1,x2,y2);
	}
}
