package Ents;

import java.awt.Color;
import java.io.PrintWriter;

import se.lth.cs.window.SimpleWindow;

public class Santa extends Entity {
	private double[] sin;
	private double[] cos;

	public Santa(int x, int y) {
		super(x, y);
		sin = new double[360];
		cos = new double[360];
		for (int i = 0; i < 360; i++) {
			sin[i] = Math.sin(Math.toRadians(i));
			cos[i] = Math.cos(Math.toRadians(i));
		}
	}

	/** Ritar tomten. */
	public void draw(SimpleWindow w) {
		w.setLineColor(Color.RED);
		for (int i = 0; i < 360; i++) {
			w.moveTo(x + (int) (radius * Math.cos(Math.toRadians(i - 1))),
					y + (int) (radius * Math.sin(Math.toRadians(i - 1))));
			if (i % 30 == 0) {
				w.lineTo(x - (int) (radius * cos[i]), y - (int) (radius * sin[i]));
				w.moveTo(x + (int) (radius * cos[i]), y + (int) (radius * sin[i]));
				w.lineTo(x - (int) (radius * cos[i]), y + (int) (radius * sin[i]));
			}
		}
	}

	/** Suddar ut tomten. */
	public void erase(SimpleWindow w) {
		w.setLineColor(Color.WHITE);
		for (int i = 0; i < 360; i++) {
			w.moveTo(x + (int) (radius * Math.cos(Math.toRadians(i - 1))),
					y + (int) (radius * Math.sin(Math.toRadians(i - 1))));
			if (i % 30 == 0) {
				w.lineTo(x - (int) (radius * cos[i]), y - (int) (radius * sin[i]));
				w.moveTo(x + (int) (radius * cos[i]), y + (int) (radius * sin[i]));
				w.lineTo(x - (int) (radius * cos[i]), y + (int) (radius * sin[i]));
			}
		}
	}

	public Entity clone() {
		return null;
	}

	public void write(PrintWriter p) {

	}
}
