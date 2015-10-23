package Ents;
import java.awt.Color;
import java.io.PrintWriter;

import se.lth.cs.window.SimpleWindow;

public class Button extends Entity {
	private int num;

	public Button(int x, int y, int radius, int num) {
		super(x, y);
		this.radius = radius;
		this.num = num;
	}

	public void draw(SimpleWindow w) {
		w.setLineColor(Color.BLUE);
		w.moveTo(x, y);
		w.lineTo(x + radius, y);
		w.lineTo(x + radius, y + radius);
		w.lineTo(x, y + radius);
		w.lineTo(x, y);
		if (num < 10) {
			w.moveTo(x + radius / 2 - 2, y + radius / 2 + 3);
		} else if (num < 100) {
			w.moveTo(x + radius / 2 - 6, y + radius / 2 + 3);
		} else {
			w.moveTo(x + radius / 2 - 10, y + radius / 2 + 3);
		}
		w.writeText("" + num);
	}
	
	public void erase(SimpleWindow w) {
		w.setLineColor(Color.WHITE);
		w.moveTo(x, y);
		w.lineTo(x + radius, y);
		w.lineTo(x + radius, y + radius);
		w.lineTo(x, y + radius);
		w.lineTo(x, y);
		if (num < 10) {
			w.moveTo(x + radius / 2 - 2, y + radius / 2 + 3);
		} else if (num < 100) {
			w.moveTo(x + radius / 2 - 6, y + radius / 2 + 3);
		} else {
			w.moveTo(x + radius / 2 - 10, y + radius / 2 + 3);
		}
		w.writeText("" + num);
	}

	public int clicked() {
		return num;
	}

	public Entity clone() {
		return null;
	}

	public void write(PrintWriter p) {
	}
}
