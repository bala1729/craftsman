package think;

import display.ExtendedLine;
import display.LineAnimator;

/**
 * Animator following the perceptron 'learning algorithm':
 * 
 * @author Sam Stokes
 */
public class PerceptronLineAnimator extends LineAnimator {
	protected int [] xs;
	protected int [] ys;
	protected boolean [] cs;
	private double wx, wy, d;	// current line parameters - w.x + d = 0
	private long r;				// max x*x + y*y
	private double eta = 1e-5;	// default value of adjustment param
	int c;						// 1 or -1 for opposite sides of line
	boolean adjustedThisTime;	// was any adjustment made on this iteration?
	boolean done = false;		// has the solution been found?
	
	/**
	 * Create a new perceptron animator using the default value of
	 * eta (=1e-5).
	 * @param l  Line to animate.  The initial value of this line is
	 * 			 also used as the perceptron's initial guess.
	 * @param xs x-values of the points in the training set.
	 * @param ys y-values of the points in the training set.
	 * @param cs labels of the points in the training set.
	 */
	public PerceptronLineAnimator(ExtendedLine l,
			int [] xs, int [] ys, boolean [] cs) {
		super(l);
		if (xs.length != ys.length || ys.length != cs.length)
			throw new IllegalArgumentException("Arrays are of different lengths!");
		this.xs = xs;
		this.ys = ys;
		this.cs = cs;
//		wx = wy = d = 0;			// this looks silly and is silly:
		wx = l.getwx();				// use the line's initial configuration
		wy = l.getwy();				// as the initial guess instead.
		d = l.getd();
		r = 0;
		long temp;
		for (int i=0; i < xs.length; i++) {
			temp = xs[i] * xs[i] + ys[i] * ys[i];
			r = temp > r ? temp : r;
		}							// set r to the maximum x*x+y*y.
	}
	
	/**
	 * Create a new perceptron animator with a specified value of eta.
	 * @param l   Line to animate.  The initial value of this line is
	 * 			  also used as the perceptron's initial guess.
	 * @param xs  x-values of the points in the training set.
	 * @param ys  y-values of the points in the training set.
	 * @param cs  labels of the points in the training set.
	 * @param eta adjustment parameter (must be >= 1e-8).
	 */
	public PerceptronLineAnimator(ExtendedLine l,
			int [] xs, int [] ys, boolean [] cs, double eta) {
		this(l, xs, ys, cs);
		if (eta < 1e-8)		// don't want negative values, zero or underflow
			throw new IllegalArgumentException("Value of eta too small or negative.");
		this.eta = eta;
	}

	public void anim() {
		if (done) return;	// don't waste CPU time if you've finished already
		adjustedThisTime = false;
		for (int i=0; i<xs.length; i++) {
			c = (cs[i] ? 1 : -1);
			if (c * (wx*xs[i] + wy*ys[i] + d) <= 0) {	// if there's a point
				wx += eta * (c * xs[i]);				// on wrong side of line
				wy += eta * (c * ys[i]);				// adjust line a bit
				d  += eta * (c * r);
				adjustedThisTime = true;
			}
		}
		if (adjustedThisTime)
			l.adjust(wx, wy, d);
		else
			done = true;
	}
}