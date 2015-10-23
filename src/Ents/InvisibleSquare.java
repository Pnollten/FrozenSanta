package Ents;
import java.awt.Color;
import java.io.PrintWriter;
import java.util.ArrayList;

import se.lth.cs.window.SimpleWindow;

public class InvisibleSquare extends Entity {
	private boolean vis;

	public InvisibleSquare(int x, int y) {
		super(x, y);
		vis = false;
	}

	public void draw(SimpleWindow w) {
		if (vis) {
			w.setLineColor(Color.BLUE);
		} else {
			w.setLineColor(new Color(0, 0, 255, 15));
		}
		w.moveTo(x - radius, y - radius);
		w.lineTo(x + radius, y - radius);
		w.lineTo(x + radius, y + radius);
		w.lineTo(x - radius, y + radius);
		w.lineTo(x - radius, y - radius);
	}
	
	public void erase(SimpleWindow w){
		w.setLineColor(Color.WHITE);
		w.moveTo(x - radius, y - radius);
		w.lineTo(x + radius, y - radius);
		w.lineTo(x + radius, y + radius);
		w.lineTo(x - radius, y + radius);
		w.lineTo(x - radius, y - radius);
	}

	public void hit(ArrayList<Entity> stuff, Entity prev, Entity s, SimpleWindow w) {
		s.stop();
		vis = true;
		draw(w);
	}
	
	public void write(PrintWriter p){
		p.println("I " + x + " " + y);
	}

	@Override
	public Entity clone() {
		return new InvisibleSquare(x, y);
	}
}
