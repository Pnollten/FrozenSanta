package Ents;

import java.awt.Color;
import java.io.PrintWriter;
import java.util.ArrayList;

import se.lth.cs.window.SimpleWindow;

public class Teleport extends Entity {
	private boolean entry;
	private String opening;

	public Teleport(int x, int y, String entry) {
		super(x, y);
		this.opening = entry;
		if (entry.equals("ENTRY")) {
			this.entry = true;
		} else {
			this.entry = false;
		}
	}

	public void draw(SimpleWindow w) {
		w.setLineColor(new Color(0, 200, 0));
		for (int i = 0; i < 360; i++) {
			if (i % 30 == 0) {
				w.moveTo(x, y);
			}
			w.lineTo(x + (int) Math.round((radius) * Math.cos(Math.toRadians(i))),
					y + (int) Math.round((radius) * Math.sin(Math.toRadians(i))));
		}
	}

	public void erase(SimpleWindow w) {
		w.setLineColor(Color.WHITE);
		for (int i = 0; i < 360; i++) {
			if (i % 30 == 0) {
				w.moveTo(x, y);
			}
			w.lineTo(x + (int) Math.round((radius) * Math.cos(Math.toRadians(i))),
					y + (int) Math.round((radius) * Math.sin(Math.toRadians(i))));
		}
	}

	/** Flyttar tomten från den ena teleporten till dess par. */
	public void into(ArrayList<Entity> stuff, Entity prev, Entity s, SimpleWindow w) {
		int i;
		if (entry) {
			i = stuff.indexOf(this) + 1;
		} else {
			i = stuff.indexOf(this) - 1;
		}
		s.erase(w);
		s.moveTo(stuff.get(i).getX(), stuff.get(i).getY());
		draw(w);
		for (int j = 1; j <= 20; j++) {
			s.erase(w);
			s.moveTo(stuff.get(i).getX() + s.getVX() * j, stuff.get(i).getY() + s.getVY() * j);
			stuff.get(i).draw(w);
			s.draw(w);
			SimpleWindow.delay(delay);
		}
	}

	public void hit(ArrayList<Entity> stuff, Entity prev, Entity s, SimpleWindow w) {
	}

	public void write(PrintWriter p) {
		if (entry) {
			p.println("T " + x + " " + y + " ENTRY");
		} else {
			p.println("T " + x + " " + y + " EXIT");
		}
	}

	public boolean getEntry() {
		return entry;
	}

	@Override
	public Entity clone() {
		return new Teleport(x, y, opening);
	}

}
