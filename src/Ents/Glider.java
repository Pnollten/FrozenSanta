package Ents;

import java.awt.Color;
import java.io.PrintWriter;
import java.util.ArrayList;

import se.lth.cs.window.SimpleWindow;

public class Glider extends Entity {

	public Glider(int x, int y) {
		super(x, y);
	}

	public void draw(SimpleWindow w) {
		w.setLineColor(Color.RED);
		w.moveTo(x - radius, y - radius);
		w.lineTo(x + radius, y - radius);
		w.lineTo(x + radius, y + radius);
		w.lineTo(x - radius, y + radius);
		w.lineTo(x - radius, y - radius);
		w.lineTo(x + radius, y + radius);
		w.moveTo(x - radius, y + radius);
		w.lineTo(x + radius, y - radius);
		w.moveTo(x, y - radius);
		w.lineTo(x + radius, y);
		w.lineTo(x, y + radius);
		w.lineTo(x - radius, y);
		w.lineTo(x, y - radius);
	}

	public void erase(SimpleWindow w) {
		w.setLineColor(Color.WHITE);
		w.moveTo(x - radius, y - radius);
		w.lineTo(x + radius, y - radius);
		w.lineTo(x + radius, y + radius);
		w.lineTo(x - radius, y + radius);
		w.lineTo(x - radius, y - radius);
		w.lineTo(x + radius, y + radius);
		w.moveTo(x - radius, y + radius);
		w.lineTo(x + radius, y - radius);
		w.moveTo(x, y - radius);
		w.lineTo(x + radius, y);
		w.lineTo(x, y + radius);
		w.lineTo(x - radius, y);
		w.lineTo(x, y - radius);
	}

	public void hit(ArrayList<Entity> stuff, Entity prev, Entity s, SimpleWindow w) {
		String direction = s.getDirection();
		s.erase(w);
		s.stop();
		s.draw(w);
		move(direction, stuff, w);
	}

	public void into(ArrayList<Entity> stuff, Entity prev, Entity s, SimpleWindow w) {
		String direction = s.getDirection();
		erase(w);
		for (int i = 1; i < 20; i++) {
			moveTo(s.getX() + s.getVX() * i, s.getY() + s.getVY() * i);
			SimpleWindow.delay(delay);
		}
		draw(w);
		SimpleWindow.delay(delay);
		s.setVX(0);
		s.setVY(0);
		s.stop();
		s.draw(w);
		move(direction, stuff, w);
	}

	public Entity clone() {
		return new Glider(x, y);
	}

	public void write(PrintWriter p) {
		p.println("G " + x + " " + y);
	}

}
