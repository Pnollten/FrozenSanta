package Ents;

import java.awt.Color;
import java.io.PrintWriter;
import java.util.ArrayList;

import se.lth.cs.window.SimpleWindow;

public class Pipe extends Entity {
	private boolean hitX;

	public Pipe(int x, int y, boolean hitX) {
		super(x, y);
		this.hitX = hitX;
	}

	@Override
	public void draw(SimpleWindow w) {
		w.setLineColor(Color.BLUE);
		w.moveTo(x - radius, y - radius);
		if (hitX) {
			w.lineTo(x - radius, y + radius);
			w.moveTo(x + radius, y - radius);
			w.lineTo(x + radius, y + radius);
		} else {
			w.lineTo(x + radius, y - radius);
			w.moveTo(x - radius, y + radius);
			w.lineTo(x + radius, y + radius);
		}
	}

	@Override
	public void erase(SimpleWindow w) {
		w.setLineColor(Color.WHITE);
		w.moveTo(x - radius, y - radius);
		if (hitX) {
			w.lineTo(x - radius, y + radius);
			w.moveTo(x + radius, y - radius);
			w.lineTo(x + radius, y + radius);
		} else {
			w.lineTo(x + radius, y - radius);
			w.moveTo(x - radius, y + radius);
			w.lineTo(x + radius, y + radius);
		}
	}

	public void hit(ArrayList<Entity> stuff, Entity prev, Entity s, SimpleWindow w) {
		if ((hitX && s.getVX() != 0) || (!hitX && s.getVY() != 0)) {
			s.stop();
			draw(w);
		}
	}
	
	public void into(ArrayList<Entity> stuff, Entity prev, Entity s, SimpleWindow w) {
		for (int j = 1; j <= 20; j++) {
			s.erase(w);
			s.moveTo(x + s.getVX() * j, y + s.getVY() * j);
			draw(w);
			s.draw(w);
			SimpleWindow.delay(delay);
		}
	}

	@Override
	public void write(PrintWriter p) {
		p.println("P " + x + " " + y + " " + hitX);
	}

	@Override
	public Entity clone() {
		return new Pipe(x, y, hitX);
	}

}
