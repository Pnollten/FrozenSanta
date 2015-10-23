package Ents;
import java.awt.Color;
import java.io.PrintWriter;
import java.util.ArrayList;

import se.lth.cs.window.SimpleWindow;

public class FakeSquare extends Entity {

	public FakeSquare(int x, int y) {
		super(x, y);
	}

	public void draw(SimpleWindow w) {
		w.setLineColor(new Color(0, 0, 100));
		w.moveTo(x - radius, y - radius);
		w.lineTo(x + radius, y - radius);
		w.lineTo(x + radius, y + radius);
		w.lineTo(x - radius, y + radius);
		w.lineTo(x - radius, y - radius);
	}

	public void erase(SimpleWindow w) {
		w.setLineColor(Color.WHITE);
		w.moveTo(x - radius, y - radius);
		w.lineTo(x + radius, y - radius);
		w.lineTo(x + radius, y + radius);
		w.lineTo(x - radius, y + radius);
		w.lineTo(x - radius, y - radius);
	}

	public void hit(ArrayList<Entity> stuff, Entity prev, Entity s, SimpleWindow w) {
		erase(w);
		moveTo(-10, -10);
	}
	
	public void write(PrintWriter p){
		p.println("F " + x + " " + y);
	}

	@Override
	public Entity clone() {
		return new FakeSquare(x, y);
	}
}
