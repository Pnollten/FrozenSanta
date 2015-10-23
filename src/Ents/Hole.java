package Ents;
import java.awt.Color;
import java.io.PrintWriter;
import java.util.ArrayList;

import se.lth.cs.window.SimpleWindow;

public class Hole extends Entity {

	public Hole(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(SimpleWindow w) {
		int temp = radius/3;
		int temp1 = radius;
		w.setLineWidth(radius/9);
		w.setLineColor(Color.BLACK);
		for (int j = 0; j < 4; j++) {
			w.moveTo(x + radius, y);
			for (int i = 0; i < 360; i++) {
				w.lineTo(x + (int) Math.round(radius * Math.cos(Math.toRadians(i))),
						y + (int) Math.round(radius * Math.sin(Math.toRadians(i))));
			}
			radius -= temp;
		}
		radius = temp1;
		w.setLineWidth(1);
	}

	@Override
	public void erase(SimpleWindow w) {
		int temp = radius/3;
		w.setLineWidth(radius/9);
		w.setLineColor(Color.WHITE);
		for (int j = 0; j < 4; j++) {
			w.moveTo(x + radius, y);
			for (int i = 0; i < 360; i++) {
				w.lineTo(x + (int) Math.round(radius * Math.cos(Math.toRadians(i))),
						y + (int) Math.round(radius * Math.sin(Math.toRadians(i))));
			}
			radius -= temp;
		}
		radius = 9;
		w.setLineWidth(1);
	}

	public void into(ArrayList<Entity> stuff, Entity prev, Entity s, SimpleWindow w) {
		s.erase(w);
		s.stop();
		s.moveTo(30, 570);
		draw(w);
	}
	public void hit(ArrayList<Entity> stuff, Entity prev, Entity s, SimpleWindow w){
		
	}

	public void write(PrintWriter p) {
		p.println("H " + x + " " + y);
	}

	@Override
	public Entity clone() {
		return new Hole(x, y);
	}

}
