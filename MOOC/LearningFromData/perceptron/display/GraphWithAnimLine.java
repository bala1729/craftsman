package display;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import think.PerceptronLineAnimator;
import uk.co.ss496.util.Console;

/**
 * As ExamplesGraph but displays a line which can be animated.
 * @author Sam Stokes
 */
public class GraphWithAnimLine extends ExamplesGraph {
	ExtendedLine line;
	private LineAnimator anim;
	private Timer t;
	
	/**
	 * Create a new graph with an animated line which rotates
	 * around the centre of the graph area.
	 * @param w  width in pixels of the graph area.
	 * @param h  height in pixels of the graph area.
	 * @param xs x-coords of the points to plot.
	 * @param ys y-coords of the points to plot.
	 * @param cs labels of the points to plot.
	 * @param l  line to draw.
	 */
	public GraphWithAnimLine(int w, int h, int[] xs, int[] ys, boolean[] cs, ExtendedLine l) {
		this(w, h, xs, ys, cs, l, new RotatingLineAnimator(l, w, h));
	}
	
	/**
	 * Create a new graph with animated line.
	 * @param w  width in pixels of the graph area.
	 * @param h  height in pixels of the graph area.
	 * @param xs x-coords of the points to plot.
	 * @param ys y-coords of the points to plot.
	 * @param cs labels of the points to plot.
	 * @param l  line to draw.
	 * @param a  animator specifying how the line should be animated.
	 */
	public GraphWithAnimLine(int w, int h, int[] xs, int[] ys, boolean[] cs,
			ExtendedLine l, LineAnimator a) {
		super(w, h, xs, ys, cs);
		init(xs, ys, cs, l, a);
	}
	
	/**
	 * Initialise the graph and begin the animation after 5 seconds.<br/>
	 * If the graph has already been initialised, this will reset it,
	 * discarding the previous data and line and halting the animator.
	 * @param xs x-coords of the points to plot.
	 * @param ys y-coords of the points to plot.
	 * @param cs labels of the points to plot.
	 * @param l  line to draw.
	 * @param a  animator specifying how the line should be animated.
	 */
	public void init(int[] xs, int[] ys, boolean[] cs,
			ExtendedLine l, LineAnimator a) {
		super.init(xs, ys, cs);
		line = l;
		anim = a;
		if (t != null) t.cancel();	// stop the timer if it's already running
		t = new Timer(true);	// daemon
		t.schedule(new TimerTask() {
			public void run() {
				anim.anim();
				repaint();
			}
		} , 5000, 16);			// start in 5 seconds, run at ~60fps
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, width, height);	// avoid line overdraw
		super.paint(g);						// draw the points
		g.setColor(java.awt.Color.BLACK);
		line.draw(g);
	}
	
	/** Test code: tests this with a perceptron on some random data. */
	public static void main(String [] args) {
		int NUM = 300, WIDTH = 400, HEIGHT = 400;
		int [] xs = new int[NUM];
		int [] ys = new int[NUM];
		boolean [] cs = new boolean[NUM];
		
		genTestData(WIDTH, HEIGHT, xs, ys, cs);

//		ExamplesGraph eg = new GraphWithAnimLine(WIDTH, HEIGHT, xs, ys, cs,
//			new ExtendedLine(0, 0, WIDTH, HEIGHT, WIDTH, HEIGHT));
		ExtendedLine l = new ExtendedLine(0, 0, WIDTH, HEIGHT, WIDTH, HEIGHT);
		ExamplesGraph eg = new GraphWithAnimLine(WIDTH, HEIGHT, xs, ys, cs,
			l, new PerceptronLineAnimator(l, xs, ys, cs));
		Console.run(eg, WIDTH, HEIGHT);
	}
}
