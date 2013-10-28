package applet;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import display.*;
import think.PerceptronLineAnimator;
import uk.co.ss496.util.test.*;

/**
 * Applet that displays the perceptron algorithm on a randomly
 * generated training set.
 * @author Sam Stokes
 */
public class PerceptronApplet extends JApplet{
	final static int WIDTH      = 600	// graph panel width
				   , HEIGHT     = 600	// graph panel height
				   , CPL_HEIGHT = 32	// extra height for control panel
				   , NUM        = 200	// number of points in training set
				   ;
	final static double ETA		= 1e-5;	// perceptron adjustment coefficient
	int [] xs = new int[NUM];			// \_ These define the points in the
	int [] ys = new int[NUM];			// /  training set
	boolean [] cs = new boolean[NUM];	// -- and their labels
	GraphWithAnimLine gwal;
	ExtendedLine l;
	JTextField txtEta;

	/**	Generates a random test training set. */
	protected static void genTestData(int w, int h, int[] xs, int[] ys, boolean[] cs) {
		if (xs.length != ys.length || ys.length != cs.length)
			throw new IllegalArgumentException("Arrays are of different lengths!");
		int n = xs.length;
		IntGenerator ig = new RandIntGenerator(w);
		int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
		while (x1 == x2 && y1 == y2) {	// generate two distinct points
			x1 = ig.next();				// to define the initial line
			y1 = ig.next();
			x2 = ig.next();
			y2 = ig.next();
		}
		ExtendedLine l = new ExtendedLine(x1, y1, x2, y2, w, h);
		for (int i=0; i<n; i++) {		// now generate random points
			xs[i] = ig.next();
			ys[i] = ig.next();
			cs[i] = l.above(xs[i], ys[i]);	// and label them
		}
	}
	
	/** Sets up the applet controls and initialises the graph. */
	public void init() {
		genTestData(WIDTH, HEIGHT, xs, ys, cs);
		// Line is diagonal initially
		l = new ExtendedLine(0, 0, WIDTH, HEIGHT, WIDTH, HEIGHT);
		Container cp = this.getContentPane();
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
		gwal = new GraphWithAnimLine(WIDTH, HEIGHT, xs, ys, cs, l,
				new PerceptronLineAnimator(l, xs, ys, cs, ETA));
		cp.add(gwal);
		final JPanel cpl = new JPanel();
		
		txtEta = new JTextField(Double.toString(ETA));
		
		JButton butRestart = new JButton("Restart");
		butRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				double eta;
				try {
					eta = Double.parseDouble(txtEta.getText());
					if (eta < 1e-8) throw new NumberFormatException();
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(cpl,
						"Invalid value of eta!");
					txtEta.selectAll();
					txtEta.grabFocus();
					return;
				}
				l = new ExtendedLine(0, 0, WIDTH, HEIGHT, WIDTH, HEIGHT);
				gwal.init(xs, ys, cs, l,
						new PerceptronLineAnimator(l, xs, ys, cs, eta));
				gwal.repaint();
			}
		});
		cpl.add(butRestart);
		
		JLabel lblEta = new JLabel("\u03b7:");
		lblEta.setFont(new Font(null, Font.ITALIC, 12));
		cpl.add(lblEta);
		
		cpl.add(txtEta);
		
		JButton butRegen = new JButton("Regenerate training set");
		butRegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				double eta;
				try {
					eta = Double.parseDouble(txtEta.getText());
					if (eta < 1e-8) throw new NumberFormatException();
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(cpl,
						"Invalid value of eta!");
					txtEta.selectAll();
					txtEta.grabFocus();
					return;
				}
				genTestData(WIDTH, HEIGHT, xs, ys, cs);
				l = new ExtendedLine(0, 0, WIDTH, HEIGHT, WIDTH, HEIGHT);
				gwal.init(xs, ys, cs, l,
						new PerceptronLineAnimator(l, xs, ys, cs, eta));
				gwal.repaint();
			}			
		});
		cpl.add(butRegen);
		
		cpl.setPreferredSize(new Dimension(WIDTH, CPL_HEIGHT));
		cp.add(cpl);
	}

	/** Allows this applet to be run as a standalone application as well. */
	public static void main(String[] args) {
		uk.co.ss496.util.Console.run(new PerceptronApplet(), WIDTH, HEIGHT + CPL_HEIGHT);
	}
}
