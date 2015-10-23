package Ents;

import java.awt.Color;
import java.io.PrintWriter;
import java.util.ArrayList;

import se.lth.cs.window.SimpleWindow;

public class Mover extends Entity {

	public Mover(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(SimpleWindow w) {
		w.setLineColor(new Color(0, 150, 0));
		w.moveTo(x - radius, y - radius);
		w.lineTo(x + radius, y - radius);
		w.lineTo(x + radius, y + radius);
		w.lineTo(x - radius, y + radius);
		w.lineTo(x - radius, y - radius);
		w.lineTo(x + radius, y + radius);
		w.moveTo(x - radius, y + radius);
		w.lineTo(x + radius, y - radius);
	}

	@Override
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
	}

	public void hit(ArrayList<Entity> stuff, Entity prev, Entity s, SimpleWindow w) {
		String direction = s.getDirection();
		s.erase(w);
		s.stop();
		s.draw(w);
		move(direction, stuff, w);
	}

	public void move(String direction, ArrayList<Entity> stuff, SimpleWindow w) {
		switch (direction) {
		case "up":
			vy = -1;
			break;
		case "down":
			vy = 1;
			break;
		case "right":
			vx = 1;
			break;
		case "left":
			vx = -1;
			break;
		}

		for (int j = 0; j < 20; j++) {
			erase(w);
			moveTo(x + vx, y + vy);
			draw(w);
			SimpleWindow.delay(3);
			for (int i = 0; i < stuff.size(); i++) {
				Entity e = stuff.get(i);
				if ((x + vx * radius == e.getX() - vx * radius && y == e.getY() 
						|| y + vy * radius == e.getY() - vy * radius && x == e.getX()) && e != this) {
					j = 20;
					erase(w);
					if (e instanceof Mover) {
						e.hit(stuff, prev, this, w);
					} else{
						stop();
						draw(w);
						e.draw(w);
					}
					prev = e;
				}
			}
		}
		vx = 0;
		vy = 0;
	}

	public void into(ArrayList<Entity> stuff, Entity prev, Entity s, SimpleWindow w) {
		
	}

	@Override
	public Entity clone() {
		return new Mover(x, y);
	}

	public void write(PrintWriter p) {
		p.println("M " + x + " " + y);
	}

}
