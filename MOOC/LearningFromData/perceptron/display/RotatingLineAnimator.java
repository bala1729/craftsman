package display;

/**
 * Proof-of-concept implementation of a line animator: just
 * draws a rotating line centred on the centre of the viewport.
 * @author Sam Stokes
 */
public class RotatingLineAnimator extends LineAnimator {
	double theta;
	int centX, centY, r, x, y;
	public RotatingLineAnimator(ExtendedLine l, int w, int h) {
		super(l);
		centX = w / 2; centY = h / 2;
		r = (w > h ? h : w) / 4;
	}

	public void anim() {
		x = centX + (int)((double)r * Math.cos(theta));
		y = centY + (int)((double)r * Math.sin(theta));
		l.adjust(centX, centY, x, y);
		theta = (theta + Math.PI / 50) % Math.PI;	// no overflow please
	}
}
