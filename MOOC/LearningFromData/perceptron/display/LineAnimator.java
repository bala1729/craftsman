package display;

/**
 * Animator specifying how to animate a given line.
 * @author Sam Stokes
 */
public abstract class LineAnimator {
	/** Line to animate */
	protected ExtendedLine l;
	/** Create a new animator for line l. */
	public LineAnimator(ExtendedLine l) {
		this.l = l;
	}

	/**
	 * Specifies how to animate the line.  It is expected this will be called
	 * repeatedly, e.g. by a timer.
	 */
	public abstract void anim();
}
